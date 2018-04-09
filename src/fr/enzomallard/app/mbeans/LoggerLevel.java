package fr.enzomallard.app.mbeans;

import fr.enzomallard.app.mbeans.interfaces.LoggerLevelMBean;
import fr.enzomallard.app.servlets.Login;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Cannot change the level dynamicaly (see Log4J2 change 7 : http://logging.apache.org/log4j/2.x/manual/migration.html)
 * So we try to get all servlet and manually use the configurator.
 */
public class LoggerLevel implements LoggerLevelMBean {
    private Level currentLevel = LogManager.getRootLogger().getLevel();

    @Override
    public void setLevelError() {
        currentLevel = Level.ERROR;
        Configurator.setLevel(getForLevel(Level.ERROR));
    }
    @Override
    public void setLevelInfo() {
        currentLevel = Level.INFO;
        Configurator.setLevel(getForLevel(Level.INFO));

    }

    @Override
    public void setLevelDebug() {
        currentLevel = Level.DEBUG;
        Configurator.setLevel(getForLevel(Level.DEBUG));
    }

    @Override
    public String getCurrentLevel() {
        return currentLevel.name();
    }

    private List<Class> getServlets(){
        try {
            return Arrays.asList(getClasses("fr.enzomallard.app.servlets"));
        } catch (ClassNotFoundException | IOException e) {
            return new ArrayList<>();
        }
    }

    private Map<String,Level> getForLevel(Level error) {
        return getServlets()
                .stream()
                .map(Class::getName)
                .map((s)->new SimpleEntry<>(s, error))
                .collect(Collectors.toMap(
                        SimpleEntry::getKey,
                        SimpleEntry::getValue,
                        (a, b) -> b, HashMap::new)
                );
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}

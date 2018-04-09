package fr.enzomallard.app.listeners;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.mbeans.DatabaseCounter;
import fr.enzomallard.app.mbeans.LoggerLevel;
import fr.enzomallard.app.mbeans.interfaces.DatabaseCounterMBean;
import fr.enzomallard.app.mbeans.interfaces.LoggerLevelMBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.management.ManagementFactory;

@WebListener()
public class JMXStarter implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(JMXStarter.class);

    // Public constructor is required by servlet spec
    public JMXStarter() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            System.out.println("Starting JMXs ...");

            ObjectName dbBeanName = new ObjectName("fr.enzomallard.app:type=DatabaseCounterMBean");
            ObjectName loggerLevelBeanName = new ObjectName("fr.enzomallard.app:type=LoggerLevelMBean");

            mbs.registerMBean(new StandardMBean(new DatabaseCounter(), DatabaseCounterMBean.class), dbBeanName);
            mbs.registerMBean(new StandardMBean(new LoggerLevel(), LoggerLevelMBean.class), loggerLevelBeanName);
        } catch (MalformedObjectNameException | NullPointerException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }
}

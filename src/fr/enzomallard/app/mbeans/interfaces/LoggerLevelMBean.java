package fr.enzomallard.app.mbeans.interfaces;

/**
 * Created by Enzo Mallard
 * The 09/04/2018
 */
public interface LoggerLevelMBean {
    void setLevelError();

    void setLevelInfo();

    void setLevelDebug();

    String getCurrentLevel();
}

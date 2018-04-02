package fr.enzomallard.app.listeners;

import fr.enzomallard.app.DaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class StartupListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(StartupListener.class);

    // Public constructor is required by servlet spec
    public StartupListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

        System.out.println("Starting app... ");
        logger.error("Announces loaded : " + DaoFactory.getInstance().getSaleDao().size());
        logger.error("Registered users : " + DaoFactory.getInstance().getUserDao().size());
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }
}

package fr.enzomallard.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private static final Logger logger = LogManager.getLogger(DaoFactory.class);

    private static String DB_URL = "jdbc:hsqldb:mem:0";
    private static String DB_DRIVER = "";

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if(instance == null) instance = new DaoFactory();
        return instance;
    }

    public Connection getConnection(){
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("Couldn't find the SQL driver in the classpath", e);
        }

        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            logger.error("Couldn't connect to the Database", e);
            throw new Error();
        }
    }
}

package fr.enzomallard.app;

import fr.enzomallard.app.dao.ISaleDao;
import fr.enzomallard.app.dao.IUserDao;
import fr.enzomallard.app.dao.SqlSaleDao;
import fr.enzomallard.app.dao.SqlUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private static final Logger logger = LogManager.getLogger(DaoFactory.class);

    private static final String DB_URL = "jdbc:hsqldb:hsql://localhost:9003/";
    private static final String DB_DRIVER = "org.hsqldb.jdbcDriver";

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) instance = new DaoFactory();
        return instance;
    }

    public Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("Couldn't find the SQL driver in the classpath", e);
        }

        try {
            return DriverManager.getConnection(DB_URL, "SA", "");
        } catch (SQLException e) {
            logger.error("Couldn't connect to the Database", e);
            throw new Error();
        }
    }

    public IUserDao getUserDao() {
        return new SqlUserDao(this);
    }

    public ISaleDao getSaleDao() {
        return new SqlSaleDao(this);
    }
}

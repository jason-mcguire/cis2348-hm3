package main;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The DbHelper class is a singleton that encapsulates the code to initialize
 * the database and manage database connections.
 */
public class DbHelper {

    private BasicDataSource ds;

    // This is the one and only instance of the DBHelper for
    // the application
    private static DbHelper INSTANCE = new DbHelper();

    // In order to create a singleton, we hide the constructor

    /**
     * This is a private constructor. The purpose is to make this class a singleton.
     */
    private DbHelper(){


    }

    /**
     * Get the application instance of the DbHelper
     * @return a DbHelper instance
     */
    public static DbHelper getInstance() {
        return INSTANCE;
    }

    /**
     * Return a connection object
     * @return Connection instance
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * Initialize the DBHelper. This should be called
     * once when the application starts
     * @throws SQLException
     */
    public void init() throws SQLException {

//        // run Flyway to update the db if needed
//        Flyway flyway = new Flyway();
//        flyway.setDataSource("jdbc:sqlite:hm3.db","","");
//        flyway.migrate();
//
        // initialize datasource
        ds = new BasicDataSource();
        ds.setUrl("jdbc:sqlite:hm3.db");
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.getConnection();

    }

    /**
     * Close the datasource. This should be called once
     * when the application exits.
     * @throws SQLException
     */
    public void close() throws SQLException {
        if (ds != null) {
            ds.close();
        }
    }
}
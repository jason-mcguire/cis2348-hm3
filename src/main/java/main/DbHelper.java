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
     * @return
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

        // initialize database
        ds = new BasicDataSource();
        ds.setUrl("jdbc:h2:./target/db;AUTO_SERVER=TRUE");
        ds.setDriverClassName("org.h2.Driver");
        ds.setUsername("sa");
        ds.setPassword("1234");
        ds.getConnection();

        // run Flyway to update the db if needed
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.migrate();
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
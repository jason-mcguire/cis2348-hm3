package main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;


public class DbHelperTest {

    private DbHelper helper;

    @Before
    public void initHelper() throws SQLException {
        helper = DbHelper.getInstance();
        helper.init();
    }

    @After
    public void closeHelper() throws SQLException {
        helper.close();
    }

    @Test
    public void connectionTest(){
        try {
            Connection conn = helper.getConnection();
            assertNotNull("could not get database connection",conn);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

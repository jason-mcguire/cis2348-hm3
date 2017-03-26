package main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class ContactTest {

    private Contact contact;
    private DbHelper helper;
    @Before
    public void init() throws SQLException {
        helper = DbHelper.getInstance();
        helper.init();
    }

    @After
    public void closeHelper() throws SQLException {
        if(contact != null) {
            contact.delete();
        }
        helper.close();
    }

    @Test
    public void saveDataTest(){
        try {
            contact = new Contact("John","1123","999-999-9999");
            contact.save();
            Contact contactLoad = Contact.load(contact.getID());
            assertEquals(contact,contactLoad);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

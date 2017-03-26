package main;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        DbHelper dbHelper = DbHelper.getInstance();
        dbHelper.init();

        // start your work here
        // open and read the file contacts.txt
        // for each line in the file, use the String split function to extract the name, address, and phone number
        //    create a new contact and set the fields that you just extracted
        //    call the save method on the contact to save it to the database
        // retrieve a list of all the contacts and print them out to the console

        dbHelper.close();
    }
}

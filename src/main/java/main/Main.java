package main;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DbHelper dbHelper = DbHelper.getInstance();
        dbHelper.init();
        processDataFile("contacts.txt");



        dbHelper.close();
    }

    private static void processDataFile(String fileName) throws FileNotFoundException, SQLException {
        // start your work here
        // open and read the file contacts.txt
        Scanner scn = new Scanner(new File(fileName));
        while(scn.hasNextLine()){
            String[] data = scn.nextLine().trim().split(",");
            String name = data[0];
            String address = data[1];
            String phone = data[2];
            Contact contact = new Contact(name,address,phone);
            contact.save();

        }
    }
}

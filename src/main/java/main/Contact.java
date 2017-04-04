package main;

import java.sql.*;

public class Contact {


    /**
     * Setting variables to respected name
     */
    private int ID;
    private String name;
    private String address;
    private String phoneNumber;

    /**
     * "Contact" method consists of name, address, and phone
     * @param name
     * @param address
     * @param phone
     */
    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phone;
    }

    /**
     * Returns name string
     * @return name
     */
    public String getName() {return name; }


    /**
     *Method name "setName" is equal to "name" in "Contact" method
     * @param name
     */
    public void setName(String name) {this.name = name; }

    /**
     * Returns address string
     * @return address
     */
    public String getAddress() {return address; }

    /**
     * Method name "setAddress" is equal to "address" in "Contact" method
     * @param address
     */
        public void setAddress(String address) {this.address = address; }


    /**
     * Returns phoneNumber string
     * @return phoneNumber
     */
    public String getPhoneNumber() {return phoneNumber; }

    /**
     * Method name "setPhoneNumber" is equal to "phoneNumber" in "Contact" method
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Declare setID
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }


    /**
     * Multiple loops that return false if specific condition is met
     * @param o
     * @return false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (ID != contact.ID) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (address != null ? !address.equals(contact.address) : contact.address != null) return false;
        return phoneNumber != null ? phoneNumber.equals(contact.phoneNumber) : contact.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }


    /**
     * Saves contact to add to database
     * @throws SQLException
     */
    public void save() throws SQLException {
        Connection connection = DbHelper.getInstance().getConnection();

        String insertSQL = "insert into contact(name,address,phone) values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(insertSQL);
        statement.setString(1, this.name);
        statement.setString(2, this.address);
        statement.setString(3, this.phoneNumber);
        statement.execute();
        this.ID = statement.getGeneratedKeys().getInt(1);
        statement.close();
        connection.close();

    }

    /**
     *
     * @return String.format
     */
    @Override
    public String toString() {
        return String.format("ID: %s Name: %s Address %s Phone %s",ID,name,address,phoneNumber);
    }


    /**
     * Returns ID
     * @return
     */
    public int getID() {
        return ID;
    }


    /**
     * Creates connection to database
     * @param id
     * @return
     * @throws SQLException
     */
    public static Contact load(int id) throws SQLException {
        Connection connection = DbHelper.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("select * from contact where ID = %d",id);
        ResultSet rs = statement.executeQuery(query);
        Contact contact = null;
        if(rs.next()) {
            contact = new Contact(rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"));
            contact.setID(id);

        } else {
            System.out.println("Query returned empty!");
        }
        connection.close();
        return contact;

    }

    /**
     * Deletes contact from database
     * @throws SQLException
     */
    public void delete() throws SQLException {
        Connection connection = DbHelper.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("delete from contact where ID = %d",this.ID);
        statement.execute(query);
        statement.close();
        connection.close();

    }
}

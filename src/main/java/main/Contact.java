package main;

import java.sql.*;

public class Contact {

    private int ID;

    private String name;
    private String address;
    private String phoneNumber;
    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    @Override
    public String toString() {
        return String.format("ID: %s Name: %s Address %s Phone %s",ID,name,address,phoneNumber);
    }

    public int getID() {
        return ID;
    }

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

    public void delete() throws SQLException {
        Connection connection = DbHelper.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("delete from contact where ID = %d",this.ID);
        statement.execute(query);
        statement.close();
        connection.close();

    }
}

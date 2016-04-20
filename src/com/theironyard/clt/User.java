package com.theironyard.clt;

import sun.plugin2.message.Message;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by mac on 4/18/16.
 */
public class User {
    private final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS messages (id IDENTITY, userName VARCHAR, message VARCHAR)";
    private final String INSERT_SQL = "INSERT INTO messages VALUES (null, ?, ?)";
    private final String UPDATE_SQL = "UPDATE messages SET message = ? Where userName = ?";
    private final String DELETE_SQL = "DELETE FROM messages WHERE id = ?";
    private final String SELECT_SQL = "SELECT * FROM messages where userName = ?";
    private final String CONNECTION_STRING = "jdbc:h2:./main";

    public String userName;
    public String password;
    public String name;
    public String messages;
    ArrayList<String> messenger;

    User (String name) {
        this.name = name;
        password = "password";
        userName = "name";
    }


    public void addMessage(String text) throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        PreparedStatement statement = conn.prepareStatement(INSERT_SQL);
        statement.setString(1, userName);
        statement.setString(2, text);

        statement.execute();
    }

    public ArrayList<Message> getMessages() throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        PreparedStatement statement = conn.prepareStatement(SELECT_SQL);
        statement.setString(1, userName);

        ResultSet queryResults = statement.executeQuery();

        ArrayList<Message> results = new ArrayList<>();

        // while loop that goes over all the results rom queryResults
        // for each result, make a new Message object
        // set the fields of the message object to the values of the columns in the result
        // add the mesage to the results arraylist.

        return results;


    }


//    public static void main(String[] args) throws SQLException {
//    Connection conn = DriverManager.getConnection("jdbc:h2:./main");
//
//    Statement stmt = conn.createStatement();
//
//    stmt.execute("CREATE TABLE IF NOT EXISTS messages (id IDENTITY, userName VARCHAR, messages VARCHAR)");
//    stmt.execute("INSERT INTO messages VALUES (null, ?, ?)");
//    stmt.execute("UPDATE messages SET message = ? Where userName = ?");
//    stmt.execute("DELETE FROM messages WHERE userName = ? ");
//
//    PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO messages VALUES (NULL, ?, ?)");
//    stmt2.setString(1, userName);
//    stmt2.setString(2, message);
//    stmt2.execute();




    ResultSet results = stmt.executeQuery("SELECT * FROM messages where userName = ?");
     while (results.next())

    {
        String messages = results.getString("messages");
        String userId = results.getString("userId");
        String userName = results.getString("userName");

        System.out.printf("%s %s %s\n", userId, userName, messages);

    }

    }

}





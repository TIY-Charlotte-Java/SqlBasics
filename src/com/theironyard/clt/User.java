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
    public String messageId;
    public int id;
    ArrayList<String> messages;

    User (String name, int id) {
        this.name = name;
        this.id = id;
        password = "password";
        userName = "name";
        messageId = "id";


    }


    public void addMessage(String text) throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        PreparedStatement statement = conn.prepareStatement(INSERT_SQL);
        statement.setString(1, userName);
        statement.setString(2, text);
        statement.setString(3, messageId);

        statement.execute();
    }

    public void createMessage(String text) throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        PreparedStatement statement = conn.prepareStatement(CREATE_SQL);
        statement.setString(1, userName);
        statement.setString(2, text);
        statement.setString(3, messageId);

        statement.execute();
    }

    public void editMessage(String text) throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        PreparedStatement statement = conn.prepareStatement(UPDATE_SQL);
        statement.setString(1, text);
        statement.setString(2, messageId);

        statement.execute();
    }

    public void deleteMessage(String messageId) throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        PreparedStatement statement = conn.prepareStatement(DELETE_SQL);
        statement.setString(1, messageId);


        statement.execute();



    }


    public ArrayList<Message> getMessages() throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        PreparedStatement statement = conn.prepareStatement(SELECT_SQL);
        statement.setString(1, userName);

        statement.executeQuery();

        ArrayList<Message> results = new ArrayList<>();

        // while loop that goes over all the results rom queryResults
        // for each result, make a new Message object
        // set the fields of the message object to the values of the columns in the result
        // add the message to the results arraylist.

        return results;


    }



    }







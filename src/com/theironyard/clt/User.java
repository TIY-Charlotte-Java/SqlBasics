package com.theironyard.clt;

import java.sql.*;
import java.util.ArrayList;



public class User {
    public String name;
    public String password = "42";


    public User(String name) {

        this.name = name;

    }
    public ArrayList<Message> getMessages() throws SQLException{
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        PreparedStatement stmnt2 = conn.prepareStatement("SELECT * FROM messages WHERE username = ?");
        stmnt2.setString(1, name);

        ResultSet results = stmnt2.executeQuery();
        ArrayList<Message> messages = new ArrayList<>();
        while (results.next()) {
            String text = results.getString("text");
            String username = results.getString("username");
            int id = results.getInt("id");
            Message message = new Message(id,username,text);
            messages.add(message);

        }
        
        return messages;
    }

    public static void addMessage(String text) throws SQLException{
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO messages VALUES(', currentUser, text");
        //make a statement of add
        //execute statement

    }

    public static void deleteMessage(int messageId) throws SQLException{
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM messages WHERE id=messageId");


    }

    public static void editMessage(String text, int messageId) throws SQLException{
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("UPDATE messages SET text=text, WHERE id=messageId");
        //make statement of edit
        //execute statement
    }

    private static void ensureMessagesExists() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS messages(id IDENTITY , username VARCHAR, text VARCHAR");
    }
}

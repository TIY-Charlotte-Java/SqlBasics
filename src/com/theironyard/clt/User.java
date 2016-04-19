package com.theironyard.clt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

        Statement stmt = conn.createStatement();
        //make statement
        //execute statement
//        ArrayList<String> messages = new ArrayList<>();
//        messages.add(name);

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
        stmt.execute("DELETE FROM message WHERE id=messageId");


    }

    public static void editMessage(String text, int messageId) throws SQLException{
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("UPDATE messages SET message=text, WHERE id=messageId");
        //make statement of edit
        //execute statement
    }

    private static void ensureMessagesExists() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS messages(id IDENTITY , username VARCHAR, messages VARCHAR");
    }
}

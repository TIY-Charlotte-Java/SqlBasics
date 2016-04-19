package com.theironyard.clt;

import java.util.ArrayList;



public class User {
    public String name;
    public String password = "42";


    public User(String name) {

        this.name = name;

    }
    public ArrayList<message> getMessages() {
        //ensure messages exists
        //connect ot database
        //make statement
        //execute statement
//        ArrayList<String> messages = new ArrayList<>();
//        messages.add(name);

    }

    public void addMessage(String text) {
        //ensure messages exists
        //connect to database
        //make a statement of add
        //execute statement

    }

    public static void deleteMessage(int messageId) {
        //ensure messages exists
        //connect to database
        //make statement of delete
        //execute statement

    }

    public static void editMessage(String text, int messageId) {
        //ensure messages exists
        //connect to database
        //make statement of edit
        //execute statement
    }
}

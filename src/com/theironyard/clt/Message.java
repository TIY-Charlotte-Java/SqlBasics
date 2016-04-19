package com.theironyard.clt;

/**
 * Created by Ultramar on 4/19/16.
 */
public class Message {
    public static int ID;
    public static String userName;
    public static String text;

    public Message(int ID,String userName,String text) {
        this.ID = ID;
        this.userName = userName;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("%s. %s by %s",ID, text, userName);
    }
}

package com.theironyard.clt;

/**
 * Created by Ultramar on 4/19/16.
 */
public class Message {
    public int ID;
    public String userName;
    public String message;

    @Override
    public String toString() {
        return String.format("%s. %s by %s",ID, message, userName);
    }
}

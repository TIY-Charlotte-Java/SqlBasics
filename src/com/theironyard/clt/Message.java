package com.theironyard.clt;

/**
 * Created by mac on 4/20/16.
 */
public class Message {
    public int ID;
    public String userName;
    public String messages;

    @Override
    public String toString() {
        return String.format("%s : %s by %s", ID, messages, userName);
    }

}

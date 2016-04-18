package com.theironyard.clt;

import java.util.ArrayList;

/**
 * Created by Ultramar on 4/18/16.
 */
public class User {
    public String name;
    public String password = "42";
    public ArrayList<String> messages = new ArrayList<>();

    public User(String name) {

        this.name = name;

    }
}

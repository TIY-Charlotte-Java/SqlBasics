package com.theironyard.clt;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;


public class Main {


    public static HashMap<String, User> users = new HashMap<>();




    public static void serializeMessageState() throws IOException {
        File f = new File("messages.json");
        FileWriter fw = new FileWriter(f);

        JsonSerializer serializer = new JsonSerializer();

        fw.write(serializer.deep(true).serialize(users));

        fw.close();
    }

    public static void getMessagesFromDisk() {
        try {
            File f = new File("messages.json");
            JsonParser parser = new JsonParser();
            Scanner fileScanner = new Scanner(f);

            fileScanner.useDelimiter("\\Z");
            String fileContents = fileScanner.next();

            parser.map("values", User.class);

            users = parser.parse(fileContents);

        } catch (IOException ex) {

        }

    }


    public static void main(String[] args)  {

//        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

//        Statement stmt = conn.createStatement();
//
//        stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, userName VARCHAR, messages VARCHAR, messageNumber INT) ");
//        stmt.execute("INSERT INTO users VALUE (NULL, userName, messages, messageNumber) ");
//        stmt.execute("UPDATE users messages");
//        stmt.execute("DELETE FROM users WHERE ");




        Spark.init();

        getMessagesFromDisk();

        Spark.post("/destroy-user", (request, response) -> {
            Session session = request.session();

            session.invalidate();

            response.redirect("/");
            return "";
        });

        Spark.post("/delete-message", (request, response) -> {
            Session session = request.session();

            if (session.attribute("userName") == null) {
                response.redirect("/");
                return "";
            }

            int messageNumber = Integer.valueOf(request.queryParams("messageNumber"));
            User currentUser = users.get(session.attribute("userName"));

            currentUser.messages.remove(messageNumber - 1);

            response.redirect("/");

            serializeMessageState();

            return "";
        });


        Spark.post("/edit-message", (request, response) -> {
            Session session = request.session();

            if (session.attribute("userName") == null) {
                response.redirect("/");
                return "";
            }

            String newMessage = request.queryParams("newMessage");
            int messageNumber = Integer.valueOf(request.queryParams("messageNumber"));
            User currentUser = users.get(session.attribute("userName"));

            currentUser.messages.remove(messageNumber - 1);
            currentUser.messages.add(messageNumber - 1, newMessage);

            response.redirect("/");

            serializeMessageState();
            return "";
        });

        Spark.get(
                "/",
                (request, response) -> {
                    Session context = request.session();

                    // if we can't get a user from session, show login
                    if (context.attribute("userName") == null) {
                        return new ModelAndView(users, "login.html");
                    } else {
                        User current = users.get(context.attribute("userName"));

                        HashMap<String, User> model = new HashMap<>();

                        model.put("user", current);

                        return new ModelAndView(model, "messages.html");
                    }
                },
                new MustacheTemplateEngine()
        );

        Spark.post("/create-user",
                (request, response) -> {
                    Session session = request.session();

                    // get name from query string
                    String name = request.queryParams("loginName");
                    String password = request.queryParams("loginPassword");

                    // if user exists at username
                    User tempUser = new User(name);

                    if (password.equals(tempUser.password)) {
                        // save name to session
                        session.attribute("userName", name);

                        // make sure that the users hashmap has an entry with that name
                        users.putIfAbsent(name, tempUser);
                    }

                    response.redirect("/");
                    return "";
                }
        );

        Spark.post("/create-message",
                (request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");

                    if (userName == null) {
                        response.redirect("/");
                        return "";
                    }

                    // get message from query string
                    String message = request.queryParams("message");

                    users.get(userName).messages.add(message);

                    response.redirect("/");

                    serializeMessageState();
                    return "";
                }
        );

    }

}
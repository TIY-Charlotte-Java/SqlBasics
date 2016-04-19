package com.theironyard.clt;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.sql.*;
import java.util.HashMap;

public class Main {

    public static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) throws SQLException {
	// write your code here
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS messages(id IDENTITY , username VARCHAR, messages VARCHAR");
        User.getMessages();

        Spark.init();



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

            int messageNumber = Integer.valueOf(request.queryParams("choice"));
            User currentUser = users.get(session.attribute("userName"));

            User.deleteMessage(messageNumber);

            response.redirect("/");



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

            User.editMessage(newMessage, messageNumber);

            response.redirect("/");


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

                    User.addMessage(message);

                    response.redirect("/");


                    return "";
                }
        );


    }

}

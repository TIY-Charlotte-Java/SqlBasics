package com.theironyard.clt;


import spark.*;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.HashMap;


public class Main {


    public static HashMap<String, User> messages = new HashMap<>();


    public static void main(String[] args) {

        Spark.init();

        Spark.post("/delete-message", (request, response) -> {
            Session session = request.session();

            if (session.attribute("userName") == null) {
                response.redirect("/");
                return "";
            }

            return "";
        });


        Spark.post("/edit-message", (request, response) -> {
            Session session = request.session();

            if (session.attribute("messageID") == null) {
                response.redirect("/");
                return "";
            }

            String message = request.queryParams("message");

            messages.get(message).messages.add(message);


            response.redirect("/");


            return "";
        });

        Spark.get("/", (request, response) -> {
                    Session context = request.session();

            // if we can't get a user from session, show login
            if (context.attribute("userName") == null) {
            return new ModelAndView(messages, "login.html");

            } else {
            User current = messages.get(context.attribute("userName"));

            HashMap<String, Object> model = new HashMap<>();

            model.put("user", current);
            model.put("messages", current.getMessages());


            return new ModelAndView(model, "messages.html");
            }

        },
                new MustacheTemplateEngine()
        );

        Spark.post("/login", (request, response) -> {
            Session session = request.session();

            // if we can't get a user from session, show login
            if (session.attribute("userName") == null) {
                response.redirect("/");
                return "";
            } else {


            HashMap<String, Object> model = new HashMap<>();

            model.put("error", "userName not found");
            return new ModelAndView(model, "login.html");

           }


        });



        Spark.post("/create-message", (request, response) -> {
           Session session = request.session();
           String userName = session.attribute("userName");

           if (userName == null) {
           response.redirect("/");
           return "";
                    }

           // get message from query string
           String message = request.queryParams("message");

           messages.get(userName).messages.add(message);

           response.redirect("/");

           return "";

        });

    }

}
package com.theironyard.clt;


import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.HashMap;

//import static java.awt.SystemColor.text;



public class Main {
     static User user;




    //public static HashMap<String, User> users = new HashMap<>();

    public static HashMap<String, User> messages = new HashMap<>();





    public static void main(String[] args) {

        user.editMessage();
        user.createMessage();
        user.addMessage();
        user.deleteMessage();
        

        Spark.init();


//        Spark.post("/destroy-user", (request, response) -> {
//            Session session = request.session();
//
//            session.invalidate();
//
//            response.redirect("/");
//            return "";
//        });

        Spark.post("/delete-message", (request, response) -> {
            Session session = request.session();

            if (session.attribute("userName") == null) {
                response.redirect("/");
                return "";
            }

//            int messageNumber = Integer.valueOf(request.queryParams("messageNumber"));
//            User messages = messages.get(session.attribute("userName"));
//
//            messages.messages.remove(messageNumber - 1);
//
//            response.redirect("/");


            return "";
        });


        Spark.post("/edit-message", (request, response) -> {
            Session session = request.session();

            if (session.attribute("messageID") == null) {
                response.redirect("/");
                return "";
            }

//            String newMessage = request.queryParams("newMessage");
//            int messageNumber = Integer.valueOf(request.queryParams("messageNumber"));
//            User messages = get.messages(session.attribute("userName"));
//
//            messages.remove(messageNumber - 1);
//            messages.messages.add(messageNumber - 1, newMessage);



            String message = request.queryParams("message");

            messages.get(message).messages.add(message);


            response.redirect("/");


            return "";
        });

        Spark.get(
                "/",
                (request, response) -> {
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

//        Spark.post("/create-user",
//                (request, response) -> {
//                    Session session = request.session();
//
//                    // get name from query string
//                    String name = request.queryParams("loginName");
//                    String password = request.queryParams("loginPassword");
//
//                    // if user exists at username
//                    User tempUser = new User(name);
//
//                    if (password.equals(tempUser.password)) {
//                        // save name to session
//                        session.attribute("userName", name);
//
//                        // make sure that the users hashmap has an entry with that name
//                        users.putIfAbsent(name, tempUser);
//                    }
//
//                    response.redirect("/");
//                    return "";
//                }
       // );

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

                    messages.get(userName).messages.add(message);

                    response.redirect("/");

                    return "";
                }
        );

    }

}
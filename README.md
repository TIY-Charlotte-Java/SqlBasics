# SQL Basics
### Working Microblog Gist
[Here is an example of a working Microblog using Spark.](https://gist.github.com/raynjamin/7f94b4694f47a901e01db9a8429f1762)

## Description
Copy the TodoList project we made for the weekend homework. Modify it to use a database instead of a file for its storage.

## Requirements

* Create the `Connection` and execute a query to create a `messages` table that stores a message `id`, `varchar` username, and a `varchar` text
* Remove the `ArrayList<String>` that stored user messages
* Add a `getMessages()` method to User that returns an `ArrayList<String>` of messages
* Add an `addMessage(String text)` method to User that adds a message to the database.
* Add a static `editMessage(int id, String text)` method to User that edits a given message.
* Add a static `deleteMessage(int id)` method to User that deletes a given message
* Modify your `edit-message`, `create-message` and `delete-message` routes to use these new methods instead of accessing the now-unexistant messages field on User. 

### Hard Mode
* Make a users table and relate the users to messages on a Foreign Key.

package com.theironyard.clt;

import java.sql.*;

/**
 * Created by mac on 4/18/16.
 */
public class User {
//    private static Connection conn;
//    private static String messages;
//    private static int id;
//    private static int seqNum;
//    private static String user;
    public String name;

    Connection conn = DriverManager.getConnection("jdbc:h2:./main");

    Statement stmt = conn.createStatement();

    stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, userName VARCHAR, messages VARCHAR, messageNumber INT) ");
    stmt.execute("INSERT INTO messages (" + "user_id," + " username," + " messageNumber," + " messages ) VALUES (" + "null, ?, ?, ?)";
    stmt.execute("UPDATE messages SET messageNumber int i = 0 WHERE 0 = messageNumber ");
//        stmt.execute("DELETE FROM users WHERE ");



    public User(String name) {
        this.name = name;

    }

//    public static void updateMessages (
//            Connection conn,
//            String messages,
//            int id,
//            int seqNum,
//            String user
//
//    )
//
//        throws SQLException
//
//    {
//
//        User.messages = messages;
//        User.id = id;
//        User.seqNum = seqNum;
//        User.user = user;
//        try
//        {
//            // create our java preparedstatement using a sql update query
//            PreparedStatement ps = conn.prepareStatement(
//                    "UPDATE Messages SET messages = ?, user = ? WHERE id = ? AND seq_num = ?");
//
//            // set the preparedstatement parameters
//            ps.setString(1,messages);
//            ps.setString(2,user);
//            ps.setInt(3,id);
//            ps.setInt(4,seqNum);
//
//            // call executeUpdate to execute our sql update statement
//            ps.executeUpdate();
//            ps.close();
//        }
//    }
}

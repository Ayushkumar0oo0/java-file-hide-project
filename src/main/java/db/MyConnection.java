package main.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student", "SqL name", "SQl passsword"
            ); // create database 
//             CREATE TABLE users (
//     user_id INT PRIMARY KEY AUTO_INCREMENT,
//     username VARCHAR(50) NOT NULL UNIQUE,
//     email VARCHAR(100) NOT NULL UNIQUE,
//     password VARCHAR(255) NOT NULL
// );
// CREATE TABLE files (
//     file_id INT PRIMARY KEY AUTO_INCREMENT,
//     user_id INT NOT NULL,
//     file_path TEXT NOT NULL,
//     is_hidden BOOLEAN DEFAULT FALSE,
//     FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
// );

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

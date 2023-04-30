package com.tm.store.configuratiuion;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/vegetable_shop";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";
    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL_CONNECTION, USER_NAME, PASSWORD);
            System.out.println("Connect database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not find driver for jdbc connection!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not find database");
        }
        return connection;
    }

//    public static void main(String[] args) {
//        System.out.println(BCrypt.hashpw("12345678", BCrypt.gensalt()));
//    }
}

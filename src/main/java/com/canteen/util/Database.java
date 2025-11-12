package com.canteen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/canteen_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql@98422"; // change this

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database Connected Successfully!");
            } catch (SQLException e) {
                System.out.println("❌ Database Connection Failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}

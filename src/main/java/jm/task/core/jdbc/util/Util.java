package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String HOST = "jdbc:mysql://localhost:3306/mysqld";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Andrey231175!";



    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

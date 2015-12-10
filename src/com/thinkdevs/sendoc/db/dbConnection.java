package com.thinkdevs.sendoc.db;

import java.sql.*;

/**
 * Main class for start application
 *
 * @author Maxim Tikhanovskiy
 */
public class dbConnection {
    public static void main(String[] args) {
        String userName = "cn91564_sendoc";
        String password = "sendoc";
        String urlDataBase = "jdbc:mysql://92.53.96.36:3306/cn91564_sendoc";
        String jdbcDriver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(urlDataBase, userName, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from orders");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("order_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

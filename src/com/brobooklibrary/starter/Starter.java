package com.brobooklibrary.starter;

import java.sql.*;
import java.util.Properties;

public class Starter {

	public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String hostName = "jdbc:mysql://localhost:3306/book-library";
        String userName = "root";
        String userPass = "12";

        Connection conn = null;
        ResultSet rt = null;
        Statement stm = null;
        try {
            conn = DriverManager.getConnection(hostName,userName,userPass);
            rt = (ResultSet) conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mark
 */
public class Db {
        private static final String URL = "jdbc:mysql://localhost:3306/db_sms";
    
        public static Connection connect() {
            try {
                Connection connection = DriverManager.getConnection(URL);
                System.out.println("Connected to the database.");
                return connection;
            } catch (SQLException e) {
                System.err.println("Connection failed: " + e.getMessage());
                return null;
            }
        }
    
        public static void main(String[] args) {
            connect();
        }
    }

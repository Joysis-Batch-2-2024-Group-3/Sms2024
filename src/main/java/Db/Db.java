/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Db;

import java.sql.*;

/**
 *
 * @author mark
 */
import Repository.QueryConstant;

public class Db implements QueryConstant {

    public Connection con;
    public Statement state;
    public ResultSet result;
    public PreparedStatement prep;

    public void connect(){
        try {
            // call the driver
            Class.forName(DRIVER);
            // creating a connection
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

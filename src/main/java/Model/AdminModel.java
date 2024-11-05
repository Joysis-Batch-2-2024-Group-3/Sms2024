/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Utils.PasswordHashing;

/**
 *
 * @author mark
 */
public class AdminModel {
    private int admin_id;
    private String username;
    private String password;

    public AdminModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getAdminId()
    {
        return admin_id;
    }
    
    public void setAdminUsername(String username)
    {
        this.username = username;
    }
    
    public String getAdminUsername()
    {
        return username;
    }
    
    public void setAdminPassword(String password)
    {
//        hash the password
        this.password = PasswordHashing.hashPassword(password);
    }
    
    public String getAdminPassword()
    {
        return password;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author mark
 */

 public class Index {
    public static void main(String[] args) {
    boolean auth = Admin.LoginAdmin();
    if (auth){
        MainMenu.DisplayMainMenu();
    }
    }
 }

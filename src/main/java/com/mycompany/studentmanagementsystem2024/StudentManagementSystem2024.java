/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmanagementsystem2024;
import View.MainMenu;
import View.Admin;
import Utils.ClearConsole;

/**
 *
 * @author mark
 */
public class StudentManagementSystem2024 {

    public static void main(String[] args) {
        while(true)
        {
           ClearConsole.Cls();

           String firstAction = MainMenu.DisplayMainMenu();
           
           if(firstAction.equals("1"))
           {
               Admin.LoginAdmin();
           }else{
               System.out.println("hi");
           }
        }
    }
}

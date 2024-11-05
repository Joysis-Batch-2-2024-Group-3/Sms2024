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
    private static boolean authenticated = false;

    public static void main(String[] args) throws InterruptedException {
        
//        while loop will keep the app running
       try
       {
            while(true)
            {
               ClearConsole.Cls();


               authenticated = Admin.LoginAdmin();



               if(authenticated)
               {
                   MainMenu.DisplayActionsMenu();
               }
            }
       }catch(InterruptedException e)
       {
           System.out.println("Error Encountered: " + e);
       }
    }
}

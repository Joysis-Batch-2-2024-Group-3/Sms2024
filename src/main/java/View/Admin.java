/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.AdminController;
import java.util.Scanner;
import Utils.*;
import Model.AdminModel;

/**
 *
 * @author mark
 */
public class Admin {
    static Scanner sc = new Scanner(System.in);
    static private final AdminController ac = new AdminController();
    

    public static boolean LoginAdmin()
    {
        boolean authenticated = false;
        try
        {    
            ClearConsole.Cls();
            String[] account = new String[2];
            MainMenu.MainMenuHeader();
            System.out.println("\n|--------------------------LOGIN--------------------------|\n");
            System.out.print("Username: ");
            String username = Input.getUserInput();
            System.out.print("Password: ");
            String password = Input.getUserInput();
            
            authenticated = ac.authenticateAdmin(new AdminModel(username, password));
            
            if(authenticated)
            {
                System.out.println("-------------Access Granted. Redirecting------------\n");
                Thread.sleep(1000);
                return true;
            }else{
                System.out.println("\n----------Wrong Username or Password----------\n");
                System.out.print("Press [1] to Retry: ");
               if(sc.next().equals("1"))
               {
                   LoginAdmin();
               }else{
                   return false;

               }
            }
            
            
            
        }
        catch(Exception e)
        {
            System.out.println("---------- Error Logging in: " + e + "----------");
        }
        return authenticated;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.AdminController;
import java.util.Scanner;
import Utils.ClearConsole;
import Model.AdminModel;

/**
 *
 * @author mark
 */
public class Admin {
    static Scanner sc = new Scanner(System.in);
    static private final AdminController ac = new AdminController();
    
//    run as long as the input is not valid, otherwise return it
public static String getUserInput() { String input = sc.next(); 
    while (input.isEmpty()) { 
        System.out.println("\n-----Invalid Input! Please enter a valid input:\n-----"); 
        input = sc.next(); } 
        return input; 
    }

    public static boolean LoginAdmin()
    {
        boolean authenticated = false;
        try
        {    
            ClearConsole.Cls();
            String[] account = new String[2];
            MainMenu.MainMenuHeader();
            System.out.println("|--------------------------LOGIN--------------------------|\n");
            System.out.print("Username: ");
            String username = getUserInput();
            System.out.print("Password: ");
            String password = getUserInput();
            
            authenticated = ac.authenticateAdmin(new AdminModel(username, password));
            
            if(authenticated)
            {
                System.out.println("-----------Access Granted----------");
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

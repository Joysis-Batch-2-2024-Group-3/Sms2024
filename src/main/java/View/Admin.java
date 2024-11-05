/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.Scanner;
import Utils.ClearConsole;

/**
 *
 * @author mark
 */
public class Admin {
    static Scanner sc = new Scanner(System.in);
    
//    run as long as the input is not valid, otherwise return it
public static String getUserInput() { String input = sc.next(); 
    while (input.isEmpty()) { 
        System.out.println("\n-----Invalid Input! Please enter a valid input:\n-----"); 
        input = sc.next(); } 
        return input; 
    }

    public static String[] DisplayAdminLogin()
    {
        ClearConsole.Cls();
        String[] account = new String[2];
        MainMenu.MainMenuHeader();
        System.out.println("|--------------------------LOGIN--------------------------|\n");
        System.out.print("Username: ");
        account[0] = getUserInput();
        System.out.print("Password: ");
        account[1] = getUserInput();

        return account;       
    }
}

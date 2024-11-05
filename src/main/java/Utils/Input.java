/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import java.util.Scanner;
/**
 *
 * @author mark
 */
public class Input {
     private static Scanner sc = new Scanner(System.in);
    //    run as long as the input is not valid, otherwise return it
    public static String getUserInput() { 
        String input = sc.next(); 
        while (input.isEmpty()) { 
            System.out.println("\n-----Invalid Input! Please enter a valid input:\n-----"); 
            input = sc.next(); 
        } 
            return input.trim(); 
    }
    
    
    public static String HoldState()
    {
        System.out.print("Back (any): ");
        return sc.next();
    }
}

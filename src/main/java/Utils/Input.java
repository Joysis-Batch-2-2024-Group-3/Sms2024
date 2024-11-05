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
    
    
    static public void COut(String info)
    {
        System.out.println("\n--------------------"+info+"--------------------\n");
    }
    
    
    public static String HoldState()
    {
        System.out.print("Back (any): ");
        return sc.next();
    }
    
    public static void HeaderBox(String input) {
        int n = input.length();
        int totalWidth = 50;
        int padding = (totalWidth - n) / 2;

        System.out.println(" ________________________________________________");
        System.out.println("|                                                |");

        // Print leading spaces
        System.out.print("|");
        for (int i = 1; i < padding; i++) {
            System.out.print(" ");
        }

        // Print the input string
        System.out.print(input);

        // Print trailing spaces
        for (int i = 1; i < (totalWidth - n - padding); i++) {
            System.out.print(" ");
        }
        System.out.println("|");

        System.out.println("|________________________________________________|\n");
    }

    
    public static int parseIfPossible(String input) { 
        try { return Integer.parseInt(input);
     }catch (NumberFormatException e) { return 0;} 
        
    }
}

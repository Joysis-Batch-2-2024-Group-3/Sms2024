package View;

import java.util.Scanner;

public class MainMenu {
    private static Scanner sc = new Scanner(System.in);
    final private static String[] s1 = {
        "|                    ",
        "|              ###   ",
        "|            ##   ## ",
        "|           ##       ",
        "|            ##      ",
        "|             #####  ",
        "|                 ## ",
        "|           ##   ##  ",
        "|             ####   ",
        "|                    "
    };

    final private static String[] m = {
        "            ",
        " ##     ##  ",
        " ## # # ##  ",
        " ##  #  ##  ",
        " ##  #  ##  ",
        " ##     ##  ",
        " ##     ##  ",
        " ##     ##  ",
        " ##     ##  ",
        "            "
    };
    final private static String[] s2 = {
        "                     |",
        "   ###               |",
        " ##   ##             |",
        "##                   |",
        " ##                  |",
        "  #####              |",
        "      ##             |",
        " ##   ##             |",
        "   ####              |",
        "                     |"
    };
      
    public static void MainMenuHeader()
    {
         System.out.println("----------------------------------------------------------");
        for (int i = 0; i < s1.length; i++) {
            // Print "S" in Red and "M" in the default color
            System.out.print(s1[i]  + "  " + m[i] + "  " + s2[i] + "\n");
        }
        
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|          Welcome To Student Management System           |");
        System.out.println("-----------------------------------------------------------");
    }

    
//    this function will return the user response, it should only accept 1 as valid response
    public static String DisplayMainMenu() {
       MainMenuHeader();

       System.out.println("\n[1]. Admin Login");
        String response = sc.next();
        
        while(!response.equals("1"))
        {
            System.out.println("\n----------Invalid Input, Try Again--------- \n");
            System.out.println("\n[1]. Admin Login \n");
            response = sc.next();
        }
       
       return response;
    }
    
    

}

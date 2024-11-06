package View;

import Utils.ClearConsole;
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
        ClearConsole.Cls();
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
    
    public static void DisplayActionsMenu() throws InterruptedException
    {
        MainMenuHeader();
        System.out.println("\n|------------------------Options------------------------|\n");
        System.out.println(" __________________________________________________");
        System.out.println("|                                                  |");
        System.out.println("| 1. Student                                       |");
        System.out.println("| 2. Course                                        |");
        System.out.println("| 3. Section                                       |");
        System.out.println("| 4. Subject                                       |");
        System.out.println("| 5. Schedule                                      |");
        System.out.println("| 6. Student Subject                               |");
        System.out.println("| 7. Logout                                        |");
        System.out.println("|__________________________________________________|\n");
        System.out.print("Enter Selection: ");
        
        
        String selected = sc.nextLine();
      
        switch(selected)
        {
                case "1" -> StudentView.DisplayStudentMenu();
                case "2" -> CourseView.DisplayCourseView();
                case "3" -> SectionView.DisplaySectionMenu();
                case "4" -> SubjectView.DisplaySubjectMenu();
                case "5" -> ScheduleView.DisplayScheduleView();
                case "6" -> StudentSubject.DisplaySubjectView();

                case "7" -> {
                    System.out.println("\n----------Session Ended. redirecting..........\n\n");
                    Thread.sleep(1000);
                }
                default -> {
                    System.out.println("Invalid Selection. Try Again: ");
                    Thread.sleep(500);
                    DisplayActionsMenu();
                }
        }
        
    }
    
    

}

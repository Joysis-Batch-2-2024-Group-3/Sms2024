package View;

import Utils.*;
import java.util.Scanner;

public class MainMenu {
    public static ConsoleColors cc = new ConsoleColors();

    private static Scanner sc = new Scanner(System.in);
    final private static String[] s1 = {
            cc.BB_WHITE+" "+cc.BB_BLACK+"                ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"      "+cc.BG_GREEN+"      "+cc.BB_BLACK+"    ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"    "+cc.BB_GREEN+"  "+cc.BG_BLACK+"     "+cc.BB_GREEN+"  "+cc.BB_BLACK+"   ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"    "+cc.BB_GREEN+"  "+cc.BB_BLACK+"          ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"    "+cc.BB_GREEN+"  "+cc.BB_BLACK+"          ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"     "+cc.BB_GREEN+"       "+cc.BB_BLACK+"    ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"           "+cc.BB_GREEN+"  "+cc.BB_BLACK+"   ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"           "+cc.BB_GREEN+"  "+cc.BB_BLACK+"   ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"    "+cc.BB_GREEN+"  "+cc.BB_BLACK+"     "+cc.BB_GREEN+"  "+cc.BB_BLACK+"   ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"     "+cc.BB_GREEN+"       "+cc.BB_BLACK+"    ",
            cc.BB_WHITE+" "+cc.BB_BLACK+"                "
    };

    final private static String[] m = {
            "                            ",
            " "+cc.BB_GREEN+"    "+cc.BB_BLACK+"                 "+cc.BB_GREEN+"    "+cc.BB_BLACK+"  ",
            " "+cc.BB_GREEN+"  "+cc.BB_BLACK+"  "+cc.BB_GREEN+" "+cc.BB_BLACK+"               "+cc.BB_GREEN+" "+cc.BB_BLACK+"  "+cc.BB_GREEN+"  "+cc.BB_BLACK+"  ",
            " "+cc.BB_GREEN+"  "+cc.BB_BLACK+"   "+cc.BB_GREEN+" "+cc.BB_BLACK+"             "+cc.BB_GREEN+" "+cc.BB_BLACK+"   "+cc.BB_GREEN+"  "+cc.BB_BLACK+"  ",
            " "+cc.BB_GREEN+"  "+cc.BB_BLACK+"    "+cc.BB_GREEN+" "+cc.BB_BLACK+"           "+cc.BB_GREEN+" "+cc.BB_BLACK+"    "+cc.BB_GREEN+"  "+cc.BB_BLACK+"  ",
            " "+cc.BB_GREEN+"  "+cc.BB_BLACK+"     "+cc.BB_GREEN+" "+cc.BB_BLACK+"         "+cc.BB_GREEN+" "+cc.BB_BLACK+"     "+cc.BB_GREEN+"  "+cc.BB_BLACK+"  ",
            " "+cc.BB_GREEN+"  "+cc.BB_BLACK+"      "+cc.BB_GREEN+" "+cc.BB_BLACK+"       "+cc.BB_GREEN+" "+cc.BB_BLACK+"      "+cc.BB_GREEN+"  "+cc.BB_BLACK+"  ",
            " "+cc.BB_GREEN+"  "+cc.BB_BLACK+"       "+cc.BB_GREEN+" "+cc.BB_BLACK+"     "+cc.BB_GREEN+" "+cc.BB_BLACK+"       "+cc.BB_GREEN+"  "+cc.BB_BLACK+"  ",
            " "+cc.BB_GREEN+"  "+cc.BB_BLACK+"        "+cc.BB_GREEN+" "+cc.BB_BLACK+"   "+cc.BB_GREEN+" "+cc.BB_BLACK+"        "+cc.BB_GREEN+"  "+cc.BB_BLACK+"  ",
            " "+cc.BB_GREEN+"  "+cc.BB_BLACK+"         "+cc.BB_GREEN+"   "+cc.BB_BLACK+"         "+cc.BB_GREEN+"  "+cc.BB_BLACK+"  ",
            "                            ",

    };
    final private static String[] s2 = {
            "                "+cc.BB_WHITE+" "+cc.RESET,
            "   "+cc.BG_GREEN+"       "+cc.BG_BLACK+"      "+cc.BB_WHITE+" "+cc.RESET,
            "  "+cc.BG_GREEN+"  "+cc.BG_BLACK+"     "+cc.BG_GREEN+"  "+cc.BG_BLACK+"     "+cc.BB_WHITE+" "+cc.RESET,
            "  "+cc.BG_GREEN+"  "+cc.BG_BLACK+"            "+cc.BB_WHITE+" "+cc.RESET,
            "  "+cc.BG_GREEN+"  "+cc.BG_BLACK+"            "+cc.BB_WHITE+" "+cc.RESET,
            "   "+cc.BG_GREEN+"       "+cc.BG_BLACK+"      "+cc.BB_WHITE+" "+cc.RESET,
            "         "+cc.BG_GREEN+"  "+cc.BG_BLACK+"     "+cc.BB_WHITE+" "+cc.RESET,
            "         "+cc.BG_GREEN+"  "+cc.BG_BLACK+"     "+cc.BB_WHITE+" "+cc.RESET,
            "  "+cc.BG_GREEN+"  "+cc.BG_BLACK+"     "+cc.BG_GREEN+"  "+cc.BG_BLACK+"     "+cc.BB_WHITE+" "+cc.RESET,
            "   "+cc.BG_GREEN+"       "+cc.BG_BLACK+"      "+cc.BB_WHITE+" "+cc.RESET,
            "                "+cc.BB_WHITE+" "+cc.RESET,
    };

    public static void MainMenuHeader()
    {
        ClearConsole.Cls();
        System.out.println(cc.whiteLine);
        for (int i = 0; i < s1.length; i++) {
            // Print "S" in Red and "M" in the default color
            System.out.print(cc.center+s1[i]  + "  " + m[i] + "  " + s2[i] + "\n");
        }

        System.out.println(cc.whiteLine);
        System.out.println(cc.whiteSpace);
        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t   "+cc.BR_GREEN+"Welcome To Student Management System"+cc.BB_BLACK+"\t\t\t     "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.whiteSpace);
    }

    //    this function will return the user response, it should only accept 1 as valid response
    public static String DisplayMainMenu() {
        MainMenuHeader();

        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t\t\t   "+cc.BR_GREEN+"Press [1] to Login"+cc.BB_BLACK+"\t\t\t\t\t\t "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.whiteSpace);
        System.out.println(cc.whiteLine);
        System.out.print(cc.centerResponse);
        String response = sc.next();

        while(!response.equals("1"))
        {
            System.out.println(cc.redLine);
            System.out.println(cc.redSpace);
            System.out.println(cc.center+cc.BB_RED+" "+cc.BB_BLACK+"\t\t\t\t  "+cc.BR_RED+"Invalid Input, Try Again [1]"+cc.BB_BLACK+"\t\t\t\t\t" +
                    " "+cc.BB_RED+" "+cc.RESET);
            System.out.println(cc.redSpace);
            System.out.println(cc.redLine);
            response = sc.next();
        }

        return response;
    }

    public static void DisplayActionsMenu() throws InterruptedException
    {
        MainMenuHeader();
        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t\t\t\t  "+cc.BR_GREEN+"O P T I O N S"+cc.BB_BLACK+"\t\t\t\t\t\t\t "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.whiteSpace);
        System.out.println(cc.whiteLine);
        System.out.println(cc.whiteSpace);
        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t\t\t\t  "+cc.BR_BLUE+"[1] Student"+cc.BB_BLACK+"\t\t\t\t\t\t\t "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t\t\t\t  "+cc.BR_BLUE+"[2] Course"+cc.BB_BLACK+"\t\t\t\t\t\t\t "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t\t\t\t  "+cc.BR_BLUE+"[3] Section"+cc.BB_BLACK+"\t\t\t\t\t\t\t "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t\t\t\t  "+cc.BR_BLUE+"[4] Subject"+cc.BB_BLACK+"\t\t\t\t\t\t\t "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t\t\t\t  "+cc.BR_BLUE+"[5] Schedule"+cc.BB_BLACK+"\t\t\t\t\t\t\t "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t\t\t\t  "+cc.BR_BLUE+"[6] Student Subject"+cc.BB_BLACK+"\t\t\t\t\t "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.center+cc.BB_WHITE+" "+cc.BB_BLACK+"\t\t\t\t\t\t  "+cc.BR_BLUE+"[7] Logout"+cc.BB_BLACK+"\t\t\t\t\t\t\t "+cc.BB_WHITE+" "+cc.RESET);
        System.out.println(cc.whiteSpace);
        System.out.println(cc.whiteLine);
        System.out.print(cc.YELLOW+"\n"+cc.center+"Enter Selection: "+cc.RESET);

        String selected = sc.nextLine();

        switch(selected)
        {
                case "1" -> StudentView.DisplayStudentMenu();
                case "2" -> CourseView.DisplayCourseView();
                case "3" -> SectionView.DisplaySectionMenu();
                case "4" -> SubjectView.DisplaySubjectMenu();
                case "5" -> ScheduleView.DisplayScheduleView();
                case "6" -> SubjectView.DisplaySubjectMenu();

                case "7" -> {
                    System.out.println(cc.redLine);
                    System.out.println(cc.redSpace);
                    System.out.println(cc.center+cc.BB_RED+" "+cc.BB_BLACK+"\t\t\t\t\t\t  "+cc.BR_RED+"Session Ended"+cc.BB_BLACK+"\t\t\t\t\t\t\t" +
                            " "+cc.BB_RED+" "+cc.RESET);
                    System.out.println(cc.center+cc.BB_RED+" "+cc.BB_BLACK+"\t\t\t\t\t  "+cc.BR_GREEN+"R E D I R E C T I N G"+cc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+cc.BB_RED+" "+cc.RESET);
                    System.out.println(cc.redSpace);
                    System.out.println(cc.redLine);
                    Thread.sleep(1000);
                }
                default -> {
                    System.out.println(cc.redLine);
                    System.out.println(cc.redSpace);
                    System.out.println(cc.center+cc.BB_RED+" "+cc.BB_BLACK+"\t\t\t\t\t\t "+cc.BR_RED+"Invalid Selection"+cc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+cc.BB_RED+" "+cc.RESET);
                    System.out.println(cc.center+cc.BB_RED+" "+cc.BB_BLACK+"\t\t\t\t\t\t "+cc.BR_GREEN+"T r y   A g a i n"+cc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+cc.BB_RED+" "+cc.RESET);
                    System.out.println(cc.redSpace);
                    System.out.println(cc.redLine);
                    Thread.sleep(500);
                    DisplayActionsMenu();
                }
        }

    }



}

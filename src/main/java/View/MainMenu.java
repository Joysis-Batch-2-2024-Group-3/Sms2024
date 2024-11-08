package View;

import java.util.Scanner;

import Utils.ClearConsole;
import Utils.ConsoleColors;

public class MainMenu {
    public static ConsoleColors cc = new ConsoleColors();

    private static Scanner sc = new Scanner(System.in);
    final private static String[] s1 = {
            cc.BB_WHITE + " " + cc.BB_BLACK + "                ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "      " + cc.BG_GREEN + "      " + cc.BB_BLACK + "    ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "    " + cc.BB_GREEN + "  " + cc.BG_BLACK + "     " + cc.BB_GREEN + "  "
                    + cc.BB_BLACK + "   ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "    " + cc.BB_GREEN + "  " + cc.BB_BLACK + "          ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "    " + cc.BB_GREEN + "  " + cc.BB_BLACK + "          ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "     " + cc.BB_GREEN + "       " + cc.BB_BLACK + "    ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "           " + cc.BB_GREEN + "  " + cc.BB_BLACK + "   ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "           " + cc.BB_GREEN + "  " + cc.BB_BLACK + "   ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "    " + cc.BB_GREEN + "  " + cc.BB_BLACK + "     " + cc.BB_GREEN + "  "
                    + cc.BB_BLACK + "   ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "     " + cc.BB_GREEN + "       " + cc.BB_BLACK + "    ",
            cc.BB_WHITE + " " + cc.BB_BLACK + "                "
    };

    final private static String[] m = {
            "                            ",
            " " + cc.BB_GREEN + "    " + cc.BB_BLACK + "                 " + cc.BB_GREEN + "    " + cc.BB_BLACK + "  ",
            " " + cc.BB_GREEN + "  " + cc.BB_BLACK + "  " + cc.BB_GREEN + " " + cc.BB_BLACK + "               "
                    + cc.BB_GREEN + " " + cc.BB_BLACK + "  " + cc.BB_GREEN + "  " + cc.BB_BLACK + "  ",
            " " + cc.BB_GREEN + "  " + cc.BB_BLACK + "   " + cc.BB_GREEN + " " + cc.BB_BLACK + "             "
                    + cc.BB_GREEN + " " + cc.BB_BLACK + "   " + cc.BB_GREEN + "  " + cc.BB_BLACK + "  ",
            " " + cc.BB_GREEN + "  " + cc.BB_BLACK + "    " + cc.BB_GREEN + " " + cc.BB_BLACK + "           "
                    + cc.BB_GREEN + " " + cc.BB_BLACK + "    " + cc.BB_GREEN + "  " + cc.BB_BLACK + "  ",
            " " + cc.BB_GREEN + "  " + cc.BB_BLACK + "     " + cc.BB_GREEN + " " + cc.BB_BLACK + "         "
                    + cc.BB_GREEN + " " + cc.BB_BLACK + "     " + cc.BB_GREEN + "  " + cc.BB_BLACK + "  ",
            " " + cc.BB_GREEN + "  " + cc.BB_BLACK + "      " + cc.BB_GREEN + " " + cc.BB_BLACK + "       "
                    + cc.BB_GREEN + " " + cc.BB_BLACK + "      " + cc.BB_GREEN + "  " + cc.BB_BLACK + "  ",
            " " + cc.BB_GREEN + "  " + cc.BB_BLACK + "       " + cc.BB_GREEN + " " + cc.BB_BLACK + "     " + cc.BB_GREEN
                    + " " + cc.BB_BLACK + "       " + cc.BB_GREEN + "  " + cc.BB_BLACK + "  ",
            " " + cc.BB_GREEN + "  " + cc.BB_BLACK + "        " + cc.BB_GREEN + " " + cc.BB_BLACK + "   " + cc.BB_GREEN
                    + " " + cc.BB_BLACK + "        " + cc.BB_GREEN + "  " + cc.BB_BLACK + "  ",
            " " + cc.BB_GREEN + "  " + cc.BB_BLACK + "         " + cc.BB_GREEN + "   " + cc.BB_BLACK + "         "
                    + cc.BB_GREEN + "  " + cc.BB_BLACK + "  ",
            "                            ",

    };
    final private static String[] s2 = {
            "                " + cc.BB_WHITE + " " + cc.RESET,
            "   " + cc.BG_GREEN + "       " + cc.BG_BLACK + "      " + cc.BB_WHITE + " " + cc.RESET,
            "  " + cc.BG_GREEN + "  " + cc.BG_BLACK + "     " + cc.BG_GREEN + "  " + cc.BG_BLACK + "     " + cc.BB_WHITE
                    + " " + cc.RESET,
            "  " + cc.BG_GREEN + "  " + cc.BG_BLACK + "            " + cc.BB_WHITE + " " + cc.RESET,
            "  " + cc.BG_GREEN + "  " + cc.BG_BLACK + "            " + cc.BB_WHITE + " " + cc.RESET,
            "   " + cc.BG_GREEN + "       " + cc.BG_BLACK + "      " + cc.BB_WHITE + " " + cc.RESET,
            "         " + cc.BG_GREEN + "  " + cc.BG_BLACK + "     " + cc.BB_WHITE + " " + cc.RESET,
            "         " + cc.BG_GREEN + "  " + cc.BG_BLACK + "     " + cc.BB_WHITE + " " + cc.RESET,
            "  " + cc.BG_GREEN + "  " + cc.BG_BLACK + "     " + cc.BG_GREEN + "  " + cc.BG_BLACK + "     " + cc.BB_WHITE
                    + " " + cc.RESET,
            "   " + cc.BG_GREEN + "       " + cc.BG_BLACK + "      " + cc.BB_WHITE + " " + cc.RESET,
            "                " + cc.BB_WHITE + " " + cc.RESET,
    };

    public static void MainMenuHeader() {
        ClearConsole.Cls();
        Sout(cc.whiteLine);
        for (int i = 0; i < s1.length; i++) {
            // Print "S" in Red and "M" in the default color
            System.out.print(cc.center + s1[i] + "  " + m[i] + "  " + s2[i] + "\n");
        }

        Sout(cc.whiteLine);
        Sout(cc.whiteSpace);
        Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t   " + cc.BR_GREEN
                + "Welcome To Student Management System" + cc.BB_BLACK + "\t\t\t     " + cc.BB_WHITE + " " + cc.RESET);
        Sout(cc.whiteSpace);
    }

    // this function will return the user response, it should only accept 1 as valid
    // response
    public static void DisplayMainMenu() {
        MainMenuHeader();
        try {
            DisplayActionsMenu();
        } catch (InterruptedException e) {
            Sout("Interrupted exception occurred");
        }
    }

    public static void DisplayActionsMenu() throws InterruptedException {
        MainMenuHeader();
        Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "O P T I O N S"
                + cc.BB_BLACK + "\t\t\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
        Sout(cc.whiteSpace);
        Sout(cc.whiteLine);
        Sout(cc.whiteSpace);
        Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "[1] Student" + cc.BB_BLACK
                + "\t\t\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
        Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "[2] Course" + cc.BB_BLACK
                + "\t\t\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
        Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "[3] Section" + cc.BB_BLACK
                + "\t\t\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
        Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "[4] Subject" + cc.BB_BLACK
                + "\t\t\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
        Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "[5] Schedule" + cc.BB_BLACK
                + "\t\t\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
        Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "[6] Student Subject"
                + cc.BB_BLACK + "\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
        Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "[7] Logout" + cc.BB_BLACK
                + "\t\t\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
        Sout(cc.whiteSpace);
        Sout(cc.whiteLine);
        System.out.print(cc.YELLOW + "\n" + cc.center + "Enter Selection: " + cc.RESET);

        String selected = sc.nextLine();

        switch (selected) {
            case "1" -> StudentView.DisplayStudentMenu();
            case "2" -> CourseView.DisplayCourseView();
            case "3" -> SectionView.DisplaySectionMenu();
            case "4" -> SubjectView.DisplaySubjectMenu();
            case "5" -> ScheduleView.DisplayScheduleView();
            case "6" -> StudentSubject.DisplaySubjectView();
            case "7" -> {
                Sout(cc.redLine);
                Sout(cc.redSpace);
                Sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_RED + "Session Ended"
                        + cc.BB_BLACK + "\t\t\t\t\t\t\t" +
                        " " + cc.BB_RED + " " + cc.RESET);
                Sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t\t\t  " + cc.BR_GREEN + "R E D I R E C T I N G"
                        + cc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + cc.BB_RED + " " + cc.RESET);
                Sout(cc.redSpace);
                Sout(cc.redLine);
                Thread.sleep(1000);
            }
            default -> {
                Sout(cc.redLine);
                Sout(cc.redSpace);
                Sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t\t\t\t " + cc.BR_RED + "Invalid Selection"
                        + cc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + cc.BB_RED + " " + cc.RESET);
                Sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t\t\t\t " + cc.BR_GREEN + "T r y   A g a i n"
                        + cc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + cc.BB_RED + " " + cc.RESET);
                Sout(cc.redSpace);
                Sout(cc.redLine);
                Thread.sleep(500);
                DisplayActionsMenu();
            }
        }

    }
    private  static void Sout(String input)
    {
        System.out.println(input);
    }
}

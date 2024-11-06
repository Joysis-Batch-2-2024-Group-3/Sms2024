package View;

import Controller.AdminController;
import java.util.Scanner;
import Utils.*;
import Model.AdminModel;

public class Admin {
    private static ConsoleColors cc = new ConsoleColors();
    static Scanner sc = new Scanner(System.in);
    static private final AdminController ac = new AdminController();

    public static boolean LoginAdmin() {
        boolean authenticated = false;
        try {
            while (!authenticated) {
                ClearConsole.Cls();
                MainMenu.MainMenuHeader();
                sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN
                        + "L  O  G  I  N" + cc.BB_BLACK + "\t\t\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
                sout(cc.whiteSpace);
                sout(cc.whiteLine);
                System.out.print(cc.YELLOW + "\n" + cc.center + "Enter Username: " + cc.RESET);
                String username = Input.getUserInput();
                System.out.print(cc.YELLOW + "\n" + cc.center + "Enter Password: " + cc.RESET);
                String password = Input.getUserInput();

                authenticated = ac.authenticateAdmin(new AdminModel(username, password));

                if (authenticated) {
                    sout(cc.greenLine);
                    sout(cc.greenSpace);
                    sout(cc.center + cc.BB_GREEN + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN
                            + "Access Granted" + cc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + cc.BB_GREEN + " " + cc.RESET);
                    sout(cc.center + cc.BB_GREEN + " " + cc.BB_BLACK + "\t\t\t\t\t  " + cc.BR_GREEN
                            + "R E D I R E C T I N G" + cc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + cc.BB_GREEN + " " + cc.RESET);
                    sout(cc.greenSpace);
                    sout(cc.greenLine);
                    Thread.sleep(600);
                    return true;
                } else {
                    sout(cc.redLine);
                    sout(cc.redSpace);
                    sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t\t\t" + cc.BR_RED
                            + "Wrong Username or Password" + cc.BB_BLACK + "\t\t\t\t\t" +
                            " " + cc.BB_RED + " " + cc.RESET);
                    sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t  " + cc.BR_RED
                            + "Press [1] to Retry or any other key to Exit" + cc.BB_BLACK + "\t\t" +
                            " " + cc.BB_RED + " " + cc.RESET);
                    sout(cc.redSpace);
                    sout(cc.redLine);

                    System.out.print(cc.YELLOW + "\n" + cc.center + "Enter response: " + cc.RESET);
                    String retry = sc.next();
                    if (!retry.equals("1")) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            sout(cc.redLine);
            sout(cc.redSpace);
            sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t\t\t\t" + cc.BR_RED
                    + "Error Logging in" + cc.BB_BLACK + "\t\t\t\t\t\t" +
                    " " + cc.BB_RED + " " + cc.RESET);
            sout(cc.redSpace);
            sout(cc.redLine);
            System.out.print(cc.YELLOW + "\n" + cc.center + e + cc.RESET);
        }
        return authenticated;
    }

    private static void sout(String input) {
        System.out.println(input);
    }
}

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
                Sout(cc.center + cc.BB_WHITE + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "L  O  G  I  N"
                        + cc.BB_BLACK + "\t\t\t\t\t\t\t " + cc.BB_WHITE + " " + cc.RESET);
                Sout(cc.whiteSpace);
                Sout(cc.whiteLine);
                System.out.print(cc.YELLOW + "\n" + cc.center + "Enter Username: " + cc.RESET);
                String username = Input.getUserInput();
                System.out.print(cc.YELLOW + "\n" + cc.center + "Enter Password: " + cc.RESET);
                String password = Input.getUserInput();

                authenticated = ac.authenticateAdmin(new AdminModel(username, password));

                if (authenticated) {
                    Sout(cc.greenLine);
                    Sout(cc.greenSpace);
                    Sout(cc.center + cc.BB_GREEN + " " + cc.BB_BLACK + "\t\t\t\t\t\t  " + cc.BR_GREEN + "Access Granted"
                            + cc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + cc.BB_GREEN + " " + cc.RESET);
                    Sout(cc.center + cc.BB_GREEN + " " + cc.BB_BLACK + "\t\t\t\t\t  " + cc.BR_GREEN
                            + "R E D I R E C T I N G" + cc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + cc.BB_GREEN + " " + cc.RESET);
                    Sout(cc.greenSpace);
                    Sout(cc.greenLine);
                    Thread.sleep(600);
                    return true;
                } else {
                    Sout(cc.redLine);
                    Sout(cc.redSpace);
                    Sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t\t\t" + cc.BR_RED
                            + "Wrong Username or Password" + cc.BB_BLACK + "\t\t\t\t\t" +
                            " " + cc.BB_RED + " " + cc.RESET);
                    Sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t  " + cc.BR_RED
                            + "Press [1] to Retry or any other key to Exit" + cc.BB_BLACK + "\t\t" +
                            " " + cc.BB_RED + " " + cc.RESET);
                    Sout(cc.redSpace);
                    Sout(cc.redLine);

                    System.out.print(cc.YELLOW + "\n" + cc.center + "Enter response: " + cc.RESET);
                    String retry = sc.next();
                    if (!retry.equals("1")) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            Sout(cc.redLine);
            Sout(cc.redSpace);
            Sout(cc.center + cc.BB_RED + " " + cc.BB_BLACK + "\t\t\t\t\t\t" + cc.BR_RED + "Error Logging in"
                    + cc.BB_BLACK + "\t\t\t\t\t\t" +
                    " " + cc.BB_RED + " " + cc.RESET);
            Sout(cc.redSpace);
            Sout(cc.redLine);
            System.out.print(cc.YELLOW + "\n" + cc.center + e + cc.RESET);
        }
        return authenticated;
    }

    private  static void Sout(String input)
    {
        System.out.println(input);
    }
}

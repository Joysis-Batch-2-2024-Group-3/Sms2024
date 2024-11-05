package View;

import Controller.AdminController;
import java.util.Scanner;
import Utils.*;
import Model.AdminModel;

public class Admin {
    static Scanner sc = new Scanner(System.in);
    static private final AdminController ac = new AdminController();

    public static boolean LoginAdmin() {
        boolean authenticated = false;
        try {
            while (!authenticated) {
                ClearConsole.Cls();
                MainMenu.MainMenuHeader();
                System.out.println("\n|--------------------------LOGIN--------------------------|\n");
                System.out.print("Username: ");
                String username = Input.getUserInput();
                System.out.print("Password: ");
                String password = Input.getUserInput();

                authenticated = ac.authenticateAdmin(new AdminModel(username, password));

                if (authenticated) {
                    System.out.println("-------------Access Granted. Redirecting------------\n");
                    Thread.sleep(600);
                    return true;
                } else {
                    System.out.println("\n----------Wrong Username or Password----------\n");
                    System.out.print("Press [1] to Retry or any other key to Exit: ");
                    String retry = sc.next();
                    if (!retry.equals("1")) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("---------- Error Logging in: " + e + " ----------");
        }
        return authenticated;
    }
}

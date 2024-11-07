/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.*;
import Model.*;
import Utils.*;
import static View.MainMenu.MainMenuHeader;
import java.sql.Date;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author mark
 */
public class StudentView {
    private static StudentController sc = new StudentController();
    private static final IndexController ic = new IndexController();
    private static final CourseController cc = new CourseController();
    private static Scanner scan = new Scanner(System.in);
    private static final SectionController sec = new SectionController();
    private static final ConsoleColors conc = new ConsoleColors();

    public static void DisplayStudentMenu() throws InterruptedException {
        MainMenuHeader();
        Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t  " + conc.BR_GREEN + "S T U D E N T"
                + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
        Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t  " + conc.BR_GREEN + "O P T I O N S"
                + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
        Sout(conc.whiteSpace);
        Sout(conc.whiteLine);
        Sout(conc.whiteSpace);
        Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                + "[1] Display Student List" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
        Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[2] Add New Student"
                + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
        Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[3] Remove Student"
                + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
        Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[4] Update Student"
                + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
        Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[5] Back to Main Menu"
                + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
        Sout(conc.whiteSpace);
        Sout(conc.whiteLine);

        System.out.print(conc.YELLOW + "\n" + conc.center + "Enter Selection: " + conc.RESET);
        String selected = Input.getUserInput();

        switch (selected) {
            case "1" -> StudentView.DisplayStudentOptions();
            case "2" -> addStudentSubMenu();
            case "3" -> removeStudent();
            case "4" -> updateStudent();
            case "5" -> {
                MainMenu.DisplayActionsMenu();
            }
            default -> {
                Sout("Invalid Selection. Try Again: ");
                Thread.sleep(500);
                DisplayStudentMenu();
            }

        }
    }

    public static void DisplayStudentOptions() {
        try {
            ClearConsole.Cls();
            MainMenuHeader();
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t  " + conc.BR_GREEN
                    + "S T U D E N T   S U B   M E N U" + conc.BB_BLACK + "\t\t\t\t " + conc.BB_WHITE + " "
                    + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Search by Section" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Search by Course" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[3] Search by First Name" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[4] Search by Last Name" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[5] Search by Student ID" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[6] Search by specifying a column" + conc.BB_BLACK + "\t\t\t " + conc.BB_WHITE + " "
                    + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[7] Show all Students" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[8] Show all archived students" + conc.BB_BLACK + "\t\t\t\t " + conc.BB_WHITE + " "
                    + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[9] Back to Main Menu" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);

            System.out.print(conc.YELLOW + "\n" + conc.center + "Enter Selection: " + conc.RESET);
            String selection = Input.getUserInput();
            studentSubMenu(selection);
        } catch (Exception e) {
            Sout(conc.redLine);
            Sout(conc.redSpace);
            Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Something went wrong"
                    + conc.BB_BLACK + "\t\t\t\t\t\t" +
                    " " + conc.BB_RED + " " + conc.RESET);
            Sout(conc.redSpace);
            Sout(conc.redLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + e.getMessage() + conc.RESET);
        }

    }

    private static void studentSubMenu(String selection) throws InterruptedException {
        StudentModel sm = new StudentModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();

        switch (selection) {
            case "1" -> {
                Sout(conc.yellowLine);
                Sout(conc.yellowSpace);
                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                        + "Enter Section Name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_YELLOW + " " + conc.RESET);
                Sout(conc.yellowSpace);
                Sout(conc.yellowLine);
                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                String value = Input.getUserInput();
                sc.displayStudentbySection(value, sm, cm, secm);
                GoBack();
            }
            case "2" -> {
                Sout(conc.yellowLine);
                Sout(conc.yellowSpace);
                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                        + "Enter Course Name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_YELLOW + " " + conc.RESET);
                Sout(conc.yellowSpace);
                Sout(conc.yellowLine);
                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                String value = Input.getUserInput();
                sc.displayStudentbyCourse(value, sm, cm, secm);
                GoBack();
            }
            case "3" -> {
                Sout(conc.yellowLine);
                Sout(conc.yellowSpace);
                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                        + "Enter First Name" + conc.BB_BLACK + "\t\t\t\t\t" +
                        " " + conc.BB_YELLOW + " " + conc.RESET);
                Sout(conc.yellowSpace);
                Sout(conc.yellowLine);
                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                String value = Input.getUserInput();
                sc.displayStudentbyFName(value, sm, cm, secm);
                GoBack();
            }
            case "4" -> {
                Sout(conc.yellowLine);
                Sout(conc.yellowSpace);
                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                        + "Enter Last Name" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                        " " + conc.BB_YELLOW + " " + conc.RESET);
                Sout(conc.yellowSpace);
                Sout(conc.yellowLine);
                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                String value = Input.getUserInput();
                sc.displayStudentbyLname(value, sm, cm, secm);
                GoBack();
            }
            case "5" -> {
                Sout(conc.yellowLine);
                Sout(conc.yellowSpace);
                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                        + "Enter Student ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_YELLOW + " " + conc.RESET);
                Sout(conc.yellowSpace);
                Sout(conc.yellowLine);
                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                String value = Input.getUserInput();
                sc.displayStudentbyStudent_ID(value, sm, cm, secm);
                GoBack();
            }
            case "6" -> {
                Sout(conc.whiteLine);
                Sout(conc.whiteSpace);
                Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_WHITE
                        + "Choose from this specific column" + conc.BB_BLACK + "\t\t\t" +
                        " " + conc.BB_WHITE + " " + conc.RESET);
                Sout(conc.whiteSpace);
                Sout(conc.whiteLine);
                Sout("Choose from this specific column: ");
                for (String column : StudentController.validColumns) {
                    System.out.print("| " + column + " | ");
                }

                String columnName = null;
                boolean validColumn = false;
                while (!validColumn) {
                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                            + "Enter Column Name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    columnName = Input.getUserInput();
                    // Check if the input columnName is valid
                    if (Arrays.asList(StudentController.validColumns).contains(columnName)) {
                        validColumn = true; // Exit loop if valid
                    } else {
                        Input.COut("Invalid column name. Please choose from the valid columns.");
                    }
                }
                Sout("\n" + conc.yellowLine);
                Sout(conc.yellowSpace);
                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                        + "Enter Value" + conc.BB_BLACK + "\t\t\t\t\t\t\t\t" +
                        " " + conc.BB_YELLOW + " " + conc.RESET);
                Sout(conc.yellowSpace);
                Sout(conc.yellowLine);
                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                String columnValue = Input.getUserInput();
                Sout("Searching for " + columnValue + " in " + columnName);
                sc.filterStudent(columnName, columnValue, sm, cm, secm);
                GoBack();

            }
            case "7" -> {
                sc.displayStudents(sm, cm, secm);
                GoBack();
            }
            case "8" -> {
                sc.displayArchivedStudents(sm, cm, secm);
                GoBack();
            }
            case "9" -> {
                Sout("Returning to Student Menu...");
                Thread.sleep(700);
                DisplayStudentMenu();
            }
            default -> {
                ClearConsole.Cls();
                DisplayStudentOptions();
            }
        }

    }

    static private void addStudentSubMenu() throws InterruptedException {
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        StudentModel sm = new StudentModel();
        ClearConsole.Cls();

        MainMenuHeader();
        Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t  " + conc.BR_GREEN
                + "A D D   S T U D E N T" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
        Sout(conc.whiteSpace);
        Sout(conc.whiteLine);

        // First Name Input
        Sout("\n" + conc.yellowLine);
        Sout(conc.yellowSpace);
        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW + "Enter First Name"
                + conc.BB_BLACK + "\t\t\t\t\t\t" +
                " " + conc.BB_YELLOW + " " + conc.RESET);
        Sout(conc.yellowSpace);
        Sout(conc.yellowLine);
        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
        String firstName = Input.getUserInput();
        sm.setStudentFirstname(firstName);

        // Last Name Input
        Sout("\n" + conc.yellowLine);
        Sout(conc.yellowSpace);
        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t    " + conc.BR_YELLOW + "Enter Last Name"
                + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                " " + conc.BB_YELLOW + " " + conc.RESET);
        Sout(conc.yellowSpace);
        Sout(conc.yellowLine);
        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
        String lastName = Input.getUserInput();
        sm.setStudentLastname(lastName);

        // Birth Date Input with Validation
        boolean validDate = false;
        while (!validDate) {
            Sout("\n" + conc.yellowLine);
            Sout(conc.yellowSpace);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                    + "Enter Birth Date (yyyy-mm-dd)" + conc.BB_BLACK + "\t\t\t\t" +
                    " " + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.yellowSpace);
            Sout(conc.yellowLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
            String birthDate = Input.getUserInput();
            if (ic.isValidDate(birthDate)) {
                sm.setStudentDob(Date.valueOf(birthDate));
                validDate = true;
            } else {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                        + "Invalid date format" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                        + "Enter in yyyy-mm-dd format" + conc.BB_BLACK + "\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
            }
        }

        // Sex Input with Validation
        boolean validSex = false;
        while (!validSex) {
            Sout("\n" + conc.yellowLine);
            Sout(conc.yellowSpace);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                    + "Enter Sex (F or M)" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                    " " + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.yellowSpace);
            Sout(conc.yellowLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
            String input = Input.getUserInput().trim().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'M' || input.charAt(0) == 'F')) {
                sm.setStudentSex(input.charAt(0));
                validSex = true;
            } else {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Invalid input"
                        + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                        + "Enter F or M only" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
            }
        }

        // Course ID Input with Validation
        int courseId;
        cc.displayAllCourse(cm);
        boolean validCourseID = false;
        while (!validCourseID) {
            Sout("\n" + conc.yellowLine);
            Sout(conc.yellowSpace);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                    + "Enter Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                    " " + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.yellowSpace);
            Sout(conc.yellowLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
            courseId = scan.nextInt();
            if (cc.isValidCourse("course_id", courseId)) {
                sm.setStudentCourse(courseId);
                validCourseID = true;
            } else {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                        + "Invalid Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Please try again"
                        + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
            }
        }

        // Filter and Display Sections by Course ID
        sec.filterSection("course_tbl.course_id", sm.getStudentCourse(), secm, cm);

        // Section ID Input with Validation
        boolean validSectionId = false;
        while (!validSectionId) {
            Sout("\n" + conc.yellowLine);
            Sout(conc.yellowSpace);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                    + "Enter Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                    " " + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.yellowSpace);
            Sout(conc.yellowLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
            int sectionId = scan.nextInt();
            if (sec.isValidSectionValue("section_id", sectionId)) {
                sm.setStudentSection(sectionId);
                validSectionId = true;
            } else {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                        + "Invalid Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Please try again"
                        + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
            }
        }

        // Add Student and Display All Students
        sc.addStudent(sm, cm, secm);
        sc.displayStudents(sm, cm, secm);
        Input.HoldState();

        try {
            MainMenu.DisplayActionsMenu();
        } catch (InterruptedException e) {
            Sout("Error: " + e.getMessage());
        }
    }

    private static void GoBack() {
        try {
            Input.HoldState();
            DisplayStudentMenu();
        } catch (Exception e) {
            Input.COut("Something went wrong: " + e.getMessage());
        }
    }

    public static void removeStudent() {
        try {
            LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
            boolean isValidStudent = false;
            while (!isValidStudent) {
                Sout("\n" + conc.yellowLine);
                Sout(conc.yellowSpace);
                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                        + "Enter Student's First Name" + conc.BB_BLACK + "\t\t\t\t\t" +
                        " " + conc.BB_YELLOW + " " + conc.RESET);
                Sout(conc.yellowSpace);
                Sout(conc.yellowLine);
                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                String firstName = scan.nextLine();
                Sout("\n" + conc.yellowLine);
                Sout(conc.yellowSpace);
                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                        + "Enter Student's Last Name" + conc.BB_BLACK + "\t\t\t\t\t" +
                        " " + conc.BB_YELLOW + " " + conc.RESET);
                Sout(conc.yellowSpace);
                Sout(conc.yellowLine);
                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                String lastName = scan.nextLine();
                if (sc.isValidStudentName(firstName, lastName)) {
                    isValidStudent = true;
                    String name = firstName + " " + lastName;
                    values.put("student_name", name);
                } else {
                    Sout(conc.redLine);
                    Sout(conc.redSpace);
                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                            + "Invalid student name" + conc.BB_BLACK + "\t\t\t\t\t" +
                            " " + conc.BB_RED + " " + conc.RESET);
                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                            + "Please enter a valid student name." + conc.BB_BLACK + "\t\t\t" +
                            " " + conc.BB_RED + " " + conc.RESET);
                    Sout(conc.redSpace);
                    Sout(conc.redLine);
                }
            }
            boolean isValidSectionName = false;
            while (!isValidSectionName) {
                Sout("\n" + conc.yellowLine);
                Sout(conc.yellowSpace);
                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                        + "Enter Student's Section" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_YELLOW + " " + conc.RESET);
                Sout(conc.yellowSpace);
                Sout(conc.yellowLine);
                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                String sectionName = scan.nextLine();
                if (sec.isValidSectionValue("section_name", sectionName)) {
                    isValidSectionName = true;
                    values.put("section_name", sectionName);
                } else {
                    Sout(conc.redLine);
                    Sout(conc.redSpace);
                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                            + "Invalid section name" + conc.BB_BLACK + "\t\t\t\t\t" +
                            " " + conc.BB_RED + " " + conc.RESET);
                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                            + "Please enter a valid section name." + conc.BB_BLACK + "\t\t\t" +
                            " " + conc.BB_RED + " " + conc.RESET);
                    Sout(conc.redSpace);
                    Sout(conc.redLine);
                }
            }
            sc.dropStudent(values);
            DisplayStudentMenu();
        } catch (Exception e) {
            Input.COut("Something went wrong: " + e.getMessage());
        }
    }

    static private void updateStudent() {
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        StudentModel sm = new StudentModel();
        sc.displayStudents(sm, cm, secm);

        Sout("\n" + conc.yellowLine);
        Sout(conc.yellowSpace);
        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW + "Enter Student ID"
                + conc.BB_BLACK + "\t\t\t\t\t\t" +
                " " + conc.BB_YELLOW + " " + conc.RESET);
        Sout(conc.yellowSpace);
        Sout(conc.yellowLine);
        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
        int studentId = scan.nextInt();
        sm.setStudentId(studentId); // Set the student ID in the model
        try {
            updateStudentField(studentId);
        } catch (InterruptedException e) {
            Sout(conc.redLine);
            Sout(conc.redSpace);
            Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Invalid section name"
                    + conc.BB_BLACK + "\t\t\t\t\t" +
                    " " + conc.BB_RED + " " + conc.RESET);
            Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                    + "Please enter a valid section name." + conc.BB_BLACK + "\t\t\t" +
                    " " + conc.BB_RED + " " + conc.RESET);
            Sout(conc.redSpace);
            Sout(conc.redLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
            Input.COut(e.getMessage());
        }
    }

    static private void updateStudentField(int StudentID) throws InterruptedException {

        LinkedHashMap<String, Object> value = new LinkedHashMap<>();
        boolean updatingStudent = true;

        while (updatingStudent) {
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "Select to Update Student" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Update Firstname" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Update Lastname " + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[3] Update Date of Birth" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[4] Update Sex (F/M)" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[5] Update Year Level" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[6] Update Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[7] Update Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[8] Update archived"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[9] Finish Update"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout("\n" + conc.yellowLine);
            Sout(conc.yellowSpace);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                    + "Choose an option" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                    " " + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.yellowSpace);
            Sout(conc.yellowLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
            int choice = scan.nextInt();
            scan.nextLine(); // Clear newline character
            try {
                switch (choice) {
                    case 1 -> {
                        Sout("\n" + conc.yellowLine);
                        Sout(conc.yellowSpace);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                + "Enter new firstname" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.yellowSpace);
                        Sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        String newFirstname = scan.nextLine();
                        value.put("first_name", newFirstname);
                    }
                    case 2 -> {
                        Sout("\n" + conc.yellowLine);
                        Sout(conc.yellowSpace);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                + "Enter new lastname" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.yellowSpace);
                        Sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        String newLastname = scan.nextLine();
                        value.put("last_name", newLastname);
                    }
                    case 3 -> {
                        boolean validDate = false;
                        while (!validDate) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Birth Date (yyyy-mm-dd)" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            String birthDate = scan.nextLine();
                            if (ic.isValidDate(birthDate)) {
                                value.put("birth_date", Date.valueOf(birthDate));
                                validDate = true;
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid date format" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                        + "Enter in yyyy-mm-dd format" + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                    }
                    case 4 -> {
                        Sout("\n" + conc.yellowLine);
                        Sout(conc.yellowSpace);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                + "Enter new Sex (F or M)" + conc.BB_BLACK + "\t\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.yellowSpace);
                        Sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        char newSex = scan.nextLine().toUpperCase().charAt(0);
                        if (newSex == 'M' || newSex == 'F') {
                            value.put("sex", newSex);
                        } else {
                            Sout(conc.redLine);
                            Sout(conc.redSpace);
                            Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                    + "Invalid input" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                    " " + conc.BB_RED + " " + conc.RESET);
                            Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                    + "Enter F or M only" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                    " " + conc.BB_RED + " " + conc.RESET);
                            Sout(conc.redSpace);
                            Sout(conc.redLine);
                        }
                    }
                    case 5 -> {
                        Sout("\n" + conc.yellowLine);
                        Sout(conc.yellowSpace);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                + "Enter new year level" + conc.BB_BLACK + "\t\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.yellowSpace);
                        Sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        int newYearLevel = scan.nextInt();
                        scan.nextLine(); // Clear newline character
                        value.put("year_level", newYearLevel);
                    }
                    case 6 -> {
                        int courseId;
                        cc.displayAllCourse(new CourseModel());
                        boolean validCourseID = false;
                        while (!validCourseID) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                value.put("course_id", courseId);
                                validCourseID = true;
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }

                    }
                    case 7 -> {
                        int sectionId;
                        sec.displayAllSection(new SectionModel(), new CourseModel());
                        boolean validSectionID = false;
                        while (!validSectionID) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            sectionId = scan.nextInt();
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                value.put("section_id", sectionId);
                                validSectionID = true;
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                    }
                    case 8 -> {
                        Sout("\n" + conc.yellowLine);
                        Sout(conc.yellowSpace);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                                + "Toggle Archived Status" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                                + "\tCurrently (" + value.getOrDefault("archived", "No")
                                + ")." + conc.BB_BLACK + "\t\t\t\t\t\t\t" + " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.yellowSpace);
                        Sout("" + conc.yellowLine);
                        Sout(conc.yellowSpace);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                + "Enter 'Y' to Archive or 'N' to Unarchive" + conc.BB_BLACK + "\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.yellowSpace);
                        Sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        char archivedChoice = scan.nextLine().toUpperCase().charAt(0);
                        if (archivedChoice == 'Y') {
                            value.put("archived", true);
                        } else if (archivedChoice == 'N') {
                            value.put("archived", false);
                        } else {
                            Sout(conc.redLine);
                            Sout(conc.redSpace);
                            Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                    + "Invalid input" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                    " " + conc.BB_RED + " " + conc.RESET);
                            Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                    + "Please enter 'Y' or 'N'" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                    " " + conc.BB_RED + " " + conc.RESET);
                            Sout(conc.redSpace);
                            Sout(conc.redLine + "\n");
                        }
                    }
                    case 9 -> {
                        if (!value.isEmpty()) {
                            sc.updateStudent(value, StudentID);
                        } else {
                            Input.COut("No fields to update.");
                        }
                        updatingStudent = false; // Exit the loop after finishing updates
                        DisplayStudentMenu();
                    }
                    default -> Input.COut("Invalid option. Please try again.");

                }
            } catch (InputMismatchException e) {
                Input.COut("Invalid input. Please enter a valid option.");
                scan.nextLine(); // Clear invalid input
            }
        }
    }

    private  static void Sout(String input)
    {
        System.out.println(input);
    }

}

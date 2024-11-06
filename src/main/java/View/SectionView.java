/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.*;
import Model.*;
import Utils.*;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author mark
 */
public class SectionView {
    private static final CourseController cc = new CourseController();
    private static Scanner scan = new Scanner(System.in);
    private static final SectionController sec = new SectionController();
    private static final ConsoleColors conc = new ConsoleColors();

    static void DisplaySectionMenu() {
        SectionModel sm = new SectionModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            ClearConsole.Cls();
            MainMenu.MainMenuHeader();
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_GREEN
                    + "S E C T I O N   M E N U" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Display All Sections" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Search for Section/s" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[3] Add Section"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[4] Delete Section by Name" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[5] Edit Section"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[6] Back to Main Menu" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);

            System.out.print(conc.YELLOW + "\n" + conc.center + "Choose an option: " + conc.RESET);
            String choicestr = Input.getUserInput();
            int choice = Input.parseIfPossible(choicestr);

            while (choice == 0) {
                System.out.print("Invalid Choice. Try Again: ");
                choicestr = Input.getUserInput();
                choice = Input.parseIfPossible(choicestr);
            }

            try {
                switch (choice) {
                    case 1 -> {
                        ClearConsole.Cls();
                        sec.displayAllSection(sm, cm);
                        Input.HoldState();
                    }

                    case 2 -> {
                        Sout("\n" + conc.yellowLine);
                        Sout(conc.yellowSpace);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                + "Enter the section name or substring" + conc.BB_BLACK + "\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.yellowSpace);
                        Sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        String searchValue = scan.nextLine();
                        sec.filterSection("section_tbl.section_name", searchValue, sm, cm);
                        Input.HoldState();
                    }
                    case 3 -> {
                        ClearConsole.Cls();
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Adding new section
                        Sout("\n" + conc.yellowLine);
                        Sout(conc.yellowSpace);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                + "Enter Section Name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.yellowSpace);
                        Sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        String sectionName = scan.nextLine();
                        values.put("`section_name`", sectionName);
                        boolean isValidCourseId = false;
                        cc.displayAllCourse(cm);
                        while (!isValidCourseId) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int courseId = scan.nextInt();

                            if (cc.isValidCourse("course_id", courseId)) {
                                values.put("`course_id`", courseId);
                                sm.setCourse_id(courseId);
                                isValidCourseId = true;
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
                        sm.setSectionName(sectionName);

                        if (!sec.sectionConflictChecker(values)) {
                            sec.addSection(sm);
                            sec.displayAllSection(sm, cm);
                        } else {
                            Input.COut("Section conflict detected. Please enter new section details.");
                        }
                    }
                    case 4 -> {
                        // Deleting a section by name
                        ClearConsole.Cls();
                        boolean isValidSectionName = false;
                        while (!isValidSectionName) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Section Name to Delete" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            String sectionName = Input.getUserInput();
                            if (sec.isValidSectionValue("section_name", sectionName)) {
                                sec.deleteSection(sectionName);
                                sec.displayAllSection(sm, cm);
                                Input.HoldState();
                                isValidSectionName = true;
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid section name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_RED
                                        + "Please enter a valid section name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                    }
                    case 5 -> {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Editing a section
                        ClearConsole.Cls();
                        sec.displayAllSection(sm, cm);
                        boolean isSectionValid = false;
                        while (!isSectionValid) {
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
                                values.put("`section_id`", sectionId);
                                updateSectionField(sectionId);
                                isSectionValid = true;

                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please enter a valid section ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                                Input.COut("Invalid section ID. Please enter a valid section ID.");
                            }
                        }
                    }
                    case 6 -> MainMenu.DisplayActionsMenu();

                    default -> Input.COut("Invalid option. Please try again.");
                }

            } catch (InputMismatchException e) {
                Input.COut("Invalid input. Please enter a valid option.");
                scan.nextLine(); // Clear invalid input
            } catch (Exception e) {
                Input.COut("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    static private void updateSectionField(int sectionID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingSection = true;

        while (updatingSection) {
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "Select to Update Section" + conc.BB_BLACK + "\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Edit Section Name" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Edit Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE + "[3] Finish Update"
                    + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);

            System.out.print(conc.YELLOW + "\n" + conc.center + "Choose an option: " + conc.RESET);
            int choice = scan.nextInt();
            scan.nextLine(); // Clear newline character

            try {
                switch (choice) {
                    case 1 -> {
                        Sout("\n" + conc.yellowLine);
                        Sout(conc.yellowSpace);
                        Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                                + "Enter the new Section Name" + conc.BB_BLACK + "\t\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        Sout(conc.yellowSpace);
                        Sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        String newSectionName = scan.nextLine();
                        values.put("section_name", newSectionName);
                    }
                    case 2 -> {
                        // Display courses and get the new Course ID
                        cc.displayAllCourse(new CourseModel());
                        boolean isValidCourseId = false;
                        while (!isValidCourseId) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter the new Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int newCourseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", newCourseId)) {
                                isValidCourseId = true;
                                values.put("course_id", newCourseId);
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
                    case 3 -> {
                        if (!values.isEmpty()) {
                            Input.COut("Section updated successfully.");

                            sec.updateSection(values, sectionID);
                            Sout(conc.greenLine);
                            Sout(conc.greenSpace);
                            Sout(conc.center + conc.BB_GREEN + " " + conc.BB_BLACK + "\t\t\t\t\t  " + conc.BR_GREEN
                                    + "Section Updated Successfully." + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_GREEN + " " + conc.RESET);
                            Sout(conc.greenSpace);
                            Sout(conc.greenLine);
                        }
                        updatingSection = false;
                    }
                    default -> {
                        Sout(conc.redLine);
                        Sout(conc.redSpace);
                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                + "Invalid Option" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_RED + " " + conc.RESET);
                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_RED + " " + conc.RESET);
                        Sout(conc.redSpace);
                        Sout(conc.redLine);
                    }
                }
            } catch (InputMismatchException e) {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Invalid Input"
                        + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Please try again"
                        + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
                scan.nextLine(); // Clear invalid input
            }
        }
    }

    private static void Sout(String input) {
        System.out.println(input);
    }
}

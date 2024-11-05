/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.*;
import Model.*;
import Model.*;
import Utils.ClearConsole;
import Utils.Input;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author mark
 */
public class SectionView {
    private static StudentController sc = new StudentController();
    private static final IndexController ic = new IndexController();
    private static final CourseController cc = new CourseController();
    private static Scanner scan = new Scanner(System.in);
    private static final SectionController sec = new SectionController();
    
    static void DisplaySectionMenu()
    {
         SectionModel sm = new SectionModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            ClearConsole.Cls();
            System.out.println("----------------------Section Menu------------------\n");
            System.out.println(" ___________________________________________________");
            System.out.println("|                                                   |");
            System.out.println("| 1. Display All Sections                           |");
            System.out.println("| 2. Search for Section/s                           |");
            System.out.println("| 3. Add Section                                    |");
            System.out.println("| 4. Delete Section by Name                         |");
            System.out.println("| 5. Edit Section                                   |");
            System.out.println("| 6. Back to Main Menu                              |");
            System.out.println("|___________________________________________________|\n");
            System.out.print("Choose an option: ");
            String choicestr = Input.getUserInput();
            int choice = Input.parseIfPossible(choicestr);
            
            while(choice == 0)
            {
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
                        System.out.print("\nEnter the section name or substring: ");
                        String searchValue = scan.nextLine();
                        sec.filterSection("section_tbl.section_name", searchValue, sm, cm);
                        Input.HoldState();
                    }
                    case 3 -> {
                        ClearConsole.Cls();
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Adding new section
                        System.out.print("\nEnter Section Name: ");
                        String sectionName = scan.nextLine();
                        values.put("`section_name`", sectionName);
                        boolean isValidCourseId = false;
                        cc.displayAllCourse(cm);
                        while (!isValidCourseId) {
                            System.out.print("Enter Course ID: ");
                            int courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                values.put("`course_id`", courseId);
                                sm.setCourse_id(courseId);
                                isValidCourseId = true;
                            } else {
                                Input.COut("Invalid Course ID. Please try again.");
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
                            System.out.print("\nEnter the Section Name to delete: ");
                            String sectionName = Input.getUserInput();
                            if (sec.isValidSectionValue("section_name", sectionName)) {
                                sec.deleteSection(sectionName);
                                sec.displayAllSection(sm, cm);
                                Input.HoldState();
                                isValidSectionName = true;
                            } else {
                                Input.COut("Invalid section name. Please enter a valid section name.");
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
                            System.out.print("Enter a section ID: ");
                            int sectionId = scan.nextInt();
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                values.put("`section_id`", sectionId);
                                updateSectionField(sectionId);
                                isSectionValid = true;

                            }else{
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
    
    static  private void updateSectionField(int sectionID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingSection = true;

        while (updatingSection) {
            Input.HeaderBox("Select To Update Section");
            System.out.println("1. Edit Section Name");
            System.out.println("2. Edit Course ID");
            System.out.println("3. Finish Update");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine(); // Clear newline character

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter the new Section Name: ");
                        String newSectionName = scan.nextLine();
                        values.put("section_name", newSectionName);
                    }
                    case 2 -> {
                        // Display courses and get the new Course ID
                        cc.displayAllCourse(new CourseModel());
                        boolean isValidCourseId = false;
                        while (!isValidCourseId) {
                            System.out.print("Enter the new Course ID: ");
                            int newCourseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", newCourseId)) {
                                isValidCourseId = true;
                                values.put("course_id", newCourseId);
                            } else {
                                Input.COut("Invalid Course ID. Please try again.");
                            }
                        }
                    }
                    case 3 -> {
                                if(!values.isEmpty()){
                                    sec.updateSection(values, sectionID);
                                    Input.COut("Section updated successfully.");
                                }
                        updatingSection = false;
                    }
                    default -> Input.COut("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                Input.COut("Invalid input. Please enter a valid option.");
                scan.nextLine(); // Clear invalid input
            }
        }
    }
}

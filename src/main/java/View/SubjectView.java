/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.*;
import Controller.*;
import Utils.ClearConsole;
import Utils.Input;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author mark
 */
public class SubjectView {
    private static StudentController sc = new StudentController();
    private static final IndexController ic = new IndexController();
    private static final CourseController cc = new CourseController();
    private static Scanner scan = new Scanner(System.in);
    private static final SubjectController sub = new SubjectController();
    private static final SectionController sec = new SectionController();
    
    static void DisplaySubjectMenu()
    {
           SubjectModel subm = new SubjectModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n-----------------Subject Menu------------------\n");
            System.out.println(" ________________________________________________");
            System.out.println("|                                                |");
            System.out.println("| 1. Display All Subjects                        |");
            System.out.println("| 2. Search Subject by Course                    |");
            System.out.println("| 3. Add Subject                                 |");
            System.out.println("| 4. Delete Subject by Name                      |");
            System.out.println("| 5. Edit Subject                                |");
            System.out.println("| 6. Back to Main Menu                           |");
            System.out.println("|________________________________________________|\n");
            System.out.print("Choose an option: ");

            try {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                        ClearConsole.Cls();
                        sub.displayAllSubject(subm, cm);
                        Input.HoldState();
                    }
                    case 2 -> {
                        System.out.print("\nEnter the course name or substring: ");
                        String searchValue = scan.nextLine();
                        sub.displaySubjectByCourse("course_tbl.course_name", searchValue, subm, cm);
                        Input.HoldState();
                    }
                    case 3 -> {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Adding new subject
                        System.out.print("\nEnter Subject Name: ");
                        String subjectName = scan.nextLine();
                        values.put("`subject_name`", subjectName);

                        boolean validCourse = false;
                        cc.displayAllCourse(cm);
                        while (!validCourse) {
                            System.out.print("Enter Course ID: ");
                            int courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                subm.setCourse_id(courseId);
                                values.put("course_id", courseId);
                                validCourse = true;
                            } else {
                                Input.COut("Invalid course id, please check all available courses");
                            }
                        }
                        subm.setSubject_name(subjectName);
                        if (!sub.subjectConflictChecker(values)) {
                            sub.addSubject(subm);
                            sub.displayAllSubject(subm, cm);
                            Input.HoldState();
                        } else {
                            Input.COut("Subject conflict detected. Please enter new subject details.");
                        }
                    }
                    case 4 -> {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Option to delete a subject by name
                        boolean isSubjectValid = false;
                        while (!isSubjectValid) {
                            System.out.print("\nEnter Subject Name: ");
                            String subjectName = scan.nextLine();
                            System.out.println(subjectName);
                            if (sub.isValidSubjectValue("subject_name", subjectName)) {
                                isSubjectValid = true;
                                values.put("subject_name", subjectName);
                            } else {
                                Input.COut("Invalid subject name. Please enter a valid subject name.");
                            }
                        }
                        cc.displayAllCourse(cm);
                        boolean isCourseValid = false;
                        while (!isCourseValid) {
                            System.out.print("Enter Course ID: ");
                            int courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                isCourseValid = true;
                                values.put("course_id", courseId);
                            } else {
                                System.out.println("Invalid course id. Please enter a valid course id.");
                            }
                        }
                        sub.deleteSubject(values);
                        sub.displayAllSubject(subm, cm);
                        Input.HoldState();
                    }
                    case 5 -> {
                             boolean isValidSubjectID = false;
                             int subjectId =0;
                             sub.displayAllSubject(subm, cm);
                             while (!isValidSubjectID) {
                                 System.out.print("Enter Subject ID: ");
                                 subjectId = scan.nextInt();
                                 if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                     isValidSubjectID = true;
                                 } else {
                                     System.out.println("Invalid subject id. Please enter a valid subject id.");
                                 }

                             }
                        updateSubjectField(subjectId);

                    }

                    case 6 -> MainMenu.DisplayActionsMenu();
                    default -> Input.COut("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                Input.COut("Invalid input. Please enter a number.");
                scan.nextLine();
            } catch (Exception e) {
                Input.COut("An unexpected error occurred: " + e.getMessage());
            }
        }  
    }
    
    static private void updateSubjectField(int subjectID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingSubject = true;

        while (updatingSubject) {
            System.out.println("\n-----------Select To Update Subject------------\n");
            System.out.println(" ________________________________________________");
            System.out.println("|                                                |");
            System.out.println("| 1. Update Subject Name                         |");
            System.out.println("| 2. Update Course ID                            |");
            System.out.println("| 3. Finish Update                               |");
            System.out.println("|________________________________________________|\n");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine(); // Clear newline character

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new subject name: ");
                        String newSubjectName = scan.nextLine();
                        values.put("subject_name", newSubjectName);
                    }
                    case 2 -> {
                        cc.displayAllCourse(new CourseModel());
                        boolean validCourseID = false;
                        while (!validCourseID) {
                            System.out.print("Enter new Course ID: ");
                            int courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                values.put("course_id", courseId);
                                Input.HoldState();
                                validCourseID = true;
                            } else {
                                Input.COut("Invalid Course ID. Please try again.");
                            }
                        }
                    }
                    case 3 -> {
                        if (!values.isEmpty()) {
                            sub.updateSubject(values, subjectID);
                        } else {
                            Input.COut("No fields to update.");
                        }
                        updatingSubject = false; // Exit loop
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

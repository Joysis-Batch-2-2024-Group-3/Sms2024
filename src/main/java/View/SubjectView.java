/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.*;
import Controller.*;
import Utils.ClearConsole;
import Utils.ConsoleColors;
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
    private static final ConsoleColors conc = new ConsoleColors();

    static void DisplaySubjectMenu() {
        SubjectModel subm = new SubjectModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            ClearConsole.Cls();
            MainMenu.MainMenuHeader();
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_GREEN
                    + "S U B J E C T   M E N U" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.whiteSpace);
            sout(conc.whiteLine);
            sout(conc.whiteSpace);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Display All Subjects" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Search Subject by Course" + conc.BB_BLACK + "\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[3] Add Subject"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[4] Delete Subject by Name" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[5] Edit Subject"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[6] Back to Main Menu" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.whiteSpace);
            sout(conc.whiteLine);

            System.out.print(conc.YELLOW + "\n" + conc.center + "Choose an option: " + conc.RESET);

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
                        sout("\n" + conc.yellowLine);
                        sout(conc.yellowSpace);
                        sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                + "Enter the course name or substring" + conc.BB_BLACK + "\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        sout(conc.yellowSpace);
                        sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        String searchValue = scan.nextLine();
                        sub.displaySubjectByCourse("course_tbl.course_name", searchValue, subm, cm);
                        Input.HoldState();
                    }
                    case 3 -> {
                        ClearConsole.Cls();
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Adding new subject
                        sout("\n" + conc.yellowLine);
                        sout(conc.yellowSpace);
                        sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                + "Enter subject name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        sout(conc.yellowSpace);
                        sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        String subjectName = scan.nextLine();
                        values.put("`subject_name`", subjectName);

                        boolean validCourse = false;
                        cc.displayAllCourse(cm);
                        while (!validCourse) {
                            sout("\n" + conc.yellowLine);
                            sout(conc.yellowSpace);
                            sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            sout(conc.yellowSpace);
                            sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                subm.setCourse_id(courseId);
                                values.put("course_id", courseId);
                                validCourse = true;
                            } else {
                                sout(conc.redLine);
                                sout(conc.redSpace);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid course id" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                        + "Please check all available courses." + conc.BB_BLACK + "\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.redSpace);
                                sout(conc.redLine);
                            }
                        }
                        subm.setSubject_name(subjectName);
                        if (!sub.subjectConflictChecker(values)) {
                            sub.addSubject(subm);
                            sub.displayAllSubject(subm, cm);
                            Input.HoldState();
                        } else {
                            sout(conc.redLine);
                            sout(conc.redSpace);
                            sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                                    + "Subject conflict detected" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                    " " + conc.BB_RED + " " + conc.RESET);
                            sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                    + "Please enter new subject details." + conc.BB_BLACK + "\t\t\t\t\t" +
                                    " " + conc.BB_RED + " " + conc.RESET);
                            sout(conc.redSpace);
                            sout(conc.redLine);
                        }
                    }
                    case 4 -> {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Option to delete a subject by name
                        boolean isSubjectValid = false;
                        while (!isSubjectValid) {
                            sout("\n" + conc.yellowLine);
                            sout(conc.yellowSpace);
                            sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Subject Name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            sout(conc.yellowSpace);
                            sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            String subjectName = scan.nextLine();
                            sout(subjectName);
                            if (sub.isValidSubjectValue("subject_name", subjectName)) {
                                isSubjectValid = true;
                                values.put("subject_name", subjectName);
                            } else {
                                sout(conc.redLine);
                                sout(conc.redSpace);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid subject name" + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_RED
                                        + "Please enter a valid subject name." + conc.BB_BLACK + "\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.redSpace);
                                sout(conc.redLine);
                            }
                        }
                        cc.displayAllCourse(cm);
                        boolean isCourseValid = false;
                        while (!isCourseValid) {
                            sout("\n" + conc.yellowLine);
                            sout(conc.yellowSpace);
                            sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            sout(conc.yellowSpace);
                            sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                isCourseValid = true;
                                values.put("course_id", courseId);
                            } else {
                                sout(conc.redLine);
                                sout(conc.redSpace);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid course id" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                        + "Please enter a valid course id." + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.redSpace);
                                sout(conc.redLine);
                            }
                        }
                        sub.deleteSubject(values);
                        sub.displayAllSubject(subm, cm);
                        Input.HoldState();
                    }
                    case 5 -> {
                        boolean isValidSubjectID = false;
                        int subjectId = 0;
                        sub.displayAllSubject(subm, cm);
                        while (!isValidSubjectID) {
                            sout("\n" + conc.yellowLine);
                            sout(conc.yellowSpace);
                            sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Subject ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            sout(conc.yellowSpace);
                            sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            subjectId = scan.nextInt();
                            if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                isValidSubjectID = true;
                            } else {
                                sout(conc.redLine);
                                sout(conc.redSpace);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid Subject ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please enter a valid subject id." + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.redSpace);
                                sout(conc.redLine);
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
            ClearConsole.Cls();
            sout(conc.whiteLine);
            sout(conc.whiteSpace);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_GREEN + "U P D A T E"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_GREEN + "S U B J E C T"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.whiteSpace);
            sout(conc.whiteLine);
            sout(conc.whiteSpace);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Update Subject Name" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Update Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[3] Finish Update"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            sout(conc.whiteSpace);
            sout(conc.whiteLine);

            System.out.print(conc.YELLOW + "\n" + conc.center + "Choose an option: " + conc.RESET);

            int choice = scan.nextInt();
            scan.nextLine(); // Clear newline character

            try {
                switch (choice) {
                    case 1 -> {
                        sout("\n" + conc.yellowLine);
                        sout(conc.yellowSpace);
                        sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                                + "Enter new subject name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_YELLOW + " " + conc.RESET);
                        sout(conc.yellowSpace);
                        sout(conc.yellowLine);
                        System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                        String newSubjectName = scan.nextLine();
                        values.put("subject_name", newSubjectName);
                    }
                    case 2 -> {
                        cc.displayAllCourse(new CourseModel());
                        boolean validCourseID = false;
                        while (!validCourseID) {
                            sout("\n" + conc.yellowLine);
                            sout(conc.yellowSpace);
                            sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter new Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            sout(conc.yellowSpace);
                            sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                values.put("course_id", courseId);
                                Input.HoldState();
                                validCourseID = true;
                            } else {
                                sout(conc.redLine);
                                sout(conc.redSpace);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid Course ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                sout(conc.redSpace);
                                sout(conc.redLine);
                            }
                        }
                    }
                    case 3 -> {
                        if (!values.isEmpty()) {
                            sub.updateSubject(values, subjectID);
                        } else {
                            sout(conc.redLine);
                            sout(conc.redSpace);
                            sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                    + "No fields to update" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                    " " + conc.BB_RED + " " + conc.RESET);
                            sout(conc.redSpace);
                            sout(conc.redLine);
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

    private static void sout(String input) {
        System.out.println(input);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.*;
import Controller.*;
import Utils.*;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author mark
 */
public class StudentSubject {
    private static StudentController sc = new StudentController();
    private static final CourseController cc = new CourseController();
    private static Scanner scan = new Scanner(System.in);
    private static final SectionController sec = new SectionController();
    static private final SubjectController sub = new SubjectController();
    private static final Student_SubjectController ssc = new Student_SubjectController();
    private static final ConsoleColors conc = new ConsoleColors();

    static void DisplaySubjectView() throws InterruptedException {
        StudentModel sm = new StudentModel();
        SubjectModel subm = new SubjectModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        Student_SubjectModel ssm = new Student_SubjectModel();
        boolean running = true;

        while (running) {
            ClearConsole.Cls();
            MainMenu.MainMenuHeader();
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_GREEN
                    + "S T U D E N T  S U B J E C T" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " "
                    + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_GREEN + "MENU"
                    + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Add Student Subject" + conc.BB_BLACK + "\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Remove Student Subject" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[3] Display Student Subjects" + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " "
                    + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[4] Update Student Subject" + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " "
                    + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[5] Back to Main Menu" + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);

            System.out.print(conc.YELLOW + "\n" + conc.center + "Choose an option: " + conc.RESET);
            String choiceStr = Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);

            while (choice == 0) {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Invalid Choice"
                        + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Please try again"
                        + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
                System.out.print(conc.RED + "\n" + conc.center + "Choose an option: " + conc.RESET);
                choiceStr = Input.getUserInput();
                choice = Input.parseIfPossible(choiceStr);
            }

            switch (choice) {
                case 1 -> {
                    try {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<>();

                        // Validate Course Name
                        boolean isValidCourseName = false;
                        String courseName = "";
                        while (!isValidCourseName) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter a Course Name" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            courseName = scan.nextLine();
                            if (courseName.isEmpty()) {
                                Input.COut("Course Name cannot be empty.");
                            } else {
                                if (cc.isValidCourse("course_name", courseName)) {
                                    isValidCourseName = true;
                                } else {
                                    Input.COut("Enter a valid course to filter students.");
                                }
                            }
                        }
                        sc.displayStudentbyCourse(courseName, sm, cm, secm);

                        // Validate Student ID
                        boolean isValidStudent = false;
                        while (!isValidStudent) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Student ID" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int studentId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sc.isValidStudent("student_id", studentId)) {
                                isValidStudent = true;
                                values.put("student_id", studentId);
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid student ID" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                        sub.displayAllSubject(subm, cm);
                        // Validate Subject ID
                        boolean isValidSubject = false;
                        while (!isValidSubject) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Subject ID" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int subjectId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                isValidSubject = true;
                                values.put("subject_id", subjectId);
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid subject id" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }

                        sec.displayAllSection(secm, cm);
                        // Validate Section ID
                        boolean isValidSection = false;
                        while (!isValidSection) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Section ID" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int sectionId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                isValidSection = true;
                                values.put("section_id", sectionId);
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid section id" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }

                        // Add Student Subject after all validations
                        ssc.addStudentSubject(values);

                    } catch (SQLException e) {
                        Input.COut("SQLError: " + e.getMessage());
                    } catch (Exception e) {
                        Input.COut("Error: " + e.getMessage());
                    }
                }

                case 2 -> {
                    try {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        boolean isValidStudent = false;
                        while (!isValidStudent) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Student's First Name" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            String firstName = scan.nextLine();
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Student's Last Name" + conc.BB_BLACK + "\t\t\t\t" +
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
                                        + "Invalid student name" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please enter a valid student name." + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                        boolean isValidSubject = false;
                        while (!isValidSubject) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Subject's Name" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            String subjectName = scan.nextLine();
                            if (sub.isValidSubjectValue("subject_name", subjectName)) {
                                isValidSubject = true;
                                values.put("subject_name", subjectName);
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid subject name" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                        + "Please enter a valid subject name." + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                        ssc.deleteStudentSubject(values);
                    } catch (Exception e) {
                        Sout("Error while deleting student subject: " + e.getMessage());
                    }
                }
                case 3 -> studentSubjectDisplayMenu(false);
                case 4 -> {
                    studentSubjectDisplayMenu(true); // Display the menu
                    // After returning from the menu, check if the user chose to go back
                    System.out.print("Would you like to continue editing a student subject? (y/n): ");
                    String continueEdit = Input.getUserInput();

                    if (continueEdit.equalsIgnoreCase("y")) {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
                        boolean isStudentSubject = false;

                        while (!isStudentSubject) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter student name" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            String studentName = Input.getUserInput();
                            values.put("student_name", studentName);
                            boolean validSubject = false;
                            while (!validSubject) {
                                Sout("\n" + conc.yellowLine);
                                Sout(conc.yellowSpace);
                                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t"
                                        + conc.BR_YELLOW + "Enter subject Name" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                        " " + conc.BB_YELLOW + " " + conc.RESET);
                                Sout(conc.yellowSpace);
                                Sout(conc.yellowLine);
                                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                                String subjectName = Input.getUserInput();
                                try {
                                    if (sub.isValidSubjectValue("subject_name", "%" + subjectName + "%")) {
                                        validSubject = true;
                                        values.put("subject_name", subjectName);
                                    } else {
                                        Sout(conc.redLine);
                                        Sout(conc.redSpace);
                                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t"
                                                + conc.BR_RED + "Invalid subject name" + conc.BB_BLACK
                                                + "\t\t\t\t\t\t\t" +
                                                " " + conc.BB_RED + " " + conc.RESET);
                                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t"
                                                + conc.BR_RED + "Please enter a valid subject name" + conc.BB_BLACK
                                                + "\t\t\t\t\t\t" +
                                                " " + conc.BB_RED + " " + conc.RESET);
                                        Sout(conc.redSpace);
                                        Sout(conc.redLine);
                                    }
                                } catch (Exception e) {
                                    Sout("Error: " + e.getMessage());
                                }
                            }
                            if (ssc.isValidStudentSubject(values)) {
                                isStudentSubject = true;
                                int ssid = ssc.getSpecificSSID(values, ssm, sm, subm, secm);
                                updateStudentSubjectField(ssid); // Proceed with the update
                            } else {
                                Input.COut("Student Subject ID doesn't exist'");
                            }
                        }
                    } else {
                        Sout("Returning...");
                    }
                }

                case 5 -> MainMenu.DisplayActionsMenu();
                default -> {
                    Sout(conc.redLine);
                    Sout(conc.redSpace);
                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                            + "Invalid Subject ID" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                            " " + conc.BB_RED + " " + conc.RESET);
                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                            + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + conc.BB_RED + " " + conc.RESET);
                    Sout(conc.redSpace);
                    Sout(conc.redLine);
                }
            }
        }

    }

    static private void studentSubjectDisplayMenu(boolean flag) throws InterruptedException {
        StudentModel sm = new StudentModel();
        SubjectModel sub = new SubjectModel();
        SectionModel secm = new SectionModel();
        Student_SubjectModel ssm = new Student_SubjectModel();

        boolean running = true;

        while (running) {
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "Display Student Subject Menu" + conc.BB_BLACK + "\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Display Student Subjects based on Student's name" + conc.BB_BLACK + "\t\t\t\t\t "
                    + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Display Student Subjects based on Subject's name" + conc.BB_BLACK + "\t\t\t " + conc.BB_WHITE
                    + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[3] Display Student Subjects based on Section name" + conc.BB_BLACK + "\t\t\t " + conc.BB_WHITE
                    + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE + "[4] Go Back"
                    + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);

            System.out.print(conc.YELLOW + "\n" + conc.center + "Choose an option: " + conc.RESET);

            String choiceStr = Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);

            while (choice == 0) {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Invalid Choice"
                        + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Please try again"
                        + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
                System.out.print(conc.RED + "\n" + conc.center + "Choose an option: " + conc.RESET);
                choiceStr = Input.getUserInput();
                choice = Input.parseIfPossible(choiceStr);
            }

            switch (choice) {
                case 1 -> {
                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                            + "Enter the student's name or substring" + conc.BB_BLACK + "\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectByStudent(searchValue, ssm, sm, sub, secm);
                    if (!flag) {
                        Input.HoldState();
                    }
                }
                case 2 -> {
                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                            + "Enter the subject's name or substring" + conc.BB_BLACK + "\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectBySubject(searchValue, ssm, sm, sub, secm);
                    if (!flag) {
                        Input.HoldState();
                    }
                }
                case 3 -> {
                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                            + "Enter the section's name or substring" + conc.BB_BLACK + "\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectBySection(searchValue, ssm, sm, sub, secm);
                    if (!flag) {
                        Input.HoldState();
                    }
                }
                case 4 -> {
                    MainMenu.DisplayActionsMenu();
                }
                default -> {
                    Sout(conc.redLine);
                    Sout(conc.redSpace);
                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                            + "Invalid option" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                            " " + conc.BB_RED + " " + conc.RESET);
                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                            + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t" +
                            " " + conc.BB_RED + " " + conc.RESET);
                    Sout(conc.redSpace);
                    Sout(conc.redLine);
                }
            }

            return;
        }
    }

    static private void updateStudentSubjectField(int studentSubjectID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingStudentSubject = true;

        while (updatingStudentSubject) {
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "Select To Update Student-Subject" + conc.BB_BLACK + "\t\t\t\t " + conc.BB_WHITE + " "
                    + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Update Student ID" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Update Subject ID" + conc.BB_BLACK + "\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[3] Update Section ID" + conc.BB_BLACK + "\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[3] Archive Student-Subject" + conc.BB_BLACK + "\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE + "[4] Finish Update"
                    + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);

            System.out.print(conc.YELLOW + "\n" + conc.center + "Choose an option: " + conc.RESET);
            String choiceStr = Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);

            while (choice == 0) {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Invalid Choice"
                        + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED + "Please try again"
                        + conc.BB_BLACK + "\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
                System.out.print(conc.RED + "\n" + conc.center + "Choose an option: " + conc.RESET);
                choiceStr = Input.getUserInput();
                choice = Input.parseIfPossible(choiceStr);
            }

            try {
                switch (choice) {
                    case 1 -> {
                        sc.displayStudents(new StudentModel(), new CourseModel(), new SectionModel());
                        boolean validStudentID = false;
                        while (!validStudentID) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter new Student ID" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int studentId = scan.nextInt();
                            if (sc.isValidStudent("student_id", studentId)) {
                                values.put("student_id", studentId);
                                validStudentID = true;
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid student id" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                        + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                            scan.nextLine(); // Clear newline character
                        }
                    }
                    case 2 -> {
                        sub.displayAllSubject(new SubjectModel(), new CourseModel());
                        try {
                            boolean validSubjectID = false;
                            while (!validSubjectID) {
                                Sout("\n" + conc.yellowLine);
                                Sout(conc.yellowSpace);
                                Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                        + "Enter new Subject ID" + conc.BB_BLACK + "\t\t\t\t" +
                                        " " + conc.BB_YELLOW + " " + conc.RESET);
                                Sout(conc.yellowSpace);
                                Sout(conc.yellowLine);
                                System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                                int subjectId = scan.nextInt();
                                if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                    values.put("subject_id", subjectId);
                                    validSubjectID = true;
                                } else {
                                    Sout(conc.redLine);
                                    Sout(conc.redSpace);
                                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t"
                                            + conc.BR_RED + "Invalid subject id" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                            " " + conc.BB_RED + " " + conc.RESET);
                                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                            + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t" +
                                            " " + conc.BB_RED + " " + conc.RESET);
                                    Sout(conc.redSpace);
                                    Sout(conc.redLine);
                                }
                                scan.nextLine(); // Clear newline character
                            }
                        } catch (Exception e) {
                            Input.COut("Error :" + e.getMessage());
                        }
                    }
                    case 3 -> {
                        sec.displayAllSection(new SectionModel(), new CourseModel());
                        boolean validSectionID = false;
                        while (!validSectionID) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter new Section ID" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            int sectionId = scan.nextInt();
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                values.put("section_id", sectionId);
                                validSectionID = true;
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid section id" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                        + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                            scan.nextLine(); // Clear newline character
                        }
                    }
                    case 4 -> {
                        boolean validInput = false;
                        while (!validInput) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Archive Student-Subject? (y/n)" + conc.BB_BLACK + "\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            String input = scan.nextLine();
                            if (input.equalsIgnoreCase("y")) {
                                values.put("archived", true);
                                validInput = true;
                            } else if (input.equalsIgnoreCase("n")) {
                                values.put("archived", false);
                                validInput = true;
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid input" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                        + "Please enter 'y' or 'n'." + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                    }
                    case 5 -> {
                        if (!values.isEmpty()) {
                            ssc.updateStudentSubject(values, studentSubjectID);
                        } else {
                            Sout("No fields to update.");
                        }
                        updatingStudentSubject = false; // Exit loop
                    }
                    default -> {
                        Sout(conc.redLine);
                        Sout(conc.redSpace);
                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                                + "Invalid option" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                " " + conc.BB_RED + " " + conc.RESET);
                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                + "Please try again." + conc.BB_BLACK + "\t\t\t\t\t" +
                                " " + conc.BB_RED + " " + conc.RESET);
                        Sout(conc.redSpace);
                        Sout(conc.redLine);
                    }
                }
            } catch (InputMismatchException e) {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED + "Invalid input"
                        + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                        + "Please enter a valid option" + conc.BB_BLACK + "\t\t\t\t\t" +
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

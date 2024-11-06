/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.*;
import Controller.*;
import Utils.*;
import java.sql.SQLException;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;


/**
 *
 * @author mark
 */
public class StudentSubject {
    private static StudentController sc = new StudentController();
    private static final IndexController ic = new IndexController();
    private static final CourseController cc = new CourseController();
    private static Scanner scan = new Scanner(System.in);
    private static final ScheduleController sched = new ScheduleController();
    private static final SectionController sec = new SectionController();
    static private final SubjectController sub = new SubjectController();
    private static final Student_SubjectController ssc = new Student_SubjectController();
    private static final ConsoleColors conc = new ConsoleColors();


    static void DisplaySubjectView() throws InterruptedException
    {
        StudentModel sm = new StudentModel();
        SubjectModel subm = new SubjectModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        Student_SubjectModel ssm = new Student_SubjectModel();
        boolean running = true;

        while (running) {
            ClearConsole.Cls();
            MainMenu.MainMenuHeader();
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_GREEN+"S T U D E N T  S U B J E C T"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_GREEN+"MENU"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.whiteLine);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[1] Add Student Subject"+conc.BB_BLACK+"\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[2] Remove Student Subject"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[3] Display Student Subjects"+conc.BB_BLACK+"\t\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[4] Update Student Subject"+conc.BB_BLACK+"\t\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[5] Back to Main Menu"+conc.BB_BLACK+"\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.whiteLine);

            System.out.print(conc.YELLOW+"\n"+conc.center+"Choose an option: "+conc.RESET);
            String choiceStr = Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);


            while(choice == 0)
            {
                System.out.println(conc.redLine);
                System.out.println(conc.redSpace);
                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid Choice"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                        " "+conc.BB_RED+" "+conc.RESET);
                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                        " "+conc.BB_RED+" "+conc.RESET);
                System.out.println(conc.redSpace);
                System.out.println(conc.redLine);
                System.out.print(conc.RED+"\n"+conc.center+"Choose an option: "+conc.RESET);
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
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter a Course Name"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
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
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter Student ID"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            int studentId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sc.isValidStudent("student_id", studentId)) {
                                isValidStudent = true;
                                values.put("student_id", studentId);
                            } else {
                                System.out.println(conc.redLine);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid student ID"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.redLine);
                            }
                        }
                        sub.displayAllSubject(subm, cm);
                        // Validate Subject ID
                        boolean isValidSubject = false;
                        while (!isValidSubject) {
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter Subject ID"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            int subjectId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                isValidSubject = true;
                                values.put("subject_id", subjectId);
                            } else {
                                System.out.println(conc.redLine);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid subject id"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.redLine);
                            }
                        }

                        sec.displayAllSection(secm, cm);
                        // Validate Section ID
                        boolean isValidSection = false;
                        while (!isValidSection) {
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter Section ID"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            int sectionId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                isValidSection = true;
                                values.put("section_id", sectionId);
                            } else {
                                System.out.println(conc.redLine);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid section id"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.redLine);
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
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter Student's First Name"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            String firstName = scan.nextLine();
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter Student's Last Name"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            String lastName = scan.nextLine();
                            if (sc.isValidStudentName(firstName, lastName)) {
                                isValidStudent = true;
                                String name = firstName + " " + lastName;
                                values.put("student_name", name);
                            } else {
                                System.out.println(conc.redLine);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid student name"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please enter a valid student name."+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.redLine);
                            }
                        }
                        boolean isValidSubject = false;
                        while (!isValidSubject) {
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter Subject's Name"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            String subjectName = scan.nextLine();
                            if (sub.isValidSubjectValue("subject_name", subjectName)) {
                                isValidSubject = true;
                                values.put("subject_name", subjectName);
                            } else {
                                System.out.println(conc.redLine);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid subject name"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please enter a valid subject name."+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.redLine);
                            }
                        }
                        ssc.deleteStudentSubject(values);
                    } catch (Exception e) {
                        System.out.println("Error while deleting student subject: " + e.getMessage());
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
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter student name"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            String studentName = Input.getUserInput();
                            values.put("student_name", studentName);
                            boolean validSubject = false;
                            while (!validSubject) {
                                System.out.println("\n"+conc.yellowLine);
                                System.out.println(conc.yellowSpace);
                                System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter subject Name"+conc.BB_BLACK+"\t\t\t\t" +
                                        " "+conc.BB_YELLOW+" "+conc.RESET);
                                System.out.println(conc.yellowSpace);
                                System.out.println(conc.yellowLine);
                                System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                                String subjectName = Input.getUserInput();
                                try{
                                    if (sub.isValidSubjectValue("subject_name", "%"+subjectName+"%")) {
                                        validSubject = true;
                                        values.put("subject_name", subjectName);
                                    } else {
                                        System.out.println(conc.redLine);
                                        System.out.println(conc.redSpace);
                                        System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid subject name"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                                " "+conc.BB_RED+" "+conc.RESET);
                                        System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please enter a valid subject name"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                                " "+conc.BB_RED+" "+conc.RESET);
                                        System.out.println(conc.redSpace);
                                        System.out.println(conc.redLine);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error: "+ e.getMessage());
                                }
                            }
                            if (ssc.isValidStudentSubject(values)) {
                                isStudentSubject = true;
                                int ssid = ssc.getSpecificSSID(values, ssm, sm, subm,secm);
                                updateStudentSubjectField(ssid); // Proceed with the update
                            } else {
                                Input.COut("Student Subject ID doesn't exist'");
                            }
                        }
                    } else {
                        System.out.println("Returning...");
                    }
                }


                case 5 -> MainMenu.DisplayActionsMenu();
                default -> {
                    System.out.println(conc.redLine);
                    System.out.println(conc.redSpace);
                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid Subject ID"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                            " "+conc.BB_RED+" "+conc.RESET);
                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+conc.BB_RED+" "+conc.RESET);
                    System.out.println(conc.redSpace);
                    System.out.println(conc.redLine);
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
            System.out.println(conc.whiteLine);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"Display Student Subject Menu"+conc.BB_BLACK+"\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.whiteLine);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[1] Display Student Subjects based on Student's name"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[2] Display Student Subjects based on Subject's name"+conc.BB_BLACK+"\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[3] Display Student Subjects based on Section name"+conc.BB_BLACK+"\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[4] Go Back"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.whiteLine);

            System.out.print(conc.YELLOW+"\n"+conc.center+"Choose an option: "+conc.RESET);

            String choiceStr = Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);

            while(choice == 0)
            {
                System.out.println(conc.redLine);
                System.out.println(conc.redSpace);
                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid Choice"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                        " "+conc.BB_RED+" "+conc.RESET);
                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                        " "+conc.BB_RED+" "+conc.RESET);
                System.out.println(conc.redSpace);
                System.out.println(conc.redLine);
                System.out.print(conc.RED+"\n"+conc.center+"Choose an option: "+conc.RESET);
                choiceStr = Input.getUserInput();
                choice = Input.parseIfPossible(choiceStr);
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n"+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter the student's name or substring"+conc.BB_BLACK+"\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);
                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectByStudent(searchValue, ssm, sm, sub, secm);
                    if(!flag)
                    {
                        Input.HoldState();
                    }
                }
                case 2 -> {
                    System.out.println("\n"+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter the subject's name or substring"+conc.BB_BLACK+"\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);
                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectBySubject(searchValue, ssm, sm, sub, secm);
                    if(!flag)
                    {
                        Input.HoldState();
                    }
                }
                case 3 -> {
                    System.out.println("\n"+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter the section's name or substring"+conc.BB_BLACK+"\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);
                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectBySection(searchValue, ssm, sm, sub, secm);
                    if(!flag)
                    {
                        Input.HoldState();
                    }
                }
                case 4 -> {
                    MainMenu.DisplayActionsMenu();
                }
                default -> {
                    System.out.println(conc.redLine);
                    System.out.println(conc.redSpace);
                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t\t"+conc.BR_RED+"Invalid option"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                            " "+conc.BB_RED+" "+conc.RESET);
                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t" +
                            " "+conc.BB_RED+" "+conc.RESET);
                    System.out.println(conc.redSpace);
                    System.out.println(conc.redLine);
                }
            }

            return;
        }
    }

    static private void updateStudentSubjectField(int studentSubjectID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingStudentSubject = true;

        while (updatingStudentSubject) {
            System.out.println(conc.whiteLine);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"Select To Update Student-Subject"+conc.BB_BLACK+"\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.whiteLine);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[1] Update Student ID"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[2] Update Subject ID"+conc.BB_BLACK+"\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[3] Update Section ID"+conc.BB_BLACK+"\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[3] Archive Student-Subject"+conc.BB_BLACK+"\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[4] Finish Update"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.whiteLine);

            System.out.print(conc.YELLOW+"\n"+conc.center+"Choose an option: "+conc.RESET);
            String choiceStr = Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);


            while(choice == 0)
            {
                System.out.println(conc.redLine);
                System.out.println(conc.redSpace);
                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid Choice"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                        " "+conc.BB_RED+" "+conc.RESET);
                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                        " "+conc.BB_RED+" "+conc.RESET);
                System.out.println(conc.redSpace);
                System.out.println(conc.redLine);
                System.out.print(conc.RED+"\n"+conc.center+"Choose an option: "+conc.RESET);
                choiceStr = Input.getUserInput();
                choice = Input.parseIfPossible(choiceStr);
            }

            try {
                switch (choice) {
                    case 1 -> {
                        sc.displayStudents(new StudentModel(),new CourseModel(), new SectionModel());
                        boolean validStudentID = false;
                        while (!validStudentID) {
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter new Student ID"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            int studentId = scan.nextInt();
                            if (sc.isValidStudent("student_id", studentId)) {
                                values.put("student_id", studentId);
                                validStudentID = true;
                            } else {
                                System.out.println(conc.redLine);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t\t"+conc.BR_RED+"Invalid student id"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.redLine);
                            }
                            scan.nextLine(); // Clear newline character
                        }
                    }
                    case 2 -> {
                        sub.displayAllSubject(new SubjectModel(), new CourseModel());
                        try {
                            boolean validSubjectID = false;
                            while (!validSubjectID) {
                                System.out.println("\n"+conc.yellowLine);
                                System.out.println(conc.yellowSpace);
                                System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter new Subject ID"+conc.BB_BLACK+"\t\t\t\t" +
                                        " "+conc.BB_YELLOW+" "+conc.RESET);
                                System.out.println(conc.yellowSpace);
                                System.out.println(conc.yellowLine);
                                System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                                int subjectId = scan.nextInt();
                                if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                    values.put("subject_id", subjectId);
                                    validSubjectID = true;
                                } else {
                                    System.out.println(conc.redLine);
                                    System.out.println(conc.redSpace);
                                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t\t"+conc.BR_RED+"Invalid subject id"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                            " "+conc.BB_RED+" "+conc.RESET);
                                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t" +
                                            " "+conc.BB_RED+" "+conc.RESET);
                                    System.out.println(conc.redSpace);
                                    System.out.println(conc.redLine);
                                }
                                scan.nextLine(); // Clear newline character
                            }
                        } catch (Exception e) {
                            Input.COut("Error :" + e.getMessage());
                        }
                    }
                    case 3 -> {
                        sec.displayAllSection(new SectionModel(),new CourseModel());
                        boolean validSectionID = false;
                        while (!validSectionID) {
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter new Section ID"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            int sectionId = scan.nextInt();
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                values.put("section_id", sectionId);
                                validSectionID = true;
                            } else {
                                System.out.println(conc.redLine);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t\t"+conc.BR_RED+"Invalid section id"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.redLine);
                            }
                            scan.nextLine(); // Clear newline character
                        }
                    }
                    case 4 -> {
                        boolean validInput = false;
                        while (!validInput) {
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Archive Student-Subject? (y/n)"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            String input = scan.nextLine();
                            if (input.equalsIgnoreCase("y")) {
                                values.put("archived", true);
                                validInput = true;
                            } else if (input.equalsIgnoreCase("n")) {
                                values.put("archived", false);
                                validInput = true;
                            } else {
                                System.out.println(conc.redLine);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t\t"+conc.BR_RED+"Invalid input"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_RED+"Please enter 'y' or 'n'."+conc.BB_BLACK+"\t\t\t\t\t" +
                                        " "+conc.BB_RED+" "+conc.RESET);
                                System.out.println(conc.redSpace);
                                System.out.println(conc.redLine);
                            }
                        }
                    }
                    case 5 -> {
                        if (!values.isEmpty()) {
                            ssc.updateStudentSubject(values, studentSubjectID);
                        } else {
                            System.out.println("No fields to update.");
                        }
                        updatingStudentSubject = false; // Exit loop
                    }
                    default -> {
                        System.out.println(conc.redLine);
                        System.out.println(conc.redSpace);
                        System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t\t"+conc.BR_RED+"Invalid option"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                " "+conc.BB_RED+" "+conc.RESET);
                        System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_RED+"Please try again."+conc.BB_BLACK+"\t\t\t\t\t" +
                                " "+conc.BB_RED+" "+conc.RESET);
                        System.out.println(conc.redSpace);
                        System.out.println(conc.redLine);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println(conc.redLine);
                System.out.println(conc.redSpace);
                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t\t"+conc.BR_RED+"Invalid input"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                        " "+conc.BB_RED+" "+conc.RESET);
                System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_RED+"Please enter a valid option"+conc.BB_BLACK+"\t\t\t\t\t" +
                        " "+conc.BB_RED+" "+conc.RESET);
                System.out.println(conc.redSpace);
                System.out.println(conc.redLine);
                scan.nextLine(); // Clear invalid input
            }
        }
    }
}

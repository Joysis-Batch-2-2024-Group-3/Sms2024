/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Model.*;
import Controller.*;
import Utils.ClearConsole;
import Utils.Input;
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
    
    
    static void DisplaySubjectView() throws InterruptedException
    {
        StudentModel sm = new StudentModel();
        SubjectModel subm = new SubjectModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        Student_SubjectModel ssm = new Student_SubjectModel();
        boolean running = true;
        while (running) {
            Input.COut("Student Subject Menu");
            System.out.println(" _____________________________________ ");
            System.out.println("|                                     |");
            System.out.println("| 1. Add Student Subject              |");
            System.out.println("| 2. Remove Student Subject           |");
            System.out.println("| 3. Display Student Subjects         |");
            System.out.println("| 4. Edit Student Subject             |");
            System.out.println("| 5. Back to Main Menu                |");
            System.out.println("|_____________________________________|\n");
            System.out.print("Choose an option: ");
            String choiceStr = Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);
            
            
            while(choice == 0)
            {
                System.out.print("Invalid Input. Try Again: ");
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
                            System.out.print("Enter a Course Name: ");
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
                            System.out.print("Enter Student ID: ");
                            int studentId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sc.isValidStudent("student_id", studentId)) {
                                isValidStudent = true;
                                values.put("student_id", studentId);
                            } else {
                                Input.COut("Invalid student id. Please enter a valid student id.");
                            }
                        }
                        sub.displayAllSubject(subm, cm);
                        // Validate Subject ID
                        boolean isValidSubject = false;
                        while (!isValidSubject) {
                            System.out.print("Enter Subject ID: ");
                            int subjectId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                isValidSubject = true;
                                values.put("subject_id", subjectId);
                            } else {
                                Input.COut("Invalid subject id. Please enter a valid subject id.");
                            }
                        }

                        sec.displayAllSection(secm, cm);
                        // Validate Section ID
                        boolean isValidSection = false;
                        while (!isValidSection) {
                            System.out.print("Enter Section ID: ");
                            int sectionId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                isValidSection = true;
                                values.put("section_id", sectionId);
                            } else {
                                Input.COut("Invalid section id. Please enter a valid section id.");
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
                            System.out.println("Enter Student's First Name:");
                            String firstName = scan.nextLine();
                            System.out.println("Enter Student's Last Name:");
                            String lastName = scan.nextLine();
                            if (sc.isValidStudentName(firstName, lastName)) {
                                isValidStudent = true;
                                String name = firstName + " " + lastName;
                                values.put("student_name", name);
                            } else {
                                Input.COut("Invalid student name. Please enter a valid student name.");
                            }
                        }
                        boolean isValidSubject = false;
                        while (!isValidSubject) {
                            System.out.println("Enter Subject's Name:");
                            String subjectName = scan.nextLine();
                            if (sub.isValidSubjectValue("subject_name", subjectName)) {
                                isValidSubject = true;
                                values.put("subject_name", subjectName);
                            } else {
                                Input.COut("Invalid subject name. Please enter a valid subject name.");
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
                            System.out.print("Enter student name: ");
                            String studentName = Input.getUserInput();
                            values.put("student_name", studentName);
                            boolean validSubject = false;
                            while (!validSubject) {
                                System.out.print("Enter subject name: ");
                                String subjectName = Input.getUserInput();
                               try{
                                if (sub.isValidSubjectValue("subject_name", "%"+subjectName+"%")) {
                                    validSubject = true;
                                    values.put("subject_name", subjectName);
                                } else {
                                    Input.COut("Invalid subject name. Please enter a valid subject name.");
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
                default -> Input.COut("Invalid option. Please try again.");
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
            Input.COut("Display Student Subject Menu");
            System.out.println(" __________________________________________________________ ");
            System.out.println("|                                                          |");
            System.out.println("| 1. Display Student Subjects based on Student's name      |");
            System.out.println("| 2. Display Student Subjects based on Subject's name      |");
            System.out.println("| 3. Display Student Subjects based on Section name        |");
            System.out.println("| 4. Go Back                                               |");
            System.out.println("|__________________________________________________________|\n");
            System.out.print("Choose an option: ");
            
            String choiceStr = Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);
            
            while(choice == 0)
            {
                System.out.print("Invalid Choice. Try Again: ");
                choiceStr = Input.getUserInput();
                choice = Input.parseIfPossible(choiceStr);    
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the student's name or substring: ");
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectByStudent(searchValue, ssm, sm, sub, secm);
                    if(!flag)
                    {
                        Input.HoldState();
                    }
                }
                case 2 -> {
                    System.out.print("Enter the subject's name or substring: ");
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectBySubject(searchValue, ssm, sm, sub, secm);
                    if(!flag)
                    {
                        Input.HoldState();
                    }
                }
                case 3 -> {
                    System.out.print("Enter the section name or substring: ");
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
                default -> Input.COut("Invalid option. Please try again.");
            }
            
            return;
        }
    }
    
    static private void updateStudentSubjectField(int studentSubjectID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingStudentSubject = true;

        while (updatingStudentSubject) {
            Input.COut("Select To Update Student-Subject");
            System.out.println(" __________________________________________________________ ");
            System.out.println("|                                                          |");
            System.out.println("| 1. Update Student ID                                     |");
            System.out.println("| 2. Update Subject ID                                     |");
            System.out.println("| 3. Update Section ID                                     |");
            System.out.println("| 4. Archive Student-Subject                               |");
            System.out.println("| 5. Finish Update                                         |");
            System.out.println("|__________________________________________________________|\n");
            System.out.print("Choose an option: ");

            
            String choiceStr = Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);
            
            
            while(choice == 0)
            {
                System.out.print("Invalid Input. Try Again: ");
                choiceStr = Input.getUserInput();
                choice = Input.parseIfPossible(choiceStr);
            }

            try {
                switch (choice) {
                    case 1 -> {
                        sc.displayStudents(new StudentModel(),new CourseModel(), new SectionModel());
                        boolean validStudentID = false;
                        while (!validStudentID) {
                            System.out.print("Enter new Student ID: ");
                            int studentId = scan.nextInt();
                            if (sc.isValidStudent("student_id", studentId)) {
                                values.put("student_id", studentId);
                                validStudentID = true;
                            } else {
                                Input.COut("Invalid Student ID. Please try again.");
                            }
                            scan.nextLine(); // Clear newline character
                        }
                    }
                    case 2 -> {
                        sub.displayAllSubject(new SubjectModel(), new CourseModel());
                        try {
                        boolean validSubjectID = false;
                        while (!validSubjectID) {
                            System.out.print("Enter new Subject ID: ");
                            int subjectId = scan.nextInt();
                            if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                values.put("subject_id", subjectId);
                                validSubjectID = true;
                            } else {
                                Input.COut("Invalid Subject ID. Please try again.");
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
                            System.out.print("Enter new Section ID: ");
                            int sectionId = scan.nextInt();
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                values.put("section_id", sectionId);
                                validSectionID = true;
                            } else {
                                Input.COut("Invalid Section ID. Please try again.");
                            }
                            scan.nextLine(); // Clear newline character
                        }
                    }
                    case 4 -> {
                        boolean validInput = false;
                        while (!validInput) {
                            System.out.print("Archive Student-Subject? (y/n): ");
                            String input = scan.nextLine();
                            if (input.equalsIgnoreCase("y")) {
                                values.put("archived", true);
                                validInput = true;
                            } else if (input.equalsIgnoreCase("n")) {
                                values.put("archived", false);
                                validInput = true;
                            } else {
                                Input.COut("Invalid input. Please enter 'y' or 'n'.");
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
                    default -> Input.COut("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                Input.COut("Invalid input. Please enter a valid option.");
                scan.nextLine(); // Clear invalid input
            }
        }
    }
}

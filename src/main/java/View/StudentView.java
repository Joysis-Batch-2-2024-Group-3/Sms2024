/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Controller.*;
import Model.*;
import Utils.ClearConsole;
import Utils.Input;
import static View.MainMenu.MainMenuHeader;
import java.sql.Date;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    
    
    public static void DisplayStudentMenu() throws InterruptedException
    {
        
         MainMenuHeader();
         System.out.println("\n----------------Student Options----------------\n");
         System.out.println(" _____________________________________________________");
         System.out.println("|                                                     |");
         System.out.println("| 1. Display Student List                             |");
         System.out.println("| 2. Add New Student                                  |");
         System.out.println("| 3. Remove Student                                   |");
         System.out.println("| 4. Update Student                                   |");
         System.out.println("| 5. Back to main menu                                |");
         System.out.println("|_____________________________________________________|\n");
         
         System.out.print("Enter Selection: ");
         String selected = Input.getUserInput();
         
        switch(selected)
        {
                case "1" -> StudentView.DisplayStudentOptions();
                case "2" -> addStudentSubMenu();
                case "3" -> removeStudent();
                case "4" -> updateStudent();
                case "5" -> {
                    MainMenu.DisplayActionsMenu();
                }
                default -> {
                    System.out.println("Invalid Selection. Try Again: ");
                    Thread.sleep(500);
                    DisplayStudentMenu();
                }

        }
    }
    
    public static void DisplayStudentOptions()
    {
        try{
            ClearConsole.Cls();
            MainMenuHeader();
            System.out.println("\n----------------Student Sub Menu----------------\n\n");
            System.out.println(" _____________________________________________________");
            System.out.println("|                                                     |");
            System.out.println("| 1. Search by section                                |");
            System.out.println("| 2. Search by course                                 |");
            System.out.println("| 3. Search by First Name                             |");
            System.out.println("| 4. Search by Last Name                              |");
            System.out.println("| 5. Search by Student ID                             |");
            System.out.println("| 6. Search by specifying a column                    |");
            System.out.println("| 7. Show all Students                                |");
            System.out.println("| 8. Show all archived students                       |");
            System.out.println("| 9. Back to Main Menu                                |");
            System.out.println("|_____________________________________________________|\n");
            System.out.print("Enter Selection: ");


            String selection = Input.getUserInput();
            studentSubMenu(selection);
        }catch(Exception e)
        {
            Input.COut("Something went wrong: " + e.getMessage());
        }
        
    }
    
     private static void studentSubMenu(String selection) throws InterruptedException  {
        StudentModel sm = new StudentModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();

        switch (selection) {
            case "1" -> {
                System.out.print("Enter Section Name: ");
                String value = Input.getUserInput();
                sc.displayStudentbySection(value, sm, cm, secm);
                GoBack();
            }
            case "2" -> {
                System.out.print("Enter Course Name: ");
                String value = Input.getUserInput();
                sc.displayStudentbyCourse(value, sm, cm, secm);
                GoBack();
            }
            case "3" -> {
                System.out.print("Enter First Name: ");
                String value = Input.getUserInput();
                sc.displayStudentbyFName(value, sm, cm, secm);
                GoBack();
            }
            case "4" -> {
                System.out.print("Enter Last Name: ");
                String value = Input.getUserInput();
                sc.displayStudentbyLname(value, sm, cm, secm);
                GoBack();
            }
            case "5" -> {
                System.out.print("Enter Student ID: ");
                String value = Input.getUserInput();
                sc.displayStudentbyStudent_ID(value, sm, cm, secm);
                GoBack();
            }
            case "6" -> {
                System.out.println("Choose from this specific column: ");
                for (String column : StudentController.validColumns) {
                    System.out.print("| " + column + " | ");
                }
                System.out.println("Choose from this specific column: ");
                for (String column : StudentController.validColumns) {
                    System.out.print("| " + column + " | ");
                }

                String columnName = null;
                boolean validColumn = false;
                while (!validColumn) {
                    System.out.print("\nEnter Column Name: ");
                    columnName = Input.getUserInput();
                    // Check if the input columnName is valid
                    if (Arrays.asList(StudentController.validColumns).contains(columnName)) {
                        validColumn = true; // Exit loop if valid
                    } else {
                        Input.COut("Invalid column name. Please choose from the valid columns.");
                    }
                }

                System.out.print("Enter Value: ");
                String columnValue = Input.getUserInput();
                System.out.println("Searching for " + columnValue + " in " + columnName);
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
                System.out.println("Returning to Student Menu...");
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
        System.out.println("\n----------------Add Student----------------\n");

        // First Name Input
        System.out.print("Enter First Name: ");
        String firstName = Input.getUserInput();
        sm.setStudentFirstname(firstName);

        // Last Name Input
        System.out.print("Enter Last Name: ");
        String lastName = Input.getUserInput();
        sm.setStudentLastname(lastName);

        // Birth Date Input with Validation
        boolean validDate = false;
        while (!validDate) {
            System.out.print("Enter Birth Date (yyyy-mm-dd): ");
            String birthDate = Input.getUserInput();
            if (ic.isValidDate(birthDate)) {
                sm.setStudentDob(Date.valueOf(birthDate));
                validDate = true;
            } else {
                Input.COut("Invalid date format. Please enter in yyyy-mm-dd format.");
            }
        }

        // Sex Input with Validation
        boolean validSex = false;
        while (!validSex) {
            System.out.print("Enter Sex (F or M): ");
            String input = Input.getUserInput().trim().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'M' || input.charAt(0) == 'F')) { 
                sm.setStudentSex(input.charAt(0)); 
                validSex = true; 
            } else { 
                Input.COut("Invalid input. Please enter either 'F' or 'M'.");
            }
        }

        // Course ID Input with Validation
        int courseId;
        cc.displayAllCourse(cm);
        boolean validCourseID = false;
        while (!validCourseID) {
            System.out.print("Enter Course ID: ");
            courseId = scan.nextInt();
            if (cc.isValidCourse("course_id", courseId)) {
                sm.setStudentCourse(courseId);
                validCourseID = true;
            } else {
                Input.COut("Invalid Course ID. Please try again.");
            }
        }

        // Filter and Display Sections by Course ID
        sec.filterSection("course_tbl.course_id", sm.getStudentCourse(), secm, cm);

        // Section ID Input with Validation
        boolean validSectionId = false;
        while (!validSectionId) {
            System.out.print("Enter Section ID: ");
            int sectionId = scan.nextInt();
            if (sec.isValidSectionValue("section_id", sectionId)) {
                sm.setStudentSection(sectionId);
                validSectionId = true;
            } else {
                Input.COut("Invalid Section ID. Please try again");
            }
        }

        // Add Student and Display All Students
        sc.addStudent(sm, cm, secm);
        sc.displayStudents(sm, cm, secm);
         Input.HoldState();

         try{
             MainMenu.DisplayActionsMenu();
         } catch (InterruptedException e) {
             System.out.println("Error: "+ e.getMessage());
         }
    }
     
     private static void GoBack()
     {
        try{
            Input.HoldState();
            DisplayStudentMenu();
        }catch(Exception e){
            Input.COut("Something went wrong: "+ e.getMessage());
        }
     }
     
     
     public static void removeStudent()
     {
        try{
            LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
        boolean isValidStudent = false;
        while (!isValidStudent) {
            System.out.println("Enter Student's First Name: ");
            String firstName = scan.nextLine();
            System.out.println("Enter Student's Last Name: ");
            String lastName = scan.nextLine();
            if (sc.isValidStudentName(firstName, lastName)) {
                isValidStudent = true;
                String name = firstName + " " + lastName;
                values.put("student_name", name);
            } else {
                Input.COut("Invalid student name. Please enter a valid student name.");
            }
        }
        boolean isValidSectionName = false;
        while (!isValidSectionName) {
            System.out.println("Enter Section's Name: ");
            String sectionName = scan.nextLine();
            if (sec.isValidSectionValue("section_name", sectionName)) {
                isValidSectionName = true;
                values.put("section_name", sectionName);
            } else {
                Input.COut("Invalid section name. Please enter a valid section name.");
            }
        }
        sc.dropStudent(values);
        DisplayStudentMenu();
        }catch(Exception e){
            Input.COut("Something went wrong: " + e.getMessage());
        }
     }
 
     
     static private void updateStudent()
     {
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        StudentModel sm = new StudentModel();
        sc.displayStudents(sm, cm, secm);

        System.out.print("Enter the Student ID: ");
        int studentId = scan.nextInt();
        sm.setStudentId(studentId); // Set the student ID in the model
        try {
            updateStudentField(studentId);
        } catch (InterruptedException e) {
            Input.COut("Something went wrong: " + e.getMessage());
        }
     }
     
     
     static private void updateStudentField(int StudentID) throws InterruptedException {

        LinkedHashMap<String, Object> value = new LinkedHashMap<>();
        boolean updatingStudent = true;

        while (updatingStudent) {
            System.out.println("\n----------------------Select To Update Student--------------------\n");
            System.out.println(" _____________________________________________________");
            System.out.println("|                                                     |");
            System.out.println("| 1. Update Firstname                                 |");
            System.out.println("| 2. Update Lastname                                  |");
            System.out.println("| 3. Update Date of Birth                             |");
            System.out.println("| 4. Update Sex (M/F)                                 |");
            System.out.println("| 5. Update Year Level                                |");
            System.out.println("| 6. Update Course ID                                 |");
            System.out.println("| 7. Update Section ID                                |");
            System.out.println("| 8. Update archived                                  |");
            System.out.println("| 9. Finish Update                                    |");
            System.out.println("|_____________________________________________________|\n");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Clear newline character
            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new firstname: ");
                        String newFirstname = scan.nextLine();
                        value.put("first_name", newFirstname);
                    }
                    case 2 -> {
                        System.out.print("Enter new lastname: ");
                        String newLastname = scan.nextLine();
                        value.put("last_name", newLastname);
                    }
                    case 3 -> {
                        boolean validDate = false;
                        while (!validDate) {
                            System.out.print("Enter Birth Date (yyyy-mm-dd): ");
                            String birthDate = scan.nextLine();
                            if (ic.isValidDate(birthDate)) {
                                value.put("birth_date", Date.valueOf(birthDate));
                                validDate = true;
                            } else {
                                Input.COut("Invalid date format. Please enter in yyyy-mm-dd format.");
                            }
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter new sex (M/F): ");
                        char newSex = scan.nextLine().toUpperCase().charAt(0);
                        if (newSex == 'M' || newSex == 'F') {
                            value.put("sex", newSex);
                        } else {
                            Input.COut("Invalid input. Please enter 'M' or 'F'.");
                        }
                    }
                    case 5 -> {
                        System.out.print("Enter new year level: ");
                        int newYearLevel = scan.nextInt();
                        scan.nextLine(); // Clear newline character
                        value.put("year_level", newYearLevel);
                    }
                    case 6 -> {
                        int courseId;
                        cc.displayAllCourse(new CourseModel());
                        boolean validCourseID = false;
                        while (!validCourseID) {
                            System.out.print("Enter Course ID: ");
                            courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                value.put("course_id", courseId);
                                validCourseID = true;
                            } else {
                                Input.COut("Invalid Course ID. Please try again.");
                            }
                        }

                    }
                    case 7 -> {
                        int sectionId;
                        sec.displayAllSection(new SectionModel(), new CourseModel());
                        boolean validSectionID = false;
                        while (!validSectionID) {
                            System.out.print("Enter Section ID: ");
                            sectionId = scan.nextInt();
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                value.put("section_id", sectionId);
                                validSectionID = true;
                            } else {
                                Input.COut("Invalid Section ID. Please try again.");
                            }
                        }
                    }
                    case 8 -> {
                        System.out.print("Toggle Archived Status (current: " + (value.getOrDefault("archived", "No")) + "). Enter 'Y' to Archive or 'N' to Unarchive: ");
                        char archivedChoice = scan.nextLine().toUpperCase().charAt(0);
                        if (archivedChoice == 'Y') {
                            value.put("archived", true);
                        } else if (archivedChoice == 'N') {
                            value.put("archived", false);
                        } else {
                            Input.COut("Invalid input. Please enter 'Y' or 'N'.");
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
    
}

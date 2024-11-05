/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.*;
import Model.*;
import Utils.ClearConsole;
import Utils.Input;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author mark
 */
public class CourseView {
    private static Scanner scan = new Scanner(System.in);
    private static StudentController sc = new StudentController();
    private static final IndexController ic = new IndexController();
    private static final CourseController cc = new CourseController();
    
    static void DisplayCourseView() throws InterruptedException
    {
        
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            ClearConsole.Cls();
            MainMenu.MainMenuHeader();
            System.out.println("\n----------------------Course Menu---------------------\n");
            System.out.println("_____________________________");
            System.out.println("|                           |");
            System.out.println("| 1. Display All Courses    |");
            System.out.println("| 2. Search for Course/s    |");
            System.out.println("| 3. Update for Courses     |");
            System.out.println("| 4. Add Course             |");
            System.out.println("| 5. Delete Course          |");
            System.out.println("| 6. Back to Main Menu      |");
            System.out.println("|___________________________|");
            System.out.print("\nChoose an option: ");
            String choiceStr =Input.getUserInput();
            int choice = Input.parseIfPossible(choiceStr);
            
            if(choice == 0)
            {
                Input.COut("Invalid Choice. Returning to Menu...");
                Thread.sleep(600);
                DisplayCourseView();
            }
            
            System.out.println("");

            switch (choice) {
                case 1 -> {
                    ClearConsole.Cls();
                    cc.displayAllCourse(cm);
                    Input.HoldState();
                }
                case 2 -> {
                    ClearConsole.Cls();
                    System.out.println("\n----------------------Filter Course--------------------\n");
                    System.out.println("1. Course Name");
                    System.out.println("2. Department Name");
                    System.out.print("Choose a column to filter by: ");
                    String optionstr = Input.getUserInput();
                    int option = Input.parseIfPossible(optionstr);
                    
                    if(option == 0)
                    {
                        Input.COut("Invalid Choice. Returning to Menu...");
                        Thread.sleep(600);
                        DisplayCourseView();
                    }

                    switch (option) {
                        case 1 -> {
                            System.out.print("\nEnter the course name: ");
                            String searchValue = scan.nextLine().toLowerCase();
                            cc.filterCourse("course_name", searchValue, cm);
                            Input.HoldState();
                        }
                        case 2 -> {
                            System.out.print("\nEnter the department name: ");
                            String departmentName = scan.nextLine().toLowerCase();
                            cc.filterCourse("department_name", departmentName, cm);
                            Input.HoldState();
                        }
                        default -> System.out.println("\n------------------Invalid option. Please try again.------------------\n");
                    }
                }
                case 3 -> {
                    ClearConsole.Cls();
                    cc.displayAllCourse(cm);
                    System.out.println("\nEnter the course ID: ");
                    String courseIdStr = Input.getUserInput();
                    int courseId = Input.parseIfPossible(courseIdStr);
                    
                    while(courseId == 0)
                    {
                        Input.COut("Wrong Input");
                         System.out.print("Enter the course ID: ");
                        courseIdStr = Input.getUserInput();
                        courseId = Input.parseIfPossible(courseIdStr);
                    }
                    
                    updateCourseField(courseId);
                     Input.HoldState();

                }
                case 4 -> {
                    ClearConsole.Cls();
                    LinkedHashMap<String, Object> values = new LinkedHashMap<>();

                    System.out.println("\n-------------------Add a new course------------------\n");
                    System.out.print("Enter course name: ");
                    String courseName = scan.nextLine();
                    values.put("`course_name`", courseName);
                    System.out.print("Enter department name: ");
                    String departmentName = scan.nextLine();
                    values.put("`department_name`", departmentName);
                    if (!cc.courseConflictChecker(values)) {
                        cc.addCourse(courseName, departmentName);
                        Input.HoldState();
                    } else {
                        System.out.println("\n---------Course conflict detected. Please enter new course details.----------\n");
                    }
                }
                case 5 -> {
                    ClearConsole.Cls();
                    LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                    cc.displayAllCourse(cm);
                    boolean isValidInput = false;
                    while(!isValidInput){
                        System.out.print("Enter course name: ");
                        String courseName = scan.nextLine();
                        values.put("course_name", courseName);
                        System.out.print("Enter department name: ");
                        String departmentName = scan.nextLine();
                        values.put("department_name", departmentName);
                        if (cc.validateBothName(values)) {
                            isValidInput = true;

                        }
                        else {
                            System.out.println("\n----------Course not found. Please enter valid course details.---------\n");
                        }
                    }
                    System.out.print("Confirm delete: "+values.get("course_name")+" "+values.get("department_name") +" (Y/N): ");
                    char option = scan.nextLine().charAt(0);
                    if (option == 'Y' || option == 'y') {
                        cc.deleteCourse(values);
                    } else if (option == 'N' || option == 'n'){
                        System.out.println("\n--------------------Course deletion cancelled.------------------\n");
                    }
                    else {
                        System.out.println("\n--------------------Invalid option. Course deletion cancelled.------------------\n");
                    }


                }
                case 6 -> MainMenu.DisplayActionsMenu();
                default -> System.out.println("\n------------------Invalid option. Please try again.----------------\n");
            }
        }
    }
    
    
      static private void updateCourseField(int courseID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingCourse = true;

        while (updatingCourse) {
            System.out.println("\nSelect Course Field to update");
            System.out.println("1. Course Name");
            System.out.println("2. Course Department Name");
            System.out.println("3. Finish Update");

            System.out.print("Choose an option: ");
            String chooseStr = Input.getUserInput();
            int choose = Input.parseIfPossible(chooseStr);
          
            while(choose == 0)
            {
                Input.COut("Invalid Choice.");
                System.out.print("Choose an option: ");
                chooseStr = Input.getUserInput();
                choose = Input.parseIfPossible(chooseStr);
            }

            switch (choose) {
                case 1 -> {
                    System.out.print("Enter the new course name: ");
                    String newCourseName = scan.nextLine();
                    values.put("course_name", newCourseName);
                }
                case 2 -> {
                    System.out.print("Enter the new course department name: ");
                    String newDepartmentName = scan.nextLine();
                    values.put("department_name", newDepartmentName);
                }
                case 3 -> {
                    updatingCourse = false;
                    System.out.println("\n-------------------Course information update completed.-------------------\n");
                    cc.updateCourse(values, courseID);
                }
                default -> System.out.println("\n-------------------Invalid option. Please try again.-------------------\n");
            }
        }
    }
}

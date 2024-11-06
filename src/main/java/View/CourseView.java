/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.*;
import Model.*;
import Utils.ClearConsole;
import Utils.*;
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
    private static final ConsoleColors conc = new ConsoleColors();

    static void DisplayCourseView() throws InterruptedException
    {
        
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            ClearConsole.Cls();
            MainMenu.MainMenuHeader();
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_GREEN+"C O U R S E   M E N U"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.whiteLine);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[1] Display All Courses"+conc.BB_BLACK+"\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[2] Search for Course/s"+conc.BB_BLACK+"\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[3] Update for Courses"+conc.BB_BLACK+"\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[4] Add Course"+conc.BB_BLACK+"\t\t\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[5] Delete Course"+conc.BB_BLACK+"\t\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[6] Back to Main Menu"+conc.BB_BLACK+"\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.whiteLine);

            System.out.print(conc.YELLOW+"\n"+conc.center+"Choose an option: "+conc.RESET);
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
                    System.out.println(conc.whiteLine);
                    System.out.println(conc.whiteSpace);
                    System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"F I L T E R   C O U R S E"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
                    System.out.println(conc.whiteSpace);
                    System.out.println(conc.whiteLine);
                    System.out.println(conc.whiteSpace);
                    System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[1] Course Name"+conc.BB_BLACK+"\t\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
                    System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_BLUE+"[2] Department Name"+conc.BB_BLACK+"\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
                    System.out.println(conc.whiteSpace);
                    System.out.println(conc.whiteLine);

                    System.out.println("\n"+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_YELLOW+"Choose a column to filter by"+conc.BB_BLACK+"\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);
                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
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
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_YELLOW+"Enter the course name"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            String searchValue = scan.nextLine().toLowerCase();
                            cc.filterCourse("course_name", searchValue, cm);
                            Input.HoldState();
                        }
                        case 2 -> {
                            System.out.println("\n"+conc.yellowLine);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_YELLOW+"Enter the department name"+conc.BB_BLACK+"\t\t\t\t\t" +
                                    " "+conc.BB_YELLOW+" "+conc.RESET);
                            System.out.println(conc.yellowSpace);
                            System.out.println(conc.yellowLine);
                            System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                            String departmentName = scan.nextLine().toLowerCase();
                            cc.filterCourse("department_name", departmentName, cm);
                            Input.HoldState();
                        }

                        default ->{
                            System.out.println(conc.redLine);
                            System.out.println(conc.redSpace);
                            System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid input"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                    " "+conc.BB_RED+" "+conc.RESET);
                            System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                    " "+conc.BB_RED+" "+conc.RESET);
                            System.out.println(conc.redSpace);
                            System.out.println(conc.redLine);
                        }
                    }
                }
                case 3 -> {
                    ClearConsole.Cls();
                    cc.displayAllCourse(cm);
                    System.out.println("\n"+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_YELLOW+"Enter the Course ID"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);
                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                    String courseIdStr = Input.getUserInput();
                    int courseId = Input.parseIfPossible(courseIdStr);
                    
                    while(courseId == 0)
                    {
                        System.out.println("\n"+conc.yellowLine);
                        System.out.println(conc.yellowSpace);
                        System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t\t"+conc.BR_RED+"Wrong Input"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                                " "+conc.BB_YELLOW+" "+conc.RESET);
                        System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_YELLOW+"Enter the Course ID"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                " "+conc.BB_YELLOW+" "+conc.RESET);
                        System.out.println(conc.yellowSpace);
                        System.out.println(conc.yellowLine);
                        System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                        courseIdStr = Input.getUserInput();
                        courseId = Input.parseIfPossible(courseIdStr);
                    }
                    
                    updateCourseField(courseId);
                     Input.HoldState();

                }
                case 4 -> {
                    ClearConsole.Cls();
                    LinkedHashMap<String, Object> values = new LinkedHashMap<>();

                    System.out.println(conc.whiteLine);
                    System.out.println(conc.whiteSpace);
                    System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_WHITE+"A D D   A   N E W   C O U R S E"+conc.BB_BLACK+"\t\t\t\t\t" +
                            " "+conc.BB_WHITE+" "+conc.RESET);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_YELLOW+"Enter course name"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);

                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                    String courseName = scan.nextLine();

                    values.put("`course_name`", courseName);

                    System.out.println("\n"+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_YELLOW+"Enter department name"+conc.BB_BLACK+"\t\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);
                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                    String departmentName = scan.nextLine();
                    values.put("`department_name`", departmentName);
                    if (!cc.courseConflictChecker(values)) {
                        cc.addCourse(courseName, departmentName);
                        Input.HoldState();
                    } else {
                        System.out.println(conc.redLine);
                        System.out.println(conc.redSpace);
                        System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_RED+"Course conflict detected"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                " "+conc.BB_RED+" "+conc.RESET);
                        System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_RED+"Please enter new course details"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                " "+conc.BB_RED+" "+conc.RESET);
                        System.out.println(conc.redSpace);
                        System.out.println(conc.redLine);
                    }
                }
                case 5 -> {
                    ClearConsole.Cls();
                    LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                    cc.displayAllCourse(cm);
                    boolean isValidInput = false;
                    while(!isValidInput){
                        System.out.println(conc.whiteLine);
                        System.out.println(conc.whiteSpace);
                        System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_WHITE+"D E L E T E   A   C O U R S E"+conc.BB_BLACK+"\t\t\t\t\t" +
                                " "+conc.BB_WHITE+" "+conc.RESET);
                        System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_YELLOW+"Enter course name"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                " "+conc.BB_YELLOW+" "+conc.RESET);
                        System.out.println(conc.yellowSpace);
                        System.out.println(conc.yellowLine);

                        System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                        String courseName = scan.nextLine();

                        values.put("course_name", courseName);
                        System.out.println("\n"+conc.yellowLine);
                        System.out.println(conc.yellowSpace);
                        System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_YELLOW+"Enter department name"+conc.BB_BLACK+"\t\t\t\t\t" +
                                " "+conc.BB_YELLOW+" "+conc.RESET);
                        System.out.println(conc.yellowSpace);
                        System.out.println(conc.yellowLine);
                        System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                        String departmentName = scan.nextLine();
                        values.put("department_name", departmentName);
                        if (cc.validateBothName(values)) {
                            isValidInput = true;

                        }
                        else {
                            System.out.println(conc.redLine);
                            System.out.println(conc.redSpace);
                            System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Course not found"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                    " "+conc.BB_RED+" "+conc.RESET);
                            System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_RED+"Please enter valid course details"+conc.BB_BLACK+"\t\t\t\t" +
                                    " "+conc.BB_RED+" "+conc.RESET);
                            System.out.println(conc.redSpace);
                            System.out.println(conc.redLine);
                        }
                    }
                    System.out.println("\n"+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_YELLOW+"Confirm Delete"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_WHITE+values.get("course_name")+" "+values.get("department_name") + " (Y/N)"+conc.BB_BLACK+"\t\t\t\t" + " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(""+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_YELLOW+"Enter 'Y' for Yes or 'N' for No"+conc.BB_BLACK+"\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);
                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                    char option = scan.nextLine().charAt(0);
                    if (option == 'Y' || option == 'y') {
                        cc.deleteCourse(values);
                    } else if (option == 'N' || option == 'n'){
                        System.out.println(conc.whiteLine);
                        System.out.println(conc.whiteSpace);
                        System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_WHITE+"Course deletion cancelled"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
                        System.out.println(conc.whiteSpace);
                        System.out.println(conc.whiteLine);
                    }
                    else {
                        System.out.println(conc.redLine);
                        System.out.println(conc.redSpace);
                        System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid option"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                " "+conc.BB_RED+" "+conc.RESET);
                        System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_RED+"Course deletion cancelled"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                                " "+conc.BB_RED+" "+conc.RESET);
                        System.out.println(conc.redSpace);
                        System.out.println(conc.redLine);
                    }


                }
                case 6 -> MainMenu.DisplayActionsMenu();
                default -> {
                    System.out.println(conc.redLine);
                    System.out.println(conc.redSpace);
                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid option"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+conc.BB_RED+" "+conc.RESET);
                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+conc.BB_RED+" "+conc.RESET);
                    System.out.println(conc.redSpace);
                    System.out.println(conc.redLine);
                }
            }
        }
    }
    
    
      static private void updateCourseField(int courseID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingCourse = true;

        while (updatingCourse) {
            System.out.println(conc.whiteLine);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"Select Course Field to update"+conc.BB_BLACK+"\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[1] Course Name"+conc.BB_BLACK+"\t\t\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[2] Course Department Name"+conc.BB_BLACK+"\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_BLUE+"[3] Finish Update"+conc.BB_BLACK+"\t\t\t\t\t\t\t "+conc.BB_WHITE+" "+conc.RESET);
            System.out.println(conc.whiteSpace);
            System.out.println(conc.whiteLine);

            System.out.print(conc.YELLOW+conc.center+"Choose an option: "+conc.RESET);
            String chooseStr = Input.getUserInput();
            int choose = Input.parseIfPossible(chooseStr);
          
            while(choose == 0)
            {
                System.out.println("\n"+conc.yellowLine);
                System.out.println(conc.yellowSpace);
                System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t\t"+conc.BR_RED+"Invalid Input"+conc.BB_BLACK+"\t\t\t\t\t\t\t" +
                        " "+conc.BB_YELLOW+" "+conc.RESET);
                System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_YELLOW+"Choose an option"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                        " "+conc.BB_YELLOW+" "+conc.RESET);
                System.out.println(conc.yellowSpace);
                System.out.println(conc.yellowLine);
                System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                chooseStr = Input.getUserInput();
                choose = Input.parseIfPossible(chooseStr);
            }

            switch (choose) {
                case 1 -> {
                    System.out.println("\n"+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t\t"+conc.BR_YELLOW+"Enter the course name"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);
                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                    String newCourseName = scan.nextLine();
                    values.put("course_name", newCourseName);
                }
                case 2 -> {
                    System.out.println("\n"+conc.yellowLine);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.center+conc.BB_YELLOW+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_YELLOW+"Enter the new course department name"+conc.BB_BLACK+"\t\t\t" +
                            " "+conc.BB_YELLOW+" "+conc.RESET);
                    System.out.println(conc.yellowSpace);
                    System.out.println(conc.yellowLine);
                    System.out.print(conc.YELLOW+"\n"+conc.center+"\t\t\t\t\t\t\t"+conc.RESET);
                    String newDepartmentName = scan.nextLine();
                    values.put("department_name", newDepartmentName);
                }
                case 3 -> {
                    updatingCourse = false;
                    System.out.println("\n"+conc.whiteLine);
                    System.out.println(conc.whiteSpace);
                    System.out.println(conc.center+conc.BB_WHITE+" "+conc.BB_BLACK+"\t\t\t\t"+conc.BR_WHITE+"Course information update completed"+conc.BB_BLACK+"\t\t\t\t" +
                            " "+conc.BB_WHITE+" "+conc.RESET);
                    System.out.println(conc.whiteSpace);
                    System.out.println(conc.whiteLine);
                    cc.updateCourse(values, courseID);
                }
                default -> {
                    System.out.println(conc.redLine);
                    System.out.println(conc.redSpace);
                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Invalid option"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+conc.BB_RED+" "+conc.RESET);
                    System.out.println(conc.center+conc.BB_RED+" "+conc.BB_BLACK+"\t\t\t\t\t\t"+conc.BR_RED+"Please try again"+conc.BB_BLACK+"\t\t\t\t\t\t" +
                            " "+conc.BB_RED+" "+conc.RESET);
                    System.out.println(conc.redSpace);
                    System.out.println(conc.redLine);
                }
            }
        }
    }
}

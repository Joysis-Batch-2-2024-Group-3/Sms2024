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
public class ScheduleView {
    private static StudentController sc = new StudentController();
    private static final IndexController ic = new IndexController();
    private static final CourseController cc = new CourseController();
    private static Scanner scan = new Scanner(System.in);
    private static final ScheduleController sched = new ScheduleController();
    private static final SectionController sec = new SectionController();
    static private final SubjectController sub = new SubjectController();
    static void DisplayScheduleView() throws InterruptedException
    {
        ScheduleModel sm = new ScheduleModel();
        SubjectModel subm = new SubjectModel();
        SectionModel secm = new SectionModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            ClearConsole.Cls();
            Input.COut("Schedule Menu");
            System.out.println(" _____________________________________________ ");
            System.out.println("|                                             |");
            System.out.println("| 1. Search Schedule by Section               |");
            System.out.println("| 2. Search Schedule by Day                   |");
            System.out.println("| 3. Add Schedule                             |");
            System.out.println("| 4. Update Schedule                          |");
            System.out.println("| 5. Back to Main Menu                        |");
            System.out.println("|_____________________________________________|\n");
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
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    ClearConsole.Cls();
                    Input.COut("Result");
                    sched.displayScheduleBySection("section_tbl.section_name", searchValue, sm, subm, secm);
                    Input.HoldState();
                }
                case 2 -> {
                    System.out.print("Enter the day: ");
                    String day = scan.nextLine();
                    ClearConsole.Cls();
                    Input.COut("Result");
                    sched.displayScheduleByDay(day, sm, subm, secm);
                    Input.HoldState();
                }
                case 3 -> addScheduleSubMenu(subm, secm, cm, sm);
                case 4 -> {
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    sched.displayScheduleBySection("section_tbl.section_name", searchValue, sm, subm, secm);

                    System.out.println("Enter Schedule ID: ");
                    int scheduleId = scan.nextInt();
                    updateScheduleField(scheduleId);

                }
                case 5 -> MainMenu.DisplayActionsMenu();
                default -> Input.COut("Invalid option. Please try again.");
            }
        }
    }
    
    static private void addScheduleSubMenu(SubjectModel subm, SectionModel secm, CourseModel cm, ScheduleModel sm) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean run = true;
        while (run) {
            try {
                // Loop for Section ID
                boolean validSection = false;
                while (!validSection) {
                    sec.displayAllSection(secm, cm);
                    System.out.print("Enter Section ID: ");
                    int sectionId = scan.nextInt();

                    if (sec.isValidSectionValue("section_id", sectionId)) {
                        int courseId = cc.getCourseID("section_tbl", "section_id", sectionId);
                        sm.setSection_id(sectionId);
                        values.put("section_id", sectionId);
                        sched.displayScheduleBySection("section_tbl.section_id", sectionId, sm, subm, secm);
                        sub.displaySubjectByCourse("course_tbl.course_id", courseId, subm, cm);
                        validSection = true; // Valid section, exit loop
                    } else {
                        Input.COut("Invalid Section ID, Please try again.");
                    }
                }

                // Loop for Subject ID
                boolean validSubject = false;
                while (!validSubject) {
                    System.out.print("Enter Subject ID: ");
                    int subjectId = scan.nextInt();

                    if (sub.isValidSubjectValue("subject_id", subjectId)) {
                        sm.setSubject_id(subjectId);
                        validSubject = true; // Valid subject, exit loop
                    } else {
                        Input.COut("Invalid Subject ID, Please try again.");
                    }
                }
                run = false; // Exit outer loop if both IDs are valid

            } catch (SQLException e) {
                Input.COut("SQLException: " + e.getMessage());
            } catch (InputMismatchException e) {
                Input.COut("InputMismatchException: Please enter valid numeric IDs.");
                scan.next(); // Clear the invalid input
            } catch (Exception e) {
                Input.COut("Exception: " + e.getMessage());
            }
        }
        scan.nextLine(); // Clear the invalid
        String day = null;
        boolean validDay = false;
        while (!validDay) {
            System.out.print("Enter Day (Monday, Tuesday, Wednesday, etc.): ");
            day = scan.nextLine();
            if (day.matches("(?i)Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday")) {
                validDay = true; // Exit loop if valid
                sm.setDay(day);
                values.put("`day`", day);
            } else {
                Input.COut("Invalid day. Please enter a valid day of the week.");
            }
        }


        Time startTime = null;
        while (startTime == null) {
            System.out.print("Enter Start Time (HH:MM:SS Military): ");
            try {
                startTime = Time.valueOf(scan.nextLine());
                values.put("`start_time`", startTime);

            } catch (IllegalArgumentException e) {
                Input.COut("Invalid time format. Please enter in HH:MM:SS format.");
            }
        }

        Time endTime = null;
        while (endTime == null) {
            System.out.print("Enter End Time (HH:MM:SS Military): ");
            try {
                endTime = Time.valueOf(scan.nextLine());
                sm.setEnd_time(endTime);
                values.put("`end_time`", endTime);
            } catch (IllegalArgumentException e) {
                Input.COut("Invalid time format. Please enter in HH:MM:SS format.");
            }
        }
        sm.setStart_time(startTime);

        if (!sched.checkScheduleConflict(values)) {
            sched.addSchedule(sm, subm, secm);
        } else {
            Input.COut("Schedule conflict detected, please try again");
        }
    }
    
    static private void updateScheduleField(int SchedID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingSched = true;

        while (updatingSched) {
            Input.COut("Select to Update Schedule");
            System.out.println(" ________________________________________________ ");
            System.out.println("|                                                |");
            System.out.println("| 1. Update Day Schedule                         |");
            System.out.println("| 2. Update Start Time Schedule                  |");
            System.out.println("| 3. Update End Time Schedule                    |");
            System.out.println("| 4. Update Section ID                           |");
            System.out.println("| 5. Finish Update                               |");
            System.out.println("|________________________________________________\n");
            System.out.print("Enter to choose to update schedule: ");

            try {
                int chooseToUpdateSched = scan.nextInt();
                scan.nextLine(); // Clear the newline character

                switch (chooseToUpdateSched) {
                    case 1 -> {
                        String day = null;
                        boolean validDay = false;
                        while (!validDay) {
                            System.out.print("Enter Day (Monday, Tuesday, Wednesday, etc.): ");
                            day = scan.nextLine();
                            if (day.matches("(?i)Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday")) {
                                validDay = true; // Exit loop if valid
                                values.put("day", day);
                            } else {
                                Input.COut("Invalid day. Please enter a valid day of the week.");
                            }
                        }
                    }
                    case 2 -> {
                        Time startTime = null;
                        while (startTime == null) {
                            System.out.print("Enter to update new Start Time (HH:MM:SS): ");
                            try {
                                startTime = Time.valueOf(scan.nextLine());
                                values.put("start_time", startTime);

                            } catch (IllegalArgumentException e) {
                                Input.COut("Invalid time format. Please enter in HH:MM:SS format.");
                            }
                        }
                    }
                    
                    case 3 -> {
                        Time endTime = null;
                        while (endTime == null) {
                            System.out.print("Enter to update new End Time (HH:MM:SS): ");
                            try {
                                endTime = Time.valueOf(scan.nextLine());
                                values.put("end_time", endTime);

                            } catch (IllegalArgumentException e) {
                                Input.COut("Invalid time format. Please enter in HH:MM:SS format.");
                            }
                        }
                    }
                    case 4 -> {
                        sec.displayAllSection(new SectionModel(), new CourseModel());
                        // Validate Section ID
                        boolean isValidSection = false;
                        while (!isValidSection) {
                            System.out.print("Enter new Section ID: ");
                            int sectionId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                isValidSection = true;
                                values.put("section_id", sectionId);
                            } else {
                                Input.COut("Invalid section id. Please enter a valid section id.");
                            }
                        }
                    }

                    case 5 -> {
                        if (!values.isEmpty()) {
                            sched.updateSchedule(values, SchedID);
                        } else {
                            Input.COut("No fields to update.");
                        }
                        updatingSched = false;
                    }
                    default -> Input.COut("Invalid option. Please try again!");
                }
            } catch (InputMismatchException e) {
                Input.COut("Invalid input. Please enter a valid option.");
                scan.nextLine(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                Input.COut("Invalid time format. Please use HH:MM:SS.");
            }
        }
    }
}

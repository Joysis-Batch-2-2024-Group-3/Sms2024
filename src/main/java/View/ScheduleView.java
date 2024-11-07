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
public class ScheduleView {
    private static final CourseController cc = new CourseController();
    private static Scanner scan = new Scanner(System.in);
    private static final ScheduleController sched = new ScheduleController();
    private static final SectionController sec = new SectionController();
    static private final SubjectController sub = new SubjectController();
    private static final ConsoleColors conc = new ConsoleColors();

    static void DisplayScheduleView() throws InterruptedException {
        ScheduleModel sm = new ScheduleModel();
        SubjectModel subm = new SubjectModel();
        SectionModel secm = new SectionModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            ClearConsole.Cls();
            MainMenu.MainMenuHeader();
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_GREEN
                    + "S C H E D U L E   M E N U" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Search Schedule by Section" + conc.BB_BLACK + "\t\t\t\t " + conc.BB_WHITE + " "
                    + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Search Schedule by Day " + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[3] Add Schedule"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_BLUE + "[4] Update Schedule"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
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
                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                            + "Enter the section name or substring" + conc.BB_BLACK + "\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    String searchValue = scan.nextLine();
                    ClearConsole.Cls();
                    Input.COut("Result");
                    sched.displayScheduleBySection("section_tbl.section_name", searchValue, sm, subm, secm);
                    Input.HoldState();
                }
                case 2 -> {
                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_YELLOW
                            + "Enter the day" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    String day = scan.nextLine();
                    ClearConsole.Cls();
                    Input.COut("Result");
                    sched.displayScheduleByDay(day, sm, subm, secm);
                    Input.HoldState();
                }
                case 3 -> addScheduleSubMenu(subm, secm, cm, sm);
                case 4 -> {
                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                            + "Enter the section name or substring" + conc.BB_BLACK + "\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    String searchValue = scan.nextLine();
                    sched.displayScheduleBySection("section_tbl.section_name", searchValue, sm, subm, secm);

                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                            + "Enter Schedule ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    int scheduleId = scan.nextInt();
                    updateScheduleField(scheduleId);

                }
                case 5 -> MainMenu.DisplayActionsMenu();
                default -> {
                    Sout(conc.redLine);
                    Sout(conc.redSpace);
                    Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                            + "Invalid Option" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
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

    static private void addScheduleSubMenu(SubjectModel subm, SectionModel secm, CourseModel cm, ScheduleModel sm) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean run = true;
        while (run) {
            try {
                // Loop for Section ID
                boolean validSection = false;
                while (!validSection) {
                    sec.displayAllSection(secm, cm);
                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                            + "Enter Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    int sectionId = scan.nextInt();

                    if (sec.isValidSectionValue("section_id", sectionId)) {
                        int courseId = cc.getCourseID("section_tbl", "section_id", sectionId);
                        sm.setSection_id(sectionId);
                        values.put("section_id", sectionId);
                        sched.displayScheduleBySection("section_tbl.section_id", sectionId, sm, subm, secm);
                        sub.displaySubjectByCourse("course_tbl.course_id", courseId, subm, cm);
                        validSection = true; // Valid section, exit loop
                    } else {
                        Sout(conc.redLine);
                        Sout(conc.redSpace);
                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                + "Invalid Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                " " + conc.BB_RED + " " + conc.RESET);
                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_RED + " " + conc.RESET);
                        Sout(conc.redSpace);
                        Sout(conc.redLine);
                    }
                }

                // Loop for Subject ID
                boolean validSubject = false;
                while (!validSubject) {
                    Sout("\n" + conc.yellowLine);
                    Sout(conc.yellowSpace);
                    Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                            + "Enter Subject ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                            " " + conc.BB_YELLOW + " " + conc.RESET);
                    Sout(conc.yellowSpace);
                    Sout(conc.yellowLine);
                    System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                    int subjectId = scan.nextInt();

                    if (sub.isValidSubjectValue("subject_id", subjectId)) {
                        sm.setSubject_id(subjectId);
                        validSubject = true; // Valid subject, exit loop
                    } else {
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
            Sout("\n" + conc.yellowLine);
            Sout(conc.yellowSpace);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_YELLOW + "Enter Day"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                    " " + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + conc.BR_YELLOW
                    + "\t\t\t(Monday, Tuesday, Wednesday, Thursday, Friday," + conc.BB_BLACK + "\t\t "
                    + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_YELLOW
                    + "Saturday, Sunday)" + conc.BB_BLACK + "\t\t\t\t\t" +
                    " " + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.yellowSpace);
            Sout(conc.yellowLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
            day = scan.nextLine();
            if (day.matches("(?i)Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday")) {
                validDay = true; // Exit loop if valid
                sm.setDay(day);
                values.put("`day`", day);
            } else {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED + "Invalid Day"
                        + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                        + "Please enter a valid day of the week" + conc.BB_BLACK + "\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
            }
        }

        Time startTime = null;
        while (startTime == null) {
            Sout("\n" + conc.yellowLine);
            Sout(conc.yellowSpace);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW
                    + "Enter Start Time" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                    " " + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + conc.BR_YELLOW + "\t\t\t\t\t\t(HH:MM:SS Military)"
                    + conc.BB_BLACK + "\t\t\t\t\t\t "
                    + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.yellowSpace);
            Sout(conc.yellowLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
            try {
                startTime = Time.valueOf(scan.nextLine());
                values.put("`start_time`", startTime);

            } catch (IllegalArgumentException e) {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                        + "Invalid time format" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                        + "Please enter in HH:MM:SS format" + conc.BB_BLACK + "\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
            }
        }

        Time endTime = null;
        while (endTime == null) {
            Sout("\n" + conc.yellowLine);
            Sout(conc.yellowSpace);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_YELLOW + "Enter End Time"
                    + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                    " " + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + conc.BR_YELLOW + "\t\t\t\t\t\t(HH:MM:SS Military)"
                    + conc.BB_BLACK + "\t\t\t\t\t\t "
                    + conc.BB_YELLOW + " " + conc.RESET);
            Sout(conc.yellowSpace);
            Sout(conc.yellowLine);
            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
            try {
                endTime = Time.valueOf(scan.nextLine());
                sm.setEnd_time(endTime);
                values.put("`end_time`", endTime);
            } catch (IllegalArgumentException e) {
                Sout(conc.redLine);
                Sout(conc.redSpace);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                        + "Invalid time format" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                        + "Please enter in HH:MM:SS format" + conc.BB_BLACK + "\t\t\t\t\t" +
                        " " + conc.BB_RED + " " + conc.RESET);
                Sout(conc.redSpace);
                Sout(conc.redLine);
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
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "Select to Update Schedule" + conc.BB_BLACK + "\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);
            Sout(conc.whiteSpace);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[1] Update Day Schedule" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[2] Update Start Time Schedule" + conc.BB_BLACK + "\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[3] Update End Time Schedule" + conc.BB_BLACK + "\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE
                    + "[4] Update Section ID" + conc.BB_BLACK + "\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.center + conc.BB_WHITE + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_BLUE + "[5] Finish Update"
                    + conc.BB_BLACK + "\t\t\t\t\t\t " + conc.BB_WHITE + " " + conc.RESET);
            Sout(conc.whiteSpace);
            Sout(conc.whiteLine);

            System.out.print(conc.YELLOW + "\n" + conc.center + "Enter to choose to update schedule: " + conc.RESET);
            try {
                int chooseToUpdateSched = scan.nextInt();
                scan.nextLine(); // Clear the newline character

                switch (chooseToUpdateSched) {
                    case 1 -> {
                        String day = null;
                        boolean validDay = false;
                        while (!validDay) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter Day" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + conc.BR_YELLOW
                                    + "\t\t\t(Monday, Tuesday, Wednesday, Thursday, Friday," + conc.BB_BLACK + "\t\t "
                                    + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Saturday, Sunday)" + conc.BB_BLACK + "\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            day = scan.nextLine();
                            if (day.matches("(?i)Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday")) {
                                validDay = true; // Exit loop if valid
                                values.put("day", day);
                            } else {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid Day" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                        + "Please enter a valid day of the week" + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                    }
                    case 2 -> {
                        Time startTime = null;
                        while (startTime == null) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter to Update New Start Time" + conc.BB_BLACK + "\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + conc.BR_YELLOW
                                    + "\t\t\t\t\t\t(HH:MM:SS Military)" + conc.BB_BLACK + "\t\t\t\t\t\t "
                                    + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            try {
                                startTime = Time.valueOf(scan.nextLine());
                                values.put("start_time", startTime);

                            } catch (IllegalArgumentException e) {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid time format" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                        + "Please enter in HH:MM:SS format" + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                    }

                    case 3 -> {
                        Time endTime = null;
                        while (endTime == null) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter to Update New End Time" + conc.BB_BLACK + "\t\t\t\t\t" +
                                    " " + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + conc.BR_YELLOW
                                    + "\t\t\t\t\t\t(HH:MM:SS Military)" + conc.BB_BLACK + "\t\t\t\t\t\t "
                                    + conc.BB_YELLOW + " " + conc.RESET);
                            Sout(conc.yellowSpace);
                            Sout(conc.yellowLine);
                            System.out.print(conc.YELLOW + "\n" + conc.center + "\t\t\t\t\t\t\t" + conc.RESET);
                            try {
                                endTime = Time.valueOf(scan.nextLine());
                                values.put("end_time", endTime);

                            } catch (IllegalArgumentException e) {
                                Sout(conc.redLine);
                                Sout(conc.redSpace);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t\t" + conc.BR_RED
                                        + "Invalid time format" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_RED
                                        + "Please enter in HH:MM:SS format" + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
                            }
                        }
                    }
                    case 4 -> {
                        sec.displayAllSection(new SectionModel(), new CourseModel());
                        // Validate Section ID
                        boolean isValidSection = false;
                        while (!isValidSection) {
                            Sout("\n" + conc.yellowLine);
                            Sout(conc.yellowSpace);
                            Sout(conc.center + conc.BB_YELLOW + " " + conc.BB_BLACK + "\t\t\t\t\t" + conc.BR_YELLOW
                                    + "Enter new Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t" +
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
                                        + "Invalid Section ID" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t" + conc.BR_RED
                                        + "Please enter a valid Section ID" + conc.BB_BLACK + "\t\t\t\t\t" +
                                        " " + conc.BB_RED + " " + conc.RESET);
                                Sout(conc.redSpace);
                                Sout(conc.redLine);
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
                    default -> {
                        Sout(conc.redLine);
                        Sout(conc.redSpace);
                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                + "Invalid Option" + conc.BB_BLACK + "\t\t\t\t\t\t\t" +
                                " " + conc.BB_RED + " " + conc.RESET);
                        Sout(conc.center + conc.BB_RED + " " + conc.BB_BLACK + "\t\t\t\t\t\t" + conc.BR_RED
                                + "Please try again" + conc.BB_BLACK + "\t\t\t\t\t\t" +
                                " " + conc.BB_RED + " " + conc.RESET);
                        Sout(conc.redSpace);
                        Sout(conc.redLine);
                    }
                }
            } catch (InputMismatchException e) {
                Input.COut("Invalid input. Please enter a valid option.");
                scan.nextLine(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                Input.COut("Invalid time format. Please use HH:MM:SS.");

            }
        }
    }


    private static void Sout(String input) {
        System.out.println(input);
    }
}

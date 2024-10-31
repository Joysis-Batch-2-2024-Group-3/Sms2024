package View;

import Controller.*;
import Model.*;

import java.sql.Time;
import java.util.Arrays;
import java.util.Scanner;

public class Temp_View {
    private final StudentController sc = new StudentController();
    private final SubjectController sub = new SubjectController();
    private final SectionController sec = new SectionController();
    private final CourseController cc = new CourseController();
    private final ScheduleController sched = new ScheduleController();
    private final Student_SubjectController ssc = new Student_SubjectController();
    private final Scanner scan = new Scanner(System.in);

    public void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Student Menu");
            System.out.println("2. Course Menu");
            System.out.println("3. Section Menu");
            System.out.println("4. Subject Menu");
            System.out.println("5. Schedule Menu");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> courseMenu();
                case 3 -> sectionMenu();
                case 4 -> subjectMenu();
                case 5 -> scheduleMenu();
                case 6 -> {
                    running = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void studentMenu() {
        StudentModel sm = new StudentModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Display All Students");
            System.out.println("2. Search for Student/s");
            System.out.println("3. Display Archived Students");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> sc.displayStudents(sm, cm, secm);
                case 2 -> {
                    System.out.println("==== STUDENT SUB MENU====");
                    System.out.println("1. Search by section");
                    System.out.println("2. Search by course");
                    System.out.println("3. Search by First Name");
                    System.out.println("4. Search by Last Name");
                    System.out.println("5. Search by Student ID");
                    System.out.println("6. Search by specifying a column");
                    System.out.println("7. Back to Main Menu");
                    System.out.print("Choose an option: ");
                    int i = scan.nextInt();
                    scan.nextLine();
                    studentSubMenu(i);
                }
                case 3 -> sc.displayArchivedStudents(sm, cm, secm);
                case 4 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void courseMenu() {
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Course Menu ===");
            System.out.println("1. Display All Courses");
            System.out.println("2. Search for Course/s");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> cc.displayAllCourse(cm);
                case 2 -> {
                    System.out.println("======= Filter Course =======");
                    System.out.println("1. Course Name");
                    System.out.println("2. Department Name");
                    System.out.print("Choose a column to filter by: ");
                    int option = scan.nextInt();
                    scan.nextLine();

                    switch (option) {
                        case 1 -> {
                            System.out.print("Enter the course name: ");
                            String searchValue = scan.nextLine().toLowerCase();
                            cc.filterCourse("course_name", searchValue, cm);
                        }
                        case 2 -> {
                            System.out.print("Enter the department name: ");
                            String departmentName = scan.nextLine().toLowerCase();
                            cc.filterCourse("department_name", departmentName, cm);
                        }
                        default -> System.out.println("Invalid option. Please try again.");
                    }
                }
                case 3 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void sectionMenu() {
        SectionModel sm = new SectionModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Section Menu ===");
            System.out.println("1. Display All Sections");
            System.out.println("2. Search for Section/s");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> sec.displayAllSection(sm, cm);
                case 2 -> {
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    sec.filterSection(searchValue, sm, cm);
                }
                case 3 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void subjectMenu() {
        SubjectModel subm = new SubjectModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Subject Menu ===");
            System.out.println("1. Display All Subjects");
            System.out.println("2. Filter Subject by Course");
            System.out.println("3. Display Student Subject");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> sub.displayAllSubject(subm, cm);
                case 2 -> {
                    System.out.print("Enter the course name or substring: ");
                    String searchValue = scan.nextLine();
                    sub.displaySubjectByCourse(searchValue, subm, cm);
                }
                case 3 -> {
                    studentSubjectMenu();
                }
                case 4 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public void studentSubjectMenu() {
        StudentModel sm = new StudentModel();
        SubjectModel sub = new SubjectModel();
        SectionModel secm = new SectionModel();
        Student_SubjectModel ssm = new Student_SubjectModel();

        boolean running = true;

        while (running) {
            System.out.println("=== Student Subject Menu ===");
            System.out.println("1. Display Student Subjects based on Student's name");
            System.out.println("2. Display Student Subjects based on Subject's name");
            System.out.println("3. Display Student Subjects based on Section name");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the student's name or substring: ");
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectByStudent(searchValue, ssm, sm, sub, secm);
                }
                case 2 -> {
                    System.out.print("Enter the subject's name or substring: ");
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectBySubject(searchValue, ssm, sm, sub, secm);
                }
                case 3 -> {
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectBySection(searchValue, ssm, sm, sub, secm);
                }
                case 4 -> {
                    running = false;
                    System.out.println("Returning to Main Menu...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void scheduleMenu() {
        ScheduleModel sm = new ScheduleModel();
        SubjectModel subm = new SubjectModel();
        SectionModel secm = new SectionModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Schedule Menu ===");
            System.out.println("1. Display All Schedule");
            System.out.println("2. Search Schedule by Section");
            System.out.println("3. Search Schedule by Day");
            System.out.println("4. Add Schedule");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> sched.displayAllSchedule(sm, subm, secm);
                case 2 -> {
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    sched.displayScheduleBySection(searchValue, sm, subm, secm);
                }
                case 3 -> {
                    System.out.print("Enter the day: ");
                    String day = scan.nextLine();
                    sched.displayScheduleByDay(day, sm, subm, secm);
                }
                case 4 -> {
                    String day = null;
                    boolean validDay = false;
                    while (!validDay) {
                        System.out.print("Enter Day (Monday, Tuesday, Wednesday, etc.): ");
                        day = scan.nextLine();
                        if (day.matches("(?i)Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday")) {
                            validDay = true; // Exit loop if valid
                        } else {
                            System.out.println("Invalid day. Please enter a valid day of the week.");
                        }
                    }

                    Time startTime = null;
                    while (startTime == null) {
                        System.out.print("Enter Start Time (HH:MM:SS Military): ");
                        try {
                            startTime = Time.valueOf(scan.nextLine());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid time format. Please enter in HH:MM:SS format.");
                        }
                    }

                    Time endTime = null;
                    while (endTime == null) {
                        System.out.print("Enter End Time (HH:MM:SS Military): ");
                        try {
                            endTime = Time.valueOf(scan.nextLine());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid time format. Please enter in HH:MM:SS format.");
                        }
                    }
                    sub.displayAllSubject(subm, cm);
                    System.out.print("Enter Subject ID: ");
                    int subjectId = scan.nextInt();

                    sec.displayAllSection(secm, cm);
                    System.out.print("Enter Section ID: ");
                    int sectionId = scan.nextInt();
                    scan.nextLine(); // Consume the newline character

                    sm.setDay(day);
                    sm.setStart_time(startTime);
                    sm.setEnd_time(endTime);
                    sm.setSubject_id(subjectId);
                    sm.setSection_id(sectionId);

                    sched.addSchedule(sm, subm, secm);
                }
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void studentSubMenu(int number) {
        StudentModel sm = new StudentModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();

        switch (number) {
            case 1 -> {
                System.out.print("Enter Section Name: ");
                String value = scan.nextLine();
                sc.displayStudentbySection(value, sm, cm, secm);
            }
            case 2 -> {
                System.out.print("Enter Course Name: ");
                String value = scan.nextLine();
                sc.displayStudentbyCourse(value, sm, cm, secm);
            }
            case 3 -> {
                System.out.print("Enter First Name: ");
                String value = scan.nextLine();
                sc.displayStudentbyFName(value, sm, cm, secm);
            }
            case 4 -> {
                System.out.print("Enter Last Name: ");
                String value = scan.nextLine();
                sc.displayStudentbyLname(value, sm, cm, secm);
            }
            case 5 -> {
                System.out.print("Enter Student ID: ");
                String value = scan.nextLine();
                sc.displayStudentbyStudent_ID(value, sm, cm, secm);
            }
            case 6 -> {
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
                    columnName = scan.nextLine();
                    // Check if the input columnName is valid
                    if (Arrays.asList(StudentController.validColumns).contains(columnName)) {
                        validColumn = true; // Exit loop if valid
                    } else {
                        System.out.println("Invalid column name. Please choose from the valid columns.");
                    }
                }

                System.out.print("Enter Value: ");
                String columnValue = scan.nextLine();
                System.out.println("Searching for " + columnValue + " in " + columnName);
                sc.filterStudent(columnName, columnValue, sm, cm, secm);

            }
            case 7 -> System.out.println("Returning to Student Menu...");
            default -> System.out.println("Invalid option. Please try again.");
        }
    }
    public static void main(String[] args) {
        Temp_View view = new Temp_View();
        view.mainMenu();
    }

}

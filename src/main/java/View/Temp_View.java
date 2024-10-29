package View;

import Controller.*;
import Model.*;
import java.util.Scanner;

public class Temp_View {
    private final StudentController sc = new StudentController();
    private final SubjectController sub = new SubjectController();
    private final SectionController sec = new SectionController();
    private final CourseController cc = new CourseController();
    private final ScheduleController sched = new ScheduleController();
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
        SubjectModel sm = new SubjectModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Subject Menu ===");
            System.out.println("1. Display All Subjects");
            System.out.println("2. Filter Subject by Course");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> sub.displayAllSubject(sm, cm);
                case 2 -> {
                    System.out.print("Enter the course name or substring: ");
                    String searchValue = scan.nextLine();
                    sub.displaySubjectByCourse(searchValue, sm, cm);
                }
                case 3 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void scheduleMenu() {
        ScheduleModel sm = new ScheduleModel();
        SubjectModel sub = new SubjectModel();
        SectionModel secm = new SectionModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Schedule Menu ===");
            System.out.println("1. Display All Schedule");
            System.out.println("2. Search Schedule by Section");
            System.out.println("3. Search Schedule by Day");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> sched.displayAllSchedule(sm, sub, secm);
                case 2 -> {
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    sched.displayScheduleBySection(searchValue, sm, sub, secm);
                }
                case 3 -> {
                    System.out.print("Enter the day: ");
                    String day = scan.nextLine();
                    sched.displayScheduleByDay(day, sm, sub, secm);
                }
                case 4 -> running = false;
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
                System.out.print("\nEnter Column Name: ");
                String columnName = scan.nextLine();
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

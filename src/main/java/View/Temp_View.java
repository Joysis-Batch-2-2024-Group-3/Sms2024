package View;

import Controller.StudentController;
import Model.StudentModel;
import Model.CourseModel;
import Model.SectionModel;
import Model.SubjectModel;
import java.util.Scanner;
import Controller.SectionController;
import Controller.CourseController;
import Controller.SubjectController;
public class Temp_View {
    StudentController sc = new StudentController();
    SubjectController sub = new SubjectController();
    SectionController sec = new SectionController();
    CourseController cc = new CourseController();
    Scanner scan = new Scanner(System.in);

    public void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Student Menu");
            System.out.println("2. Course Menu");
            System.out.println("3. Section Menu");
            System.out.println("4. Subject Menu");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    courseMenu();
                    break;
                case 3:
                    sectionMenu();
                    break;
                case 4:
                    subjectMenu();
                    break;
                case 5:
                    running = false; // Exit the loop
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
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
            scan.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    sc.displayStudents(sm, cm, secm);
                    break;
                case 2:
                    System.out.print("Enter the column key (first_name, last_name, student_id, etc.): ");
                    String searchKey = scan.nextLine();
                    System.out.print("Enter the filter value: ");
                    String searchValue = scan.nextLine();
                    sc.filterStudent(searchKey, searchValue, sm, cm, secm);
                    break;
                case 3:
                    sc.displayArchivedStudents(sm, cm, secm);
                    break;
                case 4:
                    running = false; // Exit the loop and go back to the main menu
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void courseMenu() {
        Scanner sc = new Scanner(System.in);
        CourseModel cm = new CourseModel(); // Moved here to ensure a fresh instance
        boolean running = true;
        while (running) {
            System.out.println("\n=== Course Menu ===");
            System.out.println("1. Display All Courses");
            System.out.println("2. Search for Course/s");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    cc.displayAllCourse(cm);
                    break;
                case 2:
                    System.out.println("======= Filter Course =======");
                    System.out.println("1. Course Name");
                    System.out.println("2. Department Name");
                    System.out.print("Choose a column to filter by: ");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1:
                            System.out.print("Enter the course name (case-insensitive): ");
                            String searchValue = scan.nextLine().toLowerCase();
                            cc.filterCourse("course_name", searchValue, cm);
                            break;
                        case 2:
                            System.out.print("Enter the department name (case-insensitive): ");
                            String departmentName = scan.nextLine().toLowerCase();
                            cc.filterCourse("department_name", departmentName, cm);
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case 3:
                    running = false; // Exit the loop and go back to the main menu
                    break;
                default:
                    System.out.println("Invalid option. Please try again .");
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
                case 1:
                    sec.displayAllSection(sm, cm);
                    break;
                case 2:
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    sec.filterSection(searchValue, sm, cm);
                    // Implement search functionality for sections here
                    break;
                case 3:
                    running = false; // Exit the loop and go back to the main menu
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public void subjectMenu(){
        SubjectModel sm = new SubjectModel();
        CourseModel cm = new CourseModel();
        boolean running = true;
        while (running) {
            System.out.println("\n=== Subject Menu ===");
            System.out.println("1. Display All Subjects");
            System.out.println("2. Search Subjects by Course");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    sub.displayAllSubject(sm, cm);
                    break;
                case 2:
                    System.out.print("Enter the course name or substring: ");
                    String searchValue = scan.nextLine();
                    sub.displaySubjectByCourse(searchValue, sm, cm);
                    break;
                case 3:
                    running = false; // Exit the loop and go back to the main menu
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            }

    }


    public static void main(String[] args) {
        Temp_View view = new Temp_View();
        view.mainMenu(); // Start with the main menu
    }
}
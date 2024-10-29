package View;

import Controller.StudentController;
import Model.StudentModel;
import Model.CourseModel;
import Model.SectionModel;
import java.util.Scanner;
import Controller.SectionController;
import Controller.CourseController;

public class Temp_View {
    StudentController sc = new StudentController();
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
            System.out.println("4. Exit");
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
                    // Implement search functionality for courses here
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
            scan.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    sec.displayAllSection(sm, cm);
                    break;
                case 2:
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

    public static void main(String[] args) {
        Temp_View view = new Temp_View();
        view.mainMenu(); // Start with the main menu
    }
}
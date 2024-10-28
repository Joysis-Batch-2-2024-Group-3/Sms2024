package View;

import Controller.StudentController;
import Model.StudentModel;
import Model.CourseModel;
import Model.SectionModel;
import java.util.Scanner;

public class Temp_View {
    StudentController sc = new StudentController();
    Scanner scan = new Scanner(System.in);

    public void studentMenu() {
        StudentModel student = new StudentModel();
        CourseModel cm = new CourseModel();
        SectionModel sm = new SectionModel();

        boolean running = true;
        while (running) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Display All Students");
            System.out.println("2. Search for Student/s");
            System.out.println("3. Display Archived Students");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    sc.displayStudents(student, cm, sm);
                    break;
                case 2:
                    System.out.print("Enter the column key (first_name, last_name, student_id, etc.): ");
                    String searchKey = scan.nextLine();
                    System.out.print("Enter the filter value: ");
                    String searchValue = scan.nextLine();
                    sc.filterStudent(searchKey, searchValue, student, cm, sm);
                    break;
                case 3:
                    sc.displayArchivedStudents(student, cm, sm);
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

    public static void main(String[] args) {
        Temp_View view = new Temp_View();
        view.studentMenu();
    }
}
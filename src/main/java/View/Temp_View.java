package View;

import Controller.StudentController;
import Model.StudentModel;
public class Temp_View {
    StudentController sc = new StudentController();

    public  void studentMenu() {
        StudentModel student = new StudentModel();
        sc.displayStudents(student);

    }
    public static void main(String[] args) {
        Temp_View view = new Temp_View();
        view.studentMenu();
    }

}

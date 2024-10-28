package View;

import Controller.StudentController;
import Model.StudentModel;
import Model.CourseModel;
import Model.SectionModel;
public class Temp_View {
    StudentController sc = new StudentController();

    public  void studentMenu() {
        StudentModel student = new StudentModel();
        CourseModel cm = new CourseModel();
        SectionModel sm = new SectionModel();
        sc.displayArchivedStudents(student, cm, sm);
        sc.displayStudents(student, cm, sm);

    }
    public static void main(String[] args) {
        Temp_View view = new Temp_View();
        view.studentMenu();
    }

}

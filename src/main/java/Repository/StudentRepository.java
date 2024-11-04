package Repository;

import Model.AdminModel;
import Model.CourseModel;
import Model.SectionModel;
import Model.StudentModel;

public interface StudentRepository {
    public void displayArchivedStudents(StudentModel student, CourseModel course, SectionModel section);//done
    public void displayStudents( StudentModel student, CourseModel Course, SectionModel Section);
    public void displayStudentbySection(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void displayStudentbyCourse(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void displayStudentbyFName(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void displayStudentbyLname(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void displayStudentbyStudent_ID(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void addStudent(StudentModel student, CourseModel  course, SectionModel section);
    public void filterStudent( String key, String value, StudentModel student, CourseModel Course, SectionModel Section);
    public void dropStudent(StudentModel studentModel);
    public void updateStudent(StudentModel student);
    public boolean authenticateAdmin(AdminModel admin);
    public boolean isValidStudent(String Column, Object Value);
}

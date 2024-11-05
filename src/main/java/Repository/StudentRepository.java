package Repository;

import Model.AdminModel;
import Model.CourseModel;
import Model.SectionModel;
import Model.StudentModel;

import java.util.LinkedHashMap;

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
    public void dropStudent(LinkedHashMap<String, Object>values);
    public void updateStudent(LinkedHashMap<String, Object>values, int Studentid);
    public boolean isValidStudent(String Column, Object Value);
    public boolean isValidStudentName(String firstName, String lastName);

}

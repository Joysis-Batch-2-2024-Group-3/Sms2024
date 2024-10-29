package Repository;

import Model.CourseModel;
import Model.SectionModel;
import Model.StudentModel;

public interface StudentRepository {
    public void displayArchivedStudents(StudentModel student,  CourseModel Course, SectionModel Section);//done
    public void displayStudents( StudentModel student, CourseModel Course, SectionModel Section);
    public void displayStudentbySection(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void displayStudentbyCourse(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void displayStudentbyFName(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void displayStudentbyLname(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void displayStudentbyStudent_ID(String Value, StudentModel sm, CourseModel cm, SectionModel secm);
    public void filterStudent( String key, String value, StudentModel student, CourseModel Course, SectionModel Section);

}

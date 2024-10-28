package Repository;

import Model.AdminModel;
import Model.CourseModel;
import Model.SectionModel;
import Model.StudentModel;

public interface StudentRepository {
    public void displayArchivedStudents(StudentModel student,  CourseModel Course, SectionModel Section);//done
    public void displayStudents( StudentModel student, CourseModel Course, SectionModel Section);
    public void addStudent(StudentModel student, CourseModel  course, SectionModel section);
    public void filterStudent( String key, String value, StudentModel student, CourseModel Course, SectionModel Section);
    public void dropStudent(StudentModel studentModel);
    public boolean authenticateAdmin(AdminModel admin);

}

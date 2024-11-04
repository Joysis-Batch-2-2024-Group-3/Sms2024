package Repository;

import Model.SectionModel;
import Model.StudentModel;
import Model.Student_SubjectModel;
import Model.SubjectModel;

import java.util.LinkedHashMap;

public interface Student_SubjectRepository {
    public void displayStudentsSubjectByStudent(String Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel sec);
    public void displayStudentsSubjectBySubject(String Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel sec);
    public void displayStudentsSubjectBySection(String Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel sec);
    public void addStudentSubject (LinkedHashMap<String, Object> Value);
}

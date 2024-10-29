package Repository;

import Model.StudentModel;
import Model.Student_SubjectModel;
import Model.SubjectModel;

public interface Student_SubjectRepository {
    public void displayStudentsSubjectByStudent(Student_SubjectModel ssm, StudentModel sm, SubjectModel subm);
    public void displayStudentsSubjectBySubject(Student_SubjectModel ssm, StudentModel sm, SubjectModel subm);

}

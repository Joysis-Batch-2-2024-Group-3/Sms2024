package Repository;

import Model.CourseModel;
import Model.SubjectModel;

public interface SubjectRepository {
    public void displayAllSubject(SubjectModel subject, CourseModel course);
    public void displaySubjectByCourse(String value,SubjectModel subject, CourseModel course);
}

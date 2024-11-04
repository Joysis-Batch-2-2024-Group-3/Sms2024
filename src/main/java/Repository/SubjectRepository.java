package Repository;

import Model.CourseModel;
import Model.SubjectModel;


public interface SubjectRepository {
    public void displayAllSubject(SubjectModel subject, CourseModel course);
    public void displaySubjectByCourse(String key,Object value,SubjectModel subject, CourseModel course);
    
    public void addSubject(SubjectModel subject);
    public void deleteSubject(String subjectName);
    public void editSubject(String oldSubjectName, String newSubjectName, int newCourseId);
}

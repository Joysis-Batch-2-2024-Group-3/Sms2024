package Repository;

import Model.CourseModel;
import Model.SubjectModel;

import java.util.LinkedHashMap;


public interface SubjectRepository {
    public void displayAllSubject(SubjectModel subject, CourseModel course);
    public void displaySubjectByCourse(String key,Object value,SubjectModel subject, CourseModel course);
    public void addSubject(SubjectModel subject);
    public void deleteSubject(LinkedHashMap<String, Object>values);
    public void updateSubject(LinkedHashMap<String, Object>values, int subjectID);
    public boolean subjectConflictChecker(LinkedHashMap<String, Object>values);
}

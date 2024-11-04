package Repository;

import Model.CourseModel;

import java.util.LinkedHashMap;

public interface CourseRepository {
    public void displayAllCourse(CourseModel course);
    public void filterCourse(String key, String value, CourseModel course);
    public boolean isValidCourse(String column, Object value);
    public void addCourse(String CourseNmae, String Department);
    public boolean courseConflictChecker(LinkedHashMap<String, Object>values);
}

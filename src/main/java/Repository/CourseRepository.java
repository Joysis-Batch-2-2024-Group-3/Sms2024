package Repository;

import Model.CourseModel;

public interface CourseRepository {
    public void displayAllCourse(CourseModel course);
    public void filterCourse(String key, String value, CourseModel course);
    public boolean isValidCourse(String column, int value);
}

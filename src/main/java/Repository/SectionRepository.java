package Repository;

import Model.CourseModel;
import Model.SectionModel;

public interface SectionRepository {
    public void displayAllSection(SectionModel section, CourseModel course);
    public void filterSection(String key, Object value, SectionModel section, CourseModel course);
}


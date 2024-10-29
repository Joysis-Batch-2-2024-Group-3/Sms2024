package Repository;

import Model.CourseModel;
import Model.SectionModel;

public interface SectionRepository {
    public void displayAllSection(SectionModel section, CourseModel course);
}

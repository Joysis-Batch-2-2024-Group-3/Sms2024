package Repository;

import Model.CourseModel;
import Model.SectionModel;

public interface SectionRepository {
    public void displayAllSection(SectionModel section, CourseModel course);
<<<<<<< HEAD
    public void filterSection(String value, SectionModel section, CourseModel course);
    public void addSection(SectionModel section);
    public void deleteSection(String sectionName);
    public void editSection(String oldSectionName, String newSectionName, int courseId);
=======
    public void filterSection(String key, Object value, SectionModel section, CourseModel course);
>>>>>>> c0c52aaf2e4f56421ecee63deac0443cdf5f1659
}


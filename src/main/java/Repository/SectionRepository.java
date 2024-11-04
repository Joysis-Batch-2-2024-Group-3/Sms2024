package Repository;

import Model.CourseModel;
import Model.SectionModel;

public interface SectionRepository {
    public void displayAllSection(SectionModel section, CourseModel course);
    //public void filterSection(String value, SectionModel section, CourseModel course);
    public void addSection(SectionModel section);
    public void deleteSection(String sectionName);
    public void editSection(String oldSectionName, String newSectionName, int courseId);
    public void filterSection(String key, Object value, SectionModel section, CourseModel course);
    public void updateSection(SectionModel section);

}


package Repository;

import Model.CourseModel;
import Model.SectionModel;

import java.util.LinkedHashMap;

public interface SectionRepository {
    public void displayAllSection(SectionModel section, CourseModel course);
    public void addSection(SectionModel section);
    public void deleteSection(String sectionName);
    public void editSection(LinkedHashMap<String, Object>values);
    public void filterSection(String key, Object value, SectionModel section, CourseModel course);
    public boolean sectionConflictChecker(LinkedHashMap<String, Object>values);
    public void updateSection(SectionModel section);
}


package Repository;

import Model.CourseModel;
import Model.SectionModel;

import java.util.LinkedHashMap;

public interface SectionRepository {
    public void displayAllSection(SectionModel section, CourseModel course);
    public void addSection(SectionModel section);
    public void deleteSection(String sectionName);
    public void updateSection(LinkedHashMap<String, Object>values, int sectionID);
    public void filterSection(String key, Object value, SectionModel section, CourseModel course);
    public boolean sectionConflictChecker(LinkedHashMap<String, Object>values);
}


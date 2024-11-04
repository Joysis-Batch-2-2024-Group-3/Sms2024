package Controller;

import Db.Db;
import Model.CourseModel;
import Model.SectionModel;
import Repository.SectionRepository;

import java.sql.SQLException;

public class SectionController extends Db implements SectionRepository {
    private IndexController ic = new IndexController();
    public boolean isValidSectionValue (String key, Object value){
        return ic.isValidTableValue("section_tbl",key,value);
    }
    @Override
    public void displayAllSection(SectionModel section, CourseModel course) {
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_SECTIONS);
            System.out.println("|======================|");
            System.out.println("| Display All Sections |");
            System.out.println("|======================|\n");
            System.out.printf(" %-10s | %-25s | %-25s \n", "ID", "Section Name", "Course Name");

            while (result.next()) {
                section.setSectionId(result.getInt("section_id"));
                section.setSectionName(result.getString("section_name"));
                course.setCourseName(result.getString("course_name"));

                System.out.printf("%-10d | %-25s | %-25s \n",
                        section.getSectionId(), section.getSectionName(), course.getCourseName());
            }
            System.out.println("|======================|");
        } catch (SQLException e) {
            System.out.println("SQL error while retrieving sections: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while retrieving sections: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    public void filterSection(String value, SectionModel section, CourseModel course) {
        try {
            connect();
            prep = con.prepareStatement(SEARCH_SECTION);
            prep.setString(1, "%" + value + "%");
            result = prep.executeQuery();
            System.out.println("|===================|");
            System.out.println("| Filtered Sections |");
            System.out.println("|===================|\n");
            System.out.printf(" %-10s | %-25s | %-25s | \n", "ID", "Section Name", "Course Name");

            while (result.next()) {
                section.setSectionId(result.getInt("section_id"));
                section.setSectionName(result.getString("section_name"));
                course.setCourseName(result.getString("course_name"));

                System.out.printf(" %-10d | %-25s | %-25s | \n", section.getSectionId(), section.getSectionName(), course.getCourseName());
            }
        } catch (SQLException e) {
            System.out.println("SQL error while filtering sections: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while filtering sections: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    @Override
    public void addSection(SectionModel section) {
        try {
            connect();
            prep = con.prepareStatement(ADD_SECTION_QUERY); // You'll need to define this query
            prep.setString(1, section.getSectionName());
            prep.setInt(2, section.getCourse_id());
            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Section added successfully.");
            } else {
                System.out.println("Failed to add the section.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in adding section: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in section");
            }
        }
    }

    @Override
    public void deleteSection(String sectionName) {
        try {
            connect();
            prep = con.prepareStatement(DELETE_SECTION_BY_NAME_QUERY);
            prep.setString(1, sectionName);
            int rowsAffected = prep.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Section deleted successfully.");
            } else {
                System.out.println("Section not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in deleting section: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error in deleting section: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in section: " + e.getMessage());
            }
        }
    }

    @Override
    public void editSection(String oldSectionName, String newSectionName, int courseId) {
        try {
            connect();
            prep = con.prepareStatement(EDIT_SECTION_QUERY); // You'll need to define this query
            prep.setString(1, newSectionName);
            prep.setInt(2, courseId);
            prep.setString(3, oldSectionName);
            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Section updated successfully.");
            } else {
                System.out.println("Section not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in editing section: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in section");
            }
        }
    }
}

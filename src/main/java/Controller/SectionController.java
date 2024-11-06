package Controller;

import Db.Db;
import Model.CourseModel;
import Model.SectionModel;
import Repository.SectionRepository;
import Utils.ClearConsole;
import Utils.Input;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public class SectionController extends Db implements SectionRepository {
    private IndexController ic = new IndexController();

    public boolean isValidSectionValue(String key, Object value) {
        return ic.isValidTableValue("section_tbl", key, value);
    }

    @Override
    public void displayAllSection(SectionModel section, CourseModel course) {
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_SECTIONS);
            Input.HeaderBox("Display All Section");
            System.out.printf("\u001B[42m\u001B[1m\u001B[97m%-10s | %-25s | %-25s \u001B[0m\n", "ID", "Section Name", "Course Name"); 

            while (result.next()) {
                section.setSectionId(result.getInt("section_id"));
                section.setSectionName(result.getString("section_name"));
                course.setCourseName(result.getString("course_name"));

                System.out.printf("%-10d | %-25s | %-25s \n",
                        section.getSectionId(), section.getSectionName(), course.getCourseName());
            }
            System.out.println("\n|=================================================================|\n");
         
        } catch (SQLException e) {
            Input.COut("SQL error while retrieving sections: " + e.getMessage());
        } catch (Exception e) {
            Input.COut("Error while retrieving sections: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Input.COut("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    

    @Override
    public void filterSection(String Key, Object value, SectionModel section, CourseModel course) {
        try {
            connect();
            String stringQuery = String.format(SEARCH_SECTION, Key);
            prep = con.prepareStatement(stringQuery);

            prep.setString(1, "%" + value + "%");
            result = prep.executeQuery();
            Input.HeaderBox("Filter Sections");
            System.out.printf("\u001B[42m\u001B[1m\u001B[97m%-10s | %-25s | %-25s \u001B[0m\n", "ID", "Section Name", "Course Name");

            while (result.next()) {
                section.setSectionId(result.getInt("section_id"));
                section.setSectionName(result.getString("section_name"));
                course.setCourseName(result.getString("course_name"));

                System.out.printf(" %-10d | %-25s | %-25s | \n", section.getSectionId(), section.getSectionName(), course.getCourseName());
            }
        } catch (SQLException e) {
            Input.COut("SQL error while filtering sections: " + e.getMessage());
        } catch (Exception e) {
             Input.COut("SQL error while filtering sections: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                 Input.COut("Error closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean sectionConflictChecker(LinkedHashMap<String, Object> values) {
        return ic.checkConflict("section_tbl", values);
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
                Input.COut("Section deleted successfully.");
            } else {
                Input.COut("Section not found.");
            }
        } catch (SQLException e) {
            Input.COut("SQL Error in deleting section: " + e.getMessage());
        } catch (Exception e) {
                Input.COut("SQL Error in deleting section: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                Input.COut("Error in closing resources in section: " + e.getMessage());
            }
        }
    }

    @Override
    public void updateSection(LinkedHashMap<String, Object> values, int sectionID) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE section_tbl SET ");

        boolean first = true;

        for (String key : values.keySet()) {
            if (!first) {
                queryBuilder.append(", ");
            }
            queryBuilder.append(key).append(" = ?");
            first = false;
        }

        queryBuilder.append(" WHERE section_id = ?");

        try {
            connect();
            prep = con.prepareStatement(queryBuilder.toString());

            int index = 1;

            for (Object value : values.values()) {
                if (value instanceof String) {
                    prep.setString(index++, (String) value);
                } else if (value instanceof Integer) {
                    prep.setInt(index++, (Integer) value);
                }
            }

            prep.setInt(index, sectionID);

            int rowsUpdated = prep.executeUpdate();

            if (rowsUpdated > 0) {
                Input.COut("Section record " + sectionID + " successfully updated.");
            } else {
                Input.COut("No section record found with ID: " + sectionID);
            }

        } catch (Exception e) {
            Input.COut("Error updating section record: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Input.COut("Error closing resources: " + e.getMessage());
            }
        }

    }
}

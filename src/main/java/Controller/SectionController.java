package Controller;

import Db.Db;
import Model.CourseModel;
import Model.SectionModel;
import Repository.SectionRepository;

import java.sql.SQLException;

public class SectionController extends Db implements SectionRepository {
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
}

package Controller;

import Db.Db;
import Model.CourseModel;
import Repository.CourseRepository;

import java.sql.SQLException;

public class CourseController extends Db implements CourseRepository {

    @Override
    public void displayAllCourse(CourseModel course) {
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_COURSE);

            System.out.println("|===================|");
            System.out.println("| Display All Course|");
            System.out.println("|===================|");
            System.out.printf("| %-10s | %-30s | %-10s |\n", "ID", "Name", "Department");

            while (result.next()) {
                course.setCourse_id(result.getInt("course_id"));
                course.setCourseName(result.getString("course_name"));
                course.setDepartmentName(result.getString("department_name"));

                System.out.printf("| %-10d | %-30s | %-10s |\n",
                        course.getCourseID(),
                        course.getCourseName(),
                        course.getDepartmentName());
            }
        } catch (SQLException e) {
            System.out.println("SQL error while retrieving courses: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while retrieving courses: " + e.getMessage());
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

    public void filterCourse(String key, String value, CourseModel course) {
        try {
            connect();
            String searchQuery = String.format(SEARCH_COURSE, key);
            prep = con.prepareStatement(searchQuery);
            prep.setString(1, "%" + value + "%");
            result = prep.executeQuery();

            System.out.println("|=================|");
            System.out.println("| Filtered Course |");
            System.out.println("|=================|");
            System.out.printf("| %-10s | %-30s | %-10s |\n", "ID", "Name", "Department");

            while (result.next()) {
                course.setCourse_id(result.getInt("course_id"));
                course.setCourseName(result.getString("course_name"));
                course.setDepartmentName(result.getString("department_name"));

                System.out.printf("| %-10d | %-30s | %-10s |\n",
                        course.getCourseID(),
                        course.getCourseName(),
                        course.getDepartmentName());
            }
        } catch (SQLException e) {
            System.out.println("SQL error while filtering courses: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while filtering courses: " + e.getMessage());
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

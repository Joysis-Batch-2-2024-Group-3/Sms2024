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
            result = state.executeQuery(SEARCH_COURSE);

            System.out.println("|===================|");
            System.out.println("| Display All Course|");
            System.out.println("|===================|");
            System.out.printf("| %-10s | %-20s | %-10s |\n", "ID", "Name", "Department");

            while (result.next()) {
                // Create a new CourseModel instance for each row
                course.setCourse_id(result.getInt("course_id"));
                course.setCourse_name(result.getString("course_name"));
                course.setDepartment_name(result.getString("department_name"));

                // Print the course details using the CourseModel instance
                System.out.printf("| %-10d | %-20s | %-10s |\n",
                        course.getCourse_id(),
                        course.getCourse_name(),
                        course.getDepartment_name());
            }
            System.out.println("|===================|");
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Ensure resources are closed (consider implementing this in the Db class)
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
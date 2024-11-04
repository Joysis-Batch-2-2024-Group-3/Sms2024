package Controller;

import Db.Db;
import Model.CourseModel;
import Repository.CourseRepository;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public class CourseController extends Db implements CourseRepository {

    IndexController ic = new IndexController();
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
    public int getCourseID(String key,String column, Object value ){
        int courseID = 0;
        try {
            IndexController ic = new IndexController();
            courseID = (int)ic.getValuebyValue("course_id", key, column, value);
            return courseID;
        }catch (Exception e) {
            System.out.println("Error getting course ID: " + e.getMessage());
        };
        return 0;
    }

    @Override
    public void updateCourse(CourseModel course){
        try {
            connect();
            prep = con.prepareStatement(UPDATE_QUERY);
            prep.setInt( 1 ,course.getCourseID());
            prep.setString(2, course.getCourseName());
            prep.setString(3, course.getDepartmentName());
            prep.executeQuery();
            System.out.println("Course " + course.getCourseID() + " successfully updated.");
            displayAllCourse(new CourseModel());
            con.close();
        }catch (Exception e){
            System.out.println("Error update course; " + e.getMessage());
        }
    }
    public boolean isValidCourse(String column, int value){
        IndexController ic = new IndexController();
        return ic.isValidTableValue("course_tbl",column,value);

    }
    public void addCourse(String CourseNmae, String Department){
        connect();
        try {
            prep = con.prepareStatement(ADD_COURSE);
            prep.setString(1, CourseNmae);
            prep.setString(2, Department);
            prep.executeUpdate();
            System.out.println("Course: " + CourseNmae+" added successfully");
            displayAllCourse(new CourseModel());
            con.close();

        }catch (SQLException e){
            System.out.println("SQL error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean courseConflictChecker(LinkedHashMap<String, Object>values) {

        return ic.checkConflict("`course_tbl`", values);
    }
}

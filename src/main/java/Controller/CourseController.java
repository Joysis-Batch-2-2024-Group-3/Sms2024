package Controller;

import Db.Db;
import Model.CourseModel;
import Repository.CourseRepository;
import Utils.Input;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import javax.print.attribute.standard.PrinterInfo;

public class CourseController extends Db implements CourseRepository {

    IndexController ic = new IndexController();
    @Override
    public void displayAllCourse(CourseModel course) {
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_COURSE);

            System.out.println("\n __________________________________________");
            System.out.println("|                                          |");
            System.out.println("|           Display All Course             |");
            System.out.println("|__________________________________________|");
            System.out.printf("\n| %-10s | %-30s | %-10s |\n", "ID", "Name", "Department");

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
            Input.COut("SQL error while retrieving courses: " + e.getMessage());
        } catch (Exception e) {
             Input.COut("Error while retrieving courses: " + e.getMessage());
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

    public void filterCourse(String key, String value, CourseModel course) {
        try {
            connect();
            String searchQuery = String.format(SEARCH_COURSE, key);
            prep = con.prepareStatement(searchQuery);
            prep.setString(1, "%" + value + "%");
            result = prep.executeQuery();

            System.out.println("\n __________________________________________");
            System.out.println("|                                          |");
            System.out.println("|               Filter Course              |");
            System.out.println("|__________________________________________|");
            System.out.printf("\n| %-10s | %-30s | %-10s |\n", "ID", "Name", "Department");

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
                   Input.COut("SQL error while filtering courses: " + e.getMessage());
        } catch (Exception e) {
            Input.COut("Error while filtering courses: " + e.getMessage());
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
    public int getCourseID(String key,String column, Object value ){
        int courseID = 0;
        try {
            IndexController ic = new IndexController();
            courseID = (int)ic.getValuebyValue("course_id", key, column, value);
            return courseID;
        }catch (Exception e) {
            Input.COut("Error getting course ID: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public void updateCourse(LinkedHashMap<String, Object> values, int courseID) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE course_tbl SET ");
        boolean first = true;

        // Iterate over the values map to build the query
        for (String key : values.keySet()) {
            if (!first) {
                queryBuilder.append(", ");
            }
            queryBuilder.append(key).append(" = ?");
            first = false;
        }

        queryBuilder.append(" WHERE course_id= ?");

        try {
            connect();
            prep = con.prepareStatement(queryBuilder.toString());

            int index = 1;

            // Set the parameters for the fields to be updated
            for (Object value : values.values()) {
                if(value instanceof String) {
                    prep.setString(index++, (String) value);
                }
                else if(value instanceof Integer) {
                    prep.setInt(index++, (Integer) value);
                }

            }

            // Set the courseID as the last parameter
            prep.setInt(index, courseID);

            int rowsUpdated = prep.executeUpdate();

            if (rowsUpdated > 0) {
                Input.COut("Course " + courseID + " successfully updated.");
            } else {
                Input.COut("No course found with ID: " + courseID);
            }

            displayAllCourse(new CourseModel());
            Input.HoldState();
        } catch (Exception e) {
            Input.COut("Error updating course " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Input.COut("Error closing resources " + e.getMessage());
            }
        }
    }

    public boolean isValidCourse(String column, Object value){
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
            Input.COut("SQL error: " + e.getMessage());
        } catch (Exception e) {
            Input.COut("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean courseConflictChecker(LinkedHashMap<String, Object>values) {

        return ic.checkConflict("`course_tbl`", values);
    }
    @Override
    public void deleteCourse(LinkedHashMap<String, Object>values) {
        connect();
        try {
            String stringQuery = String.format(DELETE_QUERY, "course_tbl",
                    values.keySet().toArray()[0],
                    values.keySet().toArray()[1]);
            prep = con.prepareStatement(stringQuery);
            prep.setString(1, values.get("course_name").toString());
            prep.setString(2, values.get("department_name").toString());
            prep.executeUpdate();
            System.out.println("Course: " +values.get("course_name").toString()+" "+values.get("department_name").toString()  + " deleted successfully");
            displayAllCourse(new CourseModel());
            con.close();

        }catch (SQLException e){
             Input.COut("SQL error: " + e.getMessage());
        } catch (Exception e) {
           Input.COut("Error: " + e.getMessage());
        }
    }
    public boolean validateBothName (LinkedHashMap<String, Object> values){
        try{
            connect();
            prep = con.prepareStatement(VALIDATE_COURSE_DEPARTMENT);
            prep.setString(1, values.get("course_name").toString());
            prep.setString(2, values.get("department_name").toString());
            result = prep.executeQuery();
            if(result.next()){
                return result.getInt(1)>0;
            }
        } catch (SQLException e) {
             Input.COut("SQL error: " + e.getMessage());
        } catch (Exception e) {
           Input.COut("Error: " + e.getMessage());
        }
    return false;
    }
}

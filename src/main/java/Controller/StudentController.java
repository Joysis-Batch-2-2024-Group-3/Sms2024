package Controller;
import Db.Db;
import Model.*;
import Repository.StudentRepository;
import Utils.Input;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class StudentController extends Db implements StudentRepository {
    public static String[] validColumns = {"student_id", "first_name", "section_name", "course_name", "last_name", "birth_date", "sex", "year_level", "course_id", "section_id", "archived"};

    IndexController ic = new IndexController();
    @Override
    public void displayArchivedStudents(StudentModel student, CourseModel course, SectionModel section) {
        try {
            connect();
            state = con.createStatement();
            // Query to select only archived students
            result = state.executeQuery(DISPLAY_STUDENTS + " WHERE archived = true");
//            System.out.println("|===================|");
//            System.out.println("| Archived Students |");
//            System.out.println("|===================|\n");
            Input.HeaderBox("Archived Students");
            // Print the header only once
            System.out.printf("\u001B[42m\u001B[1m\u001B[97m%-5s | %-15s | %-15s | %-10s | %-5s | %-10s | %-25s | %-10s\u001B[0m\n",
                    "ID", "First Name", "Last Name", "DOB", "Sex", "Year Level", "Course", "Section");

            // Loop through the result set and display each archived student
            while (result.next()) {
                student.setStudentId(result.getInt("student_id"));
                student.setStudentFirstname(result.getString("first_name"));
                student.setStudentLastname(result.getString("last_name"));
                student.setStudentDob(result.getDate("birth_date"));
                student.setStudentSex(result.getString("sex").charAt(0));
                student.setStudentYearlvl(result.getInt("year_level"));
                course.setCourseName(result.getString("course_name"));
                section.setSectionName(result.getString("section_name"));
                student.setStudentArchive(result.getBoolean("archived"));

                System.out.printf("%-5d | %-15s | %-15s | %-10s | %-5c | %-10d | %-25s | %-10s \n",
                        student.getStudentId(), student.getStudentFirstname(), student.getStudentLastname(),
                        student.getStudentDob(), student.getStudentSex(), student.getStudentYearlvl(),
                        course.getCourseName(), section.getSectionName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception e) {
                Input.COut("Error closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    public void displayStudents(StudentModel student, CourseModel course, SectionModel section) {
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_STUDENTS);
//            System.out.println("|======================|");
//            System.out.println("| Display All Students |");
//            System.out.println("|======================|\n");
              Input.HeaderBox("Display All Students");
              System.out.printf("\u001B[42m\u001B[1m\u001B[97m%-5s | %-15s | %-15s | %-10s | %-4s  | %-10s | %-25s | %-8s | %-8s\u001B[0m\n",
                    "ID", "First Name", "Last Name", "DOB", "Sex", "Year Level", "Course", "Section", "Archived");

            while (result.next()) {
                student.setStudentId(result.getInt("student_id"));
                student.setStudentFirstname(result.getString("first_name"));
                student.setStudentLastname(result.getString("last_name"));
                student.setStudentDob(result.getDate("birth_date"));
                student.setStudentSex(result.getString("sex").charAt(0));
                student.setStudentYearlvl(result.getInt("year_level"));
                course.setCourseName(result.getString("course_name"));
                section.setSectionName(result.getString("section_name"));
                student.setStudentArchive(result.getBoolean("archived"));

                System.out.printf("%-5d | %-15s | %-15s | %-10s | %-5c | %-10d | %-25s | %-10s | %-8s\n",
                        student.getStudentId(), student.getStudentFirstname(), student.getStudentLastname(),
                        student.getStudentDob(), student.getStudentSex(), student.getStudentYearlvl(),
                        course.getCourseName(), section.getSectionName(), student.isStudentArchive() ? "Yes" : "No");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception e) {
               Input.COut("Error closing resources: " + e.getMessage());
            }
        }
    }
    @Override
    public void addStudent(StudentModel student, CourseModel  course, SectionModel section){
        try{
            connect();
            prep = con.prepareStatement(ADD_STUDENT);
            prep.setString(1, student.getStudentFirstname());
            prep.setString(2, student.getStudentLastname());
            prep.setDate(3, student.getStudentDob());
            prep.setString(4, String.valueOf(student.getStudentSex()));
            prep.setInt(5, student.getStudentYearlvl());
            prep.setInt(6, student.getStudentCourse());
            prep.setInt(7, student.getStudentSection());
            prep.executeUpdate();
            System.out.println("Student " +  student.getStudentFirstname()+" "+student.getStudentLastname() + " added successfully!");
        }catch (Exception e) {
            Input.COut("Error adding student: " + e.getMessage());
        }
    }


    @Override
    public void filterStudent(String key, String value, StudentModel student, CourseModel course, SectionModel section) {

        System.out.println();
        // Validate the key to ensure it corresponds to a valid column name
        key = key.toLowerCase();
        if (!isValidColumn(key)) {
            System.out.println("Invalid column: " + key);
            return;
        }

        try {
            connect();
            //Determine the appropriate search query

                String searchQuery = String.format(SEARCH_STUDENT, key);

            // Prepare the statement
            PreparedStatement preparedStatement = con.prepareStatement(searchQuery);
            preparedStatement.setString(1, "%"+value+"%"); // Set the value for the placeholder

            // Execute the query
            result = preparedStatement.executeQuery();

//            System.out.println("|================|");
//            System.out.println("| Search Results |");
//            System.out.println("|================|\n");
            Input.HeaderBox("Search Results");
            System.out.printf("\u001B[42m\u001B[1m\u001B[97m%-5s | %-15s | %-15s | %-10s | %-5s | %-25s | %-10s | %-10s | %-8s\u001B[0m\n", 
                    "ID", "First Name", "Last Name", "DOB", "Sex", "Year Level", "Course", "Section", "Archived");

            // Loop through the result set and display each student
            while (result.next()) {
                student.setStudentId(result.getInt("student_id"));
                student.setStudentFirstname(result.getString("first_name"));
                student.setStudentLastname(result.getString("last_name"));
                student.setStudentDob(result.getDate("birth_date"));
                student.setStudentSex(result.getString("sex").charAt(0));
                student.setStudentYearlvl(result.getInt("year_level"));
                course.setCourseName(result.getString("course_name"));
                section.setSectionName(result.getString("section_name"));
                student.setStudentArchive(result.getBoolean("archived"));

                System.out.printf("%-5d | %-15s | %-15s | %-10s | %-5c | %-10d | %-25s | %-10s | %-8s |\n",
                        student.getStudentId(), student.getStudentFirstname(), student.getStudentLastname(),
                        student.getStudentDob(), student.getStudentSex(), student.getStudentYearlvl(),
                        course.getCourseName(), section.getSectionName(), student.isStudentArchive() ? "Yes" : "No");
            }

        } catch (SQLException e){
            Input.COut("SQL Error searching for student: " + e.getMessage());
        }
        catch (Exception e) {
               Input.COut("SQL Error searching for student: " + e.getMessage());
        }
        finally {
            // Close resources to prevent memory leaks
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception e) {
               Input.COut("Error closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    public void updateStudent(LinkedHashMap<String, Object> values, int studentId) {
        if (values.isEmpty()) {
            System.out.println("No fields to update.");
            return;
        }

        StringBuilder queryBuilder = new StringBuilder("UPDATE student_tbl SET ");
        int fieldCount = values.size();
        int index = 0;

        // Dynamically construct the query
        for (String field : values.keySet()) {
            queryBuilder.append(field).append(" = ?");
            if (++index < fieldCount) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(" WHERE student_id = ?");

        try {
            connect();
            prep = con.prepareStatement(queryBuilder.toString());

            // Set the values for the prepared statement
            index = 1;
            for (Object value : values.values()) {
                if( value instanceof String){
                    prep.setString(index++, (String) value);
                }
                else if( value instanceof Integer){
                    prep.setInt(index++, (Integer) value);
                }
                else if( value instanceof java.sql.Date){
                    prep.setDate(index++, (java.sql.Date) value);
                }
                else if( value instanceof Character){
                    prep.setString(index++, String.valueOf(value));
                }
                else if( value instanceof Boolean){
                    prep.setBoolean(index++, (Boolean) value);
                }
            }
            prep.setInt(index, studentId); // Assuming studentId is passed to identify the record
            int rowsUpdated = prep.executeUpdate();
            if (rowsUpdated > 0) {
                Input.COut("Student information successfully updated.");
            } else {
                
                Input.COut("Update failed. Student not found.");
            }
        } catch (SQLException e) {
            Input.COut("SQL Error : " + e.getMessage());
        }
    }
    @Override
    public void displayStudentbySection(String Value, StudentModel sm, CourseModel cm, SectionModel secm){
        filterStudent("section_name",Value,sm,cm,secm);
    }

    @Override
    public void displayStudentbyCourse(String Value, StudentModel sm, CourseModel cm, SectionModel secm) {
        filterStudent("course_name",Value,sm,cm,secm);
    }

    @Override
    public void displayStudentbyFName(String Value, StudentModel sm, CourseModel cm, SectionModel secm) {
        filterStudent("first_name",Value,sm,cm,secm);

    }

    @Override
    public void displayStudentbyLname(String Value, StudentModel sm, CourseModel cm, SectionModel secm) {
        filterStudent("last_name",Value,sm,cm,secm);

    }

    @Override
    public void displayStudentbyStudent_ID(String Value, StudentModel sm, CourseModel cm, SectionModel secm) {
        filterStudent("student_id",Value,sm,cm,secm);
    }

    // Helper method to validate the column name
    private boolean isValidColumn(String key) {
        // List of valid column names

        for (String column : validColumns) {
            if (column.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void dropStudent(LinkedHashMap<String, Object>values){
        try{
            connect();
            prep =  con.prepareStatement(DELETE_STUDENT );
            prep.setString(1, values.get("student_name").toString());
            prep.setString(2, values.get("section_name").toString());
          //  System.out.println(prep.toString());
            prep.executeUpdate();
            con.close();
        }catch(Exception e){
            Input.COut("Error message " + e.getMessage());
        }
    }



    @Override
    public boolean isValidStudent(String Column, Object Value) {
       return ic.isValidTableValue("student_tbl",Column,Value);
    }
    @Override
    public boolean isValidStudentName(String firstName, String lastName){
        String query = "SELECT COUNT(*) FROM student_tbl WHERE first_name = ? AND last_name = ?";
        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, firstName);
            prep.setString(2, lastName);
            result = prep.executeQuery();

            if (result.next()) {
                return result.getInt(1) > 0;
            }
        } catch (SQLException e) {
            Input.COut("SQL Error: " + e.getMessage());
        }
        return false;
    }

}
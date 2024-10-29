package Controller;
import Db.Db;
import Model.CourseModel;
import Model.SectionModel;
import Model.StudentModel;
import Repository.StudentRepository;

import java.sql.PreparedStatement;

public class StudentController extends Db implements StudentRepository {
    String[] validColumns = {"student_id", "first_name", "section_name", "course_name", "last_name", "birth_date", "sex", "year_level", "course_id", "section_id", "archived"};


    @Override
    public void displayArchivedStudents(StudentModel student, CourseModel Course, SectionModel Section) {
        try {
            connect();
            state = con.createStatement();
            // Query to select only archived students
            result = state.executeQuery(DISPLAY_STUDENTS + " WHERE archived = true");
            System.out.println("|===================|");
            System.out.println("| Archived Students |");
            System.out.println("|===================|\n");
            // Print the header only once
            System.out.printf("%-5s | %-15s | %-15s | %-10s | %-5s | %-10s | %-25s | %-10s\n",
                    "ID", "First Name", "Last Name", "DOB", "Sex", "Year Level", "Course", "Section");

            // Loop through the result set and display each archived student
            while (result.next()) {
                student.setStudentId(result.getInt("student_id"));
                student.setStudentFirstname(result.getString("first_name"));
                student.setStudentLastname(result.getString("last_name"));
                student.setStudentDob(result.getDate("birth_date"));
                student.setStudentSex(result.getString("sex").charAt(0));
                student.setStudentYearlvl(result.getInt("year_level"));
                Course.setCourse_name(result.getString("course_name"));
                Section.setSection_name(result.getString("section_name"));
                student.setStudentArchive(result.getBoolean("archived"));

                System.out.printf("%-5d | %-15s | %-15s | %-10s | %-5c | %-10d | %-25s | %-10s \n",
                        student.getStudentId(), student.getStudentFirstname(), student.getStudentLastname(),
                        student.getStudentDob(), student.getStudentSex(), student.getStudentYearlvl(),
                        Course.getCourse_name(), Section.getSection_name());
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
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    public void displayStudents(StudentModel student, CourseModel Course, SectionModel Section) {
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_STUDENTS);
            System.out.println("|======================|");
            System.out.println("| Display All Students |");
            System.out.println("|======================|\n");
            System.out.printf("%-5s | %-15s | %-15s | %-10s | %-5s | %-10s | %-25s | %-10s | %-8s\n",
                    "ID", "First Name", "Last Name", "DOB", "Sex", "Year Level", "Course", "Section", "Archived");

            while (result.next()) {
                student.setStudentId(result.getInt("student_id"));
                student.setStudentFirstname(result.getString("first_name"));
                student.setStudentLastname(result.getString("last_name"));
                student.setStudentDob(result.getDate("birth_date"));
                student.setStudentSex(result.getString("sex").charAt(0));
                student.setStudentYearlvl(result.getInt("year_level"));
                Course.setCourse_name(result.getString("course_name"));
                Section.setSection_name(result.getString("section_name"));
                student.setStudentArchive(result.getBoolean("archived"));

                System.out.printf("%-5d | %-15s | %-15s | %-10s | %-5c | %-10d | %-25s | %-10s | %-8s\n",
                        student.getStudentId(), student.getStudentFirstname(), student.getStudentLastname(),
                        student.getStudentDob(), student.getStudentSex(), student.getStudentYearlvl(),
                        Course.getCourse_name(), Section.getSection_name(), student.isStudentArchive() ? "Yes" : "No");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    public void filterStudent(String key, String value, StudentModel student, CourseModel course, SectionModel section) {
        System.out.println("Select from this valid column keys:");
        for (String column : validColumns) {
            System.out.print(column + " ");
        }
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

            System.out.println("|================|");
            System.out.println("| Search Results |");
            System.out.println("|================|\n");
            System.out.printf("%-5s | %-15s | %-15s | %-10s | %-5s | %-10s | %-25s | %-10s | %-8s\n",
                    "ID", "First Name", "Last Name", "DOB", "Sex", "Year Level", "Course", "Section", "Archived");

            // Loop through the result set and display each student
            while (result.next()) {
                student.setStudentId(result.getInt("student_id"));
                student.setStudentFirstname(result.getString("first_name"));
                student.setStudentLastname(result.getString("last_name"));
                student.setStudentDob(result.getDate("birth_date"));
                student.setStudentSex(result.getString("sex").charAt(0));
                student.setStudentYearlvl(result.getInt("year_level"));
                course.setCourse_name(result.getString("course_name"));
                section.setSection_name(result.getString("section_name"));
                student.setStudentArchive(result.getBoolean("archived"));

                System.out.printf("%-5d | %-15s | %-15s | %-10s | %-5c | %-10d | %-25s | %-10s | %-8s\n",
                        student.getStudentId(), student.getStudentFirstname(), student.getStudentLastname(),
                        student.getStudentDob(), student.getStudentSex(), student.getStudentYearlvl(),
                        course.getCourse_name(), section.getSection_name(), student.isStudentArchive() ? "Yes" : "No");
            }
        } catch (Exception e) {
            System.out.println("Error searching for student: " + e.getMessage());
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
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

}
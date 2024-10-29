package Controller;
import Db.Db;
import Model.AdminModel;
import Model.CourseModel;
import Model.SectionModel;
import Model.StudentModel;
import Repository.StudentRepository;

import java.sql.PreparedStatement;

public class StudentController extends Db implements StudentRepository {
    public static String[] validColumns = {"student_id", "first_name", "section_name", "course_name", "last_name", "birth_date", "sex", "year_level", "course_id", "section_id", "archived"};


    @Override
    public void displayArchivedStudents(StudentModel student, CourseModel course, SectionModel section) {
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
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    public void displayStudents(StudentModel student, CourseModel course, SectionModel section) {
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_STUDENTS);
            System.out.println("|======================|");
            System.out.println("| Display All Students |");
            System.out.println("|======================|\n");
            System.out.printf("%-5s | %-15s | %-15s | %-10s | %-5s | %-10s | %-25s | %-10s | %-8s\n",
                    "ID", "First Name", "Last Name", "DOB", "Sex", "Student Number", "Year Level", "Course", "Section", "Archived");

            while (result.next()) {
                student.setStudentId(result.getInt("student_id"));
                student.setStudentFirstname(result.getString("first_name"));
                student.setStudentLastname(result.getString("last_name"));
                student.setStudentDob(result.getDate("birth_date"));
                student.setStudentSex(result.getString("sex").charAt(0));
                student.setStudentNumber(result.getString("student_number"));
                student.setStudentYearlvl(result.getInt("year_level"));
                course.setCourseName(result.getString("course_name"));
                section.setSectionName(result.getString("section_name"));
                student.setStudentArchive(result.getBoolean("archived"));

                System.out.printf("%-5d | %-15s | %-15s | %-10s | %-5c | %-10d | %-25s | %-10s | %-8s\n",
                        student.getStudentId(), student.getStudentFirstname(), student.getStudentLastname(),
                        student.getStudentDob(), student.getStudentSex(), student.getStudentYearlvl(), student.getStudentNumber(),
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
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    @Override
    public void addStudent(StudentModel student, CourseModel  course, SectionModel section){
        try{
            connect();
            prep = con.prepareStatement(ADD_STUDENT);
            prep.setInt(1,  student.getStudentId());
            prep.setString(2, student.getStudentNumber());
            prep.setInt(3, course.getCourseID());
            prep.setInt(4, section.getSectionId());
            prep.executeUpdate();
            System.out.println("Student " +  student.getStudentNumber() + " added successfully!");
        }catch (Exception e){
            System.out.println("Error adding student: " + e.getMessage());
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

            System.out.println("|================|");
            System.out.println("| Search Results |");
            System.out.println("|================|\n");
            System.out.printf("%-5s | %-15s | %-15s | %-10s | %-5s | %-10s | %-25s | %-10s | %-8s\n",
                    "ID", "First Name", "Last Name", "DOB", "Sex", "Student Number", "Year Level", "Course", "Section", "Archived");

            // Loop through the result set and display each student
            while (result.next()) {
                student.setStudentId(result.getInt("student_id"));
                student.setStudentFirstname(result.getString("first_name"));
                student.setStudentLastname(result.getString("last_name"));
                student.setStudentDob(result.getDate("birth_date"));
                student.setStudentSex(result.getString("sex").charAt(0));
                student.setStudentNumber(result.getString("student_number"));
                student.setStudentYearlvl(result.getInt("year_level"));
                course.setCourseName(result.getString("course_name"));
                section.setSectionName(result.getString("section_name"));
                student.setStudentArchive(result.getBoolean("archived"));

                System.out.printf("%-5d | %-15s | %-15s | %-10s | %-5c | %-10d | %-25s | %-10s | %-8s\n",
                        student.getStudentId(), student.getStudentFirstname(), student.getStudentLastname(),
                        student.getStudentDob(), student.getStudentSex(), student.getStudentNumber(), student.getStudentYearlvl(),
                        course.getCourseName(), section.getSectionName(), student.isStudentArchive() ? "Yes" : "No");
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
    public void dropStudent(StudentModel student){
        try{
            connect();
            prep =  con.prepareStatement(DELETE_STUDENT);
            prep.setInt(1, student.getStudentId());
            prep.setString(2, student.getStudentNumber());
            prep.executeUpdate();
            System.out.println("Student " + student.getStudentNumber() + " successfully deleted");
            con.close();
        }catch(Exception e){
            System.out.println("Error message " + e.getMessage());
        }
    }

    @Override
    public boolean authenticateAdmin(AdminModel admin){
        try{
            connect();
            state = con.prepareStatement(ADMIN_LOGIN);
            prep.setString(1, admin.getAdminUsername());
            prep.setString(2, admin.getAdminPassword());
            result = prep.executeQuery();
            if(result.next()){
                System.out.println("Logged in successfully!");
            }else{
                System.out.println("Logged in failed");
            }
        }catch(Exception e){
            System.out.println("Error authenticate admin: " + e.getMessage());
        }
        return false;
    }

}
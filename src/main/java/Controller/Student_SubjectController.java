package Controller;

import Db.Db;
import Model.SectionModel;
import Model.StudentModel;
import Model.Student_SubjectModel;
import Model.SubjectModel;
import Repository.Student_SubjectRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class Student_SubjectController extends Db implements Student_SubjectRepository {
    public void filterStudentSubject(String Key, String Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel sec) {
        try {
            connect();
            String searchQuery = String.format(SEARCH_STUDENT_SUBJECT, Key);
            PreparedStatement prep = con.prepareStatement(searchQuery);
            prep.setString(1, "%" + Value + "%");
            result = prep.executeQuery();
            System.out.println("|================|");
            System.out.println("| Search Results |");
            System.out.println("|================|\n");
            System.out.printf("%-5s | %-20s | %-20s | %-20s |\n",
                    "ID", "Student Name", "Subject Name", "Section");
            while (result.next()) {
                ssm.setStudent_subject_id(result.getInt("student_subject_id"));
                sm.setStudentFirstname(result.getString("first_name"));
                sm.setStudentLastname(result.getString("last_name"));
                subm.setSubject_name(result.getString("subject_name"));
                sec.setSectionName(result.getString("section_name"));
                String student_name = (sm.getStudentFirstname() + " " + sm.getStudentLastname());
                System.out.printf("%-5d | %-20s | %-20s  | %-20s |\n",
                        ssm.getStudent_subject_id(), student_name, subm.getSubject_name(), sec.getSectionName());
            }
        } catch (SQLException e) {
            System.out.println("SQL error while getting student subject: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Error in student subject: " + e.getMessage());
        }
        finally {
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error while closing resources in student subject: " + e.getMessage());
            }
        }
    }

    @Override
    public void displayStudentsSubjectByStudent(String Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel secm) {
        filterStudentSubject(" CONCAT(student_tbl.first_name, ' ', student_tbl.last_name)", Value, ssm, sm, subm, secm);
    }

    @Override
    public void displayStudentsSubjectBySubject(String Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel secm) {
        filterStudentSubject("subject_name", Value, ssm, sm, subm, secm);
    }

    @Override
    public void displayStudentsSubjectBySection(String Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel secm) {
        filterStudentSubject("section_name", Value, ssm, sm, subm, secm);
    }

    @Override
    public void addStudentSubject(LinkedHashMap<String, Object> Value) {
        try{
            connect();
            prep = con.prepareStatement(ADD_STUDENT_SUBJECT);
            prep.setInt(1, (int) Value.get("student_id"));
            prep.setInt(2, (int) Value.get("subject_id"));
            prep.setInt(3, (int) Value.get("section_id"));
            prep.executeUpdate();
            System.out.println("Student Subject added successfully.");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Error in adding student subject: " + e.getMessage());
        }
        finally {
            try {
                if (prep!= null) prep.close();
                if (con!= null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in student subject");
            }
        }
    }

    @Override
    public void deleteStudentSubject(LinkedHashMap<String, Object> values) {
        try{
        connect();
        prep = con.prepareStatement(DELETE_STUDENT_SUBJECT);
        prep.setString(1,  values.get("student_name").toString());
        prep.setString(2, values.get("subject_name").toString());
        prep.executeUpdate();
        System.out.println("Student Subject deleted successfully.");
        }catch (SQLException e){
            System.out.println("SQL Error: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Error in deleting student subject: " + e.getMessage());
        }
    }

    @Override
    public void updateStudentSubject(LinkedHashMap<String, Object> values, int studentSubjectID) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE student_subject_tbl SET ");
        boolean first = true;

        // Iterate over the values map to build the query
        for (String key : values.keySet()) {
            if (!first) {
                queryBuilder.append(", ");
            }
            queryBuilder.append(key).append(" = ?");
            first = false;
        }

        queryBuilder.append(" WHERE student_subject_id = ?");

        try {
            connect();
            prep = con.prepareStatement(queryBuilder.toString());

            int index = 1;

            // Set the parameters for the fields to be updated
            for (Object value : values.values()) {
                if (value instanceof String) {
                    prep.setString(index++, (String) value);
                } else if (value instanceof Integer) {
                    prep.setInt(index++, (Integer) value);
                } else if (value instanceof Boolean) {
                    prep.setBoolean(index++, (Boolean) value);
                }
            }

            // Set the studentSubjectID as the last parameter
            prep.setInt(index, studentSubjectID);

            int rowsUpdated = prep.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Student-Subject record " + studentSubjectID + " successfully updated.");
            } else {
                System.out.println("No student-subject record found with ID: " + studentSubjectID);
            }

        } catch (Exception e) {
            System.out.println("Error updating student-subject record: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean isValidStudentSubject(LinkedHashMap<String, Object> Value) {
        try{
            connect();
            prep = con.prepareStatement(VALIDATE_STUDENT_SUBJECT);
            prep.setString(1, Value.get("student_name").toString());
            prep.setString(2, Value.get("subject_name").toString());
            result = prep.executeQuery();
            if(result.next()){
                return result.getInt(1)>0;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: "+ e.getMessage() );
        }
        catch (Exception e){
            System.out.println("Error in validating student subject: "+ e.getMessage());
        }
        return false;
    }

    @Override
    public int getSpecificSSID(LinkedHashMap<String, Object> Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel sec) {
        int studentSubjectId = -1; // Initialize with an invalid ID
        try {
            connect();
            prep = con.prepareStatement(DISPLAY_SPECIFIC_SS);
            prep.setString(1, Value.get("student_name").toString());
            prep.setString(2, Value.get("subject_name").toString());
            result = prep.executeQuery();
            System.out.println("==== Row affected ====");
            System.out.printf("%-5s | %-20s | %-20s  | %-20s | %-5s\n",
            "ID", "Name", "subject", "Section", "Archived");
            if (result.next()) {
                studentSubjectId = result.getInt("student_subject_id"); // Get the ID
                ssm.setStudent_subject_id(studentSubjectId);
                sm.setStudentFirstname(result.getString("first_name"));
                sm.setStudentLastname(result.getString("last_name"));
                subm.setSubject_name(result.getString("subject_name"));
                sec.setSectionName(result.getString("section_name"));
                ssm.setArchived((result.getBoolean("archived")));
                String student_name = sm.getStudentFirstname() + " " + sm.getStudentLastname();
                System.out.printf("%-5d | %-20s | %-20s  | %-20s | %-5s\n",
                        studentSubjectId, student_name, subm.getSubject_name(), sec.getSectionName(), ssm.getArchived() ? "Yes" : "No");
            } else {
                System.out.println("No matching record found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Server Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error in displaying specific student subject: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return studentSubjectId; // Return the student_subject_id
    }

}


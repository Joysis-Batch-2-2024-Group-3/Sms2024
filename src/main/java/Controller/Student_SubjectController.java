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
}

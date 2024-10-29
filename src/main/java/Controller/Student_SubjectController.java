package Controller;

import Db.Db;
import Model.SectionModel;
import Model.StudentModel;
import Model.Student_SubjectModel;
import Model.SubjectModel;
import Repository.Student_SubjectRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Student_SubjectController extends Db implements Student_SubjectRepository{
    public void filterStudentSubject(String Key, String Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel sec){
        try{
            connect();
            String searchQuery = String.format(SEARCH_STUDENT_SUBJECT, Key);
            PreparedStatement prep = con.prepareStatement(searchQuery);
            prep.setString(1, "%" + Value + "%");
            result = prep.executeQuery();
            System.out.println("|================|");
            System.out.println("| Search Results |");
            System.out.println("|================|\n");
            System.out.printf("%-5s | %-5s | %-20s | %-5s | %-20s | %-5s | %-20s |\n",
                    "ID", "Student ID","Student Name","Subject ID","Subject Name" ,"Section ID","Section" );
            while(result.next()){
                ssm.setStudent_subject_id(result.getInt("student_subject_id"));
                ssm.setStudent_id(result.getInt("student_id"));
                sm.setStudentFirstname(result.getString("first_name"));
                sm.setStudentLastname(result.getString("last_name"));
                ssm.setArchived(result.getBoolean("archived"));
                ssm.setSubject_id(result.getInt("subject_id"));
                subm.setSubject_name(result.getString("subject_name"));
                sec.setSectionId(result.getInt("section_id"));
                sec.setSectionName(result.getString("section_name"));
                String student_name = (sm.getStudentFirstname() + " " + sm.getStudentLastname());
                System.out.printf("%-5d | %-5d | %-20s | %-5d | %-20s | %-5d | %-20s |\n",
                        ssm.getStudent_subject_id(), ssm.getStudent_id(), student_name,
                        subm.getSubject_id(), subm.getSubject_name(), sec.getSectionId(), sec.getSectionName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (result!= null) result.close();
                if (state!= null) state.close();
                if (con!= null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void displayStudentsSubjectByStudent(String Value,Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel secm) {
        filterStudentSubject("CONCAT(s.first_name, ' ', s.last_name)", Value, ssm, sm, subm, secm);
    }

    @Override
    public void displayStudentsSubjectBySubject(String Value,Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel secm) {
        filterStudentSubject("subject_name", Value, ssm, sm, subm, secm);
    }

    @Override
    public void displayStudentsSubjectBySection(String Value, Student_SubjectModel ssm, StudentModel sm, SubjectModel subm, SectionModel secm) {
        filterStudentSubject("section_name",Value, ssm, sm, subm, secm);
    }
}

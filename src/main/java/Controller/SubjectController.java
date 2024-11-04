package Controller;

import Db.Db;
import Model.CourseModel;
import Model.SubjectModel;
import Repository.SubjectRepository;
import java.sql.SQLException;

public class SubjectController extends Db implements SubjectRepository {
    private IndexController ic = new IndexController();
    public boolean isValidSubjectValue(String key, Object value) throws SQLException {
    return ic.isValidTableValue("subject_tbl", key, value);
    }
    @Override
    public void displayAllSubject(SubjectModel subject, CourseModel course) {
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_SUBJECT);

            System.out.println("|=============|");
            System.out.println("| All Subject |");
            System.out.println("|=============|");
            System.out.printf("| %-10s | %-30s | %-30s |\n", "ID", "Subject Name", "Course Name");

            while (result.next()) {


                subject.setSubject_id(result.getInt("subject_id"));
                subject.setSubject_name(result.getString("subject_name"));
                course.setCourseName(result.getString("course_name"));

                System.out.printf("| %-10d | %-30s | %-30s |\n", subject.getSubject_id(), subject.getSubject_name(), course.getCourseName());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in displaying subject " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error in displaying subject " + e.getMessage());
        }
        finally {
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in subject");
            }
        }
    }

    @Override
    public void displaySubjectByCourse(String key, Object value, SubjectModel subject, CourseModel course) {
        try {
            connect();
            String stringQuery = String.format(DISPLAY_SUBJECT_COURSE,key);
            prep = con.prepareStatement(stringQuery);
            prep.setString(1, "%"+ value +"%");
            result = prep.executeQuery();

            System.out.println("|====================|");
            System.out.println("| Subjects By Course |");
            System.out.println("|====================|");
            System.out.printf("| %-10s | %-40s | %-30s |\n", "ID", "Subject Name", "Course Name");

            while (result.next()) {


                subject.setSubject_id(result.getInt("subject_id"));
                subject.setSubject_name(result.getString("subject_name"));
                course.setCourseName(result.getString("course_name"));

                System.out.printf("| %-10d | %-40s | %-30s |\n", subject.getSubject_id(), subject.getSubject_name(), course.getCourseName());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in displaying subject by : "+ value +"\n"+ e.getMessage());
            } catch (Exception e) {
            System.out.println("Error in displaying subject by : "+ value +"\n"+ e.getMessage());
        }finally {
            try {
                if (result != null) result.close();
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in subject");
            }
        }
    }
    
    @Override
    public void addSubject(SubjectModel subject) {
        try {
            connect();
            prep = con.prepareStatement(ADD_SUBJECT_QUERY);
            prep.setString(1, subject.getSubject_name());
            prep.setInt(2, subject.getCourse_id());
            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Subject added successfully.");
                
            } else {
                System.out.println("Failed to add the subject.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in adding subject: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in subject");
            }
        }
    }
    
    @Override
    public void deleteSubject(String subjectName) {
        try {
            connect();
            prep = con.prepareStatement(DELETE_SUBJECT_BY_NAME_QUERY); // Define this query
            prep.setString(1, subjectName);
            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Subject deleted successfully.");
            } else {
                System.out.println("Failed to delete the subject. Subject name may not exist.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in deleting subject: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in subject");
            }
        }
    }

    @Override
    public void updateSubject(SubjectModel subject){
        try{
            connect();
            prep = con.prepareStatement(UPDATE_QUERY);
            prep.setInt(1, subject.getSubject_id());
            prep.setString(2, subject.getSubject_name());
            prep.setInt(3, subject.getCourse_id());
            prep.executeQuery();
            System.out.println("Subject " + subject.getSubject_id() + " successfully updated.");
            displayAllSubject(new SubjectModel(), new CourseModel());
            //displaySubjectByCourse();
        }catch (Exception e){
            System.out.println("Error update subject: " + e.getMessage());

        }

    }
    
    @Override
    public void editSubject(String oldSubjectName, String newSubjectName, int newCourseId) {
        try {
            connect();

            // First, retrieve the existing subject details
            prep = con.prepareStatement("SELECT subject_name, course_id FROM subject_tbl WHERE subject_name = ?");
            prep.setString(1, oldSubjectName);
            result = prep.executeQuery();

            // Check if the subject exists
            if (result.next()) {
                String currentSubjectName = result.getString("subject_name");
                int currentCourseId = result.getInt("course_id");

                // Check if the new values are different
                if (currentSubjectName.equals(newSubjectName) && currentCourseId == newCourseId) {
                    System.out.println("No changes detected. The subject name and course ID are the same.");
                    return; // Exit the method as no update is needed
                }

                // If the new subject name or course ID is different, proceed with the update
                prep = con.prepareStatement(EDIT_SUBJECT_QUERY);
                prep.setString(1, newSubjectName);
                prep.setInt(2, newCourseId);
                prep.setString(3, oldSubjectName);
                int rowsAffected = prep.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Subject updated successfully.");
                } else {
                    System.out.println("Failed to update the subject. Subject name may not exist.");
                }
            } else {
                System.out.println("Subject with name '" + oldSubjectName + "' not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in updating subject: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in subject");
            }
        }
    }
}

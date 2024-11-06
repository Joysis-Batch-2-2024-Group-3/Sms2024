package Controller;

import Db.Db;
import Model.CourseModel;
import Model.SubjectModel;
import Repository.SubjectRepository;
import Utils.Input;
import java.sql.SQLException;
import java.util.LinkedHashMap;

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

            Input.HeaderBox("All Subjects");
            System.out.printf("\u001B[42m\u001B[1m\u001B[97m| %-10s | %-30s | %-30s |\u001B[0m\n", "ID", "Subject Name", "Course Name");

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

            Input.HeaderBox("Subjects By Course");
            System.out.printf("\u001B[42m\u001B[1m\u001B[97m| %-10s | %-40s | %-30s |\u001B[0m\n", "ID", "Subject Name", "Course Name");

            while (result.next()) {
                subject.setSubject_id(result.getInt("subject_id"));
                subject.setSubject_name(result.getString("subject_name"));
                course.setCourseName(result.getString("course_name"));

                System.out.printf("| %-10d | %-40s | %-30s |\n", subject.getSubject_id(), subject.getSubject_name(), course.getCourseName());
            }
        } catch (SQLException e) {
            Input.COut("SQL Error in displaying subject by : "+ value +"\n"+ e.getMessage());
            } catch (Exception e) {
            Input.COut("SQL Error in displaying subject by : "+ value +"\n"+ e.getMessage());
        }finally {
            try {
                if (result != null) result.close();
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                Input.COut("Error in closing resources in subject");
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
    public void deleteSubject(LinkedHashMap<String, Object> values) {
        try {
            connect();
            String stringQuery = String.format(DELETE_QUERY,"subject_tbl", values.keySet().toArray()[0], values.keySet().toArray()[1]);
            prep = con.prepareStatement(stringQuery); // Define this query
            int index = 1;
            for( Object value : values.values()){
                if (value instanceof String) {
                    prep.setString(index ++, (String) value);
                } else if (value instanceof Integer) {
                    prep.setInt(index++, (Integer) value);
                }
            }
            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Subject deleted successfully.");
            } else {
                System.out.println("Failed to delete the subject. Subject name may not exist.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in deleting subject: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Error in deleting subject: " + e.getMessage());
        }
        finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources in subject");
            }
        }
    }

    @Override
    public void updateSubject(LinkedHashMap<String, Object> values, int subjectID) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE subject_tbl SET ");
        boolean first = true;

        // Iterate over the values map to build the query
        for (String key : values.keySet()) {
            if (!first) {
                queryBuilder.append(", ");
            }
            queryBuilder.append(key).append(" = ?");
            first = false;
        }

        queryBuilder.append(" WHERE subject_id = ?");

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
                }
            }

            // Set the subjectID as the last parameter
            prep.setInt(index, subjectID);

            int rowsUpdated = prep.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Subject " + subjectID + " successfully updated.");
            } else {
                System.out.println("No subject found with ID: " + subjectID);
            }

        } catch (Exception e) {
            System.out.println("Error updating subject; " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources; " + e.getMessage());
            }
        }
    }


    @Override
    public boolean subjectConflictChecker(LinkedHashMap<String, Object> values) {
        return ic.checkConflict("subject_tbl", values);
    }
}

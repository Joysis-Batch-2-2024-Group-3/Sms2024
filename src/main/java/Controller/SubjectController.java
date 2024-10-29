package Controller;

import Db.Db;
import Model.CourseModel;
import Model.SubjectModel;
import Repository.SubjectRepository;

import java.sql.SQLException;

public class SubjectController extends Db implements SubjectRepository {
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
            throw new RuntimeException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void displaySubjectByCourse(String value, SubjectModel subject, CourseModel course) {
        try {
            connect();
            prep = con.prepareStatement(DISPLAY_SUBJECT_COURSE);
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
            throw new RuntimeException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package Controller;

import Model.ScheduleModel;
import Model.SectionModel;
import Model.SubjectModel;
import Repository.ScheduleRepository;
import Db.Db;

import java.sql.SQLException;
import java.sql.Time;

public class ScheduleController extends Db implements ScheduleRepository {

    @Override
    public void displayAllSchedule(ScheduleModel schedule, SubjectModel subject, SectionModel section) {
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_SCHEDULE);
            System.out.println("|==============|");
            System.out.println("| All Schedule |");
            System.out.println("|==============|");
            System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-20s | %-20s |\n", "ID", "Section", "Subject Name", "Day", "Start", "End");

            while (result.next()) {
                schedule.setSchedule_id(result.getInt("schedule_id"));
                section.setSectionName(result.getString("section_name"));
                subject.setSubject_name(result.getString("subject_name"));
                schedule.setDay(result.getString("day"));
                schedule.setStart_time(Time.valueOf(result.getString("start_time")));
                schedule.setEnd_time(Time.valueOf(result.getString("end_time")));

                System.out.printf("| %-10d | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                        schedule.getSchedule_id(), section.getSectionName(), subject.getSubject_name(), schedule.getDay(), schedule.getStart_time(), schedule.getEnd_time());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
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

    public void filterSchedule(String key, String value, ScheduleModel schedule, SubjectModel subject, SectionModel section){
        try {
            connect();
            String searchQuery = String.format(DISPLAY_SCHEDULE_FILTERED, key);
            prep = con.prepareStatement(searchQuery);
            System.out.println(key +" "+value);

            prep.setString(1, "%"+ value+ "%");
            result = prep.executeQuery();
            System.out.println("|=====================|");
            System.out.println("| Schedule By "+key+" |");
            System.out.println("|=====================|");
            System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-20s | %-20s |\n", "ID", "Section", "Subject Name", "Day", "Start", "End");

            while (result.next()) {
                schedule.setSchedule_id(result.getInt("schedule_id"));
                section.setSectionName(result.getString("section_name"));
                subject.setSubject_name(result.getString("subject_name"));
                schedule.setDay(result.getString("day"));
                schedule.setStart_time(Time.valueOf(result.getString("start_time")));
                schedule.setEnd_time(Time.valueOf(result.getString("end_time")));

                System.out.printf("| %-10d | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                        schedule.getSchedule_id(), section.getSectionName(), subject.getSubject_name(), schedule.getDay(), schedule.getStart_time(), schedule.getEnd_time());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public void displayScheduleBySection(String value, ScheduleModel schedule, SubjectModel subject, SectionModel section) {
        filterSchedule("section_name", value, schedule, subject, section );
    }

    @Override
    public void displayScheduleByDay(String day, ScheduleModel schedule, SubjectModel subject, SectionModel section) {
        filterSchedule("day", day, schedule, subject, section );
    }
}


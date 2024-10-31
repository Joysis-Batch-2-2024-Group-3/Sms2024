package Controller;

import Model.ScheduleModel;
import Model.SectionModel;
import Model.SubjectModel;
import Repository.ScheduleRepository;
import Db.Db;

import java.sql.SQLException;
import java.sql.Time;

public class ScheduleController extends Db implements ScheduleRepository {


    public void filterSchedule(String key, Object value, ScheduleModel schedule, SubjectModel subject, SectionModel section) {
        try {
            connect();
            String searchQuery = String.format(DISPLAY_SCHEDULE_FILTERED, key);
            prep = con.prepareStatement(searchQuery);

            if (value instanceof String) {
                prep.setString(1, "%" + value + "%");
            } else if (value instanceof Integer) {
                prep.setInt(1, (Integer) value);
            }

            result = prep.executeQuery();
            if (key.equals( "section_tbl.section_id")) {
                System.out.println("Section current schedule");
            }
            else{
                System.out.println("|=====================|");
                System.out.println("| Schedule By " + key + " |");
                System.out.println("|=====================|");

            }
            System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-20s | %-20s |\n", "ID", "Section", "Subject", "Day", "Start", "End");

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
            System.out.println("SQL Error in trying to retrieve: " + key + "\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error in trying to retrieve: " + key + "\n" + e.getMessage());
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
    public void displayScheduleBySection(String key, Object value, ScheduleModel schedule, SubjectModel subject, SectionModel section) {
        filterSchedule(key, value, schedule, subject, section);
    }




    @Override
    public void displayScheduleByDay(String day, ScheduleModel schedule, SubjectModel subject, SectionModel section) {
        filterSchedule("day", day, schedule, subject, section);
    }

    @Override
    public void addSchedule(ScheduleModel schedule, SubjectModel subject, SectionModel section) {
        try {
            connect();
            prep = con.prepareStatement(ADD_SCHEDULE);
            prep.setString(1, schedule.getDay());
            prep.setInt(2, schedule.getSection_id());
            prep.setInt(3, schedule.getSubject_id());
            prep.setTime(4, schedule.getStart_time());
            prep.setTime(5, schedule.getEnd_time());
            prep.executeUpdate();
            System.out.println("Schedule added successfully!");
            System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-20s | %-20s |\n", "ID", "Section", "Subject", "Day", "Start", "End");
            System.out.printf("| %-10d | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                    schedule.getSchedule_id(), section.getSectionName(), subject.getSubject_name(), schedule.getDay(), schedule.getStart_time(), schedule.getEnd_time());
        } catch (SQLException e) {
            System.out.println("SQL Error while adding schedule: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding schedule: " + e.getMessage());
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}

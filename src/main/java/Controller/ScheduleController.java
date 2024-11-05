package Controller;

import Model.ScheduleModel;
import Model.SectionModel;
import Model.SubjectModel;
import Repository.ScheduleRepository;
import Db.Db;

import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
            if (key.equals("section_tbl.section_id")) {
                System.out.println("Section current schedule");
            } else {
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
    public boolean checkScheduleConflict(HashMap<String, Object> values) {
        try {
            connect();

            // Prepare the dynamic query based on the keys in values
            String conflictQuery = String.format(CONFLICT_CHECKER_QUERY,
                    "schedule_tbl",
                    values.keySet().toArray()[0],
                    values.keySet().toArray()[1],
                    values.keySet().toArray()[2],
                    values.keySet().toArray()[3]);
            prep = con.prepareStatement(conflictQuery);

            int index = 1; // Parameter index starts at 1 for PreparedStatement
            for (Object value : values.values()) {
                if (value instanceof String) {
                    prep.setString(index++, (String) value);
                } else if (value instanceof Integer) {
                    prep.setInt(index++, (Integer) value);
                } else if (value instanceof Time) {
                    prep.setTime(index++, (Time) value);
                }
            }
            result = prep.executeQuery();
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0; // Conflict found
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error checking schedule conflict: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (prep != null) prep.close(); // Close PreparedStatement
                if (con != null) con.close(); // Close connection
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return false;
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
            System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s |\n", "Section", "Subject", "Day", "Start", "End");
            System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                    section.getSectionName(), subject.getSubject_name(), schedule.getDay(), schedule.getStart_time(), schedule.getEnd_time());
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
    @Override
    public void updateSchedule(LinkedHashMap<String, Object> values, int SchedID) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE schedule_tbl SET ");
        boolean first = true;

        // Build the query dynamically based on the provided values
        for (String key : values.keySet()) {
            if (!first) {
                queryBuilder.append(", ");
            }
            queryBuilder.append(key).append(" = ?");
            first = false;
        }

        queryBuilder.append(" WHERE schedule_id = ?");

        try {
            connect();
            prep = con.prepareStatement(queryBuilder.toString());

            int index = 1;

            // Set parameters for the fields to be updated
            for (Object value : values.values()) {
            if(value instanceof String) {
                prep.setString(index++, (String) value);
            }
            else if(value instanceof Integer) {
                prep.setInt(index++, (Integer) value);
            }
            else if(value instanceof Time) {
                prep.setTime(index++, (Time) value);
            }
            }

            // Set the SchedID as the last parameter
            prep.setInt(index, SchedID);

            int rowsUpdated = prep.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Schedule " + SchedID + " successfully updated.");
            } else {
                System.out.println("No schedule found with ID: " + SchedID);
            }
        } catch (Exception e) {
            System.out.println("Error updating schedule: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }


}

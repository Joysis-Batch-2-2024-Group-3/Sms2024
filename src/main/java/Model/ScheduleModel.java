package Model;

import java.sql.Time;

public class ScheduleModel {
    private int schedule_id;
    private int subject_id;
    private String day;
    private Time start_time;
    private Time end_time;
    private int section_id;
    public int getSchedule_id() {
        return schedule_id;
    }
    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public Time getStart_time() {
        return start_time;
    }
    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }
    public Time getEnd_time() {
        return end_time;
    }
    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
    public int getSubject_id() {
        return subject_id;
    }
    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
    public int getSection_id() {
        return section_id;
    }
    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }
}

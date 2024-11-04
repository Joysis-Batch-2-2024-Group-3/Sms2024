package Repository;
import Model.ScheduleModel;
import Model.SectionModel;
import Model.SubjectModel;

import java.util.HashMap;


public interface ScheduleRepository {
    public void displayScheduleBySection(String key, Object value, ScheduleModel schedule,SubjectModel subject, SectionModel section);
    public void displayScheduleByDay(String day, ScheduleModel schedule, SubjectModel subject, SectionModel section);
    public void addSchedule( ScheduleModel schedule, SubjectModel subject, SectionModel section);
    public boolean checkScheduleConflict(HashMap<String, Object> values);
    public void updateSchedule(ScheduleModel schedule);
}
package Repository;
import Model.ScheduleModel;
import Model.SectionModel;
import Model.SubjectModel;

import java.util.HashMap;
import java.util.LinkedHashMap;


public interface ScheduleRepository {
    public void displayScheduleBySection(String key, Object value, ScheduleModel schedule,SubjectModel subject, SectionModel section);
    public void displayScheduleByDay(String day, ScheduleModel schedule, SubjectModel subject, SectionModel section);
    public void addSchedule( ScheduleModel schedule, SubjectModel subject, SectionModel section);
    public boolean checkScheduleConflict(HashMap<String, Object> values);
    public void updateSchedule(LinkedHashMap<String, Object> values, int SchedID);
}
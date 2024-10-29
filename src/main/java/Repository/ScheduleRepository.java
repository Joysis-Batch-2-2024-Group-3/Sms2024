package Repository;
import Model.ScheduleModel;
import Model.SectionModel;

public interface ScheduleRepository {
    public void displayAllSchedule(ScheduleModel schedule, SectionModel section);
    public void displayScheduleBySection(String value, ScheduleModel schedule, SectionModel section);
    public void displayScheduleByDay(String day, ScheduleModel schedule, SectionModel section);
}
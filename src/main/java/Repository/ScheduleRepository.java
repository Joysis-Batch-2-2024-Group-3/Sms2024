package Repository;
import Model.ScheduleModel;
import Model.SectionModel;
import Model.SubjectModel;

public interface ScheduleRepository {
    public void displayAllSchedule(ScheduleModel schedule, SubjectModel subject, SectionModel section);
    public void displayScheduleBySection(String value, ScheduleModel schedule,SubjectModel subject, SectionModel section);
    public void displayScheduleByDay(String day, ScheduleModel schedule, SubjectModel subject, SectionModel section);
}
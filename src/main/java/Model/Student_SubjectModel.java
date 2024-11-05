package Model;

public class Student_SubjectModel {
    private int student_subject_id;
    private int student_id;
    private int subject_id;
    private boolean archived;

    public int getSubject_id() {
        return subject_id;
    }
    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
    public int getStudent_subject_id() {
        return student_subject_id;
    }
    public void setStudent_subject_id(int student_subject_id) {
        this.student_subject_id = student_subject_id;
    }
    public int getStudent_id() {
        return student_id;
    }
    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
    public boolean isArchived() {
        return archived;
    }
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean getArchived() {
        return archived;

    }
}

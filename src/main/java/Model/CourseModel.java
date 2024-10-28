package Model;

public class CourseModel {
    private int courseID;
    private String courseName;
    private String departmentName;

    public int getCourseID() {
        return courseID;
    }

    public void setCourse_id(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

package Model;

public class SectionModel {
    private int sectionId;
    private String sectionName;
    private int courseId;  // Updated variable name to follow camelCase convention
    
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getCourse_id() {  // Updated getter method to follow camelCase convention
        return courseId;
    }

    public void setCourse_id(int courseId) {  // Added setter for courseId
        this.courseId = courseId;
    }
}

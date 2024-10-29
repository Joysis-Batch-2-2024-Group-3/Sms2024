package Model;

import java.sql.Date;

public class StudentModel {
    private int studentId;
    private String student_firstname;
    private String student_lastname;
    private Date student_dob;
    private char student_sex;
    private int student_yearlvl;
    private int student_course;
    private String student_section;
    private boolean student_archive;

    // Getters and Setters

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int student_id) {
        this.studentId = student_id;
    }

    public String getStudentFirstname() {
        return student_firstname;
    }

    public void setStudentFirstname(String student_firstname) {
        this.student_firstname = student_firstname;
    }

    public String getStudentLastname() {
        return student_lastname;
    }

    public void setStudentLastname(String student_lastname) {
        this.student_lastname = student_lastname;
    }

    public Date getStudentDob() {
        return student_dob;
    }

    public void setStudentDob(Date student_dob) {
        this.student_dob = student_dob;
    }

    public char getStudentSex() {
        return student_sex;
    }


    public void setStudentSex(char student_sex) {
        this.student_sex = student_sex;
    }

    public int getStudentYearlvl() {
        return student_yearlvl;
    }

    public void setStudentYearlvl(int student_yearlvl) {
        this.student_yearlvl = student_yearlvl;
    }

    public int getStudentCourse() {
        return student_course;
    }

    public void setStudentCourse(int student_course) {
        this.student_course = student_course;
    }

    public String getStudentSection() {
        return student_section;
    }

    public void setStudentSection(String student_section) {
        this.student_section = student_section;
    }

    public boolean isStudentArchive() {
        return student_archive;
    }

    public void setStudentArchive(boolean student_archive) {
        this.student_archive = student_archive;
    }

}

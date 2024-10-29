package Repository;

public interface QueryConstant {
    // FIELDS FOR DB CONNECTION
    String URL = "jdbc:mysql://localhost:3306/db_sms";
    String USERNAME = "root";
    String PASSWORD = "";
    String DRIVER = "com.mysql.cj.jdbc.Driver";

    // QUERIES FOR Students API
    String DISPLAY_STUDENTS = "SELECT student_tbl.student_id, student_tbl.first_name, student_tbl.last_name, student_tbl.birth_date, student_tbl.sex, student_tbl.year_level, course_tbl.course_name, section_tbl.section_name, student_tbl.archived " +
            "FROM student_tbl " +
            "INNER JOIN course_tbl ON student_tbl.course_id = course_tbl.course_id " +
            "INNER JOIN section_tbl ON student_tbl.section_id = section_tbl.section_id";

    String ADD_STUDENT = "INSERT INTO student_tbl(student_id, student_number, course_id, section_id) VALUES (?, ?, ?, ?)";

    // SEARCH_STUDENT query using LIKE
    String SEARCH_STUDENT = "SELECT student_tbl.student_id, student_tbl.first_name, student_tbl.last_name, student_tbl.birth_date, student_tbl.sex, student_tbl.year_level, course_tbl.course_name, section_tbl.section_name, student_tbl.archived " +
            "FROM student_tbl " +
            "INNER JOIN course_tbl ON student_tbl.course_id = course_tbl.course_id " +
            "INNER JOIN section_tbl ON student_tbl.section_id = section_tbl.section_id " +
            "WHERE %s LIKE ?";

    // DISPLAY SECTIONS
    String DISPLAY_SECTIONS = "SELECT section_tbl.section_id, section_tbl.section_name, course_tbl.course_name"
            + " FROM section_tbl INNER JOIN course_tbl ON section_tbl.course_id = course_tbl.course_id";

    //Queries for Section
    //SEARCH SECTION
    String SEARCH_SECTION = "SELECT section_tbl.section_id, section_tbl.section_name, course_tbl.course_name FROM section_tbl" +
            " INNER JOIN course_tbl ON section_tbl.course_id = course_tbl.course_id" +
            " WHERE section_tbl.section_name LIKE ?";

    //Queries for Course
    // SEARCH COURSE
    String SEARCH_COURSE = "SELECT course_tbl.course_id, course_tbl.course_name, course_tbl.department_name FROM course_tbl" +
            " WHERE %s LIKE ?";
    // DISPLAY COURSES
    String DISPLAY_COURSE = "SELECT course_tbl.course_id, course_tbl.course_name, course_tbl.department_name FROM course_tbl";


    //Queries for Subject
// DISPLAY SUBJECT
    String DISPLAY_SUBJECT = "SELECT subject_tbl.subject_id, subject_tbl.subject_name, course_tbl.course_name " +
            "FROM subject_tbl " +
            "INNER JOIN course_tbl ON subject_tbl.course_id = course_tbl.course_id";

    // DISPLAY SUBJECT BY COURSE
    String DISPLAY_SUBJECT_COURSE = "SELECT subject_tbl.subject_id, subject_tbl.subject_name, course_tbl.course_name FROM subject_tbl INNER JOIN course_tbl ON subject_tbl.course_id = course_tbl.course_id WHERE course_tbl.course_name  Like ?";


    //QUERIES FOR SCHEDULE
    //DISPLAY SCHEDULE
    String DISPLAY_SCHEDULE = "SELECT schedule_tbl.schedule_id, schedule_tbl.day, schedule_tbl.start_time, schedule_tbl.end_time, section_tbl.section_name, subject_tbl.subject_name " +
            "FROM schedule_tbl " +
            "INNER JOIN section_tbl ON schedule_tbl.section_id = section_tbl.section_id " +
            "INNER JOIN subject_tbl ON schedule_tbl.subject_id = subject_tbl.subject_id";

String DISPLAY_SCHEDULE_FILTERED = "SELECT schedule_tbl.schedule_id, schedule_tbl.day, schedule_tbl.start_time, schedule_tbl.end_time, section_tbl.section_name, subject_tbl.subject_name FROM schedule_tbl INNER JOIN section_tbl ON schedule_tbl.section_id = section_tbl.section_id INNER JOIN subject_tbl ON schedule_tbl.subject_id = subject_tbl.subject_id WHERE %s LIKE ?";
    // SEARCH_STUDENT_COURSE query using LIKE
    String SEARCH_STUDENT_COURSE = "SELECT student_tbl.student_id, student_tbl.first_name, student_tbl.last_name, student_tbl.birth_date, student_tbl.sex, student_tbl.year_level, course_tbl.course_name, section_tbl.section_name, student_tbl.archived " +
            "FROM student_tbl " +
            "INNER JOIN course_tbl ON student_tbl.course_id = course_tbl.course_id " +
            "INNER JOIN section_tbl ON student_tbl.section_id = section_tbl.section_id " +
            "WHERE course_tbl.course_name LIKE ?";
    //DELETE_STUDENT query
    String DELETE_STUDENT  = "DELETE FROM students_tbl WHERE student_id = ?";

    //ADMIN_LOGIN query
    String ADMIN_LOGIN = "SELECT * FROM admins WHERE username = ? AND password = ?";

    String SEARCH_STUDENT_SUBJECT = "SELECT \n" +
            "    ss.student_subject_id, \n" +
            "    ss.student_id, \n" +
            "    CONCAT(s.first_name, ' ', s.last_name) AS student_name, \n" +
            "    ss.subject_id, \n" +
            "    sub.subject_name AS subject_name, \n" +
            "    sec.section_name AS section_name, \n" + // Added section_name
            "    ss.archived \n" +
            "FROM \n" +
            "    student_subject_tbl AS ss\n" +
            "INNER JOIN \n" +
            "    student_tbl AS s ON ss.student_id = s.student_id\n" +
            "INNER JOIN \n" +
            "    subject_tbl AS sub ON ss.subject_id = sub.subject_id\n" +
            "INNER JOIN \n" +
            "    section_tbl AS sec ON ss.section_id = sec.section_id\n" + // Added join for section
            "WHERE %s LIKE ?"; // Ensure to include section_name in your WHERE condition as needed

}

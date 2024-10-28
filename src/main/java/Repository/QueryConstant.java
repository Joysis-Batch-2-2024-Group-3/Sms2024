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

    // SEARCH_STUDENT query using LIKE
    String SEARCH_STUDENT = "SELECT student_tbl.student_id, student_tbl.first_name, student_tbl.last_name, student_tbl.birth_date, student_tbl.sex, student_tbl.year_level, course_tbl.course_name, section_tbl.section_name, student_tbl.archived " +
            "FROM student_tbl " +
            "INNER JOIN course_tbl ON student_tbl.course_id = course_tbl.course_id " +
            "INNER JOIN section_tbl ON student_tbl.section_id = section_tbl.section_id " +
            "WHERE %s LIKE ?";

    // SEARCH_STUDENT_SECTION query using LIKE
// SEARCH_STUDENT_SECTION query using LIKE
    String SEARCH_STUDENT_SECTION = "SELECT student_tbl.student_id, student_tbl.first_name, student_tbl.last_name, student_tbl.birth_date, student_tbl.sex, student_tbl.year_level, course_tbl.course_name, section_tbl.section_name, student_tbl.archived " +
            "FROM student_tbl " +
            "INNER JOIN section_tbl ON student_tbl.section_id = section_tbl.section_id " +
            "INNER JOIN course_tbl ON student_tbl.course_id = course_tbl.course_id " +
            "WHERE section_tbl.section_name LIKE ?";

    // SEARCH_STUDENT_COURSE query using LIKE
    String SEARCH_STUDENT_COURSE = "SELECT student_tbl.student_id, student_tbl.first_name, student_tbl.last_name, student_tbl.birth_date, student_tbl.sex, student_tbl.year_level, course_tbl.course_name, section_tbl.section_name, student_tbl.archived " +
            "FROM student_tbl " +
            "INNER JOIN course_tbl ON student_tbl.course_id = course_tbl.course_id " +
            "INNER JOIN section_tbl ON student_tbl.section_id = section_tbl.section_id " +
            "WHERE course_tbl.course_name LIKE ?";

}

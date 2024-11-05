package Repository;

public interface QueryConstant {
    // FIELDS FOR DB CONNECTION
    String URL = "jdbc:mysql://localhost:3306/db_sms";
    String USERNAME = "root";
    String PASSWORD = "";
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    // VALUE VALIDATION
    String VALIDATION_QUERY = "SELECT COUNT(*) FROM %s WHERE %s LIKE ?";
    //VALUE FINDER
    String FINDER_QUERY = "SELECT %s FROM %s WHERE %s LIKE ?";
    // VALUE CONFLICT CHECKER
    String CONFLICT_CHECKER_QUERY = "SELECT COUNT(1) FROM %s WHERE %s = ? AND %s = ? AND (%s = ? OR %s = ?);";
    String CONFLICT_CHECKER_QUERY_SMALL = "SELECT COUNT(1) FROM %s WHERE %s = ? AND  %s = ?;";


    //UPDATE QUERY(GENERIC);
    String UPDATE_QUERY = "UPDATE %s SET %s =? WHERE %s =?";

    //DROP QUERY(GENERIC);
    String DELETE_QUERY = "DELETE FROM %s WHERE %s LIKE ? AND %s = ?;";


    // QUERIES FOR Students API
    String DISPLAY_STUDENTS = "SELECT student_tbl.student_id, student_tbl.first_name, student_tbl.last_name, student_tbl.birth_date, student_tbl.sex, student_tbl.year_level, course_tbl.course_name, section_tbl.section_name, student_tbl.archived " +
            "FROM student_tbl " +
            "INNER JOIN course_tbl ON student_tbl.course_id = course_tbl.course_id " +
            "INNER JOIN section_tbl ON student_tbl.section_id = section_tbl.section_id";
    //DELETE_STUDENT query
    String DELETE_STUDENT  = " DELETE FROM student_tbl \n" +
            "                      WHERE student_id IN ( \n" +
            "                         SELECT st.student_id \n" +
            "                          FROM student_tbl st \n" +
            "                          INNER JOIN section_tbl sec ON st.section_id = sec.section_id \n" +
            "                          WHERE CONCAT(st.first_name, ' ', st.last_name) = ? \n" +
            "                      AND sec.section_name = ?\n" +
            "                      );";

    String UPDATE_STUDENT = "UPDATE `student_tbl` SET `first_name` = ?, `last_name` = ?, `birth_date` = ?, `sex` = ?, `year_level` = ?, `course_id` = ?, `section_id` = ?, `archived` = ? WHERE `student_id` = ?";

    String ADD_STUDENT = "INSERT INTO `student_tbl`(`first_name`, `last_name`, `birth_date`,`sex` ,`year_level`, `course_id`, `section_id`) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

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
    
    //ADD SECTION
    String ADD_SECTION_QUERY = "INSERT INTO section_tbl (section_name, course_id) VALUES (?, ?)";

    // DELETE SECTION
    String DELETE_SECTION_BY_NAME_QUERY = "DELETE FROM section_tbl WHERE section_name = ?";



    //EDIT SUBJECT/COURSE/SECTION QUERY
    String EDIT_QUERY = "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?";

    //Queries for Course
    // SEARCH COURSE
    String SEARCH_COURSE = "SELECT course_tbl.course_id, course_tbl.course_name, course_tbl.department_name FROM course_tbl" +
            " WHERE %s LIKE ?";
    // DISPLAY COURSES
    String DISPLAY_COURSE = "SELECT course_tbl.course_id, course_tbl.course_name, course_tbl.department_name FROM course_tbl";
    //ADD COURSE
    String ADD_COURSE = "INSERT INTO `course_tbl`(`course_name`, `department_name`) VALUES (?, ?)";

    //Queries for Subject
    // DISPLAY SUBJECT
    String DISPLAY_SUBJECT = "SELECT subject_tbl.subject_id, subject_tbl.subject_name, course_tbl.course_name " +
            "FROM subject_tbl " +
            "INNER JOIN course_tbl ON subject_tbl.course_id = course_tbl.course_id";

    // DISPLAY SUBJECT BY COURSE
    String DISPLAY_SUBJECT_COURSE = "SELECT subject_tbl.subject_id, subject_tbl.subject_name, subject_tbl.course_id, course_tbl.course_name \n" +
            "FROM subject_tbl \n" +
            "INNER JOIN course_tbl ON subject_tbl.course_id = course_tbl.course_id \n" +
            "WHERE %s LIKE ?\n";


    // ADD SUBJECT
    String ADD_SUBJECT_QUERY = "INSERT INTO subject_tbl (subject_name, course_id) VALUES (?, ?)";

    //QUERIES FOR SCHEDULE
    //DISPLAY SCHEDULE
    String DISPLAY_SCHEDULE_FILTERED = "SELECT schedule_tbl.schedule_id, schedule_tbl.day, schedule_tbl.start_time, schedule_tbl.end_time, section_tbl.section_name, subject_tbl.subject_name FROM schedule_tbl INNER JOIN section_tbl ON schedule_tbl.section_id = section_tbl.section_id INNER JOIN subject_tbl ON schedule_tbl.subject_id = subject_tbl.subject_id WHERE %s LIKE ?";
    // SEARCH_STUDENT_COURSE query using LIKE
    String SEARCH_STUDENT_COURSE = "SELECT student_tbl.student_id, student_tbl.first_name, student_tbl.last_name, student_tbl.birth_date, student_tbl.sex, student_tbl.year_level, course_tbl.course_name, section_tbl.section_name, student_tbl.archived " +
            "FROM student_tbl " +
            "INNER JOIN course_tbl ON student_tbl.course_id = course_tbl.course_id " +
            "INNER JOIN section_tbl ON student_tbl.section_id = section_tbl.section_id " +
            "WHERE course_tbl.course_name LIKE ?";
    //ADD SCHEDULE

    String ADD_SCHEDULE = "INSERT INTO `schedule_tbl`(`day`, `section_id`, `subject_id`, `start_time`, `end_time`) VALUES (?, ?, ?, ?, ?)";

    //ADMIN_LOGIN query
    String ADMIN_LOGIN = "SELECT * FROM admin_tbl WHERE username = ? AND password = ?";

    //STUDENT SUBJECT QUERIES
    String SEARCH_STUDENT_SUBJECT = "SELECT student_subject_tbl.student_subject_id, student_tbl.first_name, student_tbl.last_name, subject_tbl.subject_name, section_tbl.section_name" +
            " From student_subject_tbl" +
            " INNER JOIN student_tbl ON student_subject_tbl.student_id = student_tbl.student_id" +
            " INNER JOIN subject_tbl ON student_subject_tbl.subject_id = subject_tbl.subject_id" +
            " INNER JOIN section_tbl ON student_subject_tbl.section_id = section_tbl.section_id" +
            " WHERE %s LIKE ?";  // Ensure to include section_name in your WHERE condition as needed
   String DISPLAY_SPECIFIC_SS = "SELECT ss.student_subject_id, st.first_name, st.last_name, sb.subject_name, sec.section_name \n" +
           "                       FROM student_subject_tbl ss \n" +
           "                       INNER JOIN student_tbl st ON ss.student_id = st.student_id \n" +
           "                       INNER JOIN subject_tbl sb ON ss.subject_id = sb.subject_id \n" +
           "                       INNER JOIN section_tbl sec ON ss.section_id = sec.section_id \n" +
           "                       WHERE CONCAT(st.first_name, ' ', st.last_name) = ? AND sb.subject_name = ?\n";
    //DELETE STUDENT SUBJECT
    String DELETE_STUDENT_SUBJECT = "DELETE FROM student_subject_tbl \n" +
            "WHERE student_subject_id IN (\n" +
            "    SELECT ss.student_subject_id \n" +
            "    FROM student_subject_tbl ss\n" +
            "    INNER JOIN student_tbl st ON ss.student_id = st.student_id\n" +
            "    INNER JOIN subject_tbl sb ON ss.subject_id = sb.subject_id\n" +
            "    WHERE CONCAT(st.first_name, ' ', st.last_name) = ? \n" +
            "    AND sb.subject_name = ?\n" +
            ");\n";
    String ADD_STUDENT_SUBJECT = "INSERT INTO `student_subject_tbl`(`student_id`, `subject_id`, `section_id`) VALUES (?,?,?)";
    String EDIT_STUDENT_SUBJECT = "UPDATE `student_subject_tbl` SET `student_id`=?,`subject_id`=?,`archived`=?,`section_id`=? WHERE  student_subject_id = ?";
    String VALIDATE_STUDENT_SUBJECT = "SELECT COUNT(1) \n" +
            "FROM student_subject_tbl ss\n" +
            "INNER JOIN student_tbl st ON ss.student_id = st.student_id\n" +
            "INNER JOIN subject_tbl sb ON ss.subject_id = sb.subject_id\n" +
            "WHERE CONCAT(st.first_name, ' ', st.last_name) = ? \n" +
            "AND sb.subject_name = ?;\n";
}

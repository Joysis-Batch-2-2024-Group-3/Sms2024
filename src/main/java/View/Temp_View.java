package View;

import Controller.*;
import Model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

public class Temp_View {
    private final StudentController sc = new StudentController();
    private final SubjectController sub = new SubjectController();
    private final SectionController sec = new SectionController();
    private final CourseController cc = new CourseController();
    private final ScheduleController sched = new ScheduleController();
    private final Student_SubjectController ssc = new Student_SubjectController();
    private final IndexController ic = new IndexController();
    private final Scanner scan = new Scanner(System.in);

    public void loginAdmin(){
        System.out.println("\n=== Login Admin ===");
        if (loginInputAdmin()){
            mainMenu();
        }else {
            System.out.println("Access denied!");
        }
    }

    private boolean loginInputAdmin(){
        System.out.println("Enter the username: ");
        String username = scan.nextLine();
        System.out.println("Enter the password: ");
        String password = scan.nextLine();

        AdminModel adminModel = new AdminModel(username,password);
        return sc.authenticateAdmin(adminModel);
    }

    public void mainMenu() {
        loginAdmin();
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Student Menu");
            System.out.println("2. Course Menu");
            System.out.println("3. Section Menu");
            System.out.println("4. Subject Menu");
            System.out.println("5. Schedule Menu");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> courseMenu();
                case 3 -> sectionMenu();
                case 4 -> subjectMenu();
                case 5 -> scheduleMenu();
                case 6 -> {
                    running = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void studentMenu() {
        StudentModel sm = new StudentModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Display All Students");
            System.out.println("2. Search for Student/s");
            System.out.println("3. Update Students");
            System.out.println("4. Display Archived Students");
            System.out.println("5. Add new Student");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> sc.displayStudents(sm, cm, secm);
                case 2 -> {
                    System.out.println("==== STUDENT SUB MENU====");
                    System.out.println("1. Search by section");
                    System.out.println("2. Search by course");
                    System.out.println("3. Search by First Name");
                    System.out.println("4. Search by Last Name");
                    System.out.println("5. Search by Student ID");
                    System.out.println("6. Search by specifying a column");
                    System.out.println("7. Back to Main Menu");
                    System.out.print("Choose an option: ");
                    int i = scan.nextInt();
                    scan.nextLine();
                    studentSubMenu(i);
                }
                case 3 -> {
                    System.out.println("Enter the Student ID to Update: ");
                    int studentId = scan.nextInt();
                    scan.nextLine();
                    sm.setStudentId(studentId);
                    sc.updateStudent(sm);
                    updateStudentField(sm, cm, secm);
                }
                case 4 -> sc.displayArchivedStudents(sm, cm, secm);
                case 5 -> addStudentSubMenu();
                case 6 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void updateStudentField(StudentModel student, CourseModel course, SectionModel section){
        boolean updatingStudent = true;
        while (updatingStudent){
            System.out.println("\nSelect Student field to update");
            System.out.println("1. Firstname: ");
            System.out.println("2. Lastname: ");
            System.out.println("3. Date of Birth (yyyy-mm-dd): ");
            System.out.println("4. Sex(M/F): ");
            System.out.println("5. Year Level: ");
            System.out.println("6. Course ID: ");
            System.out.println("7. Section ID: ");
            System.out.println("8. Finish update: ");

            System.out.println("Choose an option: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter new firstname: ");
                    student.setStudentFirstname(scan.nextLine());
                }
                case 2 -> {
                    System.out.println("Enter new lastname: ");
                    student.setStudentLastname(scan.nextLine());
                }
                case 3 -> {
                    try {
                        System.out.println("Enter new date of birthdate(yyyy-MM-dd): ");
                        student.setStudentDob(Date.valueOf(scan.nextLine()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd");
                    }
                }
                case 4 -> {
                    System.out.println("Enter new sex(M/F): ");
                    student.setStudentYearlvl(scan.nextLine().charAt(0));
                }
                case 5 -> {

                System.out.println("Enter new year level: ");
                student.setStudentYearlvl(scan.nextInt());

               }
                case 6 -> {
                    System.out.println("Enter new course ID: ");
                    course.setCourse_id(scan.nextInt());
                }
                case 7 -> {
                    System.out.println("Enter new section ID: ");
                    section.setSectionId(scan.nextInt());
                }
                case 8 -> {
                    sc.updateStudent(student);
                    updatingStudent = false;
                    System.out.println("Student information successfully updated.");
                }
                default -> System.out.println("Invalid Option please try again.");
            }
        }
    }

    public void courseMenu() {
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Course Menu ===");
            System.out.println("1. Display All Courses");
            System.out.println("2. Search for Course/s");
            System.out.println("3. Update for Courses");
            System.out.println("4. Add Course");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> cc.displayAllCourse(cm);
                case 2 -> {
                    System.out.println("======= Filter Course =======");
                    System.out.println("1. Course Name");
                    System.out.println("2. Department Name");
                    System.out.print("Choose a column to filter by: ");
                    int option = scan.nextInt();
                    scan.nextLine();

                    switch (option) {
                        case 1 -> {
                            System.out.print("Enter the course name: ");
                            String searchValue = scan.nextLine().toLowerCase();
                            cc.filterCourse("course_name", searchValue, cm);
                        }
                        case 2 -> {
                            System.out.print("Enter the department name: ");
                            String departmentName = scan.nextLine().toLowerCase();
                            cc.filterCourse("department_name", departmentName, cm);
                        }
                        default -> System.out.println("Invalid option. Please try again.");
                    }
                }
                case 3 -> {
                    System.out.println("Enter the course ID: ");
                    int courseId = scan.nextInt();
                    cm.setCourse_id(courseId);
                    cc.updateCourse(cm);
                    updateCourseField(cm);

                }
                case 4 -> {
                    LinkedHashMap<String, Object> values = new LinkedHashMap<>();

                    System.out.println("Add a new course");
                    System.out.print("Enter course name: ");
                    String courseName = scan.nextLine();
                    values.put("`course_name`",courseName);
                    System.out.print("Enter department name: ");
                    String departmentName = scan.nextLine();
                    values.put("`department_name`", departmentName);
                    if(!cc.courseConflictChecker(values)){
                        cc.addCourse(courseName, departmentName);
                    }
                    else{
                        System.out.println("Course conflict detected. Please enter new course details.");
                    }
                }
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void updateCourseField(CourseModel cm){
        boolean updatingCourse = true;
        while (updatingCourse){
            System.out.println("\nSelect Course Field to update");
            System.out.println("1. Course Name: ");
            System.out.println("2. Course Department Name: ");
            System.out.println("3. Finish Update");

            System.out.println("Choose an option: ");
            int choose = scan.nextInt();

            switch (choose){
                case 1 -> {
                    System.out.println("Enter the new course name: ");
                    cm.setCourseName(scan.nextLine());
                }
                case 2 ->{
                    System.out.println("Enter the new course department name: ");
                    cm.setDepartmentName(scan.nextLine());
                }
                case 3->{
                    updatingCourse = false;
                    System.out.println("Course information update completed.");
                }
                default -> System.out.println("Invalid option. Please try again.");

            }
        }

    }

    public void sectionMenu() {
        SectionModel sm = new SectionModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Section Menu ===");
            System.out.println("1. Display All Sections");
            System.out.println("2. Search for Section/s");
            System.out.println("3. Add Section");
            System.out.println("4. Delete Section by Name");
            System.out.println("5. Edit Section");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");


            try {
                String choice = scan.nextLine();

                switch (choice) {
                    case "1" -> sec.displayAllSection(sm, cm);
                    case "2" -> {
                        System.out.print("Enter the section name or substring: ");
                        String searchValue = scan.nextLine();
                        sec.filterSection("section_tbl.section_name",searchValue, sm, cm);
                    }
                    case "3" -> {
                        LinkedHashMap<String, Object>values = new LinkedHashMap<String, Object>();
                        // Adding new section
                        System.out.print("Enter Section Name: ");
                        String sectionName = scan.nextLine();
                        values.put("`section_name`", sectionName);
                        boolean isValidCourseId = false;
                        cc.displayAllCourse(cm);
                        while (!isValidCourseId) {
                            System.out.print("Enter Course ID: ");
                            int courseId = scan.nextInt();
                            if(cc.isValidCourse("course_id",courseId)) {
                                values.put("`course_id`", courseId);
                                sm.setCourse_id(courseId);
                                isValidCourseId = true;
                            }
                            else {
                                System.out.println("Invalid Course ID. Please try again.");
                            }
                        }
                        sm.setSectionName(sectionName);

                        if(!sec.sectionConflictChecker(values)) {
                            sec.addSection(sm);
                            sec.displayAllSection(sm, cm);
                        }
                        else {
                            System.out.println("Section conflict detected. Please enter new section details.");
                        }
                    }
                    case "4" -> {
                        // Deleting a section by name
                        boolean isValidSectionName = false;
                        while(!isValidSectionName){
                        System.out.print("Enter the Section Name to delete: ");
                        String sectionName = scan.nextLine();
                        if(sec.isValidSectionValue("section_name",sectionName)){
                            sec.deleteSection(sectionName);
                            sec.displayAllSection(sm, cm);
                            isValidSectionName = true;
                        }
                        else {
                            System.out.println("Invalid section name. Please enter a valid section name.");
                        }
                    }
                    }
                    case "5" -> {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Editing a section
                        sec.displayAllSection(sm, cm);
                       boolean isSectionValid = false;
                       while (!isSectionValid) {
                           System.out.print("Enter the current Section Name to edit: ");
                           String oldSectionName = scan.nextLine();
                       if (sec.isValidSectionValue("section_name", oldSectionName)) {
                        isSectionValid = true;
                        values.put("old", oldSectionName);
                       }
                       else {
                        System.out.println("Invalid section name. Please enter a valid section name.");
                        }
                       }

                       System.out.print("Enter the new Section Name: ");
                        String newSectionName = scan.nextLine();
                        values.put("`section_name`", newSectionName);

                        cc.displayAllCourse(cm);
                        boolean isValidCourseId = false;
                        while (!isValidCourseId) {
                            System.out.print("Enter the new Course ID: ");
                            int newCourseId = scan.nextInt();
                            if(cc.isValidCourse("course_id",newCourseId)) {
                                isValidCourseId = true;
                                values.put("`course_id`", newCourseId);
                            }
                            else {
                                System.out.println("Invalid Course ID. Please try again.");
                            }
                        }
                        sec.editSection(values);
                        sec.displayAllSection(sm, cm); 
                    }
                    case "6" -> running = false;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scan.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void subjectMenu() {
        SubjectModel subm = new SubjectModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Subject Menu ===");
            System.out.println("1. Display All Subjects");
            System.out.println("2. Search Subject by Course");
            System.out.println("3. Add Subject");
            System.out.println("4. Delete Subject by Name");
            System.out.println("5. Edit Subject");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> sub.displayAllSubject(subm, cm);
                    case 2 -> {
                        System.out.print("Enter the course name or substring: ");
                        String searchValue = scan.nextLine();
                        sub.displaySubjectByCourse("course_tbl.course_name", searchValue, subm, cm);
                    }
                    case 3 -> {
                        LinkedHashMap<String, Object>values = new LinkedHashMap<String, Object>();
                        // Adding new subject
                        System.out.print("Enter Subject Name: ");
                        String subjectName = scan.nextLine();
                        values.put("`subject_name`", subjectName);

                        boolean validCourse = false;
                        cc.displayAllCourse(cm);
                        while (!validCourse) {
                            System.out.print("Enter Course ID: ");
                            int courseId = scan.nextInt();
                            if(cc.isValidCourse("course_id",courseId)) {
                                subm.setCourse_id(courseId);
                                values.put("course_id", courseId);
                                validCourse = true;
                            }else {
                                System.out.println("Invalid course id, please check all available courses");
                            }
                        }
                        subm.setSubject_name(subjectName);
                        if(!sub.subjectConflictChecker(values)) {
                            sub.addSubject(subm);
                            sub.displayAllSubject(subm, cm);
                        }
                        else {
                            System.out.println("Subject conflict detected. Please enter new subject details.");
                        }
                    }
                    case 4 -> {
                        LinkedHashMap<String, Object>values = new LinkedHashMap<String, Object>();
                        // Option to delete a subject by name
                        boolean isSubjectValid = false;
                        while (!isSubjectValid) {
                            System.out.print("Enter Subject Name: ");
                            String subjectName = scan.nextLine();
                            if(sub.isValidSubjectValue("subject_name", subjectName)) {
                                isSubjectValid = true;
                                values.put("subject_name", subjectName);
                            } else {
                                System.out.println("Invalid subject name. Please enter a valid subject name.");
                            }
                        }
                        cc.displayAllCourse(cm);
                        boolean isCourseValid = false;
                        while (!isCourseValid) {
                            System.out.print("Enter Course ID: ");
                            int courseId = scan.nextInt();
                            if(cc.isValidCourse("course_id", courseId)) {
                                isCourseValid = true;
                                values.put("course_id", courseId);
                            } else {
                                System.out.println("Invalid course id. Please enter a valid course id.");
                            }
                        }
                        sub.deleteSubject(values);
                        sub.displayAllSubject(subm, cm);
                    }
                    case 5 -> {
                        LinkedHashMap<String, Object>values = new LinkedHashMap<String, Object>();
                        // edit a subject
                        boolean isSubjectValid = false;
                        while (!isSubjectValid) {
                            System.out.print("Enter the current Subject Name to edit: ");
                            String oldSubjectName = scan.nextLine();
                            if (sub.isValidSubjectValue("`subject_name`", oldSubjectName)) {
                                isSubjectValid = true;
                                values.put("old", oldSubjectName);
                            } else {
                                System.out.println("Invalid subject name. Please enter a valid subject name.");
                            }
                        }

                        System.out.print("Enter the new Subject Name: ");
                        String newSubjectName = scan.nextLine();
                        values.put("subject_name", newSubjectName);
                        cc.displayAllCourse(cm);
                        boolean isValidCourse = false;
                        while (!isValidCourse) {
                            System.out.print("Enter the new Course ID: ");
                            int newCourseId = scan.nextInt();
                            if(cc.isValidCourse("course_id", newCourseId)){
                                isValidCourse = true;
                                values.put("course_id", newCourseId);
                            }else {
                                System.out.println("Invalid course id. Please enter a valid course id.");
                            }
                        }
                        sub.editSubject(values);

                        sub.displayAllSubject(subm, cm);
                    }
                    case 6 -> running = false;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public void studentSubjectMenu() throws SQLException {
        StudentModel sm = new StudentModel();
        SubjectModel subm = new SubjectModel();
        SectionModel secm = new SectionModel();
        Student_SubjectModel ssm = new Student_SubjectModel();
        boolean running = true;
        while (running) {
            System.out.println("===Student Subject Menu===");
            System.out.println("1. Add Student Subject");
            System.out.println("2. Remove Student Subject");
            System.out.println("3. Display Student Subjects");
            System.out.println("4. Edit Student Subject");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1 -> {
                    LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                    boolean isValidStudent = false;
                    while (!isValidStudent) {
                        System.out.print("Enter Student ID: ");
                        int studentId = scan.nextInt();
                        if(sc.isValidStudent("student_id", studentId)) {
                            isValidStudent = true;
                            values.put("student_id", studentId);
                        } else {
                            System.out.println("Invalid student id. Please enter a valid student id.");
                        }
                    }
                    boolean isValidSubject = false;
                    while (!isValidSubject) {
                        System.out.print("Enter Subject ID: ");
                        int subjectId = scan.nextInt();
                        if(sub.isValidSubjectValue("subject_id", subjectId)) {
                            isValidSubject = true;
                            values.put("subject_id", subjectId);
                        } else {
                            System.out.println("Invalid subject id. Please enter a valid subject id.");
                        }
                    boolean isValidCourse = false;
                        while (!isValidCourse) {
                            System.out.print("Enter Course ID: ");
                            int sectionId = scan.nextInt();
                            if(sec.isValidSectionValue("section_id", sectionId)){
                                isValidCourse = true;
                                values.put("section_id", sectionId);
                            }else {
                                System.out.println("Invalid course id. Please enter a valid course id.");
                            }
                            ssc.addStudentSubject(values);
                        }
                    }
                }
            }
        }
    }
    public void studentSubjectDisplayMenu() {
        StudentModel sm = new StudentModel();
        SubjectModel sub = new SubjectModel();
        SectionModel secm = new SectionModel();
        Student_SubjectModel ssm = new Student_SubjectModel();

        boolean running = true;

        while (running) {
            System.out.println("=== Display Student Subject Menu ===");
            System.out.println("1. Display Student Subjects based on Student's name");
            System.out.println("2. Display Student Subjects based on Subject's name");
            System.out.println("3. Display Student Subjects based on Section name");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the student's name or substring: ");
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectByStudent(searchValue, ssm, sm, sub, secm);
                }
                case 2 -> {
                    System.out.print("Enter the subject's name or substring: ");
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectBySubject(searchValue, ssm, sm, sub, secm);
                }
                case 3 -> {
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    ssc.displayStudentsSubjectBySection(searchValue, ssm, sm, sub, secm);
                }
                case 4 -> {
                    running = false;
                    System.out.println("Returning to Main Menu...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void scheduleMenu() {
        ScheduleModel sm = new ScheduleModel();
        SubjectModel subm = new SubjectModel();
        SectionModel secm = new SectionModel();
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Schedule Menu ===");
            System.out.println("1. Search Schedule by Section");
            System.out.println("2. Search Schedule by Day");
            System.out.println("3. Add Schedule");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    sched.displayScheduleBySection("section_tbl.section_name",searchValue, sm, subm, secm);
                }
                case 2 -> {
                    System.out.print("Enter the day: ");
                    String day = scan.nextLine();
                    sched.displayScheduleByDay(day, sm, subm, secm);
                }
                case 3 -> {
                    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
                    boolean run = true;
                    while (run) {
                        try {
                            // Loop for Section ID
                            boolean validSection = false;
                            while (!validSection) {
                                sec.displayAllSection(secm, cm);
                                System.out.print("Enter Section ID: ");
                                int sectionId = scan.nextInt();

                                if (sec.isValidSectionValue("section_id", sectionId)) {
                                    int courseId = cc.getCourseID("section_tbl", "section_id", sectionId);
                                    sm.setSection_id(sectionId);
                                    values.put("section_id", sectionId);
                                    sched.displayScheduleBySection("section_tbl.section_id", sectionId, sm, subm, secm);
                                    sub.displaySubjectByCourse("course_tbl.course_id", courseId, subm, cm);
                                    validSection = true; // Valid section, exit loop
                                } else {
                                    System.out.println("Invalid Section ID, Please try again.");
                                }
                            }

                            // Loop for Subject ID
                            boolean validSubject = false;
                            while (!validSubject) {
                                System.out.print("Enter Subject ID: ");
                                int subjectId = scan.nextInt();

                                if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                    sm.setSubject_id(subjectId);
                                    validSubject = true; // Valid subject, exit loop
                                } else {
                                    System.out.println("Invalid Subject ID, Please try again.");
                                }
                            }
                            run = false; // Exit outer loop if both IDs are valid

                        } catch (SQLException e) {
                            System.out.println("SQLException: " + e.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("InputMismatchException: Please enter valid numeric IDs.");
                            scan.next(); // Clear the invalid input
                        } catch (Exception e) {
                            System.out.println("Exception: " + e.getMessage());
                        }
                    }
                    scan.nextLine(); // Clear the invalid
                    String day = null;
                    boolean validDay = false;
                    while (!validDay) {
                        System.out.print("Enter Day (Monday, Tuesday, Wednesday, etc.): ");
                        day = scan.nextLine();
                        if (day.matches("(?i)Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday")) {
                            validDay = true; // Exit loop if valid
                            sm.setDay(day);
                            values.put("`day`", day);
                        } else {
                            System.out.println("Invalid day. Please enter a valid day of the week.");
                        }
                    }


                    Time startTime = null;
                    while (startTime == null) {
                        System.out.print("Enter Start Time (HH:MM:SS Military): ");
                        try {
                            startTime = Time.valueOf(scan.nextLine());
                            values.put("`start_time`", startTime);

                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid time format. Please enter in HH:MM:SS format.");
                        }
                    }

                    Time endTime = null;
                    while (endTime == null) {
                        System.out.print("Enter End Time (HH:MM:SS Military): ");
                        try {
                            endTime = Time.valueOf(scan.nextLine());
                            sm.setEnd_time(endTime);
                            values.put("`end_time`", endTime);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid time format. Please enter in HH:MM:SS format.");
                        }
                    }
                    sm.setStart_time(startTime);

                    if (!sched.checkScheduleConflict(values)) {
                        System.out.println("Schedule added test lang to ");
                        //sched.addSchedule(sm, subm, secm);
                    } else {
                        System.out.println("Schedule conflict detected, please try again6");
                    }
                }
                case 4 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void studentSubMenu(int number) {
        StudentModel sm = new StudentModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();

        switch (number) {
            case 1 -> {
                System.out.print("Enter Section Name: ");
                String value = scan.nextLine();
                sc.displayStudentbySection(value, sm, cm, secm);
            }
            case 2 -> {
                System.out.print("Enter Course Name: ");
                String value = scan.nextLine();
                sc.displayStudentbyCourse(value, sm, cm, secm);
            }
            case 3 -> {
                System.out.print("Enter First Name: ");
                String value = scan.nextLine();
                sc.displayStudentbyFName(value, sm, cm, secm);
            }
            case 4 -> {
                System.out.print("Enter Last Name: ");
                String value = scan.nextLine();
                sc.displayStudentbyLname(value, sm, cm, secm);
            }
            case 5 -> {
                System.out.print("Enter Student ID: ");
                String value = scan.nextLine();
                sc.displayStudentbyStudent_ID(value, sm, cm, secm);
            }
            case 6 -> {
                System.out.println("Choose from this specific column: ");
                for (String column : StudentController.validColumns) {
                    System.out.print("| " + column + " | ");
                }
                System.out.println("Choose from this specific column: ");
                for (String column : StudentController.validColumns) {
                    System.out.print("| " + column + " | ");
                }

                String columnName = null;
                boolean validColumn = false;
                while (!validColumn) {
                    System.out.print("\nEnter Column Name: ");
                    columnName = scan.nextLine();
                    // Check if the input columnName is valid
                    if (Arrays.asList(StudentController.validColumns).contains(columnName)) {
                        validColumn = true; // Exit loop if valid
                    } else {
                        System.out.println("Invalid column name. Please choose from the valid columns.");
                    }
                }

                System.out.print("Enter Value: ");
                String columnValue = scan.nextLine();
                System.out.println("Searching for " + columnValue + " in " + columnName);
                sc.filterStudent(columnName, columnValue, sm, cm, secm);

            }
            case 7 -> System.out.println("Returning to Student Menu...");
            default -> System.out.println("Invalid option. Please try again.");
        }
    }
    public static void main(String[] args) {
        Temp_View view = new Temp_View();
        view.mainMenu();
    }

    public void addStudentSubMenu(){
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        StudentModel sm = new StudentModel();
        System.out.println("addStudentSubMenu");
        System.out.println("Enter First Name: ");
        String firstName = scan.nextLine();
        sm.setStudentFirstname(firstName);
        System.out.println("Enter Last Name: ");
        String lastName = scan.nextLine();
        sm.setStudentLastname(lastName);

        boolean validDate = false;
        while(!validDate) {
            System.out.println("Enter Birth Date (yyyy-mm-dd): ");
            String birthDate = scan.nextLine();
            if(ic.isValidDate(birthDate)){
                sm.setStudentDob(Date.valueOf(birthDate));
                validDate = true;
            }else {
                System.out.println("Enter a date following the format");
            }
        }

        boolean validSex = false;
        while (!validSex) {
            System.out.println("Enter Sex (F or M): ");
            char gender = scan.nextLine().toUpperCase().charAt(0);
            if (gender == 'F'  || gender == 'M'){
                sm.setStudentSex(gender);
                validSex = true;
            }
            else {
                System.out.println("Enter either F or M");
            }
        }
        int courseId;
        cc.displayAllCourse(cm);
        boolean validCourseID = false;
        while(!validCourseID) {
            System.out.println("Enter Course ID: ");
            courseId = scan.nextInt();
            if(cc.isValidCourse("course_id",courseId)){
                sm.setStudentCourse(courseId);
                validCourseID = true;
            }
            else {
                System.out.println("Invalid Course ID. Please try again.");
            }
        }

        sec.filterSection("course_tbl.course_id", sm.getStudentCourse(),secm, cm);
        boolean validSectionId = false;
        while (!validSectionId) {
            System.out.println("Enter Section ID:");
            int sectionId = scan.nextInt();
            if(sec.isValidSectionValue("section_id", sectionId)){
                sm.setStudentSection(sectionId);
                validSectionId = true;
            }
            else {
                System.out.println("Invalid Section ID. Please try again.");
            }
        }
        sc.addStudent(sm, cm ,secm);
        sc.displayStudents(sm, cm, secm);
    }

}

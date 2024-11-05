package View;

import Controller.*;
import Model.*;


import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

public class Temp_View {
    private final StudentController sc = new StudentController();
    private final AdminController ac = new AdminController();
    private final SubjectController sub = new SubjectController();
    private final SectionController sec = new SectionController();
    private final CourseController cc = new CourseController();
    private final ScheduleController sched = new ScheduleController();
    private final Student_SubjectController ssc = new Student_SubjectController();
    private final IndexController ic = new IndexController();
    private final Scanner scan = new Scanner(System.in);
    private final Admin admin = new Admin();

    private void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Student Menu");
            System.out.println("2. Course Menu");
            System.out.println("3. Section Menu");
            System.out.println("4. Subject Menu");
            System.out.println("5. Schedule Menu");
            System.out.println("6. Student Subject Menu");
            System.out.println("7. Exit");
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
                    studentSubjectMenu();
                }

                case 7 -> {
                    running = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void studentMenu() {
        StudentModel sm = new StudentModel();
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Display Student/s");
            System.out.println("2. Remove Student/s");
            System.out.println("3. Update Students");
            System.out.println("4. Add new Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("==== STUDENT SUB MENU====");
                    System.out.println("1. Search by section");
                    System.out.println("2. Search by course");
                    System.out.println("3. Search by First Name");
                    System.out.println("4. Search by Last Name");
                    System.out.println("5. Search by Student ID");
                    System.out.println("6. Search by specifying a column");
                    System.out.println("7. Show all Students");
                    System.out.println("8. Show all archived students");
                    System.out.println("9. Back to Main Menu");
                    System.out.print("Choose an option: ");
                    int i = scan.nextInt();
                    scan.nextLine();
                    studentSubMenu(i);
                }
                case 2 -> {
                    LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                    boolean isValidStudent = false;
                    while (!isValidStudent) {
                        System.out.println("Enter Student's First Name:");
                        String firstName = scan.nextLine();
                        System.out.println("Enter Student's Last Name:");
                        String lastName = scan.nextLine();
                        if (sc.isValidStudentName(firstName, lastName)) {
                            isValidStudent = true;
                            String name = firstName + " " + lastName;
                            values.put("student_name", name);
                        } else {
                            System.out.println("Invalid student name. Please enter a valid student name.");
                        }
                    }
                    boolean isValidSectionName = false;
                    while (!isValidSectionName) {
                        System.out.println("Enter Section's Name:");
                        String sectionName = scan.nextLine();
                        if (sec.isValidSectionValue("section_name", sectionName)) {
                            isValidSectionName = true;
                            values.put("section_name", sectionName);
                        } else {
                            System.out.println("Invalid section name. Please enter a valid section name.");
                        }
                    }
                    sc.dropStudent(values
                    );
                }
                case 3 -> {
                    sc.displayStudents(sm, cm, secm);

                    System.out.print("Enter the Student ID: ");
                    int studentId = scan.nextInt();
                    sm.setStudentId(studentId); // Set the student ID in the model


                    updateStudentField(studentId);
                }
                case 4 -> addStudentSubMenu();
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void courseMenu() {
        CourseModel cm = new CourseModel();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Course Menu ===");
            System.out.println("1. Display All Courses");
            System.out.println("2. Search for Course/s");
            System.out.println("3. Update for Courses");
            System.out.println("4. Add Course");
            System.out.println("5. Delete Course");
            System.out.println("6. Back to Main Menu");
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
                    cc.displayAllCourse(cm);
                    System.out.println("Enter the course ID: ");
                    int courseId = scan.nextInt();
                    updateCourseField(courseId);

                }
                case 4 -> {
                    LinkedHashMap<String, Object> values = new LinkedHashMap<>();

                    System.out.println("Add a new course");
                    System.out.print("Enter course name: ");
                    String courseName = scan.nextLine();
                    values.put("`course_name`", courseName);
                    System.out.print("Enter department name: ");
                    String departmentName = scan.nextLine();
                    values.put("`department_name`", departmentName);
                    if (!cc.courseConflictChecker(values)) {
                        cc.addCourse(courseName, departmentName);
                    } else {
                        System.out.println("Course conflict detected. Please enter new course details.");
                    }
                }
                case 5 -> {
                    LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                    cc.displayAllCourse(cm);
                    boolean isValidInput = false;
                    while(!isValidInput){
                        System.out.println("Enter course name: ");
                        String courseName = scan.nextLine();
                        values.put("course_name", courseName);
                        System.out.println("Enter department name: ");
                        String departmentName = scan.nextLine();
                        values.put("department_name", departmentName);
                        if (cc.validateBothName(values)) {
                            isValidInput = true;

                        }
                        else {
                            System.out.println("Course not found. Please enter valid course details.");
                        }
                    }
                    System.out.println("Confirm delete: "+values.get("course_name")+" "+values.get("department_name") +" (Y/N)");
                    char option = scan.nextLine().charAt(0);
                    if (option == 'Y' || option == 'y') {
                        cc.deleteCourse(values);
                    } else if (option == 'N' || option == 'n'){
                        System.out.println("Course deletion cancelled.");
                    }
                    else {
                        System.out.println("Invalid option. Course deletion cancelled.");
                    }


                }
                case 6 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void sectionMenu() {
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
            int choice = scan.nextInt();

            try {
                switch (choice) {
                    case 1 -> sec.displayAllSection(sm, cm);

                    case 2 -> {
                        System.out.print("Enter the section name or substring: ");
                        String searchValue = scan.nextLine();
                        sec.filterSection("section_tbl.section_name", searchValue, sm, cm);
                    }
                    case 3 -> {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Adding new section
                        System.out.print("Enter Section Name: ");
                        String sectionName = scan.nextLine();
                        values.put("`section_name`", sectionName);
                        boolean isValidCourseId = false;
                        cc.displayAllCourse(cm);
                        while (!isValidCourseId) {
                            System.out.print("Enter Course ID: ");
                            int courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                values.put("`course_id`", courseId);
                                sm.setCourse_id(courseId);
                                isValidCourseId = true;
                            } else {
                                System.out.println("Invalid Course ID. Please try again.");
                            }
                        }
                        sm.setSectionName(sectionName);

                        if (!sec.sectionConflictChecker(values)) {
                            sec.addSection(sm);
                            sec.displayAllSection(sm, cm);
                        } else {
                            System.out.println("Section conflict detected. Please enter new section details.");
                        }
                    }
                    case 4 -> {
                        // Deleting a section by name
                        boolean isValidSectionName = false;
                        while (!isValidSectionName) {
                            System.out.print("Enter the Section Name to delete: ");
                            String sectionName = scan.nextLine();
                            if (sec.isValidSectionValue("section_name", sectionName)) {
                                sec.deleteSection(sectionName);
                                sec.displayAllSection(sm, cm);
                                isValidSectionName = true;
                            } else {
                                System.out.println("Invalid section name. Please enter a valid section name.");
                            }
                        }
                    }
                    case 5 -> {
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
                            } else {
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
                            if (cc.isValidCourse("course_id", newCourseId)) {
                                isValidCourseId = true;
                                values.put("`course_id`", newCourseId);
                            } else {
                                System.out.println("Invalid Course ID. Please try again.");
                            }
                        }
                        sec.editSection(values);
                        sec.displayAllSection(sm, cm);
                    }


                    case 6 -> running = false;

                    default -> System.out.println("Invalid option. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scan.nextLine(); // Clear invalid input
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
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Adding new subject
                        System.out.print("Enter Subject Name: ");
                        String subjectName = scan.nextLine();
                        values.put("`subject_name`", subjectName);

                        boolean validCourse = false;
                        cc.displayAllCourse(cm);
                        while (!validCourse) {
                            System.out.print("Enter Course ID: ");
                            int courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                subm.setCourse_id(courseId);
                                values.put("course_id", courseId);
                                validCourse = true;
                            } else {
                                System.out.println("Invalid course id, please check all available courses");
                            }
                        }
                        subm.setSubject_name(subjectName);
                        if (!sub.subjectConflictChecker(values)) {
                            sub.addSubject(subm);
                            sub.displayAllSubject(subm, cm);
                        } else {
                            System.out.println("Subject conflict detected. Please enter new subject details.");
                        }
                    }
                    case 4 -> {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        // Option to delete a subject by name
                        boolean isSubjectValid = false;
                        while (!isSubjectValid) {
                            System.out.print("Enter Subject Name: ");
                            String subjectName = scan.nextLine();
                            if (sub.isValidSubjectValue("subject_name", subjectName)) {
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
                            if (cc.isValidCourse("course_id", courseId)) {
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
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
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
                            if (cc.isValidCourse("course_id", newCourseId)) {
                                isValidCourse = true;
                                values.put("course_id", newCourseId);
                            } else {
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
    private void scheduleMenu() {
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
            System.out.println("4. Update Schedule");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    sched.displayScheduleBySection("section_tbl.section_name", searchValue, sm, subm, secm);
                }
                case 2 -> {
                    System.out.print("Enter the day: ");
                    String day = scan.nextLine();
                    sched.displayScheduleByDay(day, sm, subm, secm);
                }
                case 3 -> addScheduleSubMenu(subm, secm, cm, sm);
                case 4 -> {
                    System.out.print("Enter the section name or substring: ");
                    String searchValue = scan.nextLine();
                    sched.displayScheduleBySection("section_tbl.section_name", searchValue, sm, subm, secm);

                    System.out.println("Enter Schedule ID: ");
                    int scheduleId = scan.nextInt();
                    updateScheduleField(scheduleId);


                }
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void studentSubjectMenu() {
        StudentModel sm = new StudentModel();
        SubjectModel subm = new SubjectModel();
        CourseModel cm = new CourseModel();
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
            scan.nextLine();

            switch (choice) {
                case 1 -> {
                    try {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<>();

                        // Validate Course Name
                        boolean isValidCourseName = false;
                        String courseName = "";
                        while (!isValidCourseName) {
                            System.out.println("Enter a Course Name: ");
                            courseName = scan.nextLine();
                            if (courseName.isEmpty()) {
                                System.out.println("Course Name cannot be empty.");
                            } else {
                                if (cc.isValidCourse("course_name", courseName)) {
                                    isValidCourseName = true;
                                } else {
                                    System.out.println("Enter a valid course to filter students.");
                                }
                            }
                        }
                        sc.displayStudentbyCourse(courseName, sm, cm, secm);


                        // Validate Student ID
                        boolean isValidStudent = false;
                        while (!isValidStudent) {
                            System.out.print("Enter Student ID: ");
                            int studentId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sc.isValidStudent("student_id", studentId)) {
                                isValidStudent = true;
                                values.put("student_id", studentId);
                            } else {
                                System.out.println("Invalid student id. Please enter a valid student id.");
                            }
                        }
                        sub.displayAllSubject(subm, cm);
                        // Validate Subject ID
                        boolean isValidSubject = false;
                        while (!isValidSubject) {
                            System.out.print("Enter Subject ID: ");
                            int subjectId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                isValidSubject = true;
                                values.put("subject_id", subjectId);
                            } else {
                                System.out.println("Invalid subject id. Please enter a valid subject id.");
                            }
                        }

                        sec.displayAllSection(secm, cm);
                        // Validate Section ID
                        boolean isValidSection = false;
                        while (!isValidSection) {
                            System.out.print("Enter Section ID: ");
                            int sectionId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                isValidSection = true;
                                values.put("section_id", sectionId);
                            } else {
                                System.out.println("Invalid section id. Please enter a valid section id.");
                            }
                        }

                        // Add Student Subject after all validations
                        ssc.addStudentSubject(values);

                    } catch (SQLException e) {
                        System.out.println("SQLError: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 2 -> {
                    try {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        boolean isValidStudent = false;
                        while (!isValidStudent) {
                            System.out.println("Enter Student's First Name:");
                            String firstName = scan.nextLine();
                            System.out.println("Enter Student's Last Name:");
                            String lastName = scan.nextLine();
                            if (sc.isValidStudentName(firstName, lastName)) {
                                isValidStudent = true;
                                String name = firstName + " " + lastName;
                                values.put("student_name", name);
                            } else {
                                System.out.println("Invalid student name. Please enter a valid student name.");
                            }
                        }
                        boolean isValidSubject = false;
                        while (!isValidSubject) {
                            System.out.println("Enter Subject's Name:");
                            String subjectName = scan.nextLine();
                            if (sub.isValidSubjectValue("subject_name", subjectName)) {
                                isValidSubject = true;
                                values.put("subject_name", subjectName);
                            } else {
                                System.out.println("Invalid subject name. Please enter a valid subject name.");
                            }
                        }
                        ssc.deleteStudentSubject(values);
                    } catch (Exception e) {
                        System.out.println("Error while deleting student subject: " + e.getMessage());
                    }
                }
                case 3 -> studentSubjectDisplayMenu();
                case 4 -> {
                    try {
                        LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
                        boolean isValidStudent = false;
                        while (!isValidStudent) {
                            System.out.println("Enter Student's First Name:");
                            String firstName = scan.nextLine();
                            System.out.println("Enter Student's Last Name:");
                            String lastName = scan.nextLine();
                            if (sc.isValidStudentName(firstName, lastName)) {
                                isValidStudent = true;
                                String name = firstName + " " + lastName;
                                values.put("student_name", name);
                            } else {
                                System.out.println("Invalid student name. Please enter a valid student name.");
                            }
                        }
                        boolean isValidSubject = false;
                        while (!isValidSubject) {
                            System.out.println("Enter Subject's Name:");
                            String subjectName = scan.nextLine();
                            if (sub.isValidSubjectValue("subject_name", subjectName)) {
                                isValidSubject = true;
                                values.put("subject_name", subjectName);
                            } else {
                                System.out.println("Invalid subject name. Please enter a valid subject name.");
                            }
                        }
                        if (ssc.isValidStudentSubject(values)) {
                            int SSID = ssc.getSpecificSSID(values, ssm, sm, subm, secm);
                            values.put("student_subject_id", SSID);
                            boolean isValidCourseName = false;
                            String courseName = "";
                            while (!isValidCourseName) {
                                System.out.println("Enter a Course Name: ");
                                courseName = scan.nextLine();
                                if (courseName.isEmpty()) {
                                    System.out.println("Course Name cannot be empty.");
                                } else {
                                    if (cc.isValidCourse("course_name", courseName)) {
                                        isValidCourseName = true;
                                    } else {
                                        System.out.println("Enter a valid course to filter students.");
                                    }
                                }
                            }
                            sc.displayStudentbyCourse(courseName, sm, cm, secm);

                            // Validate Student ID
                            boolean isValidStudentID = false;
                            while (!isValidStudentID) {
                                System.out.print("Enter new Student ID: ");
                                int studentId = scan.nextInt();
                                scan.nextLine(); // Clear the buffer
                                if (sc.isValidStudent("student_id", studentId)) {
                                    isValidStudentID = true;
                                    values.put("student_id", studentId);
                                } else {
                                    System.out.println("Invalid student id. Please enter a valid student id.");
                                }
                            }
                            sub.displayAllSubject(subm, cm);
                            // Validate Subject ID
                            boolean isValidSubjectID = false;
                            while (!isValidSubjectID) {
                                System.out.print("Enter new Subject ID: ");
                                int subjectId = scan.nextInt();
                                scan.nextLine(); // Clear the buffer
                                if (sub.isValidSubjectValue("subject_id", subjectId)) {
                                    isValidSubjectID = true;
                                    values.put("subject_id", subjectId);
                                } else {
                                    System.out.println("Invalid subject id. Please enter a valid subject id.");
                                }
                            }

                            sec.displayAllSection(secm, cm);
                            // Validate Section ID
                            boolean isValidSectionID = false;
                            while (!isValidSectionID) {
                                System.out.print("Enter new Section ID: ");
                                int sectionId = scan.nextInt();
                                scan.nextLine(); // Clear the buffer
                                if (sec.isValidSectionValue("section_id", sectionId)) {
                                    isValidSectionID = true;
                                    values.put("section_id", sectionId);
                                } else {
                                    System.out.println("Invalid section id. Please enter a valid section id.");
                                }
                            }
                            boolean isValidBoolean = false;
                            while (!isValidBoolean) {
                                System.out.print("Archive Student-Subject? (y/n): ");
                                String input = scan.nextLine();
                                if (input.equalsIgnoreCase("y")) {
                                    values.put("archived", true);
                                    isValidBoolean = true;
                                } else if (input.equalsIgnoreCase("n")) {
                                    values.put("archived", false);
                                    isValidBoolean = true;
                                } else {
                                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                                }
                            }
                            ssc.editStudentSubject(values);
                        } else {
                            System.out.println("No matching student-subject found.");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void studentSubjectDisplayMenu() {
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
    private void studentSubMenu(int number) {
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
            case 7 -> sc.displayStudents(sm, cm, secm);
            case 8 -> sc.displayArchivedStudents(sm, cm, secm);
            case 9 -> System.out.println("Returning to Student Menu...");
            default -> System.out.println("Invalid option. Please try again.");
        }
    }
    private void addStudentSubMenu() {
        CourseModel cm = new CourseModel();
        SectionModel secm = new SectionModel();
        StudentModel sm = new StudentModel();

        System.out.println("=== Add Student ===");

        // First Name Input
        System.out.print("Enter First Name: ");
        String firstName = scan.nextLine();
        sm.setStudentFirstname(firstName);

        // Last Name Input
        System.out.print("Enter Last Name: ");
        String lastName = scan.nextLine();
        sm.setStudentLastname(lastName);

        // Birth Date Input with Validation
        boolean validDate = false;
        while (!validDate) {
            System.out.print("Enter Birth Date (yyyy-mm-dd): ");
            String birthDate = scan.nextLine();
            if (ic.isValidDate(birthDate)) {
                sm.setStudentDob(Date.valueOf(birthDate));
                validDate = true;
            } else {
                System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            }
        }

        // Sex Input with Validation
        boolean validSex = false;
        while (!validSex) {
            System.out.print("Enter Sex (F or M): ");
            char gender = scan.nextLine().toUpperCase().charAt(0);
            if (gender == 'F' || gender == 'M') {
                sm.setStudentSex(gender);
                validSex = true;
            } else {
                System.out.println("Invalid input. Please enter either 'F' or 'M'.");
            }
        }

        // Course ID Input with Validation
        int courseId;
        cc.displayAllCourse(cm);
        boolean validCourseID = false;
        while (!validCourseID) {
            System.out.print("Enter Course ID: ");
            courseId = scan.nextInt();
            if (cc.isValidCourse("course_id", courseId)) {
                sm.setStudentCourse(courseId);
                validCourseID = true;
            } else {
                System.out.println("Invalid Course ID. Please try again.");
            }
        }

        // Filter and Display Sections by Course ID
        sec.filterSection("course_tbl.course_id", sm.getStudentCourse(), secm, cm);

        // Section ID Input with Validation
        boolean validSectionId = false;
        while (!validSectionId) {
            System.out.print("Enter Section ID: ");
            int sectionId = scan.nextInt();
            if (sec.isValidSectionValue("section_id", sectionId)) {
                sm.setStudentSection(sectionId);
                validSectionId = true;
            } else {
                System.out.println("Invalid Section ID. Please try again.");
            }
        }

        // Add Student and Display All Students
        sc.addStudent(sm, cm, secm);
        sc.displayStudents(sm, cm, secm);
    }
    private void addScheduleSubMenu(SubjectModel subm, SectionModel secm, CourseModel cm, ScheduleModel sm) {
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
            sched.addSchedule(sm, subm, secm);
        } else {
            System.out.println("Schedule conflict detected, please try again6");
        }
    }
    private void updateCourseField(int courseID) {
        CourseModel course = new CourseModel();
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingCourse = true;

        while (updatingCourse) {
            System.out.println("\nSelect Course Field to update");
            System.out.println("1. Course Name");
            System.out.println("2. Course Department Name");
            System.out.println("3. Finish Update");

            System.out.print("Choose an option: ");
            int choose = scan.nextInt();
            scan.nextLine(); // Clear the newline character

            switch (choose) {
                case 1 -> {
                    System.out.print("Enter the new course name: ");
                    String newCourseName = scan.nextLine();
                    values.put("course_name", newCourseName);
                }
                case 2 -> {
                    System.out.print("Enter the new course department name: ");
                    String newDepartmentName = scan.nextLine();
                    values.put("department_name", newDepartmentName);
                }
                case 3 -> {
                    updatingCourse = false;
                    System.out.println("Course information update completed.");
                    cc.updateCourse(values, courseID);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void updateScheduleField(int SchedID) {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        boolean updatingSched = true;

        while (updatingSched) {
            System.out.println("\nSelect to Update Schedule");
            System.out.println("1. Update Day Schedule");
            System.out.println("2. Update Start Time Schedule");
            System.out.println("3. Update End Time Schedule");
            System.out.println("4. Update Section ID");
            System.out.println("5. Finish Update");
            System.out.print("Enter to choose to update schedule: ");

            try {
                int chooseToUpdateSched = scan.nextInt();
                scan.nextLine(); // Clear the newline character

                switch (chooseToUpdateSched) {
                    case 1 -> {
                        String day = null;
                        boolean validDay = false;
                        while (!validDay) {
                            System.out.print("Enter Day (Monday, Tuesday, Wednesday, etc.): ");
                            day = scan.nextLine();
                            if (day.matches("(?i)Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday")) {
                                validDay = true; // Exit loop if valid
                                values.put("day", day);
                            } else {
                                System.out.println("Invalid day. Please enter a valid day of the week.");
                            }
                        }
                    }
                    case 2 -> {
                        Time startTime = null;
                        while (startTime == null) {
                            System.out.print("Enter to update new Start Time (HH:MM:SS): ");
                            try {
                                startTime = Time.valueOf(scan.nextLine());
                                values.put("start_time", startTime);

                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid time format. Please enter in HH:MM:SS format.");
                            }
                        }
                    }
                    case 3 -> {
                        Time endTime = null;
                        while (endTime == null) {
                            System.out.print("Enter to update new End Time (HH:MM:SS): ");
                            try {
                                endTime = Time.valueOf(scan.nextLine());
                                values.put("end_time", endTime);

                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid time format. Please enter in HH:MM:SS format.");
                            }
                        }
                    }
                    case 4 -> {
                        sec.displayAllSection(new SectionModel(), new CourseModel());
                        // Validate Section ID
                        boolean isValidSection = false;
                        while (!isValidSection) {
                            System.out.print("Enter new Section ID: ");
                            int sectionId = scan.nextInt();
                            scan.nextLine(); // Clear the buffer
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                isValidSection = true;
                                values.put("section_id", sectionId);
                            } else {
                                System.out.println("Invalid section id. Please enter a valid section id.");
                            }
                        }
                    }

                    case 5 -> {
                        if (!values.isEmpty()) {
                            sched.updateSchedule(values, SchedID);
                        } else {
                            System.out.println("No fields to update.");
                        }
                        updatingSched = false;
                    }
                    default -> System.out.println("Invalid option. Please try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scan.nextLine(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid time format. Please use HH:MM:SS.");
            }
        }
    }
    private void updateStudentField(int StudentID) {
        StudentModel student = new StudentModel();
        CourseModel course = new CourseModel();
        SectionModel section = new SectionModel();

        LinkedHashMap<String, Object> value = new LinkedHashMap<>();
        boolean updatingStudent = true;

        while (updatingStudent) {
            System.out.println("\n=== Select To Update Student===");
            System.out.println("1. Update Firstname");
            System.out.println("2. Update Lastname");
            System.out.println("3. Update Date of Birth");
            System.out.println("4. Update Sex (M/F)");
            System.out.println("5. Update Year Level");
            System.out.println("6. Update Course ID");
            System.out.println("7. Update Section ID");
            System.out.println("8. Update archived");
            System.out.println("9. Finish Update");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Clear newline character
            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new firstname: ");
                        String newFirstname = scan.nextLine();
                        value.put("first_name", newFirstname);
                    }
                    case 2 -> {
                        System.out.print("Enter new lastname: ");
                        String newLastname = scan.nextLine();
                        value.put("last_name", newLastname);
                    }
                    case 3 -> {
                        boolean validDate = false;
                        while (!validDate) {
                            System.out.print("Enter Birth Date (yyyy-mm-dd): ");
                            String birthDate = scan.nextLine();
                            if (ic.isValidDate(birthDate)) {
                                value.put("birth_date", Date.valueOf(birthDate));
                                validDate = true;
                            } else {
                                System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
                            }
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter new sex (M/F): ");
                        char newSex = scan.nextLine().toUpperCase().charAt(0);
                        if (newSex == 'M' || newSex == 'F') {
                            value.put("sex", newSex);
                        } else {
                            System.out.println("Invalid input. Please enter 'M' or 'F'.");
                        }
                    }
                    case 5 -> {
                        System.out.print("Enter new year level: ");
                        int newYearLevel = scan.nextInt();
                        scan.nextLine(); // Clear newline character
                        value.put("year_level", newYearLevel);
                    }
                    case 6 -> {
                        int courseId;
                        cc.displayAllCourse(new CourseModel());
                        boolean validCourseID = false;
                        while (!validCourseID) {
                            System.out.print("Enter Course ID: ");
                            courseId = scan.nextInt();
                            if (cc.isValidCourse("course_id", courseId)) {
                                value.put("course_id", courseId);
                                validCourseID = true;
                            } else {
                                System.out.println("Invalid Course ID. Please try again.");
                            }
                        }

                    }
                    case 7 -> {
                        int sectionId;
                        sec.displayAllSection(new SectionModel(), new CourseModel());
                        boolean validSectionID = false;
                        while (!validSectionID) {
                            System.out.print("Enter Section ID: ");
                            sectionId = scan.nextInt();
                            if (sec.isValidSectionValue("section_id", sectionId)) {
                                value.put("section_id", sectionId);
                                validSectionID = true;
                            } else {
                                System.out.println("Invalid Section ID. Please try again.");
                            }
                        }
                    }
                    case 8 -> {
                        System.out.print("Toggle Archived Status (current: " + (value.getOrDefault("archived", "No")) + "). Enter 'Y' to Archive or 'N' to Unarchive: ");
                        char archivedChoice = scan.nextLine().toUpperCase().charAt(0);
                        if (archivedChoice == 'Y') {
                            value.put("archived", true);
                        } else if (archivedChoice == 'N') {
                            value.put("archived", false);
                        } else {
                            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                        }
                    }
                    case 9 -> {
                        if (!value.isEmpty()) {
                            sc.updateStudent(value, StudentID);
                        } else {
                            System.out.println("No fields to update.");
                        }
                        updatingStudent = false; // Exit the loop after finishing updates
                    }
                    default -> System.out.println("Invalid option. Please try again.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scan.nextLine(); // Clear invalid input
            }
        }
    }


    public static void main (String[]args){
        Temp_View view = new Temp_View();
            boolean auth = Admin.LoginAdmin();
            if (auth) {
                view.mainMenu();
            }
    }
}
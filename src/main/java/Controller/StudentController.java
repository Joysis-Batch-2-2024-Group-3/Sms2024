package Controller;
import Db.Db;
import Model.StudentModel;
import Repository.StudentRepository;
public class StudentController extends Db implements StudentRepository{


    @Override
    public void displayStudents(StudentModel student) {
    try{
        connect();
        state = con.createStatement();
        result = state.executeQuery("SELECT * From student_tbl");
        while(result.next()){
        student.setStudentId(result.getInt("student_id"));
        student.setStudentFirstname(result.getString("first_name"));
        student.setStudentLastname(result.getString("last_name"));
        student.setStudentDob(result.getDate("birth_date"));
        student.setStudentSex(result.getString("sex").charAt(0));
        student.setStudentYearlvl(result.getInt("year_level"));
        student.setStudentCourse(result.getInt("course_id"));
        student.setStudentSection(result.getString("section_id"));
        student.setStudentArchive(result.getBoolean("archived"));

            System.out.printf("%-5s | %-15s | %-15s | %-10s | %-5s | %-10s | %-10s | %-10s | %-8s\n",
                    "ID", "First Name", "Last Name", "DOB", "Sex", "Year Level", "Course", "Section", "Archived");

            System.out.printf("%-5d | %-15s | %-15s | %-10s | %-5c | %-10d | %-10d | %-10s | %-8s\n",
                    student.getStudentId(), student.getStudentFirstname(), student.getStudentLastname(),
                    student.getStudentDob(), student.getStudentSex(), student.getStudentYearlvl(),
                    student.getStudentCourse(), student.getStudentSection(), student.isStudentArchive() ? "Yes" : "No");

        }

    }
    catch(Exception e){
        System.out.println(e.getMessage());
    }
    
    }
}

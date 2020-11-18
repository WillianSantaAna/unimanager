package pt.iade.unimanager.models.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pt.iade.unimanager.models.Enrolment;
import pt.iade.unimanager.models.Student;

public class StudentRepository {
    private static ArrayList<Student> students = new ArrayList<Student>();

    public static void populate() {
        Student s;
        s = new Student("John", LocalDate.parse("2000-05-24"), 'M');
        s.setEmail("john@gmail.com");
        students.add(s);
        students.add(new Student("Mary", LocalDate.parse("1999-12-23"), 'F'));
        s = new Student("James", LocalDate.parse("2001-07-02"), 'M');
        students.add(s);
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static Student getStudent(int number) {
        for (Student student : students)
            if (student.getNumber() == number)
                return student;

        return null;
    }

    public static boolean deleteStudent(int number) {
        return students.removeIf((student) -> student.getNumber() == number);
    }

    public static Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    public static ArrayList<Enrolment> getEnrolments(int number) {
        for (Student student : students)
            if (student.getNumber() == number)
                return student.getEnrolments();
        
        return null;
    }
    
    
}

package pt.iade.unimanager.models.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import pt.iade.unimanager.models.Teacher;
import pt.iade.unimanager.models.Unit;

public class TeacherRepository {

    private static ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    public static void populate() {
        teachers.add(new Teacher("Pedro", 'M', LocalDate.parse("1981-09-14")));
        teachers.add(new Teacher("Marcos", 'M', LocalDate.parse("1981-09-14")));
        teachers.add(new Teacher("Francisco", 'M', LocalDate.parse("1981-09-14")));
        teachers.add(new Teacher("Priscilla", 'F', LocalDate.parse("1981-09-14")));
        teachers.add(new Teacher("Roberto", 'M', LocalDate.parse("1981-09-14")));
        teachers.add(new Teacher("Ana", 'F', LocalDate.parse("1981-09-14")));
    }

    public static ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public static Teacher getTeacher(int mecNumber) {
        for (Teacher teacher : teachers)
            if (teacher.getMecNumber() == mecNumber)
                return teacher;
        return null;
    }

    public static ArrayList<Unit> getUnits(int mecNumber) {
        for (Teacher teacher : teachers)
            if (teacher.getMecNumber() == mecNumber)
                return teacher.getUnits();
        return null;
    }

    public static void addUnit(int mecNumber, Unit unit) {
        for (Teacher teacher : teachers)
            if (teacher.getMecNumber() == mecNumber)
                teacher.addUnit(unit);
    }

    public static boolean removeUnit(int mecNumber, int unitID) {
        for (Teacher teacher : teachers)
            if (teacher.getMecNumber() == mecNumber)
                return teacher.getUnits().removeIf(unit -> unit.getId() == unitID);
        
        return false;
    }
}

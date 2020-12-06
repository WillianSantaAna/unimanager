package pt.iade.unimanager.models;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student extends Person {
    private static int nextNumber = 0;
    private int number;

    @JsonIgnore
    private ArrayList<Enrolment> enrolments;

    public Student(String name, LocalDate birthDate, char gender) {
        super(name, gender, birthDate);
        this.number = nextNumber;
        nextNumber++;
        enrolments = new ArrayList<Enrolment>();
    }

    public static int getNextNumber() {
        return nextNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getNumber() {
        return this.number;
    }

    public void enroll(Enrolment enrolment) {
        enrolments.add(enrolment);
        enrolment.getUnit().getEnrolments().add(enrolment);
    }

    public ArrayList<Enrolment> getEnrolments() {
        return this.enrolments;
    }

    public Enrolment getEnrolmentByUnitId(int unitId) {
        for (Enrolment enr : enrolments)
            if (enr.getUnit().getId() == unitId)
                return enr;

        return null;
    }

    @Override
    public String toString() {
        return "" + this.number + ": " + this.name;
    }

    @Override
    public String getReference() {
        return "S" + this.number;
    }
}

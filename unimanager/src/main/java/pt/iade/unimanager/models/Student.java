package pt.iade.unimanager.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student extends Person implements Statistical {
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

    @Override
    public double getAverage() {
        double sum = 0;
        int counter = 0;

        for (Enrolment enrolment : enrolments) {
            if (enrolment.getGrade() >= 0) {
                sum += enrolment.getGrade();
                counter++;
            }
        }

        return counter == 0 ? sum : sum / counter;
    }

    @Override
    public double getMax() {
        return Collections.max(getGradesList());
    }

    @Override
    public double getMin() {
        return Collections.min(getGradesList());
    }

    @Override
    public ArrayList<HistogramSlot> getHistogram(int nSlots) {
        ArrayList<HistogramSlot> histograms = new ArrayList<>();
        double start = 0, end = 0, value = 0, ceilGrade = 20;
        final double SLOT = ceilGrade / nSlots;

        for (int i = 0; i < nSlots; i++) {
            value = 0;
            start = i * SLOT;
            end = start + SLOT;

            for (Enrolment enrolment : enrolments) {
                if (start == 0) {
                    if (enrolment.getGrade() >= start && enrolment.getGrade() <= end)
                        value++;
                } else {
                    if (enrolment.getGrade() > start && enrolment.getGrade() <= end)
                        value++;
                }
            }
                
            histograms.add(new HistogramSlot(start, end, value));
        }

        return histograms;
    }

    private ArrayList<Double> getGradesList() {
        ArrayList<Double> grades = new ArrayList<>();

        for (Enrolment enrolment : enrolments)
            if (enrolment.getGrade() >= 0)
                grades.add(enrolment.getGrade());

        return grades;
    }
}

package pt.iade.unimanager.models;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public String getName() {
        return this.name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
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
        double max = 0;
        for (Enrolment enrolment : enrolments)
            if (enrolment.getGrade() > max)
                max = enrolment.getGrade();

        return max;
    }

    @Override
    public double getMin() {
        double min = 20;
        
        for (Enrolment enrolment : enrolments)
            if (enrolment.getGrade() < min)
                min = enrolment.getGrade();

        return min;
    }

    @Override
    public ArrayList<HistogramSlot> getHistogram(int nSlots) {
        ArrayList<HistogramSlot> histograms = new ArrayList<>();
        double start = 0, end = 0, value = 0, gradeCeiling = 20;
        final double SLOT = gradeCeiling / nSlots;

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
}

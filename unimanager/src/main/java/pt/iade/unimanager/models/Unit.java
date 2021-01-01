package pt.iade.unimanager.models;

import java.util.ArrayList;
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Unit implements Statistical {
   private int id;
   private String name;
   private int credits;
   private Teacher teacher;

   @JsonIgnore
   private ArrayList<Enrolment> enrolments;

   public Unit(int id, String name, int credits, Teacher teacher) {
      this.id = id;
      this.name = name;
      this.credits = credits;
      enrolments = new ArrayList<Enrolment>();
      teacher = this.teacher;
   }

   public int getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public int getCredits() {
      return credits;
   }

   public ArrayList<Enrolment> getEnrolments() {
      return enrolments;
   }

   public Teacher getTeacher() {
      return this.teacher;
   }

   public void setTeacher(Teacher teacher) {
      this.teacher = teacher;
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

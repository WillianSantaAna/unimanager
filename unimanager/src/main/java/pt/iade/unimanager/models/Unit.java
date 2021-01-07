package pt.iade.unimanager.models;

import java.util.ArrayList;

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

   public ArrayList<Student> getStudents() {
      ArrayList<Student> students = new ArrayList<>();

      for (Enrolment enrolment : enrolments)
         students.add(enrolment.getStudent());

      return students;
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
}

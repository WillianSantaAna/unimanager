package pt.iade.unimanager.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Unit {
   private int id;
   private String name;
   private int credits;
   private Teacher teacher;
   
   @JsonIgnore
   private ArrayList<Enrolment> enrollments;

   public Unit (int id, String name, int credits, Teacher teacher) {
      this.id = id;
      this.name = name;
      this. credits = credits;
      enrollments = new ArrayList<Enrolment>();
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

   public ArrayList<Enrolment> getEnrollments() {
      return enrollments;
   }

   public Teacher getTeacher() {
      return this.teacher;
   }

   public void setTeacher(Teacher teacher) {
      this.teacher = teacher;
   }
}

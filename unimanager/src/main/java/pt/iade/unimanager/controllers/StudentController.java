package pt.iade.unimanager.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanager.models.Enrolment;
import pt.iade.unimanager.models.Response;
import pt.iade.unimanager.models.Student;
import pt.iade.unimanager.models.Unit;
import pt.iade.unimanager.models.repositories.StudentRepository;
import pt.iade.unimanager.models.repositories.UnitRepository;
import pt.iade.unimanager.models.exceptions.AlreadyExistsException;
import pt.iade.unimanager.models.exceptions.NotFoundException;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudents() {
        logger.info("Sending all students");
        return StudentRepository.getStudents();
    }

    @GetMapping(path = "{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable("number") int number) throws NotFoundException {
        logger.info("Sending student with number " + number);

        Student student = StudentRepository.getStudent(number);

        if (student != null)
            return student;
        else
            throw new NotFoundException("" + number, "student", "number");
    }

    @DeleteMapping(path = "{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteStudent(@PathVariable("number") int number) {
        logger.info("deleting student with number " + number);
        if (StudentRepository.deleteStudent(number))
            return new Response(number + " was deleted.", null);
        else
            return new Response(number + " not found.", null);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student addStudent(@RequestBody Student student) {
        return StudentRepository.addStudent(student);
    }

    @GetMapping(path = "{number}/enrollments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Enrolment> getEnrollments(@PathVariable("number") int number) throws NotFoundException {
        logger.info("Sending enrollments of student with number " + number);
        Student student = StudentRepository.getStudent(number);
        if (student != null)
            return student.getEnrollments();
        else
            throw new NotFoundException("" + number, "Student", "number");
    }

    @GetMapping(path = "{number}/enrollments/{unitId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrolment getEnrolment(@PathVariable("number") int number, @PathVariable("unitId") int unitId)
            throws NotFoundException {
        logger.info("Sending enrolment with id " + unitId + " of student with number " + number);
        Student student = StudentRepository.getStudent(number);
        if (student != null) {
            Enrolment enr = student.getEnrolmentByUnitId(unitId);
            if (enr != null)
                return enr;
            else
                throw new NotFoundException("" + unitId, "Unit", "id");
        } else
            throw new NotFoundException("" + number, "Student", "number");
    }

    @PutMapping(path = "{number}/enrollments/{unitId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrolment setGrade(@PathVariable("number") int number, @PathVariable("unitId") int unitId,
            @RequestBody double grade) throws NotFoundException {
        logger.info("Setting grade of enrolment with id " + unitId + " of student with number " + number);
        Student student = StudentRepository.getStudent(number);
        if (student != null) {
            Enrolment enr = student.getEnrolmentByUnitId(unitId);
            if (enr != null) {
                enr.setGrade(grade);
                return enr;
            } else
                throw new NotFoundException("" + unitId, "Unit", "id");
        } else
            throw new NotFoundException("" + number, "Student", "number");
    }

    @PostMapping(path = "{number}/enrollments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrolment addEnrolment(@PathVariable("number") int number, @RequestBody int unitId)
            throws NotFoundException, AlreadyExistsException {
        logger.info("Enrolling student with number " + number + " in unit with id " + unitId);
        Student student = StudentRepository.getStudent(number);
        if (student != null) {
            Unit unit = UnitRepository.getUnit(unitId);
            if (unit != null) {
                if (student.getEnrolmentByUnitId(unitId) != null)
                    throw new AlreadyExistsException("" + unitId, "Unit", "id");
                else {
                    Enrolment enrolment = new Enrolment(student, unit, -1);
                    student.enroll(enrolment);
                    return enrolment;
                }
            } else
                throw new NotFoundException("" + unitId, "Unit", "id");
        } else
            throw new NotFoundException("" + number, "Student", "number");
    }

    @GetMapping(path = "{number}/enrollments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrolment getEnrolmentByUnitId(@PathVariable("number") int number, @PathVariable("number") int id) {
        return null;
    }

    @PostMapping(path = "{number}/enrollments", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addEnrolment(@PathVariable("number") int number) {
        return true;
    }
}

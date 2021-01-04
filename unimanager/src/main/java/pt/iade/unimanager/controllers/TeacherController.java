package pt.iade.unimanager.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanager.models.Teacher;
import pt.iade.unimanager.models.Unit;
import pt.iade.unimanager.models.exceptions.NotFoundException;
import pt.iade.unimanager.models.repositories.TeacherRepository;
import pt.iade.unimanager.models.repositories.UnitRepository;

@RestController
@RequestMapping(path = "/api/teachers")
public class TeacherController {
    private Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Teacher> getTeachers() {
        logger.info("Sending all teachers");

        return TeacherRepository.getTeachers();
    }

    @GetMapping(path = "{mecNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Teacher getTeacher(@PathVariable("mecNumber") int mecNumber) throws NotFoundException {
        logger.info("Sending a teacher by number " + mecNumber);
        Teacher teacher = TeacherRepository.getTeacher(mecNumber);

        if (teacher != null)
            return teacher;
        else
            throw new NotFoundException("" + mecNumber, "Teacher", "mecNumber");
    }

    @GetMapping(path = "{mecNumber}/units", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Unit> getUnits(@PathVariable("mecNumber") int mecNumber) throws NotFoundException {
        logger.info("Sending units of the teacher by mecNumber " + mecNumber);
        Teacher teacher = TeacherRepository.getTeacher(mecNumber);

        if (teacher != null)
            return teacher.getUnits();
        else
            throw new NotFoundException("" + mecNumber, "Teacher", "mecNumber");
    }

    @PostMapping(path = "{mecNumber}/unit", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addTeacher(@RequestBody Teacher teacher, @RequestBody Unit unit) {
        logger.info("Add Teacher " + teacher.getMecNumber() + " to Unit " + unit.getId());

        TeacherRepository.addUnit(mecNumber, unit);
    }

    @DeleteMapping(path = "{unitId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeTeacher(@PathVariable("unitId") int unitId) {
        logger.info("Remove a teacher from Unit " + unitId);

        UnitRepository.removeTeacher(unitId);
    }
}

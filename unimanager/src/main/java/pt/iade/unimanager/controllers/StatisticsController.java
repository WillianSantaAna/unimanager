package pt.iade.unimanager.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanager.models.GroupResult;
import pt.iade.unimanager.models.HistogramSlot;
import pt.iade.unimanager.models.Statistical;
import pt.iade.unimanager.models.StatisticsResult;
import pt.iade.unimanager.models.Teacher;
import pt.iade.unimanager.models.Unit;
import pt.iade.unimanager.models.exceptions.NotFoundException;
import pt.iade.unimanager.models.repositories.StudentRepository;
import pt.iade.unimanager.models.repositories.TeacherRepository;
import pt.iade.unimanager.models.repositories.UnitRepository;

@RestController
@RequestMapping(path = "/api/statistics")
public class StatisticsController {
    private Logger logger = LoggerFactory.getLogger(StatisticsController.class);
    private static final int NSLOTS = 5;

    @GetMapping(path = "histogram/{type}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<HistogramSlot> getHistogram(@PathVariable("type") String type,
            @PathVariable("number") int number) {
        logger.info("Obtain the histogram slot list of " + type + " by number " + number);

        Statistical statistic;

        switch (type.toLowerCase()) {
            case "student":
                statistic = StudentRepository.getStudent(number);
                break;
            case "unit":
                statistic = UnitRepository.getUnit(number);
                break;
            default:
                throw new NotFoundException("" + number, type, "number");
        }

        return statistic.getHistogram(NSLOTS);
    }

    @GetMapping(path = "{type}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticsResult getStatistics(@PathVariable("type") String type, @PathVariable("number") int number) {
        logger.info("Obtain the average, maximum, minimum and range of " + type + " by number " + number);
        
        Statistical s;

        switch (type.toLowerCase()) {
            case "student":
                s = StudentRepository.getStudent(number);
                break;
            case "unit":
                s = UnitRepository.getUnit(number);
                break;
            default:
                throw new NotFoundException("" + number, type, "number");
        }

        return new StatisticsResult(s.getAverage(), s.getMax() , s.getMin(), s.getRange());
    }

    @GetMapping(path = "group/average/{type}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupResult getAverageOfAverages(@PathVariable("type") String type, @PathVariable("number") int number) {
        logger.info("Obtain the average of averages of " + type + " by number " + number);

        ArrayList<Statistical> group = new ArrayList<>();
        GroupResult groupResult;

        switch (type.toLowerCase()) {
            case "student":
                Teacher teacher = TeacherRepository.getTeacher(number);
                group.addAll(teacher.getUnits());
                groupResult = new GroupResult(Statistical.getGroupAverage(group), group.size(), teacher);
                break;
            case "unit":
                Unit unit = UnitRepository.getUnit(number);
                group.addAll(unit.getStudents());
                groupResult = new GroupResult(Statistical.getGroupAverage(group), group.size(), unit);
                break;
            default:
                throw new NotFoundException("" + number, type, "number");
        }

        return groupResult;
    }
}

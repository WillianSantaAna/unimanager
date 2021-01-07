package pt.iade.unimanager.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanager.models.Computer;
import pt.iade.unimanager.models.Laboratory;
import pt.iade.unimanager.models.Material;
import pt.iade.unimanager.models.Room;
import pt.iade.unimanager.models.exceptions.NotFoundException;
import pt.iade.unimanager.models.repositories.RoomRepository;

@RestController
@RequestMapping(path = "/api/rooms")
public class RoomController {
    private Logger logger = LoggerFactory.getLogger(RoomController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Room> getRooms() {
        logger.info("Sending all rooms");

        return RoomRepository.getRooms();
    }

    @GetMapping(path = "laboratory/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Laboratory getLaboratory(@PathVariable("designation") String designation) {
        logger.info("Sending all rooms");
        Laboratory laboratory = RoomRepository.getLaboratory(designation);

        if (laboratory == null)
            throw new NotFoundException(designation, "Laboratory", "designation");
        else
            return laboratory;
    }

    @GetMapping(path = "laboratory/{designation}/materials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Material> getMaterials(@PathVariable("designation") String designation) {
        logger.info("Sending all rooms");
        Laboratory laboratory = RoomRepository.getLaboratory(designation);

        if (laboratory == null)
            throw new NotFoundException(designation, "Laboratory", "designation");
        else
            return laboratory.getMaterials();
    }

    @PostMapping(path = "laboratory/{designation}/computers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Material addMaterial(@PathVariable("designation") String designation, @PathVariable("name") String name,
            @PathVariable("specifications") ArrayList<String> specifications) {
        logger.info("Sending all rooms");
        Laboratory laboratory = RoomRepository.getLaboratory(designation);
        
        if (laboratory != null) {
            Computer computer = new Computer(name, specifications);
            laboratory.addMaterial(computer);
        
            return computer;
        } else {
            throw new NotFoundException(designation, "Laboratory", "designation");
        }
    }
}

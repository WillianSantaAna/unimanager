package pt.iade.unimanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pt.iade.unimanager.models.repositories.RoomRepository;
import pt.iade.unimanager.models.repositories.StudentRepository;
import pt.iade.unimanager.models.repositories.TeacherRepository;
import pt.iade.unimanager.models.repositories.UnitRepository;

@SpringBootApplication
public class UnimanagerApplication {

	public static void main(String[] args) {
		StudentRepository.populate();
		UnitRepository.populate();
		TeacherRepository.populate();
		RoomRepository.populate();
		SpringApplication.run(UnimanagerApplication.class, args);
	}

}

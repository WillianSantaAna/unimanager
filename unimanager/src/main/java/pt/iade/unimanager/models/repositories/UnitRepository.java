package pt.iade.unimanager.models.repositories;

import java.util.ArrayList;

import pt.iade.unimanager.models.Unit;

public class UnitRepository {

    private static ArrayList<Unit> units = new ArrayList<Unit>();

    public static void populate() {
        units.add(new Unit(1, "Object-Oriented Programming", 6, TeacherRepository.getTeacher(1)));
        units.add(new Unit(2, "Database", 6, TeacherRepository.getTeacher(2)));
        units.add(new Unit(3, "Operating Systems", 6, TeacherRepository.getTeacher(3)));
        units.add(new Unit(4, "Discrete Mathematics", 6, TeacherRepository.getTeacher(4)));
        units.add(new Unit(5, "Communication Skills", 3, TeacherRepository.getTeacher(5)));
        units.add(new Unit(6, "Technical English I", 3, TeacherRepository.getTeacher(6)));
    }

    public static ArrayList<Unit> getUnits() {
        return units;
    }

	public static Unit getUnit(int unitId) {
        for (Unit unit : units)
            if (unit.getId() == unitId)
                return unit;
        
        return null;
    }

	public static void removeTeacher(int unitId) {
        Unit unit = getUnit(unitId);
        
        unit.setTeacher(null);
	}
    
}

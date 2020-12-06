package pt.iade.unimanager.models.repositories;

import java.util.ArrayList;

import pt.iade.unimanager.models.Unit;

public class UnitRepository {

    private static ArrayList<Unit> units = new ArrayList<Unit>();

    public static void populate() {
        
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
    
}

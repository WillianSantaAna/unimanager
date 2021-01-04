package pt.iade.unimanager.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Teacher extends Person {
    private static int nextNumber = 1;
    private int mecNumber;
    private ArrayList<Unit> units;

    public Teacher(String name, char gender, LocalDate birthDate) {
        super(name, gender, birthDate);
        this.mecNumber = nextNumber;
        nextNumber++;
        units = new ArrayList<>();
    }

    public int getMecNumber() {
        return this.mecNumber;
    }

    public ArrayList<Unit> getUnits() {
        return this.units;
    }

    public void addUnit(Unit unit) {
        unit.setTeacher(this);
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        unit.setTeacher(null);
        units.remove(unit);
    }

    @Override
    public String getName() {
        return "Professor " + this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "" + this.mecNumber + ": " + this.name;
    }

    @Override
    public String getReference() {
        return "T" + this.mecNumber;
    }
}

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
        return mecNumber;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    @Override
    public String getName() {
        return "Professor " + this.name;
    }

    @Override
    public String toString() {
        return "" + mecNumber + ": " + name;
    }
}

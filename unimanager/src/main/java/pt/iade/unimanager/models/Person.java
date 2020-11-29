package pt.iade.unimanager.models;

import java.time.LocalDate;

public abstract class Person {
    protected String name;
    protected String email;
    protected char gender;
    protected LocalDate birthDate;

    protected Person(String name, char gender, LocalDate birthDate) {
        this.name = name;
        this.email = "";
        this.gender = gender;
        this.birthDate = birthDate;
    }

    protected abstract String getName();

    public abstract String toString();
}

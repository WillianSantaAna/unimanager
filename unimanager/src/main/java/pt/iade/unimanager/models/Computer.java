package pt.iade.unimanager.models;

import java.util.ArrayList;

public class Computer extends Material {

    protected ArrayList<String> specifications;
    
    public Computer(String name, ArrayList<String> specifications) {
        super(name);
        this.specifications = specifications;
    }

    public ArrayList<String> getSpecifications() {
        return specifications;
    }
}

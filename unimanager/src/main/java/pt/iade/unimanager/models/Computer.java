package pt.iade.unimanager.models;

import java.util.ArrayList;

public class Computer extends Material {

    protected ArrayList<String> specifications;
    
    public Computer(String name, String state, ArrayList<String> specifications) {
        super(name, state);
        this.specifications = specifications;
    }

    public ArrayList<String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(ArrayList<String> specifications) {
        this.specifications = specifications;
    }
}

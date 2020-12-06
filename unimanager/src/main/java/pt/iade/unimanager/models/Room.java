package pt.iade.unimanager.models;

public class Room {
    protected String designation;
    protected int capacity;
    
    public Room(String designation, int capacity) {
        this.designation = designation;
        this.capacity = capacity;
    }

    public String getDesignation() {
        return designation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

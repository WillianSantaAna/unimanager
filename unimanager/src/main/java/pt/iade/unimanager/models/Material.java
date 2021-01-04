package pt.iade.unimanager.models;

public class Material {
    protected String name;
    protected MaterialState state;

    public Material(String name, MaterialState state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return this.name;
    }

    public MaterialState getState() {
        return this.state;
    }

    public void setState(MaterialState state) {
        this.state = state;
    }
}

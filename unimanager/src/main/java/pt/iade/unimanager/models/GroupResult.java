package pt.iade.unimanager.models;

public class GroupResult {
    private double average;
    private double listSize;
    private Object object;

    public GroupResult(double average, double listSize, Object object) {
        this.average = average;
        this.listSize = listSize;
        this.object = object;
    }

    public double getAverage() {
        return average;
    }

    public double getListSize() {
        return listSize;
    }

    public Object getObject() {
        return object;
    }
}

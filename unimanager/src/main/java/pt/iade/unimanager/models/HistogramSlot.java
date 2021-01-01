package pt.iade.unimanager.models;

public class HistogramSlot {
    private double start;
    private double end;
    private double value;

    public HistogramSlot(double start, double end, double value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public double getValue() {
        return value;
    }
}

package pt.iade.unimanager.models;

public class StatisticsResult {
    private double average;
    private double max;
    private double min;
    private double range;

    public StatisticsResult(double average, double max, double min, double range) {
        this.average = average;
        this.max = max;
        this.min = min;
        this.range = range;
    }

    public double getAverage() {
        return average;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getRange() {
        return range;
    }
}

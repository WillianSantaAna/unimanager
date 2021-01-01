package pt.iade.unimanager.models;

import java.util.ArrayList;

public interface Statistical {
    double getAverage();

    double getMax();
    
    double getMin();

    ArrayList<HistogramSlot> getHistogram(int nSlots);

    default double getRange() {
        return getMax() - getMin();
    }

    static double getGroupAverage(ArrayList<Statistical> group) {
        double sum = 0;

        for (Statistical s : group)
            sum += s.getAverage();

        return sum;
    }
}

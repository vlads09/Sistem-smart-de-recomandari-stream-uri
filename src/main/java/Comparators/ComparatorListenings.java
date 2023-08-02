package Comparators;

import Entities.Streams;

import java.util.Comparator;

public class ComparatorListenings implements Comparator<Streams> {

    @Override
    public int compare(Streams o1, Streams o2) {
        if (o1.getNoOfStreams() < o2.getNoOfStreams()) return -1;
        else if (o1.getNoOfStreams() > o2.getNoOfStreams()) return 1;
        return 0;
    }

}

package Comparators;

import Entities.Streams;

import java.util.Comparator;

public class ComparatorDate implements Comparator<Streams> {

    @Override
    public int compare(Streams o1, Streams o2) {
        if (o1.getDateAdded() < o2.getDateAdded()) return 1;
        else if (o1.getDateAdded() > o2.getDateAdded()) return -1;
        else {
            return -new ComparatorListenings().compare(o1, o2);
        }
    }

}

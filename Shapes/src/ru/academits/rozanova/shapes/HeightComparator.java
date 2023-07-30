package ru.academits.rozanova.shapes;

import java.util.Comparator;

public class HeightComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getHeight(), shape2.getHeight());
    }
}

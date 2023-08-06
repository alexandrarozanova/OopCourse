package ru.academits.rozanova.shapes_comparators;

import ru.academits.rozanova.shapes.Shape;

import java.util.Comparator;

public class ShapeWidthComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getWidth(), shape2.getWidth());
    }
}

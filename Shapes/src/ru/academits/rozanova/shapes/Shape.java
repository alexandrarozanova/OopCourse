package ru.academits.rozanova.shapes;

import java.util.Comparator;

public interface Shape extends Comparable<Shape> {
    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();

    boolean equals(Object o);

    class MaxWidthComparator implements Comparator<Shape> {
        public int compare(Shape o1, Shape o2) {
            return (int) (o1.getWidth() - o2.getWidth());
        }
    }

    class MaxHeightComparator implements Comparator<Shape> {
        public int compare(Shape o1, Shape o2) {
            return (int) (o1.getHeight() - o2.getHeight());
        }
    }

    class MaxAreaComparator implements Comparator<Shape> {
        public int compare(Shape o1, Shape o2) {
            return (int) (o1.getArea() - o2.getArea());
        }
    }

    class MaxPerimeterComparator implements Comparator<Shape> {
        public int compare(Shape o1, Shape o2) {
            return (int) (o1.getPerimeter() - o2.getPerimeter());
        }
    }
}
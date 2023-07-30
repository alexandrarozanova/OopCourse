package ru.academits.rozanova.shapes_main;

import ru.academits.rozanova.shapes.*;

import java.util.Arrays;

public class Main {
    public static boolean isEmpty(Shape[] shapes) {
        return shapes.length <= 1;
    }

    public static Shape getMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondMaxPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[shapes.length - 2];
    }

    public static Shape getMaxWidthShape(Shape[] shapes) {
        Arrays.sort(shapes, new WidthComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getMaxHeightShape(Shape[] shapes) {
        Arrays.sort(shapes, new HeightComparator());

        return shapes[shapes.length - 1];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(4),
                new Square(4),
                new Triangle(1, 1, 2, 2, 1, 3),
                new Circle(5),
                new Rectangle(3, 4),
                new Circle(10)
        };

        if (isEmpty(shapes)) {
            System.out.println("Недостаточно фигур для сравнения.");
        } else {
            if (shapes[0].equals(shapes[1])) {
                System.out.println("Первые две фигуры равны.");
            } else {
                System.out.println("Первые две фигуры не равны.");
            }

            Shape maxAreaShape = getMaxAreaShape(shapes);
            System.out.println("Максимальная площадь - " + maxAreaShape);

            Shape secondMaxPerimeterShape = getSecondMaxPerimeterShape(shapes);
            System.out.println("Второй по величине периметр - " + secondMaxPerimeterShape);

            Shape maxWidthShape = getMaxWidthShape(shapes);
            System.out.println("Максимальная ширина - " + maxWidthShape);

            Shape maxHeightShape = getMaxHeightShape(shapes);
            System.out.println("Максимальная высота - " + maxHeightShape);
        }
    }
}

package ru.academits.rozanova.shapes_main;

import ru.academits.rozanova.shapes.*;
import ru.academits.rozanova.shapes_comparators.ShapeAreaComparator;
import ru.academits.rozanova.shapes_comparators.ShapeHeightComparator;
import ru.academits.rozanova.shapes_comparators.ShapePerimeterComparator;
import ru.academits.rozanova.shapes_comparators.ShapeWidthComparator;

import java.util.Arrays;

public class Main {
    public static Shape getMaxAreaShape(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeAreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondMaxPerimeterShape(Shape[] shapes) {
        if (shapes.length <= 1) {
            return null;
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());

        return shapes[shapes.length - 2];
    }

    public static Shape getMaxWidthShape(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeWidthComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getMaxHeightShape(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeHeightComparator());

        return shapes[shapes.length - 1];
    }

    public static boolean checkShapesEquals(Shape[] shapes) {
        if (shapes.length < 2) {
            throw new IllegalArgumentException("Недостаточно фигур для сравнения.");
        }

        return shapes[0].equals(shapes[1]);
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

        if (checkShapesEquals(shapes)) {
            System.out.println("Первые две фигуры массива равны.");
        } else {
            System.out.println("Первые две фигуры массива не равны.");
        }

        Shape maxAreaShape = getMaxAreaShape(shapes);

        if (maxAreaShape == null) {
            System.out.println("Фигура с максимальной площадью не найдена, массив пуст.");
        } else {
            System.out.println("Максимальная площадь у фигуры - " + maxAreaShape);
        }

        Shape secondMaxPerimeterShape = getSecondMaxPerimeterShape(shapes);

        if (secondMaxPerimeterShape == null) {
            System.out.println("Фигура со вторым по величине периметром не найдена, недостаточно фигур для сравнения.");
        } else {
            System.out.println("Второй по величине периметр у фигуры - " + secondMaxPerimeterShape);
        }

        Shape maxWidthShape = getMaxWidthShape(shapes);

        if (maxWidthShape == null) {
            System.out.println("Фигура с максимальной шириной не найдена, массив пуст.");
        } else {
            System.out.println("Максимальная ширина у фигуры - " + maxWidthShape);
        }

        Shape maxHeightShape = getMaxHeightShape(shapes);

        if (maxHeightShape == null) {
            System.out.println("Фигура с максимальной высотой не найдена, массив пуст.");
        } else {
            System.out.println("Максимальная высота у фигуры - " + maxHeightShape);
        }
    }
}

package ru.academits.rozanova.range_main;

import ru.academits.rozanova.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начало первого диапазона:");
        double from1 = scanner.nextDouble();

        System.out.println("Введите конец первого диапазона:");
        double to1 = scanner.nextDouble();

        Range range1 = new Range(0, 0);
        range1.setFrom(from1);
        range1.setTo(to1);

        System.out.println("Начало первого диапазона - " + range1.getFrom() + ", конец - " + range1.getTo() + ", длина диапазона = " + range1.getLength() + ".");

        System.out.println("Введите начало второго диапазона:");
        double from2 = scanner.nextDouble();

        System.out.println("Введите конец второго диапазона:");
        double to2 = scanner.nextDouble();

        Range range2 = new Range(0, 0);
        range2.setFrom(from2);
        range2.setTo(to2);

        System.out.println("Начало второго диапазона - " + range2.getFrom() + ", конец - " + range2.getTo() + ", длина диапазона = " + range2.getLength() + ".");

        System.out.println("Введите число, для проверки его нахождения в первом диапазоне:");
        double numberInRange = scanner.nextDouble();

        if (range1.isInside(numberInRange)) {
            System.out.println("Число " + numberInRange + " находится в первом диапазоне заданных чисел.");
        } else {
            System.out.println("Число " + numberInRange + " не находится в первом диапазоне заданных чисел.");
        }

        Range intersection = range1.rangeIntersection(range2);

        if (intersection == null) {
            System.out.println("Пересечения нет.");
        } else {
            System.out.println("Диапазон пересечения двух интервалов = " + intersection);
        }

        Range[] union = range1.rangeUnion(range2);
        System.out.println("Объединение двух интервалов - " + Arrays.toString(union));

        Range[] difference = range1.rangeDifference(range2);
        System.out.println("Разность двух интервалов - " + Arrays.toString(difference));
    }
}

package ru.academits.rozanova.range_main;

import ru.academits.rozanova.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Range range = new Range(0, 0);
        System.out.println("Длина диапазона = " + range.getLength());

        range.setFrom(1);
        range.setTo(9);

        System.out.println("Новое начало диапазона = " + range.getFrom());
        System.out.println("Новый конец диапазона = " + range.getTo());

        System.out.println("Новая длина диапазона = " + range.getLength());

        System.out.println("Введите начало первого диапазона:");
        double from1 = scanner.nextDouble();

        System.out.println("Введите конец первого диапазона:");
        double to1 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);

        System.out.println("Первый диапазон = " + range1 + ", длина диапазона = " + range1.getLength() + ".");

        System.out.println("Введите начало второго диапазона:");
        double from2 = scanner.nextDouble();

        System.out.println("Введите конец второго диапазона:");
        double to2 = scanner.nextDouble();

        Range range2 = new Range(from2, to2);

        System.out.println("Второй диапазон - " + range2 + ", длина диапазона = " + range2.getLength() + ".");

        System.out.println("Введите число, для проверки его нахождения в первом диапазоне:");
        double number = scanner.nextDouble();

        if (range1.isInside(number)) {
            System.out.println("Число " + number + " находится в первом диапазоне заданных чисел.");
        } else {
            System.out.println("Число " + number + " не находится в первом диапазоне заданных чисел.");
        }

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Пересечения нет.");
        } else {
            System.out.println("Диапазон пересечения двух интервалов = " + intersection);
        }

        Range[] union = range1.getUnion(range2);
        System.out.println("Объединение двух интервалов - " + Arrays.toString(union));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Разность двух интервалов - " + Arrays.toString(difference));
    }
}

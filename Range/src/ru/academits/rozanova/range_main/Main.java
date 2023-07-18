package ru.academits.rozanova.range_main;

import ru.academits.rozanova.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Range range1 = new Range(0, 0);
        Range range2 = new Range(0, 0);

        System.out.println("Введите начало первого диапазона:");
        double from1 = scanner.nextDouble();
        range1.setFrom(from1);

        System.out.println("Введите конец первого диапазона:");
        double to1 = scanner.nextDouble();
        range1.setTo(to1);

        System.out.println("Диапазон введенных чисел = " + range1.getLength(from1, to1));

        System.out.println("Введите число, для проверки его нахождения в первом диапазоне:");
        double numberInRange = scanner.nextDouble();

        if (range1.isInside(numberInRange)) {
            System.out.println("Число " + numberInRange + " находится в первом диапазоне заданных чисел.");
        } else {
            System.out.println("Число " + numberInRange + " не находится в первом диапазоне заданных чисел.");
        }

        System.out.println("Введите начало второго диапазона:");
        double from2 = scanner.nextDouble();
        range2.setFrom(from2);

        System.out.println("Введите конец второго диапазона:");
        double to2 = scanner.nextDouble();
        range2.setTo(to2);

        Range[] rangeIntersection = range1.getTwoIntervalsIntersection(range2);

        if (rangeIntersection == null) {
            System.out.println("Пересечения нет.");
        } else {
            System.out.printf("Диапазон пересечения двух интервалов = [%.1f, %.1f]%n", rangeIntersection[0].getFrom(), rangeIntersection[0].getTo());
        }

        Range[] rangeUnion = range1.getTwoIntervalsUnion(range2);

        if (rangeUnion.length == 1) {
            System.out.printf("Объединение двух интервалов - [%.1f, %.1f]%n", rangeUnion[0].getFrom(), rangeUnion[0].getTo());
        } else {
            System.out.printf("Объединение двух интервалов - [%.1f, %.1f], [%.1f, %.1f]%n", rangeUnion[0].getFrom(), rangeUnion[0].getTo(), rangeUnion[1].getFrom(), rangeUnion[1].getTo());
        }

        Range[] rangeDifference = range1.getTwoIntervalsDifference(range2);

        if (rangeDifference == null) {
            System.out.println("Разности нет, один интервал входит в другой интервал.");
        } else if (rangeDifference.length == 1) {
            System.out.printf("Разность двух интервалов - [%.1f, %.1f]%n", rangeDifference[0].getFrom(), rangeDifference[0].getTo());
        } else {
            System.out.printf("Разность двух интервалов - [%.1f, %.1f], [%.1f, %.1f]%n", rangeDifference[0].getFrom(), rangeDifference[0].getTo(), rangeDifference[1].getFrom(), rangeDifference[1].getTo());
        }
    }
}

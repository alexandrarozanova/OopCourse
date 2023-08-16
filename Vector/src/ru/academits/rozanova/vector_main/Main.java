package ru.academits.rozanova.vector_main;

import ru.academits.rozanova.vector.Vector;

public class Main {
    public static void main(String[] args) {
        try {
            Vector vector = new Vector(0);
            System.out.println(vector.getSize());
        } catch (IllegalArgumentException e) {
            System.out.println("Найдена ошибка: " + e.getMessage());
        }

        Vector vector1 = new Vector(1);
        System.out.println("Первый вектор: " + vector1 + ", размер: " + vector1.getSize() + ", длина: " + vector1.getLength());

        double[] array1 = {1, 2, 3, 4, 5};

        vector1 = new Vector(array1);
        System.out.println("Новое значение первого вектора: " + vector1 + ", размер: " + vector1.getSize() + ", длина: " + vector1.getLength());

        vector1.setElement(6, 4);
        System.out.println("Изменен последний элемент вектора, новое значение: " + vector1 + ", размер: " + vector1.getSize() + ", длина: " + vector1.getLength());

        Vector vector2 = new Vector(vector1);
        System.out.println("Второй вектор: " + vector2 + ", размер: " + vector2.getSize() + ", длина: " + vector2.getLength());

        double[] array2 = {6, 7, 8, 9, 10};

        vector2 = new Vector(10, array2);
        System.out.println("Новое значение второго вектора: " + vector2 + ", размер: " + vector2.getSize() + ", длина: " + vector2.getLength());

        vector1.add(vector2);
        System.out.println("Новое значение первого вектора, после прибавления его ко второму вектору: " + vector1);

        vector2.subtract(vector1);
        System.out.println("Новое значение второго вектора, после вычитания из него первого вектора: " + vector2);

        vector1.multiply(3);
        System.out.println("Умножение первого вектора на скаляр 3: " + vector1);

        vector1.reverse();
        System.out.println("Разворот нового значения первого вектора: " + vector1);

        System.out.println("Сложение первого и второго вектора: " + Vector.getSum(vector1, vector2));

        System.out.println("Вычитание первого и второго вектора: " + Vector.getDifference(vector1, vector2));

        System.out.println("Скалярное произведение векторов: " + Vector.getScalarMultiplyProduct(vector1, vector2));
    }
}
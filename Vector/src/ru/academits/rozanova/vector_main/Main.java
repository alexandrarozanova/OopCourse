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

        Vector vector = new Vector(1);
        System.out.println("Первый вектор - " + vector + ", размер - " + vector.getSize() + ", длина - " + vector.getLength());

        double[] array = {1, 2, 3, 4, 5};

        vector = new Vector(array);
        System.out.println("Новое значение первого вектора - " + vector + ", размер - " + vector.getSize() + ", длина - " + vector.getLength());

        Vector vector2 = new Vector(vector);
        System.out.println("Второй вектор - " + vector2 + ", размер - " + vector2.getSize() + ", длина - " + vector2.getLength());

        double[] array2 = {6, 7, 8, 9, 10};

        vector2 = new Vector(10, array2);
        System.out.println("Новое значение второго вектора - " + vector2 + ", размер - " + vector2.getSize() + ", длина - " + vector2.getLength());

        vector.add(vector2);
        System.out.println("Новое значение первого вектора, после прибавления его ко второму вектору - " + vector);

        vector2.subtract(vector);
        System.out.println("Новое значение второго вектора, после вычитания из него первого вектора - " + vector2);

        vector.multiply(3);
        System.out.println("Умножение первого вектора на скаляр 3 - " + vector);

        vector.reverse();
        System.out.println("Разворот нового значения первого вектора - " + vector);

        System.out.println("Сложение первого и второго вектора - " + Vector.getAddition(vector, vector2));

        System.out.println("Вычитание первого и второго вектора - " + Vector.getSubtraction(vector, vector2));

        System.out.println("Скалярное произведение векторов - " + Vector.getScalarMultiply(vector, vector2));
    }
}
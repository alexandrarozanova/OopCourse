package ru.academits.rozanova.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, переданный размер вектора = " + size);
        }

        elements = new double[size];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не существует.");
        }

        elements = Arrays.copyOf(vector.elements, vector.elements.length);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new NullPointerException("Массив не существует.");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Длина массива для создания вектора должна быть больше 0.");
        }

        elements = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, переданный размер вектора = " + size);
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Длина массива для создания вектора должна быть больше 0.");
        }

        elements = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return elements.length;
    }

    public double getElement(int index) {
        return elements[index];
    }

    public void setElement(double element, int index) {
        elements[index] = element;
    }

    public static double[] increaseArray(double[] elements, int size) {
        if (elements.length < size) {
            return Arrays.copyOf(elements, size);
        }

        return elements;
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не существует");
        }

        elements = increaseArray(elements, vector.elements.length);

        for (int i = 0; i < elements.length && i < vector.elements.length; i++) {
            elements[i] += vector.elements[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не существует");
        }

        elements = increaseArray(elements, vector.elements.length);

        for (int i = 0; i < elements.length && i < vector.elements.length; i++) {
            elements[i] -= vector.elements[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= scalar;
        }
    }

    public void reverse() {
        multiply(-1);
    }

    public double getLength() {
        double length = 0;

        for (double element : elements) {
            length += element * element;
        }

        return Math.sqrt(length);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Невозможно вычислить сумму векторов, первого вектора не существует.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Невозможно вычислить сумму векторов, второго вектора не существует.");
        }

        Vector resultVector = new Vector(vector1);
        resultVector.add(vector2);

        return new Vector(resultVector);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Невозможно вычислить вычитание векторов, первого вектора не существует.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Невозможно вычислить вычитание векторов, второго вектора не существует.");
        }

        Vector resultVector = new Vector(vector1);
        resultVector.subtract(vector2);

        return new Vector(resultVector);
    }

    public static double getScalarMultiplyProduct(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Невозможно вычислить скалярное произведение векторов, первого вектора не существует.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Невозможно вычислить скалярное произведение векторов, второго вектора не существует.");
        }

        double scalarMultiplyProduct = 0;

        for (int i = 0; i < vector1.elements.length && i < vector2.elements.length; i++) {
            scalarMultiplyProduct += vector1.elements[i] * vector2.elements[i];
        }

        return scalarMultiplyProduct;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < elements.length - 1; i++) {
            stringBuilder.append(elements[i])
                    .append(", ");
        }

        stringBuilder.append(elements[elements.length - 1])
                .append("}");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(elements, vector.elements);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(elements);
        return hash;
    }
}

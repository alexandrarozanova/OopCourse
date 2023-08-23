package ru.academits.rozanova.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0, переданный размер вектора = " + size);
        }

        elements = new double[size];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        elements = Arrays.copyOf(vector.elements, vector.elements.length);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new NullPointerException("Массив не должен быть null.");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Длина массива для создания вектора должна быть больше 0.");
        }

        elements = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0, переданный размер вектора = " + size);
        }

        if (array == null) {
            throw new NullPointerException("Массив не должен быть null.");
        }

        elements = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return elements.length;
    }

    public double getElement(int index) {
        return elements[index];
    }

    public void setElement(int index, double element) {
        elements[index] = element;
    }

    private void increaseArray(int size) {
        if (elements.length < size) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        increaseArray(vector.elements.length);

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] += vector.elements[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не должен быть null.");
        }

        increaseArray(vector.elements.length);

        for (int i = 0; i < vector.elements.length; i++) {
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
        double sum = 0;

        for (double element : elements) {
            sum += element * element;
        }

        return Math.sqrt(sum);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Невозможно вычислить сумму векторов, первый вектор null.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Невозможно вычислить сумму векторов, второй вектор null.");
        }

        Vector resultVector = new Vector(vector1);
        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Невозможно вычислить вычитание векторов, первый вектор null.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Невозможно вычислить вычитание векторов, второй вектор null.");
        }

        Vector resultVector = new Vector(vector1);
        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Невозможно вычислить скалярное произведение векторов, первый вектор null.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Невозможно вычислить скалярное произведение векторов, второй вектор null.");
        }

        int minSize = Math.min(vector1.elements.length, vector2.elements.length);

        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.elements[i] * vector2.elements[i];
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (int i = 0; i < elements.length - 1; i++) {
            stringBuilder.append(elements[i])
                    .append(", ");
        }

        stringBuilder.append(elements[elements.length - 1])
                .append('}');

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

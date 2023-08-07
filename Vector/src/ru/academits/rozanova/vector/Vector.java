package ru.academits.rozanova.vector;

import java.util.Arrays;

public class Vector {
    private final double[] elements;

    public Vector(int vectorSize) {
        if (vectorSize <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0.");
        }

        elements = new double[vectorSize];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не существует.");
        }

        int vectorSize = vector.getSize();
        elements = new double[vectorSize];

        System.arraycopy(vector.elements, 0, elements, 0, vectorSize);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new NullPointerException("Массив не существует.");
        }

        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException("Массив не должен быть равен 0.");
        }

        int vectorSize = array.length;
        elements = new double[vectorSize];

        System.arraycopy(array, 0, elements, 0, vectorSize);
    }

    public Vector(int vectorSize, double[] array) {
        if (vectorSize <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0.");
        }

        if (array == null) {
            throw new NullPointerException("Массив не существует.");
        }

        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException("Массив не должен быть равен 0.");
        }

        int vectorNewSize = Math.min(vectorSize, array.length);
        elements = new double[vectorNewSize];

        System.arraycopy(array, 0, elements, 0, vectorNewSize);
    }

    public int getSize() {
        return elements.length;
    }

    public double getElement(int index) {
        return elements[index];
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не существует");
        }

        int vectorSize = getSize();

        for (int i = 0; i < vectorSize; i++) {
            if (i < vector.getSize()) {
                elements[i] += vector.elements[i];
            }
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не существует");
        }

        int vectorSize = getSize();

        for (int i = 0; i < vectorSize; i++) {
            if (i < vector.getSize()) {
                elements[i] -= vector.elements[i];
            } else {
                elements[i] = 0;
            }
        }
    }

    public void multiply(int scalar) {
        int vectorSize = getSize();

        for (int i = 0; i < vectorSize; i++) {
            elements[i] *= scalar;
        }
    }

    public void reverse() {
        int vectorSize = getSize();

        for (int i = 0; i < vectorSize; i++) {
            multiply(-1);
        }
    }

    public double getLength() {
        return Math.sqrt((getElement(0) * getElement(0)) + (getElement(getSize() - 1) * getElement(getSize() - 1)));
    }

    public static Vector getAddition(Vector vector, Vector vector2) {
        if (vector == null || vector2 == null) {
            throw new NullPointerException("Невозможно вычислить сумму векторов, вектора не существует.");
        }

        vector.add(vector2);

        return new Vector(vector);
    }

    public static double getScalarMultiply(Vector vector, Vector vector2) {
        if (vector == null || vector2 == null) {
            throw new NullPointerException("Невозможно вычислить скалярное произведение векторов, вектора не существует.");
        }

        int vectorSize = vector.getSize();

        double multiplyResult = 0;

        for (int i = 0; i < vectorSize; i++) {
            multiplyResult += vector.elements[i] * vector2.elements[i];
        }

        return multiplyResult;
    }

    public static Vector getSubtraction(Vector vector, Vector vector2) {
        if (vector == null || vector2 == null) {
            throw new NullPointerException("Невозможно вычислить вычитание векторов, вектора не существует.");
        }

        vector.subtract(vector2);

        return new Vector(vector);
    }

    @Override
    public String toString() {
        String result = Arrays.toString(elements);
        return "{" + result.substring(1, result.length() - 1) + "}";
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
        return elements == vector.elements;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(elements);
        return hash;
    }
}

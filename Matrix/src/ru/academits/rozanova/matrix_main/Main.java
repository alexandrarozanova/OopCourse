package ru.academits.rozanova.matrix_main;

import ru.academits.rozanova.matrix.Matrix;
import ru.academits.rozanova.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(-1, -1);
        System.out.println("Первая матрица:\n" + matrix1);

        Matrix matrix2 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println("Вторая матрица:\n" + matrix2);

        System.out.println("Первая строка во второй матрице: " + matrix2.getRow(0));

        double[] array1 = {1, 2, 3, 4, 5};
        Vector vector1 = new Vector(array1);

        double[] array2 = {6, 7, 8, 9, 10};
        Vector vector2 = new Vector(array2);

        double[] array3 = {11, 12, 13, 14, 15};
        Vector vector3 = new Vector(array3);

        Vector[] vectors = {vector1, vector2, vector3};

        Matrix matrix3 = new Matrix(vectors);
        System.out.println("Третья матрица:\n" + matrix3);

        Matrix matrix4 = new Matrix(matrix3);
        System.out.println("Четвертая матрица:\n" + matrix4);

        matrix4.transpose();
        System.out.println("Результат транспонирования четвертой матрицы:\n" + matrix4);

        matrix3.getScalarMultiply(4);
        System.out.println("Результат умножения третьей матрицы на скаляр 4:\n" + matrix3);

        System.out.println("Определитель второй матрицы: " + matrix2.getDeterminant());

        double[] array4 = {1, 3, 5};
        Vector vector4 = new Vector(array4);

        System.out.println("Умножение второй матрицы на вектор " + vector4 + ":\n" + matrix2.getMatrixOnVectorMultiply(vector4));

        Matrix matrix5 = new Matrix(matrix2);
        System.out.println("Пятая матрица:\n" + matrix5);

        System.out.println("Результат сложения второй и пятой матрицы:\n" + Matrix.getSum(matrix2, matrix5));

        System.out.println("Результат вычитания второй и пятой матрицы:\n" + Matrix.getDifference(matrix2, matrix5));

        System.out.println("Результат умножения второй и пятой матрицы:\n" + Matrix.getMultiply(matrix2, matrix5));
    }
}
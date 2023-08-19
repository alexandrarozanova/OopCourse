package ru.academits.rozanova.matrix;

import ru.academits.rozanova.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк не должно быть меньше, либо равно 0, переданный аргумент: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов не должно быть меньше, либо равно 0, переданный аргумент: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрицы не существует.");
        }

        rows = new Vector[matrix.rows.length];

        int maxRowLength = 0;

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);

            if (maxRowLength < matrix.rows[i].getSize()) {
                maxRowLength = matrix.rows[i].getSize();
            }
        }

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = increaseRowLength(rows[i], maxRowLength);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть больше 0.");
        }

        rows = new Vector[array.length];

        int maxRowLength = 0;

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(array[i]);

            if (maxRowLength < rows[i].getSize()) {
                maxRowLength = rows[i].getSize();
            }
        }

        for (int i = 0; i < array.length; i++) {
            rows[i] = increaseRowLength(rows[i], maxRowLength);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть больше 0.");
        }

        rows = new Vector[vectors.length];

        int maxRowLength = 0;

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(vectors[i]);

            if (maxRowLength < rows[i].getSize()) {
                maxRowLength = rows[i].getSize();
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = increaseRowLength(rows[i], maxRowLength);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnCount() {
        int columnsCount = 0;

        for (Vector row : rows) {
            if (columnsCount < row.getSize()) {
                columnsCount = row.getSize();
            }
        }

        return columnsCount;
    }

    public Vector getRow(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть меньше 0, переданный индекс: " + index);
        }

        if (index > rows.length) {
            throw new IllegalArgumentException("Индекс не может быть больше количества строк матрицы, переданный индекс: " + index + ", количество строк: " + rows.length);
        }

        return rows[index];
    }

    public void setRow(int index, Vector vector) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть меньше 0, переданный индекс: " + index);
        }

        if (index > rows.length) {
            throw new IllegalArgumentException("Индекс не может быть больше количества строк матрицы, переданный индекс: " + index + ", количество строк: " + rows.length);
        }

        if (vector == null) {
            throw new NullPointerException("Вектор не существует.");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть меньше 0, переданный индекс: " + index);
        }

        if (index > getColumnCount()) {
            throw new IllegalArgumentException("Индекс не может быть больше количества столбцов матрицы, переданный индекс: " + index + ", количество столбцов: " + getColumnCount());
        }

        double[] columns = new double[rows.length];

        for (int i = 0; i < columns.length; i++) {
            columns[i] = rows[i].getElement(index);
        }

        return new Vector(columns);
    }

    public static Vector increaseRowLength(Vector row, int size) {
        if (row.getSize() < size) {
            double[] rowElements = new double[row.getSize()];

            for (int i = 0; i < rowElements.length; i++) {
                rowElements[i] = row.getElement(i);
            }

            return new Vector(size, rowElements);
        }

        return row;
    }

    public void transpose() {
        Vector[] transposedMatrix = new Vector[getColumnCount()];

        for (int i = 0; i < transposedMatrix.length; i++) {
            transposedMatrix[i] = getColumn(i);
        }

        rows = transposedMatrix;
    }

    public void getScalarMultiply(int scalar) {
        for (Vector row : rows) {
            row.multiply(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Количество строк в массиве должно быть больше 0.");
        }

        if (getRowsCount() != getColumnCount()) {
            throw new IllegalArgumentException("Определитель можно вычислять только для квадратной матрицы.");
        }

        if (rows.length == 1) {
            return rows[0].getElement(0);
        }

        if (rows.length == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1) - rows[0].getElement(1) * rows[1].getElement(0);
        }

        int matrixLength = rows.length;

        rows = Arrays.copyOf(rows, rows.length + (rows.length - 1));

        for (int i = 0, j = 0; i < rows.length; i++) {
            if (i >= matrixLength) {
                rows[i] = rows[j];

                j++;
            }
        }

        double temp = 1;
        double tempDeterminant1 = 0;

        for (int i = 0; i < matrixLength; i++) {
            for (int j = i, k = 0; k < matrixLength; j++, k++) {
                temp *= rows[j].getElement(k);

                if (k == matrixLength - 1) {
                    tempDeterminant1 += temp;
                    temp = 1;
                }
            }
        }

        double tempDeterminant2 = 0;

        for (int i = 0; i < matrixLength; i++) {
            for (int j = i, k = matrixLength - 1; k >= 0; j++, k--) {
                temp *= rows[j].getElement(k);

                if (k == 0) {
                    tempDeterminant2 += temp;
                    temp = 1;
                }
            }
        }

        rows = Arrays.copyOf(rows, matrixLength);

        return tempDeterminant1 - tempDeterminant2;
    }

    public Vector getMatrixOnVectorMultiply(Vector vector) {
        if (vector.getSize() != getColumnCount()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна количеству столбцов в матрице, переданный аргумент: " + vector.getSize() + ", длина столбцов матрицы: " + rows.length);
        }

        Vector resultVector = new Vector(vector);

        for (int i = 0; i < vector.getSize(); i++) {
            double sum = 0;

            for (int j = 0; j < getColumnCount(); j++) {
                sum += rows[i].getElement(j) * vector.getElement(j);
            }

            resultVector.setElement(sum, i);
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрицы не существует.");
        }

        if (getColumnCount() != matrix.getColumnCount() || rows.length != matrix.rows.length) {
            throw new IllegalArgumentException("Получить сложение матриц можно только для матриц одной длины. Количество строк первой матрицы: " + rows.length + ", второй: " + matrix.rows.length +
                    ", количество столбцов первой матрицы: " + getColumnCount() + ", второй: " + matrix.getColumnCount());
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows.length; j++) {
                rows[i].setElement(rows[i].getElement(j) + matrix.rows[i].getElement(j), j);
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрицы не существует.");
        }

        if (getColumnCount() != matrix.getColumnCount() || rows.length != matrix.rows.length) {
            throw new IllegalArgumentException("Получить вычитание матриц можно только для матриц одной длины. Количество строк первой матрицы: " + rows.length + ", второй: " + matrix.rows.length +
                    ", количество столбцов первой матрицы: " + getColumnCount() + ", второй: " + matrix.getColumnCount());
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows.length; j++) {
                rows[i].setElement(rows[i].getElement(j) - matrix.rows[i].getElement(j), j);
            }
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первой матрицы не существует.");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй матрицы не существует.");
        }

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первой матрицы не существует.");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй матрицы не существует.");
        }

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getMultiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первой матрицы не существует.");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй матрицы не существует.");
        }

        if (matrix1.getColumnCount() != matrix2.getColumnCount() || matrix1.rows.length != matrix2.rows.length) {
            throw new IllegalArgumentException("Получить вычитание матриц можно только для матриц одной длины. Количество строк первой матрицы: " + matrix1.rows.length + ", второй: " + matrix2.rows.length +
                    ", количество столбцов первой матрицы: " + matrix1.getColumnCount() + ", второй: " + matrix2.getColumnCount());
        }

        Matrix resultMatrix = new Matrix(matrix1);

        for (int i = 0; i < resultMatrix.rows.length; i++) {
            Vector vector = new Vector(resultMatrix.rows.length);

            for (int j = 0; j < resultMatrix.rows.length; j++) {
                double sum = 0;

                for (int k = 0; k < resultMatrix.getColumnCount(); k++) {
                    sum += resultMatrix.rows[i].getElement(k) * matrix2.getColumn(j).getElement(k);
                }

                vector.setElement(sum, j);
            }

            resultMatrix.setRow(i, vector);
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Vector row : rows) {
            builder.append(row.toString()).append("\n");
        }

        return builder.toString();
    }
}

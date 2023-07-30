package ru.academits.rozanova.shapes;

public record Triangle(double x1, double y1, double x2, double y2, double x3, double y3) implements Shape {
    private static final double EPSILON = 1.0e-10;

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    public boolean isExist() {
        return !(Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) <= EPSILON);
    }

    public double getA() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double getB() {
        return Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
    }

    public double getC() {
        return Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
    }

    @Override
    public double getArea() {
        if (isExist()) {
            double a = getA();
            double b = getB();
            double c = getC();

            double triangleHalfPerimeter = (a + b + c) / 2;

            return Math.sqrt(triangleHalfPerimeter * (triangleHalfPerimeter - a) *
                    (triangleHalfPerimeter - b) * (triangleHalfPerimeter - c));
        }

        return 0;
    }

    @Override
    public double getPerimeter() {
        if (isExist()) {
            double a = getA();
            double b = getB();
            double c = getC();

            return a + b + c;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "\"Треугольник\", с координатами точек: (" + getX1() + "; " + getY1() + "), (" + getX2() + "; " + getY2() + "), (" + getX3() + "; " + getY3() + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;
        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2 && x3 == triangle.x3 && y3 == triangle.y3;
    }
}

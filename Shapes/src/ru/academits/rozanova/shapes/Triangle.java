package ru.academits.rozanova.shapes;

public record Triangle(double x1, double y1, double x2, double y2, double x3, double y3) implements Shape {
    private static final double EPSILON = 1.0e-10;

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    public boolean isDegenerateTriangle() {
        return Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) <= EPSILON;
    }

    public double getSideLength(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public double getArea() {
        if (isDegenerateTriangle()) {
            return 0;
        }

        double side1Length = getSideLength(x1, x2, y1, y2);
        double side2Length = getSideLength(x2, x3, y2, y3);
        double side3Length = getSideLength(x1, x3, y1, y3);

        double triangleHalfPerimeter = (side1Length + side2Length + side3Length) / 2;

        return Math.sqrt(triangleHalfPerimeter * (triangleHalfPerimeter - side1Length) *
                (triangleHalfPerimeter - side2Length) * (triangleHalfPerimeter - side3Length));
    }

    @Override
    public double getPerimeter() {
        if (isDegenerateTriangle()) {
            return 0;
        }

        double side1Length = getSideLength(x1, x2, y1, y2);
        double side2Length = getSideLength(x2, x3, y2, y3);
        double side3Length = getSideLength(x1, x3, y1, y3);

        return side1Length + side2Length + side3Length;
    }

    @Override
    public String toString() {
        return "\"Треугольник\", с координатами точек: (" + x1 + "; " + y1 + "), (" + x2 + "; " + y2 + "), (" + x3 + "; " + y3 + ")";
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

package ru.academits.rozanova.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return this.to - this.from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range rangeIntersection(Range range2) {
        if (to <= range2.from || from >= range2.to) {
            return null;
        }

        double newFrom = Math.max(from, range2.from);
        double newTo = Math.min(to, range2.to);

        return new Range(newFrom, newTo);
    }

    public Range[] rangeUnion(Range range2) {
        if (to < range2.from || range2.to < from) {
            return new Range[]{new Range(from, to), new Range(range2.from, range2.to)};
        }

        double newFrom = Math.min(from, range2.from);
        double newTo = Math.max(to, range2.to);

        return new Range[]{new Range(newFrom, newTo)};
    }

    public Range[] rangeDifference(Range range2) {
        if ((range2.from >= from && range2.to <= to) || (from >= range2.from && to <= range2.to)) {
            return new Range[]{new Range(0, 0)};
        }

        if ((to > range2.from && from < range2.from) || (range2.to > from && range2.from < from)) {
            double newFrom = Math.min(from, range2.from);
            double newTo = Math.max(from, range2.from);

            return new Range[]{new Range(newFrom, newTo)};
        }

        return new Range[]{new Range(from, to), new Range(range2.from, range2.to)};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}
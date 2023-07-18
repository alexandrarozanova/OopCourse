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

    public double getLength(double from, double to) {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range[] getTwoIntervalsIntersection(Range range2) {
        if (to <= range2.from || from >= range2.to) {
            return null;
        }

        double newFrom = Math.max(from, range2.from);
        double newTo = Math.min(to, range2.to);

        return new Range[]{new Range(newFrom, newTo)};
    }

    public Range[] getTwoIntervalsUnion(Range range2) {
        if (to < range2.from || range2.to < from) {
            return new Range[]{new Range(from, to), new Range(range2.from, range2.to)};
        }

        double newFrom = Math.min(from, range2.from);
        double newTo = Math.max(to, range2.to);

        return new Range[]{new Range(newFrom, newTo)};
    }

    public Range[] getTwoIntervalsDifference(Range range2) {
        if (range2.from >= from && range2.to <= to || from >= range2.from && to <= range2.to) {
            return null;
        }

        if (to > range2.from && from < range2.from || range2.to > from && range2.from < from) {
            double newFrom = Math.min(from, range2.to);
            double newTo = Math.min(to, range2.from);

            if (newFrom > newTo) {
                double temp = newFrom;
                newFrom = newTo;
                newTo = temp;
            }

            return new Range[]{new Range(newFrom, newTo)};
        }

        return new Range[]{new Range(from, to), new Range(range2.from, range2.to)};
    }
}

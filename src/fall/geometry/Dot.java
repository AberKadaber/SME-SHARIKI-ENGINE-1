package fall.geometry;

public class Dot {
    private double x;
    private double y;

    public Dot(double x, double y) {
        setXY(x, y);
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void addVector(Vector v) {
        setXY(x + v.getX(),
                y + v.getY());
    }

    public double squaredDistance(Dot other) {
        return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
    }

    public double distance(Dot other) {
        return Math.sqrt(squaredDistance(other));
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}

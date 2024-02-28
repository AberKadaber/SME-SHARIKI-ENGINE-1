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
        setXY(x + v.getDirection().getX(),
                y + v.getDirection().getY());
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}

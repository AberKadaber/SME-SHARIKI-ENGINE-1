package fall.geometry;

public class Circle {
    protected int radius;
    protected final Dot center;

    public Circle(int radius, int cx, int cy) {
        this.radius = radius;
        this.center = new Dot(cx, cy);
    }

    public Circle(int radius, Dot center) {
        this.radius = radius;
        this.center = center;
    }

    public void setR(int newR) {
        this.radius = newR;
    }

    public Dot getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

}

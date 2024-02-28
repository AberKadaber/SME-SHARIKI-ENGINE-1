import java.lang.Math;

public class CircleBorder implements Circle {
    private final int radius;
    private final Dot center;

    public CircleBorder(int r, int cx, int cy) {
        this.radius = r;
        this.center = new Dot(cx, cy);
    }

    public CircleBorder() {
        this(400, 0, 0);
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public Dot getCenter() {
        return center;
    }

    public boolean intersection(CircleElement c) {
        return Math.sqrt(Math.pow(c.getCenter().getX(), 2) +
                Math.pow(c.getCenter().getY(), 2)) > radius - c.getRadius();
    }

    public Vector tangent(CircleElement c) {
        Dot cent = c.getCenter();
        double x = (double) radius / Math.sqrt(1 + Math.pow(cent.getY() / cent.getX(), 2));
//        double y = (double) radius / Math.sqrt(1 + Math.pow((double) cent.getX() / cent.getY(), 2));
        double a = -x * Math.signum(cent.getX());
        double b = -Math.sqrt(radius * radius - x * x) * Math.signum(cent.getY());
        return new Vector((int) a, (int) b);
    }

}

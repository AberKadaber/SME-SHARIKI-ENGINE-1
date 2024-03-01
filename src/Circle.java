import javax.swing.*;
import java.awt.*;

public class Circle implements Movable {
    protected final int radius;
    private int colorInd = 0;
    protected final Dot center;
    private final double weight;
    protected final double g = 0.4;
    private final MovableType type;
    private final boolean collision;

    public Circle(int radius, int cx, int cy, MovableType type, boolean collision) {
        this.radius = radius;
        this.center = new Dot(cx, cy);
        this.weight = Math.PI * radius * radius;
        this.type = type;
        this.collision = collision;
    }

    public Dot getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public double getWeight() {
        return weight;
    }

    public void setColor(int colorId) {
        this.colorInd = colorId;
    }

    public int getColor() {
        return colorInd;
    }

    public MovableType getMovableType() {
        return type;
    }

    public boolean getCollision() {
        return collision;
    }

    public Vector tangent(Circle c) {
        System.out.println("another center: " + c.getCenter());
        System.out.println("My center: " + center);
        System.out.println("My radius: " + radius);
        Dot cent = c.getCenter();
        double x = (double) radius / Math.sqrt(1 + Math.pow((cent.getY() - center.getY()) /
                (cent.getX() - center.getX()), 2));
//        double y = (double) radius / Math.sqrt(1 + Math.pow((double) cent.getX() / cent.getY(), 2));
        double a = -x * Math.signum((cent.getX() - center.getX()));
        double b = -Math.sqrt(radius * radius - x * x) * Math.signum((cent.getY() - center.getY()));
        return new Vector(a, b).constMul(intersectionSide(c));
    }

    public int intersectionSide(Circle c) {
        // 1 - c внутри нашего, 0 не пересекается, -1 - c снаружи нашего
        double centDist = Math.sqrt((center.getX() - c.getCenter().getX()) * (center.getX() - c.getCenter().getX()) +
                (center.getY() - c.getCenter().getY()) * (center.getY() - c.getCenter().getY()));
        if (centDist < radius && centDist + c.getRadius() >= radius) {
            return 1;
        } else if (centDist > radius && centDist - c.radius <= radius) {
            return -1;
        }
        return 0;
    }

    public boolean intersection(Circle c) {
        return intersectionSide(c) != 0;
    }

    @Override
    public void simulate(double time) {
    }

    @Override
    public void move(Vector addend) {
    }

    @Override
    public void changeDirection(Vector line) {
    }

    public Vector getSpeed() {
        return null;
    }
}

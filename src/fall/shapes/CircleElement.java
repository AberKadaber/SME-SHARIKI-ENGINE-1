package fall.shapes;

import fall.geometry.Circle;
import fall.geometry.Dot;
import fall.geometry.Vector;

import java.awt.*;


public class CircleElement extends AbstractShape {
    private final Circle currentPosition;


    CircleElement(int x, int y, int radius) {
        Dot center = new Dot(x, y);
        Circle circ = new Circle(radius, center);
        double weight = Math.PI * radius * radius;
        double CentralMomentOfInertia = weight * radius * radius / 2;
        super(weight, CentralMomentOfInertia, center);
        this.currentPosition = circ;
    }

    @Override
    public void draw(Graphics g, Color color) {
        Dot center = currentPosition.getCenter();
        double x = center.getX();
        double y = center.getY();
        g.drawOval((int) x, (int) y, (int) (currentPosition.getRadius() + x), (int) (currentPosition.getRadius() + y));
    }

    @Override
    public Dot intersect(double a, double b, double c) {
        return null;
    }
}


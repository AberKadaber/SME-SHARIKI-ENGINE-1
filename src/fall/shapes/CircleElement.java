package fall.shapes;

import fall.geometry.Circle;
import fall.geometry.Dot;

import java.util.List;
import java.awt.*;


public class CircleElement extends AbstractShape {
    private final Circle currentPosition;

    CircleElement(int x, int y, int radius) {
        Dot center = new Dot(x, y);
        Circle circ = new Circle(radius, center);
        double weight = Math.PI * radius * radius;
        super(weight, center, true);
        currentPosition = circ;
    }

    @Override
    public void draw(Graphics g, Color color) {
        Dot center = currentPosition.getCenter();
        double x = center.getX();
        double y = center.getY();
        g.drawOval((int) x, (int) y, (int) (currentPosition.getRadius() + x), (int) (currentPosition.getRadius() + y));
    }

    @Override
    public List<Dot> intersect(double k, double b) {
        return null;
    }

}


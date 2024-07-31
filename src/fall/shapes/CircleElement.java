package fall.shapes;

import fall.geometry.Circle;
import fall.geometry.Dot;

import java.util.List;
import java.awt.*;


public class CircleElement extends AbstractShape {
    private final Circle currentPosition;

    public CircleElement(int cx, int cy, int radius) {
        Dot center = new Dot(cx, cy);
        Circle circ = new Circle(radius, center);
        double weight = Math.PI * radius * radius;
        super(weight, center, true);
        currentPosition = circ;
    }

    @Override
    public void draw(Graphics g, Color color, int width, int height) {
        g.setColor(color);

        int r = currentPosition.getRadius();
        int x = (int) (width / 2 + center.getX() - r);
        int y = (int) (height / 2 - center.getY() - r);

        g.drawOval(x, y, 2 * r, 2 * r);
        g.fillOval(x, y, 2 * r, 2 * r);
    }

    @Override
    public List<Dot> intersect(double k, double b) {
        double r = currentPosition.getRadius();
        double dx = Math.sqrt(r * r / (1 + k * k));
        double dy = Math.sqrt(r * r * k * k / (1 + k * k));

        return List.of(
                new Dot(center.getX() + dx, center.getY() + Math.signum(k) * dy),
                new Dot(center.getX() - dx, center.getY() - Math.signum(k) * dy));
    }
}


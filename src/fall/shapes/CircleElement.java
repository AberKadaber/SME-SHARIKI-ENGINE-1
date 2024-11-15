package fall.shapes;

import fall.geometry.Dot;

import java.util.List;
import java.awt.*;

/**
 * Class for describing movable circles that will bounce back from borders and other movable circles
 */
public class CircleElement extends AbstractShape {
    private final int radius;

    /**
     * Create new <code>CircleElement</code> with the specified radius and coordinates of center
     *
     * @param radius radius of the element
     * @param cx     x coordinate of element
     * @param cy     y coordinate of element
     */
    public CircleElement(double cx, double cy, int radius) {
        Dot center = new Dot(cx, cy);
        double weight = Math.PI * radius * radius;
        super(weight, center);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        g.setColor(color);

        int x = (int) (width / 2 + center.getX() - radius);
        int y = (int) (height / 2 - center.getY() - radius);

        g.drawOval(x, y, 2 * radius, 2 * radius);
        g.fillOval(x, y, 2 * radius, 2 * radius);
    }

    @Override
    public List<Dot> intersect(double k) {
        double dx = Math.sqrt(radius * radius / (1 + k * k));
        double dy = Math.sqrt(radius * radius * k * k / (1 + k * k));

        return List.of(
                new Dot(center.getX() + dx, center.getY() + Math.signum(k) * dy),
                new Dot(center.getX() - dx, center.getY() - Math.signum(k) * dy));
    }

    @Override
    public boolean isBorder() {
        return false;
    }

    @Override
    public String toString() {
        return """
                circle element:
                center: x: %f, y: %f
                radius: %d
                """.formatted(center.getX(), center.getY(), radius);
    }
}

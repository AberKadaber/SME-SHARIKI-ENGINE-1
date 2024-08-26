package fall.shapes;

import fall.geometry.Dot;

import java.util.List;
import java.awt.*;

/**
 * Class for describing borders that will be unmovable on screen
 */
public class CircleBorder extends AbstractShape {
    private final int radius;

    /**
     * Create new <code>CircleBorder</code> with the specified radius and coordinates of center
     *
     * @param radius radius of the border
     * @param cx     x coordinate of border
     * @param cy     y coordinate of border
     */
    public CircleBorder(double cx, double cy, int radius) {
        Dot center = new Dot(cx, cy);
        double weight = Math.PI * radius * radius * 1e5; // very big weight for ordinary Shape it looks like infinity
        super(weight, center);
        this.radius = radius;
    }

    @Override
    public void simulate(double time) {
        // we don't need to change our center coordinates or velocity because we are unmovable
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        g.setColor(color);

        int x = (int) (width / 2 + center.getX() - radius);
        int y = (int) (height / 2 - center.getY() - radius);

        g.drawOval(x, y, 2 * radius, 2 * radius);
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
        return true;
    }

    @Override
    public void updateColor() {
    }

    @Override
    public String toString() {
        return """
                circle border:
                center: x: %f, y: %f
                radius: %d
                """.formatted(center.getX(), center.getY(), radius);
    }
}

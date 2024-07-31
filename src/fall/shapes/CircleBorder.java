package fall.shapes;

import fall.geometry.Dot;

import java.util.List;
import java.awt.*;


public class CircleBorder extends AbstractShape {
    private final int radius;
    private final double cx, cy;

    CircleBorder(int cx, int cy, int radius) {
        Dot center = new Dot(cx, cy);
        double weight = Math.PI * radius * radius * 1e5;
        super(weight, center, false);
        this.radius = radius;
        this.cx = cx;
        this.cy = cy;
    }

    @Override
    public void draw(Graphics g, Color color, int width, int height) {
        g.setColor(Color.WHITE);

        int x = (int) (width / 2 + cx - radius);
        int y = (int) (height / 2 - cy - radius);

        g.drawOval(x, y, 2 * radius, 2 * radius);
    }

    @Override
    public List<Dot> intersect(double k, double b) {
        double dx = radius * radius / (1 + k * k);
        double dy = radius * radius * k * k / (1 + k * k);

        return List.of(
                new Dot(cx + dx, cy + dy),
                new Dot(cx - dx, cy - dy));
    }
}


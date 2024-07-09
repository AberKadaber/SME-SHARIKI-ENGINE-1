package fall.shapes;

import fall.geometry.*;
import java.awt.*;

public interface Shape {
    void simulate(double time);
    void update(Vector force, Dot applicationPoint, double collisionTime);
    void draw(Graphics g, Color color);

    /**
     *
     * @return a dot - intersection our shape with line <code>ax + by + c = 0</code>
     */

    Dot intersect(double a, double b, double c);
}

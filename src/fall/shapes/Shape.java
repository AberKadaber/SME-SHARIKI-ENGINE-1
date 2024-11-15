package fall.shapes;

import fall.geometry.*;

import java.util.List;
import java.awt.*;

public interface Shape {
    /**
     * Move shape, apply acceleration effect to shape
     *
     * @param time time of simulation
     */
    void simulate(double time);

    /**
     * Calculate new speed after collision with another shape
     *
     * @param other other shape that has a collision with our shape
     */
    void update(AbstractShape other);

    /**
     * Method to draw shape on the screen
     *
     * @param g      Graphics to draw
     * @param width  width of screen
     * @param height height of screen
     */
    void draw(Graphics g, int width, int height);

    /**
     * @return intersection our shape with line <code>y = k * x</code> that passes through the center of the shape
     *
     * @param k tilt angle coefficient
     */
    List<Dot> intersect(double k);

    /**
     * calculate is there an intersection with other shape
     *
     * @param other other shape to calculation intersection with our shape
     * @return is there an intersection
     */
    boolean intersect(AbstractShape other);

    /**
     * This method return whether our shape is a border.
     * <br> It uses for calculating intersections between all shapes. For example, we need to use 2 borders that
     * intersect together, but we don't need to move them. This method helps us to separate border-border collision from
     * border-movable element collision.
     *
     * @return is our shape a border
     */
    boolean isBorder();

    /**
     * This method change color of shape after collision
     */
    void updateColor();
}

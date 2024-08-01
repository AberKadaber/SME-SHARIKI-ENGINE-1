package fall.geometry;

/**
 * The class <code>Circle</code> contains some parameters and method to describe geometric circle
 */
public class Circle {
    protected int radius;
    protected final Dot center;

    /**
     * Create new <code>Circle</code> with the specified radius and coordinates of center
     *
     * @param radius radius of the circle
     * @param cx     x coordinate of center
     * @param cy     y coordinate of center
     */
    public Circle(int radius, int cx, int cy) {
        this.radius = radius;
        this.center = new Dot(cx, cy);
    }

    /**
     * Create new <code>Circle</code> with the specified radius and center
     *
     * @param radius radius of the circle
     * @param center center of the circle
     */
    public Circle(int radius, Dot center) {
        this.radius = radius;
        this.center = center;
    }

    /**
     * Set the new radius to our circle
     *
     * @param newR new radius
     */
    public void setRadius(int newR) {
        this.radius = newR;
    }

    /**
     * Get center of our circle
     *
     * @return center of our circle
     */
    public Dot getCenter() {
        return center;
    }

    /**
     * Get radius of our circle
     *
     * @return radius of our circle
     */
    public int getRadius() {
        return radius;
    }

}

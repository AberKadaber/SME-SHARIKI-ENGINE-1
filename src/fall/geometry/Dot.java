package fall.geometry;

/**
 * The class <code>Dot</code> contains parameters and methods to describe point on the plane
 */
public class Dot {
    private double x;
    private double y;

    /**
     * Create new <code>Dot</code> with specified x and y coordinate
     *
     * @param x x coordinate of dot
     * @param y y coordinate of dot
     */
    public Dot(double x, double y) {
        setXY(x, y);
    }

    /**
     * Set new coordinates to our dot
     *
     * @param x new x coordinate of dot
     * @param y new y coordinate of dot
     */
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x coordinate of dot
     */
    public double getX() {
        return x;
    }

    /**
     * @return y coordinate of dot
     */
    public double getY() {
        return y;
    }

    /**
     * move our dot by moving in on vector
     *
     * @param v vector to add
     */
    public void addVector(Vector v) {
        setXY(x + v.getX(),
                y + v.getY());
    }

    /**
     * Return distance to other dot, but squared
     *
     * @param other other dot to calculate squared distance
     * @return squared distance to <code>other</code> dot
     */
    public double squaredDistance(Dot other) {
        return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
    }

    /**
     * Return distance to other dot
     *
     * @param other other dot to calculate distance
     * @return distance to <code>other</code> dot
     */
    public double distance(Dot other) {
        return Math.sqrt(squaredDistance(other));
    }

    @Override
    public String toString() {
        return "x: %f y: %f".formatted(x, y);
    }
}

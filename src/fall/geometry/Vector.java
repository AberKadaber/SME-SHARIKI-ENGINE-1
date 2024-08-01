package fall.geometry;

/**
 * class to describe geometrical vector
 */
public class Vector {
    private final Dot direction;

    /**
     * Create new <code>Vector</code> with specified x and y parameters
     *
     * @param x x coordinate of vector
     * @param y y coordinate of vector
     */
    public Vector(double x, double y) {
        this.direction = new Dot(x, y);
    }
    /**
     * @return x coordinate of vector
     */
    public double getX() {
        return direction.getX();
    }
    /**
     * @return y coordinate of vector
     */
    public double getY() {
        return direction.getY();
    }

    /**
     * Add other vector to our vector
     *
     * @param add vector to add
     */
    public void addVector(Vector add) {
        direction.setXY(getX() + add.getX(),
                getY() + add.getY());
    }

    /**
     * Get size of our vector
     *
     * @return size of vector
     */
    public double getSize() {
        return Math.sqrt(getX() * getX() + getY() * getY());
    }

    /**
     * Stretch or compress a vector depending on the <code>scale</code>
     *
     * @param scale scale of multiplying
     * @return new Vector with size equal to old size * scale
     */
    public Vector constMul(double scale) {
        return new Vector((getX() * scale), (getY() * scale));
    }

    /**
     * Do vector product with other vector
     *
     * @param other other vector to do vector product
     * @return size of result (direction is perpendicular to our plane)
     */
    public double vectorMul(Vector other) {
        return getX() * other.getY() -
                getY() * other.getX();
    }

    /**
     * Do scalar product with other vector
     *
     * @param other other vector to do scalar product
     * @return result of scalar product
     */
    public double scalarMul(Vector other) {
        return getX() * other.getX() +
                getY() * other.getY();
    }

    /**
     * Rotates our vector by the angle <code>phi</code>
     * <list>
     *     <li>if <code>phi</code> is positive - counterclockwise </li>
     *     <li>if <code>phi</code> is negative - clockwise </li>
     * </list>
     *
     * @param phi angle to rotate
     * @return new vector - result of rotate
     */
    public Vector rotateByAngle(double phi) {
        return new Vector(
                getX() * Math.cos(phi) - getY() * Math.sin(phi),
                getX() * Math.sin(phi) + getY() * Math.cos(phi)
        );
    }

    /**
     * Rotates our vector by the angle with a given sin and cos
     *
     * @param cosPhi cos of angle to rotate
     * @param sinPhi sin of angle to rotate
     * @return new vector - result of rotate
     */
    public Vector rotateByTrig(double cosPhi, double sinPhi) {
        return new Vector(getX() * cosPhi - getY() * sinPhi,
                getX() * sinPhi + getY() * cosPhi);
    }

    @Override
    public String toString() {
        return direction.toString();
    }
}

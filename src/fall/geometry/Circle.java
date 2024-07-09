package fall.geometry;

public class Circle {
    protected int radius;
    protected final Dot center;

    public Circle(int radius, int cx, int cy) {
        this.radius = radius;
        this.center = new Dot(cx, cy);
    }

    public Circle(int radius, Dot center) {
        this.radius = radius;
        this.center = center;
    }

    public void setR(int newR) {
        this.radius = newR;
    }

    public Dot getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public Vector tangent(Circle c) {
        Dot cent = c.getCenter();
        double x = (double) radius / Math.sqrt(1 + Math.pow((cent.getY() - center.getY()) /
                (cent.getX() - center.getX()), 2));
        double a = -x * Math.signum((cent.getX() - center.getX()));
        double b = -Math.sqrt(radius * radius - x * x) * Math.signum((cent.getY() - center.getY()));
        return new Vector(a, b).constMul(intersectionSide(c));
    }

    /**
     * method to evaluate collision with other circle c
     *
     * @return <ul>
     * <li>result is 0 if there is no collision</li>
     * <li>result is 1 if there is the collision and circle c inside our circle</li>
     * <li>result is -1 if there is the collision and circle c outside our circle</li>
     * </ul>
     */
    public int intersectionSide(Circle c) {
        double centDist = center.distance(c.getCenter());
        if (centDist < radius && centDist + c.getRadius() >= radius) {
            return 1;
        } else if (centDist > radius && centDist - c.radius <= radius) {
            return -1;
        }
        return 0;
    }

    public boolean intersection(Circle c) {
        return intersectionSide(c) != 0;
    }
}

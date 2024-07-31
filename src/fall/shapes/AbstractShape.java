package fall.shapes;

import fall.geometry.*;

import java.util.List;

public abstract class AbstractShape implements Shape {
    private Vector velocity = new Vector(0, 0);
    private final Vector acceleration = new Vector(0, -10);

    private final double weight;
    public Dot center; // TODO protected

    boolean isMovable;
    /*
    I need to solve some physic problem to add shapes with rotation
    protected double angle = 0;
    private double angularVelocity = 0;
    private final double CentralMomentOfInertia;
    */

    AbstractShape(double weight, Dot center, boolean isMovable) {
        this.weight = weight;
        this.center = center;
        this.isMovable = isMovable;
    }

    @Override
    public void simulate(double time) {
        center.addVector(velocity.constMul(time));
        velocity.addVector(acceleration.constMul(time));
    }

    @Override
    public void update(AbstractShape other) {
        double deltaX = center.getX() - other.center.getX();
        double deltaY = center.getY() - other.center.getY();
        double distanceBetweenCenters = center.distance(other.center);

        double sinPhi = -Math.signum(deltaX == 0d ? 1 : deltaX) *
                Math.signum(deltaY == 0d ? 1 : deltaY) *
                Math.abs(deltaY) /
                distanceBetweenCenters;

        double cosPhi = Math.abs(deltaX) / distanceBetweenCenters;

        Vector v2 = other.velocity.rotateByTrig(cosPhi, sinPhi);

        double newV2_y = v2.getY();
        double vx2 = v2.getX();
        double w1 = weight;
        double w2 = other.weight;

        double newV2_x = ((w2 - w1) * vx2) / (w1 + w2);

        Vector newV2 = new Vector(newV2_x, newV2_y).rotateByTrig(cosPhi, -sinPhi);

        if (other.isMovable) {
            other.velocity = newV2;
        }
    }

    /**
     * check if c лежит на отрезке [a, b]
     *
     * @param a first end of отрезок
     * @param b second end of отрезок
     * @param c dot to check
     * @return <code>c</code> лежит на отрезке [a, b]
     */
    private boolean between(Dot a, Dot b, Dot c) {
        return a.distance(c) <= a.distance(b) && b.distance(c) <= b.distance(a);
    }

    @Override
    public boolean intersect(AbstractShape other) {
        double deltaX = center.getX() - other.center.getX();
        double deltaY = center.getY() - other.center.getY();
        double k = Math.atan(deltaY / deltaX);
        // y = kx + b => b = y - kx
        double b = center.getY() - k * center.getX();

        List<Dot> myIntersection = intersect(k, b);
        List<Dot> otherIntersection = other.intersect(k, b);

        int cnt = 0;
        for (Dot d : otherIntersection) {
            if (between(myIntersection.getFirst(), myIntersection.get(1), d)) {
                cnt += 1;
            }
        }
        return cnt == 1;
    }
}

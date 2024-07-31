package fall.shapes;

import fall.geometry.*;

public abstract class AbstractShape implements Shape {
    private Vector velocity = new Vector(0, 0);
    private final Vector acceleration = new Vector(0, -10);

    private final double weight;
    protected Dot center;

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

        Vector v1 = velocity.rotateByTrig(cosPhi, sinPhi);
        Vector v2 = other.velocity.rotateByTrig(cosPhi, sinPhi);

        double newV1_y = v1.getY();
        double newV2_y = v2.getY();
        double vx1 = v1.getX();
        double vx2 = v2.getX();
        double w1 = weight;
        double w2 = other.weight;

        double newV1_x = ((w1 - w2) * vx1 + 2 * w2 * vx2) / (w1 + w2);
        double newV2_x = ((w2 - w1) * vx2 + 2 * w1 * vx1) / (w1 + w2);

        Vector newV1 = new Vector(newV1_x, newV1_y).rotateByTrig(cosPhi, -sinPhi);
        Vector newV2 = new Vector(newV2_x, newV2_y).rotateByTrig(cosPhi, -sinPhi);

        if (isMovable) {
            velocity = newV1;
        }
        if (other.isMovable) {
            other.velocity = newV2;
        }
    }

    @Override
    public boolean intersect(AbstractShape other) {
        double deltaX = center.getX() - other.center.getX();
        double deltaY = center.getY() - other.center.getY();
        double k = Math.atan(deltaY / deltaX);
        // y = kx + b => b = y - kx
        double b = center.getY() - k * center.getX();

        // TODO: our shape always have 2 or more intersections
//        Dot myIntersection = intersect(k, b);
//        Dot otherIntersection = other.intersect(k, b);

//        return myIntersection.distance(center) +
//                otherIntersection.distance(other.center) >
//                center.distance(other.center);
        return false;
    }
}

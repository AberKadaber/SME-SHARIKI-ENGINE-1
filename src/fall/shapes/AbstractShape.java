package fall.shapes;

import fall.geometry.*;

public abstract class AbstractShape implements Shape {
    private final Vector velocity = new Vector(0, 0);
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

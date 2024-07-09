package fall.shapes;

import fall.geometry.*;

public abstract class AbstractShape implements Shape {
    private final Vector velocity = new Vector(0, 0);
    private final Vector acceleration = new Vector(0, -10);
    private double angularVelocity = 0;
    private final double weight;
    protected double angle = 0;
    protected Dot center;
    private final double CentralMomentOfInertia;

    AbstractShape(double weight, double CentralMomentOfInertia, Dot center) {
        this.weight = weight;
        this.CentralMomentOfInertia = CentralMomentOfInertia;
        this.center = center;
    }

    private void linearSimulate(double time) {
        center.addVector(velocity.constMul(time));
        angle += angularVelocity * time;
    }

    @Override
    public void simulate(double time) {
        linearSimulate(time);
        velocity.addVector(acceleration.constMul(time));
    }

    @Override
    public void update(Vector force, Dot applicationPoint, double collisionTime) {
        // velocity
        Vector velocityChange = force.constMul(collisionTime / weight);
        velocity.addVector(velocityChange);
        // acceleration
        double changedMomentOfInertia = CentralMomentOfInertia + center.squaredDistance(applicationPoint);
        angularVelocity += new Vector(applicationPoint.getX() - center.getX(), applicationPoint.getY() - center.getY()).vectorMul(force) * collisionTime / changedMomentOfInertia;
    }

}

package fall.shapes;

import fall.geometry.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

public abstract class AbstractShape implements Shape {
    private Vector velocity = new Vector(0, 0);
    private final Vector acceleration = new Vector(0, -10);

    private final double weight;
    protected Dot center;

    protected Color color = Color.RED;

    /*
    I need to solve some physic problem to add shapes with rotation

    protected double angle = 0;
    private double angularVelocity = 0;
    private final double CentralMomentOfInertia;
    */

    AbstractShape(double weight, Dot center) {
        this.weight = weight;
        this.center = center;
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

        // 0.995 is no energy loss
        Vector newV1 = new Vector(newV1_x, newV1_y).rotateByTrig(cosPhi, -sinPhi).constMul(0.996);
        Vector newV2 = new Vector(newV2_x, newV2_y).rotateByTrig(cosPhi, -sinPhi).constMul(0.996);
        velocity = newV1;
        other.velocity = newV2;

        updateColor();
        other.updateColor();
    }

    // TODO: change collision logic: from drawing line from center to center and intersect with shapes, we can split
    //  shape for some segments and intersect this segments
    //  for this we need new method getSegments which returns List<Segment>
    //  also we need Class Segment with function bool intersect(Segment other)

    /**
     * check if <code>c</code> lies on the segment <code>[a, b]</code>
     * <br>
     * <b>Important:</b> <code>c</code> must lies on the line <code>(a, b)</code> or really neat it
     *
     * @param a first end of segment
     * @param b second end of segment
     * @param c dot to check
     * @return <code>c</code> lies on the segment <code>[a, b]</code>
     */
    private boolean between(Dot a, Dot b, Dot c) {
        return a.distance(c) <= a.distance(b) && b.distance(c) <= b.distance(a);
    }

    @Override
    public boolean intersect(AbstractShape other) {
        double deltaX = center.getX() - other.center.getX();
        double deltaY = center.getY() - other.center.getY();
        double k = deltaY / deltaX;
        List<Dot> myIntersection = intersect(k);
        List<Dot> otherIntersection = other.intersect(k);

        int cnt = 0;
        for (Dot d : otherIntersection) {
            if (between(myIntersection.get(0), myIntersection.get(1), d)) {
                cnt += 1;
            }
        }

        return cnt == 1;
    }

    private final Map<Color, Color> nextColor = Map.of(
            Color.RED, Color.ORANGE,
            Color.ORANGE, Color.YELLOW,
            Color.YELLOW, Color.GREEN,
            Color.GREEN, Color.CYAN,
            Color.CYAN, Color.BLUE,
            Color.BLUE, Color.MAGENTA,
            Color.MAGENTA, Color.RED
    );

    @Override
    public void updateColor() {
        this.color = nextColor.get(color);
    }
}

public class CircleElement extends Circle {
    private Vector v = new Vector(0, 0);
    private final Vector a = new Vector(0, -1);
    private double energy;
    private final double weight;
    private int cnt = 0;

    public CircleElement(int radius, int cx, int cy, boolean collision) {
        super(radius, cx, cy, MovableType.MOVABLE, collision);
        this.weight = Math.PI * radius * radius;
        this.energy = weight * g * cy;
    }

    @Override
    public void simulate(double time) {
        cnt += 1;
        center.addVector(v.constMul(time));
        if (cnt == 8) {
            v.addVector(a.constMul(time));
            cnt = 0;
        }
    }

    @Override
    public void changeDirection(Vector line) {
        System.out.println("v before: " + v);
        double cosFi = -v.scalarMul(line) / (v.getSize() * line.getSize());
        double sinFi = Math.sqrt(1 - cosFi * cosFi);
        double cos2Fi = 2 * cosFi * cosFi - 1;
        double sin2Fi = 2 * sinFi * cosFi;
        boolean clock = v.vectorMul(line) < 0;
        if (clock) {
            System.out.println("clock");
            v = v.rotateByTrig(-cos2Fi, -sin2Fi);
        } else {
            v = v.rotateByTrig(-cos2Fi, sin2Fi);
        }
        System.out.println("energy:" + energy);
        System.out.println("weight: " + weight);
        System.out.println("v^2: " + 2 * Math.abs((energy / weight - center.getY() * g)));
        v = v.constMul(Math.sqrt(2 * Math.abs((energy / weight - center.getY() * g))) / v.getSize());
        System.out.println("v after: " + v);

    }


    @Override
    public void move(Vector addend) {
        center.addVector(addend);
    }

    public Vector getSpeed() {
        return v;
    }
}

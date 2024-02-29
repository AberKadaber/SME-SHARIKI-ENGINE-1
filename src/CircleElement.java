

public class CircleElement implements Movable, Circle {
    private final int radius;
    private final Dot center;
    private Vector v = new Vector(0, 0);
    private final Vector a = new Vector(0, -1);
    private double energy;
    private int cnt = 0;
    private double weight;
    private final double g = 0.4;


    public CircleElement(int radius, int cx, int cy) {
        this.radius = radius;
        this.center = new Dot(cx, cy);
        this.weight = g * Math.PI * radius * radius;
        this.energy = weight * 10 * cy;
    }

    @Override
    public void move(int time) {
        cnt += 1;
        center.addVector(v.constMul(time));
        if (cnt == 4) {
            v.addVector(a.constMul(time));
            cnt = 0;
        }
    }

    @Override
    public void changeDirection(Vector line) {
        double cosFi = -v.scalarMul(line) / (v.getSize() * line.getSize());
        double sinFi = Math.sqrt(1 - cosFi * cosFi);
        double cos2Fi = 2 * cosFi * cosFi - 1;
        double sin2Fi = 2 * sinFi * cosFi;
        double fi = Math.PI - (Math.acos((cosFi)));

//        System.out.println("normal: " + line);
//        System.out.println("v before: " + v);
        boolean clock = v.vectorMul(line) < 0;
        if (clock) {
//            v = v.rotate(-Math.PI + 2 * fi);
            v = v.rotateByTrig(-cos2Fi, -sin2Fi);
//            System.out.println("По часовой");
        } else {
//            System.out.println("По часовой");
//            v = v.rotate(-2 * fi + Math.PI);
            v = v.rotateByTrig(-cos2Fi, sin2Fi);
        }
//        System.out.println("cos(fi) " + fiRad);
//        System.out.println("fi " + fi);
//        v = v.Mul(1.1);
//        System.out.println("v after: " + v);
//        System.out.println();

        System.out.println("expected: " + Math.sqrt(2 * Math.abs((energy / weight - center.getY() * g))));
        System.out.println("got: " + v.getSize());
        v = v.constMul(Math.sqrt(2 * Math.abs((energy / weight - center.getY() * g))) / v.getSize());
//        System.out.println();
//        System.out.println(energy);
//        energy *= 0.5;
    }

    @Override
    public Dot getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public double getWeight() {
        return 0;
    }


}

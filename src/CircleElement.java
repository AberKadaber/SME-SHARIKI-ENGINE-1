

public class CircleElement implements Movable, Circle {
    private final int radius;
    private final Dot center;
    private final Vector a = new Vector(0, -1);
    private Vector v = new Vector(0, 0);
    private final double energy;
    private int cnt = 0;
    private double weight;
    private final double g = 0.4;


    public CircleElement(int radius, int cx, int cy) {
        this.radius = radius;
        this.center = new Dot(cx, cy);
        this.energy = weight * 10 * cy;
        this.weight = g * Math.PI * radius * radius;
    }

    @Override
    public void move(int time) {
        cnt += 1;
        center.addVector(v.Mul(time));
        if (cnt == 4) {
            v.addVector(a.Mul(time));
            cnt = 0;
        }

    }

    @Override
    public void changeDirection(Vector line) {
        double b = line.getDirection().getX();
        double c = line.getDirection().getY();
        double fiRad = (b * v.getDirection().getX() + c * v.getDirection().getY()) / (
                Math.sqrt(b * b + c * c) *
                        Math.sqrt(Math.pow(v.getDirection().getX(), 2) + Math.pow(v.getDirection().getY(), 2)));
        double fi = Math.PI - (Math.acos((fiRad)));
//        System.out.println("normal: " + line);
//        System.out.println("v before: " + v);
        boolean clock = v.getDirection().getX() * c - v.getDirection().getY() * b < 0;
        if (clock) {
            v = v.Rotate(-Math.PI + 2 * fi);
//            System.out.println("По часовой");
        } else {
//            System.out.println("По часовой");
            v = v.Rotate(-2 * fi + Math.PI);
        }
//        System.out.println("cos(fi) " + fiRad);
//        System.out.println("fi " + fi);
//        v = v.Mul(1.1);
//        System.out.println("v after: " + v);
//        System.out.println();

        System.out.println("expected: " + Math.sqrt(2 * Math.abs((energy / weight - center.getY() * g))));
        System.out.println("got: " + v.getSize());
        v = v.Mul(Math.sqrt(2 * Math.abs((energy / weight - center.getY() * g))) / v.getSize());
        System.out.println();
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

import java.lang.Math.*;

public class Vector {
    private final Dot direction;

    public Vector(double x, double y) {
        this.direction = new Dot(x, y);
    }

    public void addVector(Vector add) {
        direction.setXY(direction.getX() + add.direction.getX(),
                direction.getY() + add.direction.getY());
    }

    public Dot getDirection() {
        return direction;
    }

    public double getSize() {
        return Math.sqrt(direction.getX() * direction.getX() + direction.getY() * direction.getY());
    }

    public Vector Mul(double scale) {
        return new Vector((int) (direction.getX() * scale), (int) (direction.getY() * scale));
    }

    public Vector Rotate(double fi) {
//        System.out.println(fi);
//        System.out.println("cos: " + Math.cos(fi) + " sin: " + Math.sin(fi));
        return new Vector(
                direction.getX() * Math.cos(fi) - direction.getY() * Math.sin(fi),
                direction.getX() * Math.sin(fi) + direction.getY() * Math.cos(fi)
        );
    }

    @Override
    public String toString() {
        return direction.toString();
    }
}

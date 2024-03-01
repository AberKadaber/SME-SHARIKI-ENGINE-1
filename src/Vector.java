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

    public Vector constMul(double scale) {
        return new Vector((int) (direction.getX() * scale), (int) (direction.getY() * scale));
    }

    public double vectorMul(Vector other) {
        return direction.getX() * other.getDirection().getY() -
                direction.getY() * other.getDirection().getX();
    }

    public double scalarMul(Vector other) {
        return direction.getX() * other.getDirection().getX() +
                direction.getY() * other.getDirection().getY();
    }

    public Vector rotateByAngle(double fi) {
        return new Vector(
                direction.getX() * Math.cos(fi) - direction.getY() * Math.sin(fi),
                direction.getX() * Math.sin(fi) + direction.getY() * Math.cos(fi)
        );
    }

    public Vector rotateByTrig(double cosFi, double sinFi) {
        return new Vector(direction.getX() * cosFi - direction.getY() * sinFi,
                direction.getX() * sinFi + direction.getY() * cosFi);
    }

    @Override
    public String toString() {
        return direction.toString();
    }
}

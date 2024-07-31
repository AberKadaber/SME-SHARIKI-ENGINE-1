package fall.geometry;

public class Vector {
    private final Dot direction;

    public Vector(double x, double y) {
        this.direction = new Dot(x, y);
    }

    public double getX() {
        return direction.getX();
    }

    public double getY() {
        return direction.getY();
    }

    public void addVector(Vector add) {
        direction.setXY(getX() + add.getX(),
                getY() + add.getY());
    }

    public double getSize() {
        return Math.sqrt(getX() * getX() + getY() * getY());
    }

    public Vector constMul(double scale) {
        return new Vector((getX() * scale), (getY() * scale));
    }

    public double vectorMul(Vector other) {
        return getX() * other.getY() -
                getY() * other.getX();
    }

    public double scalarMul(Vector other) {
        return getX() * other.getX() +
                getY() * other.getY();
    }

    public Vector rotateByAngle(double fi) {
        return new Vector(
                getX() * Math.cos(fi) - getY() * Math.sin(fi),
                getX() * Math.sin(fi) + getY() * Math.cos(fi)
        );
    }

    public Vector rotateByTrig(double cosFi, double sinFi) {
        return new Vector(getX() * cosFi - getY() * sinFi,
                getX() * sinFi + getY() * cosFi);
    }

    @Override
    public String toString() {
        return direction.toString();
    }
}

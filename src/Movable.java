public interface Movable {
    void move(int time);
    Dot getCenter();
    double getWeight();
    void changeDirection(Vector line);
}

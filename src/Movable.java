public interface Movable {
    void move(int time);
    Dot getCenter();
    int getWeight();
    void changeDirection(Vector line);
}

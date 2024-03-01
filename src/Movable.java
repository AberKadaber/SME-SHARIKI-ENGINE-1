public interface Movable {
    void simulate(double time);
    /**
     * Directly and immediately adds addend vector to center of Movable
     *
     * @param addend vector to be added
     */
    void move(Vector addend);
    Dot getCenter();
    double getWeight();
    void changeDirection(Vector line);
}


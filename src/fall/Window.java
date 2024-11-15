package fall;

import fall.shapes.*;
import fall.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Window extends JPanel {
    private final int width = 800;
    private final int height = 800;
    private final int delay = 10;

    private final List<Shape> shapes = List.of(
            new CircleBorder(0, 0, 300),
            new CircleElement(200, 0, 50),
            new CircleElement(-150, 0, 100),
            new CircleElement(180, 190, 25),
            new CircleElement(0, 100, 25)
    );

    private boolean hasIntersection(Shape s1, Shape s2) {
        return s1 != s2 && s1.intersect((AbstractShape) s2) && !(s1.isBorder() && s2.isBorder());
    }

    private boolean hasIntersectionAllShapes() {
        for (Shape s1 : shapes) {
            for (Shape s2 : shapes) {
                if (hasIntersection(s1, s2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Window() {
        JFrame frame = new JFrame("fall");

        this.setBackground(new Color(0, 0, 0));

        frame.add(this);

        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Timer timer = new Timer(delay, _ -> {
            for (Shape s : shapes) {
                s.simulate(1d / delay);
            }
            for (Shape s1 : shapes) {
                for (Shape s2 : shapes) {
                    if (hasIntersection(s1, s2)) {
                        while (hasIntersectionAllShapes()) {
                            for (Shape s : shapes) {
                                s.simulate(-0.1 / delay);
                            }
                        }
                        s1.update((AbstractShape) s2);
                    }
                }
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g, width, height);
        }
    }
}

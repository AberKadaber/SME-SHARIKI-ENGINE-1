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
            new CircleElement(100, 0, 50),
            new CircleElement(-100, 0, 100),
            new CircleElement(0, 100, 25)
    );

    private boolean hasIntersection() {
        for (Shape s1 : shapes) {
            for (Shape s2 : shapes) {
                if (s1.intersect((AbstractShape) s2)) {
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
                    if (s1 != s2 && s1.intersect((AbstractShape) s2)) {
                        System.out.println(s1);
                        System.out.println(s2);
                        while (hasIntersection()) {
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

//    private final Color[] rainbow = {
//            Color.RED,
//            Color.ORANGE,
//            Color.YELLOW,
//            Color.GREEN,
//            Color.CYAN,
//            Color.BLUE,
//            Color.MAGENTA,
//    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g, Color.GREEN, width, height);
        }
    }
}

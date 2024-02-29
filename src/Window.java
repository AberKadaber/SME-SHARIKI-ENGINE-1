import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Window extends JPanel {
    private enum Type {
        BORDER, INSIDE
    }

    private JFrame frame;
    private final int width = 1000;
    private int height = 1000;
    private Timer timer;
    private final int delay = 5;
    private int colorPos = 0;

    private final CircleBorder border = new CircleBorder();
    private final CircleElement element = new CircleElement(50, 134, 250);

    // Интересный баг: 50, 134, 250 прилипает к левой стене через некоторое время

    public Window() {
        frame = new JFrame("fall");

        this.setBackground(new Color(0, 0, 0));

        frame.add(this);

        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        timer = new Timer(delay, e -> {
            element.simulate(1);
            if (border.intersection(element)) {
                Vector directionToBorderCenter = border.tangent(element);
                element.changeDirection(directionToBorderCenter);
                while (border.intersection(element)) {
                    Dot a = border.tangent(element).getDirection();
                    element.move(new Vector(a.getX() / Math.abs(a.getX() + a.getY()), a.getY() / Math.abs(a.getX() + a.getY())));
                }
                colorPos = (colorPos + 1) % rainbow.length;
            }
            repaint();
        });
        timer.start();
    }

    private void drawCircle(Graphics g, Circle c, int w, Type t) {
        BasicStroke pen1 = new BasicStroke(w);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(pen1);
        int x = (int) (width / 2 + c.getCenter().getX() - c.getRadius());
        int y = (int) (height / 2 - c.getCenter().getY() - c.getRadius());
        int r = c.getRadius() * 2;
        if (t == Type.INSIDE) {
            g2d.setColor(rainbow[colorPos]);
            g2d.drawOval(x, y, r, r);

            g2d.fillOval(x, y, r, r);
        } else {
            g2d.setColor(Color.WHITE);
            g2d.drawOval(x, y, r, r);
        }
    }


    //    private Color getColor(int i, Color[] g) {
//        if (i >= g.length) return Color.BLACK;
//        return g[i];
//    }
//
    private Color[] rainbow = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.BLUE,
            Color.MAGENTA,
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircle(g, border, 3, Type.BORDER);
        drawCircle(g, element, 1, Type.INSIDE);
    }
}
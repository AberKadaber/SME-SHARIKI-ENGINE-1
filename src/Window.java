import javax.swing.*;
import java.awt.*;

public class Window extends JPanel {

    private JFrame frame;
    private final int width = 1000;
    private int height = 1000;
    private Timer timer;
    private final int delay = 10;

    private final CircleBorder border = new CircleBorder();
    private final CircleElement element = new CircleElement(50, 200, 146);

    public Window() {
        frame = new JFrame("fall");

        this.setBackground(new Color(255, 255, 255));

        frame.add(this);

        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        timer = new Timer(delay, e -> {
            if (!border.intersection(element)) {
                element.move(1);
            } else {
                Vector line = border.tangent(element);

                element.changeDirection(line);
                while (border.intersection(element)) {
                    element.move(1);
                }
            }
            repaint();
        });
        timer.start();


    }

    private void drawCircle(Graphics g, Circle c, int w) {
        BasicStroke pen1 = new BasicStroke(w);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(pen1);
        int x = (int) (width / 2 + c.getCenter().getX() - c.getRadius());
        int y = (int) (height / 2 - c.getCenter().getY() - c.getRadius());
        int r = c.getRadius() * 2;
        g2d.drawOval(x, y, r, r);
    }


//    private Color getColor(int i, Color[] g) {
//        if (i >= g.length) return Color.BLACK;
//        return g[i];
//    }
//
//    private Color[] rainbow = {
//            Color.RED,
//            Color.ORANGE,
//            Color.YELLOW,
//            Color.GREEN,
//            Color.CYAN,
//            Color.BLUE,
//            Color.MAGENTA,
//    };

    //    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawCircle(g, border, 10);
        drawCircle(g, element, 5);

    }
}
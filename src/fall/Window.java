//package fall;
//
//import fall.geometry.Circle;
//import fall.geometry.Dot;
//import fall.geometry.Vector;
//import fall.shapes.CircleBorder;
//import fall.shapes.CircleElement;
//import fall.shapes.MovableType;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.HashMap;
//import java.util.List;
//
//public class Window extends JPanel {
//
//    private JFrame frame;
//    private final int width = 1000;
//    private final int height = 1000;
//    private Timer timer;
//    private final int delay = 5;
//    private int colorPos = 0;
//    private HashMap<Dot, Vector> speeds = new HashMap<>();
//
////    private final List<fall.Circle> circles = List.of(
////            new fall.shapes.CircleElement(50, -150, 0, true),
//////            new fall.shapes.CircleBorder(200, 0, 0),
////            new fall.shapes.CircleBorder(),
////            new fall.shapes.CircleElement(50, 0, 0, true),
////            new fall.shapes.CircleElement(50, 150, 200, true)
////    );
//    private final List<Circle> circles = List.of(
//        new CircleBorder(200, 200, 200),
//        new CircleBorder(200, -200, 200),
//        new CircleBorder(200, 200, -200),
//        new CircleBorder(200, -200, -200),
////        new fall.shapes.CircleElement(25, 0, 0, true),
////        new fall.shapes.CircleElement(25, 300, 300, true),
////        new fall.shapes.CircleElement(25, -275, -275, true),
//        new CircleElement(25, -320, 325, true)
////        new fall.shapes.CircleElement(25, 210, -200, true)
//);
//
//    public Window() {
//        frame = new JFrame("fall");
//
//        this.setBackground(new Color(0, 0, 0));
//
//        frame.add(this);
//
//        frame.setSize(width, height);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        timer = new Timer(delay, e -> {
//            for (Circle c : circles) {
//                c.simulate(1);
//            }
//            for (Circle c1 : circles) {
//                for (Circle c2 : circles) {
//                    if (c1 != c2 && c1.getCollision() && c2.getCollision() &&
//                            c1.getMovableType() == MovableType.MOVABLE && c2.intersection(c1)) {
//                        if (c2.getMovableType() == MovableType.UNMOVABLE) {
//                            calculateMovUn(c1, c2);
//                        } else {
//                            calculateMovMov(c1, c2);
//                        }
//                    }
//                }
//            }
//            speeds.clear();
//            for (Circle c: circles) {
//                if (c.getMovableType() == MovableType.MOVABLE) {
//                    Vector v = c.getSpeed();
//                    speeds.put(c.getCenter(), c.getSpeed());
//                }
//            }
//            repaint();
////            System.out.println("+++++++++++++++++++++++++++");
////            System.out.println(speeds);
////            System.out.println("+++++++++++++++++++++++++++");
//        });
//        timer.start();
//    }
//
//    //c1 - movable
//    public void calculateMovUn(Circle c1, Circle c2) {
////        System.out.println("radius: " + c2.radius);
//        Vector directionToBorderCenter = c2.tangent(c1);
////        System.out.println(directionToBorderCenter);
//
//        if (c2.intersectionSide(c1) == -1) {
//            System.out.println("change");
//            directionToBorderCenter = directionToBorderCenter.constMul(-1);
//        }
////        System.out.println(directionToBorderCenter);
//
//        c1.changeDirection(directionToBorderCenter);
//        while (c2.intersection(c1)) {
//            Dot a = c2.tangent(c1).getDirection();
//            System.out.println(c1.getSpeed());
//            c1.move(c1.getSpeed());
//            // new fall.geometry.Vector(a.getX() / Math.abs(a.getX() + a.getY()), a.getY() / Math.abs(a.getX() + a.getY()))
////            System.out.println("center: " + c1.getCenter());
////            System.out.println("speed: " + c1.getSpeed());
////            repaint();
//        }
//        System.out.println();
//        colorPos = (colorPos + 1) % rainbow.length;
//        c1.setColor((c1.getColor() + 1) % rainbow.length);
//
//    }
//
//    private void calculateMovMov(Circle c1, Circle c2) {
//        Vector directionToBorderCenter1 = c2.tangent(c1);
//        Vector directionToBorderCenter2 = c1.tangent(c2);
//        if (c2.intersectionSide(c1) == -1) {
//            directionToBorderCenter1 = directionToBorderCenter1.constMul(-1);
//            directionToBorderCenter2 = directionToBorderCenter2.constMul(-1);
//        }
//        c1.changeDirection(directionToBorderCenter1);
//        c2.changeDirection(directionToBorderCenter2);
//        while (c2.intersection(c1)) {
//            Dot a = c2.tangent(c1).getDirection();
//            c1.move(new Vector(a.getX() / Math.abs(a.getX() + a.getY()), a.getY() / Math.abs(a.getX() + a.getY())));
//        }
//        c1.setColor((c1.getColor() + 1) % rainbow.length);
//        c2.setColor((c2.getColor() + 1) % rainbow.length);
//    }
//
//
//    private void drawCircle(Graphics g, Circle c, int w) {
//        BasicStroke pen1 = new BasicStroke(w);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setStroke(pen1);
//        int x = (int) (width / 2 + c.getCenter().getX() - c.getRadius());
//        int y = (int) (height / 2 - c.getCenter().getY() - c.getRadius());
//        int r = c.getRadius() * 2;
//        g2d.setColor(Color.WHITE);
//        if (c.getMovableType() == MovableType.MOVABLE) {
//            g2d.setColor(rainbow[c.getColor()]);
//            g2d.fillOval(x, y, r, r);
//        }
//        g2d.drawOval(x, y, r, r);
//    }
//
//    private void drawVector(Graphics g, Dot begin, Vector v) {
//        BasicStroke pen1 = new BasicStroke(3);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setStroke(pen1);
//        g2d.setColor(Color.WHITE);
//        g2d.drawLine((int) begin.getX() + width / 2,  height / 2 - (int) begin.getY(), width / 2 + (int) (begin.getX() + v.getDirection().getX()), height / 2 - (int)(begin.getY() + v.getDirection().getY()) );
//    }
//
//
//    private final Color[] rainbow = {
//            Color.RED,
//            Color.ORANGE,
//            Color.YELLOW,
//            Color.GREEN,
//            Color.CYAN,
//            Color.BLUE,
//            Color.MAGENTA,
//    };
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        for (Circle circle : circles) {
//            drawCircle(g, circle, 5);
//        }
//        for (Dot c: speeds.keySet()) {
////            g.drawLine((int) c.getX(), (int) c.getY(), (int) speeds.get(c).getDirection().getX(), (int)speeds.get(c).getDirection().getX());
//            drawVector(g, c, speeds.get(c).constMul(15));
//        }
//    }
//}
//package fall.shapes;
//
//import fall.geometry.Dot;
//
//import java.awt.*;
//import java.util.List;
//
//public class SquareElement extends AbstractShape {
//    private final int sideSize;
//
//    public SquareElement(double cx, double cy, int sideSize) {
//        Dot center = new Dot(cx, cy);
//        super(sideSize * sideSize, center);
//        this.sideSize = sideSize;
//    }
//
//    @Override
//    public void draw(Graphics g, Color color, int width, int height) {
//        g.setColor(color);
//        g.fillRect(
//                (int) (width / 2 + center.getX() - sideSize / 2),
//                (int) (height / 2 - center.getY() - sideSize / 2),
//                sideSize, sideSize);
//        g.drawRect(
//                (int) (width / 2 + center.getX() - sideSize / 2),
//                (int) (height / 2 - center.getY() - sideSize / 2),
//                sideSize, sideSize);
//    }
//
//    @Override
//    public List<Dot> intersect(double k) {
////        System.out.println(k);
//        double dx, dy;
//        if (k >= 1 || k <= -1) {
//            dy = (double) sideSize / 2;
//            dx = dy / k;
//        } else {
//            dx = (double) sideSize / 2;
//            dy = dx * k;
//        }
////        System.out.println(dx + " " + dy);
////        System.out.println(center);
////        System.out.println( new Dot(center.getX() + Math.signum(k) * dx, center.getY() + dy));
////        System.out.println(new Dot(center.getY() - Math.signum(k) * dx, center.getY() - dy));
////        System.out.println();
//        return List.of(
//                new Dot(center.getX() + Math.signum(k) * dx, center.getY() + dy),
//                new Dot(center.getX() - Math.signum(k) * dx, center.getY() - dy)
//        );
//    }
//
//    @Override
//    public boolean isBorder() {
//        return false;
//    }
//}

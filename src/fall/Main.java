package fall;
import fall.geometry.*;

public class Main {
    static private class X {
        public Dot my;
        public X(Dot d){
            this.my = d;
        }
        void change() {
            my.addVector(new Vector(1, 1));
        }
    }

    public static void main(String[] args) {
//        new Window();
        Dot d = new Dot(1, 1);
        X x = new X(d);
        d.addVector(new Vector(1, 1));
        x.change();
        System.out.println(x.my);
    }
}
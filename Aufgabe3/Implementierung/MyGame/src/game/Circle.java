package game;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 15.05.13
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class Circle {

    private Mark[] circle;

    public Circle(int size) {
        this.circle = new Mark[size];
    }

    public Mark get(int i) {
        System.out.println(i + " -> "+ ((circle.length-1) % (i)));
        return circle[(circle.length-1) % (i)];
    }

    public Mark getPrev(int i) {
        return circle[(circle.length-1) % (i + 1)];
    }

    public Mark getNext(int i) {
        return circle[(circle.length-1) % (i - 1)];
    }

    public void set(Mark mark, int i) {
        circle[circle.length % i] = mark;
    }

    public int size() {
        return circle.length;
    }

    @Override
    public String toString() {
        return String.format("Circle{%s}", Arrays.toString(circle));
    }
}

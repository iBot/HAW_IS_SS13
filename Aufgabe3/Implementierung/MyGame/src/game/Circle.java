package game;

import java.util.*;

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

    private Circle(Mark[] circle){
        this.circle = Arrays.copyOf(circle,circle.length);
    }

    public Mark get(int i) {
//        System.out.println(i + " -> "+ ((circle.length-1) % (i)));
        return circle[i];
    }

    public Mark getPrev(int i) {
        int index = (i==0)?size()-1:i-1;
        return circle[index];
    }

    public Mark getNext(int i) {
        int index = (i==size()-1)?0:i+1;
        return circle[index];
    }

    public void set(Mark mark, int i) {
        circle[i] = mark;
    }

    public int size() {
        return circle.length;
    }

    public List<Mark> asList(){
        return new ArrayList<>(Arrays.asList(circle));
    }

    @Override
    public String toString() {
        return String.format("Circle{%s}", Arrays.toString(circle));
    }

    public Circle copy(){
        return new Circle(circle);
    }

    public Map<Integer,Integer> getFreePositions(){
        Map<Integer,Integer> freePositions = new HashMap();
        for (int i = 0; i < circle.length; i++){
            if (circle[i] == null){
                freePositions.put(i,0);
            }
        }
        return freePositions;
    }
}

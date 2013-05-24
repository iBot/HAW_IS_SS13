/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 22.05.13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class Field {
    private int x,y;
    private KColor c;
    private Figure f = null;

    @Override
    public String toString() {
        return "Field{" +
                "x=" + x +
                ", y=" + y +
                ", c=" + c +
                ", f=" + f +
                '}';
    }

    public Field(KColor c, int x, int y) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public Figure getFigure(){
        return f;
    }

    public void setFigure(Figure f){
        this.f = f;
    }

    public KColor getColor(){
        return c;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

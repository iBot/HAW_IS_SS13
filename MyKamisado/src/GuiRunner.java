import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 22.05.13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
public class GuiRunner extends Thread{
    private ArrayList<Figure> figures = new ArrayList<Figure>();
    private Board b;
    private Kamisado k;
    private Rules r;
    private ArrayList<Field> possibleTargets = new ArrayList<Field>();

    public GuiRunner(Kamisado k, Rules r){
        this.k = k;
        this.r = r;
    }

    public void clicked(int x, int y) {
        //System.out.println("Hallo?");
        //System.out.println(r.getField(x, y).toString());
        r.getTurn().move(r.getField(y, x));
    }

    public Figure getFigure(int posX, int posY) {
        return r.getFigure(posX,posY);
    }

    public void showPossibleTargets(){
        ArrayList<Field> targets = r.getPossibleTargets(r.getFigure(r.getMarkX(),r.getMarkY()));
        b.setTargets(targets);
    }

    public void setBoard(Board board) {
        this.b = board;
    }

    public void run(){
        Frame frame = new Frame(b);
        frame.pack();
        frame.setVisible(true);
    }

    public void hideTargets(){
        b.setTargets(new ArrayList<Field>());
    }
}

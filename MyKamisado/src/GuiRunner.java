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

    public GuiRunner(Kamisado k, Rules r){
        this.k = k;
        this.r = r;
    }

    public void clicked(int x, int y) {

    }

    public Figure getFigure(int posY, int posX) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setBoard(Board board) {
        this.b = board;
    }
}

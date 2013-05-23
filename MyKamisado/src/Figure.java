import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 22.05.13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class Figure {
    private Player player;
    private KColor color;
    private Field pos;
    private boolean marked = false;

    public Figure(Field pos, Player player, KColor color) {
        this.pos = pos;
        this.player = player;
        this.color = color;
    }

    public Player getPlayer() {
        return player;
    }

    public KColor getColor() {
        return color;
    }

    public void setColor(KColor color) {
        this.color = color;
    }

    public Field getPos() {
        return pos;
    }

    public void setPos(Field pos) {
        this.pos = pos;
    }

    public void setMarked(boolean marked){
        this.marked = marked;
    }

    public boolean isMarked() {
        return marked;
    }
}

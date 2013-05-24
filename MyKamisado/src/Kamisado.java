import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 23.05.13
 * Time: 08:51
 * To change this template use File | Settings | File Templates.
 */
public class Kamisado implements Runnable{
    private static KColor[][] COLOR_PATTERN;
    public static Rules rules;
    public static Kamisado kamisado;
    private Thread kGame;
    public static GuiRunner gr;
    private Player playerB, playerW;
    private boolean stopGame = false;
    private Board b;
    private Frame frame;

    //Spielerfarben
    private KColor white = new KColor(Color.WHITE, "White", 1);
    private KColor black = new KColor(Color.BLACK, "Black", 2);

    /**
     * Festlegung des Farbmusters inklusive Erzeugung der entsprechende
     * Spielfarben.
     */
    private static void setColors() {

        // deklariere Farben mit Codes und Namen

        KColor orange = new KColor(238, 118, 0, "Orange", 1);
        KColor blue = new KColor(72, 118, 255, "Blue", 2);
        KColor purple = new KColor(160, 32, 240, "Purple", 3);
        KColor pink = new KColor(255, 105, 180, "Pink", 4);
        KColor yellow = new KColor(255, 215, 0, "Yellow", 5);
        KColor red = new KColor(205, 0, 0, "Red", 6);
        KColor green = new KColor(34, 139, 34, "Green", 7);
        KColor brown = new KColor(139, 69, 19, "Brown", 8);

        // Aufbau des Farbmusters

        KColor[][] cols = {
                { orange, blue, purple, pink, yellow, red, green, brown },
                { red, orange, pink, green, blue, yellow, brown, purple },
                { green, pink, orange, red, purple, brown, yellow, blue },
                { pink, purple, blue, orange, brown, green, red, yellow },
                { yellow, red, green, brown, orange, blue, purple, pink },
                { blue, yellow, brown, purple, red, orange, pink, green },
                { purple, brown, yellow, blue, green, pink, orange, red },
                { brown, green, red, yellow, pink, purple, blue, orange } };

        COLOR_PATTERN = cols;

    }

    public static void main(String[] args) {
        setColors();
        kamisado = new Kamisado();
    }

    public Kamisado() {
        playerW = new Human(white);
        playerB = new Computer(black);
        rules = new Rules(playerW, playerB);
        gr = new GuiRunner(this, rules);
        b = new Board(gr);
        gr.start();
        rules.setGuiRunner(gr);
        kGame = new Thread(this);
        kGame.start();
    }

    @Override
    public void run() {
        try{
            gr.join();
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null,"Fehler beim Warten auf GUI!");
        }

        /*while(!stopGame && !rules.isGameOver()){

        }*/
        while(!rules.isGameOver() && !stopGame){
            if(rules.isMarked()){
                gr.showPossibleTargets();
                b.repaint();
            }
            Player p = rules.getTurn();
            Field f = p.nextMove();
            rules.setMove(f);
            b.repaint();
            gr.hideTargets();
        }
    }

    public static KColor getFieldColor(int posY, int posX) {
        return COLOR_PATTERN[posX][posY];
    }

    public static String getFieldTextX(int i) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public static String getFieldTextY(int i) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}

package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 15.05.13
 * Time: 12:40
 * To change this template use File | Settings | File Templates.
 */
public class Starter {

     public static void main(String[] args){
        List<Mark> spielfeld = new ArrayList<>();
        spielfeld.add(new Mark(new Player("Bla")));
        spielfeld.add(null);
        spielfeld.add(null);
        spielfeld.add(null);
        spielfeld.add(null);
        spielfeld.add(null);
        spielfeld.add(null);
        spielfeld.add(null);
        spielfeld.add(null);
        spielfeld.add(null);
        new AlphaBeta(5, spielfeld);
    }
}

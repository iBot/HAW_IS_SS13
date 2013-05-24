package game;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 15.05.13
 * Time: 12:40
 * To change this template use File | Settings | File Templates.
 */
public class Starter {

    public static void main(String[] args) {
//        Board board = new Board(5);
//         board.setMarkAtPosition((new Mark(new Player("Bla",true))), 0);
//        AlphaBeta abSuche= new AlphaBeta(3, board.getCircle());
//
//
//         int move = abSuche.getNextMove();
//
//         System.out.println(move);
        Board board = new Board(20);
        Player human = new Player("Human", true);
        Player computer = new Player("Computer", false);
        IGui gui = new Gui(board, human, computer);

    }
}

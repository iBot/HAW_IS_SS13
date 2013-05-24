package game;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 24.05.13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class AI  implements PlayerMovedListener{
    private final Gui gui;
    private final Board board;
    private final Player player;

    @Override
    public void computerMove() {
        System.out.println("Computer ist am Zug. Board: "+board.getCircle());
        AlphaBeta alphaBetaSearch = new AlphaBeta(15,board.getCircle().copy());

        if (!board.isGameFinished()){
//            try {
                int nextMove = alphaBetaSearch.getNextMove();
            System.out.printf(" Computer wählt Feld %d%n", nextMove);
                gui.markField(nextMove,2);
//            } catch (IGui.OffBoardException e) {
//                throw new Error("Programmer did something wrong!!!!",e);
//            }
        }
    }

    public AI(Board board, Gui gui, Player player ){
        this.board = board;
        this.player = player;
        this.gui = gui;
        gui.subscribeForPlayerMovedEvet(this);
    }
}

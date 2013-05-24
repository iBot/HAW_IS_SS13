package game;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 24.05.13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class AI  implements PlayerMovedListener{
    private final IGui gui;
    private final Board board;
    private final Player player;

    @Override
    public void computerMove() {
        AlphaBeta alphaBetaSearch = new AlphaBeta(10,board.getCircle().copy());
        try {
            gui.setField(alphaBetaSearch.getNextMove(),2);
        } catch (IGui.OffBoardException e) {
            throw new Error("Programmer did something wrong!!!!",e);
        }
    }

    public AI(Board board, IGui gui, Player player ){
        this.board = board;
        this.player = player;
        this.gui = gui;
        gui.subscribeForPlayerMovedEvet(this);
    }
}

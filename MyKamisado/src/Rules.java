/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 22.05.13
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */

public class Rules {
    private Field[][] board;
    private Player turn, player1, player2;
    private boolean marked, gameOver;
    private int markX, markY;
    private GuiRunner gr;

    public Rules(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getTurn(){
        return turn;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getMarkX() {
        return markX;
    }

    public int getMarkY() {
        return markY;
    }

    public void setGuiRunner(GuiRunner guiRunner) {
        this.gr = guiRunner;
    }
}

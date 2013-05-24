import java.util.ArrayList;

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
    private Board b;
    KColor moveColor;

    public Rules(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = this.player1;
        board = new Field[8][8];
        initBoard();
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

    public void setMarked(boolean marked){
        this.marked = marked;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setBoard(Board b){
        this.b = b;
    }

    public void setMarkY(int markY) {
        this.markY = markY;
    }

    public void setMarkX(int markX) {
        this.markX = markX;
    }

    public int getMarkX() {
        return markX;
    }

    public int getMarkY() {
        return markY;
    }

    public Field getField(int x, int y){
        return board[x][y];
    }

    public Figure getFigure(int x, int y){
        return board[x][y].getFigure();
    }

    public ArrayList<Field> getPossibleTargets(Figure figure){
        Field pos = figure.getPos();
        ArrayList<Field> targets = new ArrayList<Field>();
        boolean vertBlocked = false;
        boolean diaRigBlocked = false;
        boolean diaLefBlocked = false;

        // Diagonale Suchen jeweils aus Sicht der Spielfigur
        if(figure.getPlayer().color.getCode() == 1){
            int dl = pos.getY() - 1;
            int dr = pos.getY() + 1;
            for(int x = pos.getX()-1; x>=0; x--){
                //Geradeaus suchen
                if(!vertBlocked){
                    if(getFigure(x,pos.getY()) != null){
                        vertBlocked = true;
                    } else {
                        targets.add(getField(x, pos.getY()));
                    }
                }
                //Diagonal nach links
                if(!diaLefBlocked && dl >= 0){
                    if(getFigure(x,dl) != null){
                        diaLefBlocked = true;
                    } else {
                        System.out.println("dl:"+dl);
                        targets.add(getField(x,dl));
                    }
                }
                dl--;
                //Diagonal nach rechts
                if(!diaRigBlocked && dr <= 7){
                    if(getFigure(x,dr) != null){
                        diaRigBlocked = true;
                    } else {
                        System.out.println("dr:"+dr);
                        targets.add(getField(x,dr));
                    }
                }
                dr++;
            }
        } else {
            int dl = pos.getX() + 1;
            int dr = pos.getX() - 1;
            for(int y = pos.getY()+1; y<=7; y++){
                //Geradeaus suchen
                if(!vertBlocked){
                    if(getFigure(pos.getX(),y) == null){
                        vertBlocked = true;
                    } else {
                        targets.add(getField(pos.getX(), y));
                    }
                }
                //Diagonal nach links
                if(!diaLefBlocked && dl <= 7){
                    if(getFigure(dl, y) != null){
                        diaLefBlocked = true;
                    } else {
                        targets.add(getField(dl, y));
                    }
                }
                dl++;
                //Diagonal nach rechts
                if(!diaRigBlocked && dr >= 0){
                    if(getFigure(dr,y) != null){
                        diaRigBlocked = true;
                    } else {
                        targets.add(getField(dr, y));
                    }
                }
                dr--;
            }
        }

        return targets;
    }

    private void initBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Field(Kamisado.getFieldColor(i, j), i, j);
                if (i == 0) {
                    Figure fig = new Figure(board[i][j], player2, Kamisado.getFieldColor(i, j));
                    board[i][j].setFigure(fig);
                    //System.out.println("Black Fig set @ "+i+", "+j);
                }
                if (i == 7) {
                    Figure fig = new Figure(board[i][j], player1, Kamisado.getFieldColor(i, j));
                    board[i][j].setFigure(fig);
                    //System.out.println("White Fig set @ x: " + i + ", y: " + j);
                }
                //System.out.println("set "+board[i][j]);
            }
        }
    }

    public void setMove(Field f){
        int x = f.getX();
        int y = f.getY();
        System.out.println(f.toString());

        if(!isMarked()){
            f.getFigure().setMarked(true);
            marked = true;
            markX = x;
            markY = y;
            moveColor = f.getFigure().getColor();
        } else {
            ArrayList<Field> targets = getPossibleTargets(board[markX][markY].getFigure());
            if(targets.contains(f)){
                b.drawMove(board[markX][markY], f);
                move(f);
            }
        }
    }

    private void move(Field f){
        //Figur vom alten aufs neue Feld verschieben. Auf dem alten Feld löschen
        f.setFigure(board[markX][markY].getFigure());
        board[markX][markY].setFigure(null);

        if(turn == player1 && f.getX() == 0){
            System.out.println("Weiss gewinnt!");
            gameOver = true;
        } else if(turn == player2 && f.getX() == 7){
            System.out.println("Schwarz gewinnt!");
            gameOver = true;
        } else {
            f.getFigure().setMarked(false);
            moveColor = f.getColor();
            nextTurn();
        }
    }

    private void nextTurn(){

    }

    public void setGuiRunner(GuiRunner guiRunner) {
        this.gr = guiRunner;
    }
}

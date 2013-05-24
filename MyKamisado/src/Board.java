import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 23.05.13
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class Board extends JPanel{
    private int boardLen, boardX, boardY, fieldLen, maxX, maxY, pad;
    private int targetArcScale = 7;
    private int padScale = 22;
    private int fieldsPerLine = 8;
    private int figureCrossScale = 20;
    private int figureScale = 70;
    private int fontScale = 4;
    private int numberOfFields = fieldsPerLine * fieldsPerLine;
    private Graphics2D g2;
    private Color markColor = Color.GRAY;
    private Field moveStart, moveTarget;
    private boolean moveVisible = false;
    private ArrayList<Field> ratings = new ArrayList<Field>();
    private ArrayList<Field> targets = new ArrayList<Field>();
    private GuiRunner gr;

    public Board(GuiRunner gr) {
        this.setSize(500, 500);
        this.gr = gr;
        addListener();
        gr.setBoard(this);
    }

    private void addListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clicked(e);
            }
        });
    }

    private void clicked(MouseEvent e) {
        // Die Wurzel des Koordinatenkreuzes ist oben links
        int x = e.getX();
        int y = e.getY();
        if (x > boardX & x < boardX + boardLen & y > boardY
                & y < boardY + boardLen) {

            x = (x - boardX) / fieldLen;
            y = (y - boardY) / fieldLen;
            //System.out.println("Zugriff auf x: "+x+" y:"+y+" Pos: x:"+e.getX()+" y:"+e.getY());

            gr.clicked(x, y);
        }
    }

    private void drawBackground() {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, maxX, maxY);
    }

    private void drawBoard() {
        g2.setColor(Color.YELLOW);

        int max = maxX;

        if (maxX > maxY)
            max = maxY;

        pad = max / padScale;
        boardLen = max - 2 * pad;

        boardX = (maxX - boardLen) / 2;
        boardY = pad;

        //drawCoords();

        drawFields();

        if (moveVisible)
            drawMove();

        if(!targets.isEmpty()){
            System.out.println(targets.toString());
            drawTargets();
        }

        /*synchronized (ratings) {
            drawRatings();
        } */

    }

    private void drawCoords() {

        g2.setColor(Color.WHITE);
        Font f = new Font("Arial", Font.PLAIN, fieldLen / fontScale);
        g2.setFont(f);
        int fh = (int) (g2.getFontMetrics().getHeight() * 0.6);

        for (int i = 0; i < fieldsPerLine; i++) {

            // vertikal

            String s = Kamisado.getFieldTextY(i);

            int fw = g2.getFontMetrics().stringWidth(s);

            int x = boardX - (pad - fw) / 2 - fw;
            int y = boardY + i * fieldLen + (fieldLen - fh) / 2 + fh;

            g2.drawString(s, x, y);
            g2.drawString(s, x + boardLen + pad, y);

            // horizontal

            s = Kamisado.getFieldTextX(i);

            fw = g2.getFontMetrics().stringWidth(s);

            x = boardX + i * fieldLen + (fieldLen - fw) / 2;
            y = (pad - fh) / 2 + fh;

            g2.drawString(s, x, y);
            g2.drawString(s, x, y + pad + boardLen);

        }
    }

    private void drawFields() {

        fieldLen = boardLen / fieldsPerLine;

        for (int i = 0; i < numberOfFields; i++) {
            int posX = i % fieldsPerLine;
            int posY = i / fieldsPerLine;
            int x = boardX + posX * fieldLen;
            int y = boardY + posY * fieldLen;
            g2.setColor(Kamisado.getFieldColor(posY, posX));
            g2.fillRect(x, y, fieldLen, fieldLen);

            Figure fig = gr.getFigure(posY, posX);
            if (fig != null)
                drawFigure(fig, x, y);
        }

    }

    private void drawFigure(Figure fig, int posX, int posY) {

        drawFigureArc(fig.getPlayer().getColor(), posX, posY);

        drawFigureCross(fig.getColor(), posX, posY);

        if (fig.isMarked()) {
            drawFigureMark(markColor, posX, posY);
        }

    }

    private void drawFigureArc(KColor c, int posX, int posY) {
        // Ausgefüllter Kreis in Spielerfarbe
        g2.setColor(c);
        int w = figureScale * fieldLen / 100;
        int x = posX + (fieldLen - w) / 2;
        int y = posY + (fieldLen - w) / 2;
        g2.fillArc(x, y, w, w, 0, 360);
    }

    private void drawFigureCross(Color c, int posX, int posY) {
        // Kreuz in Figurfarbe
        g2.setColor(c);
        int w = figureCrossScale * fieldLen / 100;
        g2.setStroke(new BasicStroke(w / 2));
        int x1 = posX + (fieldLen - w) / 2;
        int y1 = posY + (fieldLen - w) / 2;
        int x2 = x1 + w;
        int y2 = y1 + w;
        g2.drawLine(x1, y1, x2, y2);
        g2.drawLine(x1, y2, x2, y1);
    }

    private void drawFigureMark(Color c, int posX, int posY) {
        g2.setColor(c);
        int w = figureScale * fieldLen / 100;
        int x = posX + (fieldLen - w) / 2;
        int y = posY + (fieldLen - w) / 2;
        g2.drawArc(x, y, w, w, 0, 360);
    }

    private void drawMove() {
        g2.setColor(markColor);
        int x1 = boardX + moveStart.getY() * fieldLen + fieldLen / 2;
        int y1 = boardY + moveStart.getX() * fieldLen + fieldLen / 2;

        int cornerX = 0;

        if (moveStart.getX() < moveTarget.getX())
            cornerX = 25;
        else if (moveStart.getX() == moveTarget.getX())
            cornerX = 50;
        else
            cornerX = 75;

        int cornerY = 0;

        if (moveStart.getY() < moveTarget.getY())
            cornerY = 25;
        else if (moveStart.getY() == moveTarget.getY())
            cornerY = 50;
        else
            cornerY = 75;

        int x2 = boardX + moveTarget.getY() * fieldLen + cornerY * fieldLen
                / 100;
        int y2 = boardY + moveTarget.getX() * fieldLen + cornerX * fieldLen
                / 100;
        g2.drawLine(x1, y1, x2, y2);
    }

    public void drawMove(Field start, Field target) {
        moveVisible = true;
        moveStart = start;
        moveTarget = target;
    }

    /*private synchronized void drawRatings() {

        Font f = new Font("Arial", Font.BOLD, fieldLen / fontScale);
        g2.setFont(f);

        g2.setColor(Kamisado.rules.getTurn().getColor());

        for (Field p : ratings) {

            Field pos = Computer.codeToField(p.getX());

            int posX = pos.getX();
            int posY = pos.getY();

            int x = boardX + posY * fieldLen;
            int y = boardY + posX * fieldLen + fieldLen;

            //g2.drawString("" + Math.round(p.getV() * 1000) / 1000., x, y);

        }

    }*/

    private void drawTargets() {
        if(targets.isEmpty())
            return;

        int w = fieldLen / targetArcScale;

        for (Field f : targets) {
            g2.setColor(Kamisado.rules.getTurn().getColor());
            int x = boardX + f.getY() * fieldLen + fieldLen / 2 - w / 2;
            int y = boardY + f.getX() * fieldLen + fieldLen / 2 - w / 2;
            g2.fillArc(x, y, w, w, 0, 360);
        }
    }

    @Override
    public void paint(Graphics g) {

        this.g2 = (Graphics2D) g;
        maxX = getWidth();
        maxY = getHeight();

        drawBackground();
        drawBoard();

    }

    public void setMoveVisible(boolean b) {
        moveVisible = b;
    }

    public void setTargets(ArrayList<Field> tar) {
        targets = tar;
        this.repaint();
    }

    public synchronized void updateRatings(ArrayList<Field> ratings) {
        synchronized (this.ratings) {
            this.ratings = ratings;
        }
    }
}

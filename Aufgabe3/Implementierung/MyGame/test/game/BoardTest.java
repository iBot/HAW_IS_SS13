package game;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 12.05.13
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class BoardTest {
    private Player p1,p2;
    private Mark m1, m2;

    @Before
    public void setUp() throws Exception {
        p1 = new Player("P1");
        p2 = new Player("P2");
        m1 = new Mark(p1);
        m2 = new Mark(p2);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testBoardSize1() throws Exception{
        System.out.println("\nBoard, size 1\n################");
        Board board = new Board(1);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());
        board.setMarkAtPosition(m1, 0);
        System.out.println(board);
        assertTrue("Game is Finished?",board.isGameFinished());
        assertEquals("Player 1 lost game: ", p1, board.getLooser());
    }

    @Test
    public void testBoardSize2() throws Exception{
        System.out.println("\nBoard, size 2\n################");
        Board board = new Board(2);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());
        board.setMarkAtPosition(m1, 0);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());
        board.setMarkAtPosition(m2, 1);
        System.out.println(board);
        assertTrue("Game is Finished?",board.isGameFinished());
        assertEquals("Player 1 lost game: ", p2, board.getLooser());
    }


    @Test
    public void testBoardSize3() throws Exception{
        System.out.println("\nBoard, size 3\n################");
        Board board = new Board(3);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());
        board.setMarkAtPosition(m1, 0);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());
        board.setMarkAtPosition(m2, 2);
        System.out.println(board);
        assertTrue("Game is Finished?",board.isGameFinished());
        assertEquals("Player 1 lost game: ", p2, board.getLooser());
    }

    @Test
    public void testBoardSize10() throws Exception{
        System.out.println("\nBoard, size 12\n################");
        Board board = new Board(12);

        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());

        board.setMarkAtPosition(m1, 0);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());

        board.setMarkAtPosition(m2, 3);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());

        board.setMarkAtPosition(m1, 8);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());

        board.setMarkAtPosition(m2, 10);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());

        board.setMarkAtPosition(m1, 5);
        System.out.println(board);
        assertFalse("Game is Finished?",board.isGameFinished());

        board.setMarkAtPosition(m2, 6);
        System.out.println(board);
        assertTrue("Game is Finished?",board.isGameFinished());

        assertEquals("Player 1 lost game: ", p2, board.getLooser());
    }
}

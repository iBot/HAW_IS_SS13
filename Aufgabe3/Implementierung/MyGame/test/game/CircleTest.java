package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 15.05.13
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class CircleTest {

    private Circle circle;
    @Before
    public void setUp() throws Exception {
        circle = new Circle(10);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testGetPrev() throws Exception {

    }

    @Test
    public void testGetNext() throws Exception {

    }

    @Test
    public void testSet() throws Exception {
        circle.set(new Mark(new Player("player")),4);
        assertNotNull(circle.get(14));
        circle.set(new Mark(new Player("player")),11);
        assertNotNull(circle.get(1));
    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }
}

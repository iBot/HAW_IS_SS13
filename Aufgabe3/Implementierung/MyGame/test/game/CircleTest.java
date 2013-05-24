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

        Mark m1 = new Mark(new Player("player",true));
        Mark m2 = new Mark(new Player("player",true));
        Mark m3 = new Mark(new Player("player",true));
        circle.set(m1,4);
        assertNotNull(circle.get(4));
        circle.set(m2,1);
        assertNotNull(circle.get(1));
        Mark m2next = circle.getNext(0);
        assertSame("Same Object: ",m2,m2next);
        Mark m2prev = circle.getPrev(2);
        assertSame("Same Object: ",m2,m2next);
        circle.set(m3,9);
        Mark m3next = circle.getNext(9);
        assertNull(m3next);
        Mark m3prev = circle.getPrev(0);
        assertSame("Same Object: ",m3,m3prev);
    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }
}

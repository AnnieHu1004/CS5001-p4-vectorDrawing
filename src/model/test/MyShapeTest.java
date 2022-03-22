package model.test;
import model.MyShape;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


/**
 * Class for testing MyShape.
 */
public class MyShapeTest {
    private MyShape myShape1;
    private MyShape myShape2;
    private MyShape myShape3;

    /**
     * Method for setting up previous situation.
     */
    @Before
    public void setup() {
        myShape1 = new MyShape("LINE", 10.0, 30.0, 42.0, 64.0, Color.BLACK, false);
        myShape2 = new MyShape("RECTANGLE", 30.0, 34.0, 14.0, 46.0, Color.BLUE, true);
    }

    /**
     * Method for testing whether myShape exists.
     */
    @Test
    public void testExists() {
        assertNotNull(myShape1);
    }

    /**
     * Method for testing getShape method.
     */
    @Test
    public void testGetShape() {
        assertEquals(Line2D.Double.class, myShape1.getShape().getClass());
        assertEquals(Rectangle2D.Double.class, myShape2.getShape().getClass());
    }

    /**
     * Method for testing getColor method.
     */
    @Test
    public void testGetColor() {
        assertEquals(Color.BLACK, myShape1.getColor());
        assertEquals(Color.BLUE, myShape2.getColor());
    }

    /**
     * Method for testing getShapeMode method.
     */
    @Test
    public void testGetShapeMode() {
        assertEquals("LINE", myShape1.getShapeMode());
        assertEquals("RECTANGLE", myShape2.getShapeMode());
    }

    /**
     * Method for testing input invalid shapemode.
     */
    @Test (expected = IllegalStateException.class)
    public void testInvalidShapeMode() {
        myShape3 = new MyShape("STAR", 10.0, 30.0, 42.0, 64.0, Color.BLUE, true);
        fail("wrong shape mode");
    }
}

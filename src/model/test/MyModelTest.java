package model.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.MyModel;
import model.MyShape;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class for testing MyModel.
 */
public class MyModelTest {
    private MyModel model = new MyModel();
    private ArrayList<MyShape> shapeList;
    private Stack<MyShape> redoList;

    /**
     * Method for testing get default color.
     */
    @Test
    public void testDefaultColor() {
        assertEquals(Color.BLACK, model.getColor());
    }

    /**
     * Method for testing setColor method.
     */
    @Test
    public void testSetColor() {
        model.setColor(Color.BLUE);
        assertEquals(Color.BLUE, model.getColor());
        assertNotEquals(Color.BLACK, model.getColor());
    }

    /**
     * Method for testing get default shapeMode.
     */
    @Test
    public void testDefaultShapeMode() {
        assertEquals("LINE", model.getShapeMode());
    }

    /**
     * Method for testing setShapeMode method.
     */
    @Test
    public void testSetShapeMode() {
        model.setShapeMode("RECTANGLE");
        assertEquals("RECTANGLE", model.getShapeMode());
        assertNotEquals("LINE", model.getShapeMode());
    }

    /**
     * Method for testing drawShape method.
     */
    @Test
    public void testDrawShape() {
        ArrayList<MyShape> shapeList = model.getShapeList();
        model.drawShape(40, 30, 69, 82);

        assertEquals(1, shapeList.size());
        assertEquals("LINE", shapeList.get(shapeList.size() - 1).getShapeMode());
        assertEquals(Line2D.Double.class, shapeList.get(shapeList.size() - 1).getShape().getClass());
    }

    /**
     * Method for testing undo and method.
     */
    @Test
    public void testUndoAndRedo() {
        shapeList = model.getShapeList();

        // create a line
        model.setShapeMode("LINE");
        model.setColor(Color.RED);
        model.drawShape(40, 30, 69, 82);
        assertEquals(1, shapeList.size());


        // create a rectangle
        model.setShapeMode("RECTANGLE");
        model.setColor(Color.GRAY);
        model.drawShape(40, 30, 69, 82);
        assertEquals(2, shapeList.size());

        // create a line
        model.setShapeMode("LINE");
        model.setColor(Color.GRAY);
        model.drawShape(4, 3, 97, 82);
        assertEquals(3, shapeList.size());

        // undo the line
        model.operate("UNDO");
        assertEquals(Rectangle2D.Double.class, shapeList.get(shapeList.size() - 1).getShape().getClass());
        assertEquals(2, shapeList.size());

        redoList = model.getRedoList();

        assertEquals(1, redoList.size());
        assertEquals(Line2D.Double.class, redoList.get(redoList.size() - 1).getShape().getClass());

        // redo the line
        model.operate("REDO");
        redoList = model.getRedoList();
        assertEquals(0, redoList.size());
        assertEquals(3, shapeList.size());
        assertEquals(Line2D.Double.class, shapeList.get(shapeList.size() - 1).getShape().getClass());
    }

    /**
     * Method for testing load method.
     */
    @Test
    public void testLoad() {
        String path = "../TestSave";
        model.save(path);
        model.load(path);
        assertEquals(0, model.getShapeList().size());

        // create a line
        model.setShapeMode("LINE");
        model.setColor(Color.RED);
        model.drawShape(40, 30, 69, 82);
        model.save(path);
        model.load(path);
        assertEquals(1, model.getShapeList().size());
        assertEquals(Line2D.Double.class, model.getShapeList().get(model.getShapeList().size() - 1).getShape().getClass());
    }
}

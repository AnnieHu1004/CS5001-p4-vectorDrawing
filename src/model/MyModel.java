package model;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class for MyModel.
 */
public class MyModel implements Serializable {
    private PropertyChangeSupport notifier;

    private ArrayList<MyShape> shapeList;
    private Stack<MyShape> redoList;
    private Color color;
    private String shapeMode;
    private boolean isLockRatio;

    /**
     * Constructor method for class MyModel.
     */
    public MyModel() {
        this.shapeList = new ArrayList<>();
        this.color = Color.BLACK;
        this.shapeMode = "LINE";
        this.isLockRatio = false;
        this.redoList = new Stack<>();
        this.notifier = new PropertyChangeSupport(this);
    }

    /**
     * Method for adding different listeners to this model.
     * @param listener the view that wants to listen to / observe this model
     */
    public void addListener(PropertyChangeListener listener) {
        notifier.addPropertyChangeListener(listener);
    }

    /**
     * Method for updating the property and then notice all listeners.
     * In this case, the property is an arrayList that contains different data of corresponding shapes
     */
    private void update() {
        notifier.firePropertyChange("shapeList", null, shapeList);
    }

    /**
     * Method for adding different shapes to the arraylist then wait to be used by different view.
     * @param x1 the horizontal coordinate of the starting point
     * @param y1 the ordinate of the starting point
     * @param x2 the horizontal coordinate of the ending point
     * @param y2 the ordinate of the ending point
     */
    public void drawShape(double x1, double y1, double x2, double y2) {
        shapeList.add(new MyShape(shapeMode, x1, y1, x2, y2, color, isLockRatio));
        // when new shape is been created
        // the old stack that saves shapes waiting to be redo need to be cleared
        // to ensure correct redo operations
        cleanRedoList();
        update();
    }

    /**
     * Method for choosing certain operation on the shape(s).
     * Including UNDO, REDO and CLEAR
     * @param operation UNDO / REDO / CLEAR
     */
    public void operate(String operation) {
        switch (operation) {
            case "UNDO":
                undo();
                break;
            case "REDO":
                redo();
                break;
            case "CLEAR":
                cleanCanvas();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
    }

    /**
     * Method for undoing the shapes requested by the user.
     */
    private void undo() {
        if (shapeList.size() > 0) {
            MyShape lastShape = shapeList.get(shapeList.size() - 1);
            redoList.push(lastShape);
            shapeList.remove(lastShape);
            update();
        }
    }

    /**
     * Method for redoing the shapes requested by the user.
     */
    private void redo() {
        if (redoList.size() > 0) {
            shapeList.add(redoList.pop());
            update();
        }
    }

    /**
     * Method for cleaning all shapes requested by the user.
     */
    private void cleanCanvas() {
        shapeList.clear();
        cleanRedoList();
        update();
    }

    /**
     * Method for cleaning the stack redoList.
     */
    private void cleanRedoList() {
        redoList = new Stack<>();
    }

    /**
     * Method for setting the shape mode of the shape.
     * @param shapeMode that indicates the name/mode of the shape
     */
    public void setShapeMode(String shapeMode) {
        this.shapeMode = shapeMode;
    }

    /**
     * Method for setting the color of the shape.
     * @param color the color of the shape
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Method for setting the state for locking the aspect ratio.
     * @param isLockRatio the state for locking the aspect ratio
     */
    public void setLockRatio(boolean isLockRatio) {
        this.isLockRatio = isLockRatio;
    }

    /**
     * Method for saving vector drawings in a format that permits them to be manipulated as vector drawings after loading.
     * @param path the location of the document
     */
    public void save(String path) {
        try {
            ObjectOutputStream drawSaving = new ObjectOutputStream(new FileOutputStream(path));
            drawSaving.writeObject(shapeList);
            drawSaving.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for loading document that permits it to be manipulated as vector drawings.
     * @param path the location of the document
     */
    public void load(String path) {
        try {
            cleanCanvas();
            ObjectInputStream drawLoading = new ObjectInputStream(new FileInputStream(path));
            shapeList = (ArrayList<MyShape>) drawLoading.readObject();
            drawLoading.close();
            update();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // methods for testing MyModel

    /**
     * Method for getting color for MyModelTest.
     * @return color for MyModelTest
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method for getting shapeMode for MyModelTest.
     * @return shapeMode for MyModelTest
     */
    public String getShapeMode() {
        return shapeMode;
    }

    /**
     * Method for getting shapeList for MyModelTest.
     * @return shapeList for MyModelTest
     */
    public ArrayList<MyShape> getShapeList() {
        return shapeList;
    }

    /**
     * Method for getting redoList for MyModelTest.
     * @return redoList for MyModelTest
     */
    public Stack<MyShape> getRedoList() {
        return redoList;
    }
}

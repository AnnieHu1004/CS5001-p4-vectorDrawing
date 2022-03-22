package controller;

import model.MyModel;

import java.awt.Color;
import java.beans.PropertyChangeListener;

/**
 * Class for DrawController.
 * Offering methods to control the drawing model.
 */
public class DrawController {
    private final MyModel model;

    /**
     * Constructor method for DrawController.
     * @param model the model that view will listen on and that data need to be passed to
     */
    public DrawController(MyModel model) {
        this.model = model;
    }

    /**
     * Method for controlling addListener method of class MyModel.
     * @param listener the view that wants to listen to / observe this model
     */
    public void controlAddListener(PropertyChangeListener listener) {
        model.addListener(listener);
    }

    /**
     * Method for controlling drawShape method of class MyModel.
     * @param x1 the horizontal coordinate of the starting point
     * @param y1 the ordinate of the starting point
     * @param x2 the horizontal coordinate of the ending point
     * @param y2 the ordinate of the ending point
     */
    public void controlDrawShape(double x1, double y1, double x2, double y2) {
        model.drawShape(x1, y1, x2, y2);
    }

    /**
     * Method for controlling operate method of class MyModel.
     * @param operation UNDO / REDO / CLEAR
     */
    public void controlOperate(String operation) {
        model.operate(operation);
    }

    /**
     * Method for controlling setShapeMode method of class MyModel.
     * @param shapeMode that indicates the name/mode of the shape
     */
    public void controlSetShapeMode(String shapeMode) {
        model.setShapeMode(shapeMode);
    }

    /**
     * Method for controlling setColor method of class MyModel.
     * @param color the color of the shape
     */
    public void controlSetColor(Color color) {
        model.setColor(color);
    }

    /**
     * Method for controlling setLockRatio method of class MyModel.
     * @param isLockRatio the state for locking the aspect ratio
     */
    public void controlSetLockRatio(boolean isLockRatio) {
        model.setLockRatio(isLockRatio);
    }

    /**
     * Method for controlling save method of class MyModel.
     * @param path the location of the document
     */
    public void controlSave(String path) {
        model.save(path);
    }

    /**
     * Method for controlling load method of class MyModel.
     * @param path the location of the document
     */
    public void controlLoad(String path) {
        model.load(path);
    }
}

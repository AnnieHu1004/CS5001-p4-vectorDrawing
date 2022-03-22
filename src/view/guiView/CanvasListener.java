package view.guiView;

import controller.DrawController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class for CanvasListener.
 */
public class CanvasListener implements MouseListener {
    private DrawController drawController;

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * Constructor method for class CanvasListener.
     * @param drawController the controller of the model that GUI view will listen on and that data need to be passed to
     */
    public CanvasListener(DrawController drawController) {
        this.drawController = drawController;
    }

    /**
     * Method for specifying the action when the mouse clicked on the canvas.
     * @param e the mouse clicked
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Method for specifying the action when the mouse pressed on the canvas.
     * In this case, when the mouse pressed, record the coordinates of the pressing point
     * @param e the mouse pressed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        System.out.println("x1 = " + x1 + ",y1 =" + y1);
    }

    /**
     * Method for specifying the action when the mouse released on the canvas.
     * In this case, when the mouse pressed, record the coordinates of the releasing point
     * then pass the data to the model
     * @param e the mouse pressed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        System.out.println("x2 = " + x2 + ",y2 =" + y2);
        drawController.controlDrawShape(x1, y1, x2, y2);
    }

    /**
     * Method for specifying the action when the mouse entered on the canvas.
     * @param e the mouse entered
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Method for specifying the action when the mouse exited on the canvas.
     * @param e the mouse exited
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}

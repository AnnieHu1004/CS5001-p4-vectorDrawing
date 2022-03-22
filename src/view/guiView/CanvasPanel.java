package view.guiView;

import controller.DrawController;
import model.MyShape;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Class for CanvasPanel.
 */
public class CanvasPanel extends JPanel implements PropertyChangeListener {
    private JPanel panel = new JPanel();
    private ArrayList<MyShape> newValue;

    /**
     * Constructor method for ColorPanel.
     * @param drawController the controller of the model that GUI view will listen on and that data need to be passed to
     */
    public CanvasPanel(DrawController drawController) {
        this.setFocusable(false); // set this panel non-focusable so the key Listener can listen to the whole frame
        this.setBackground(Color.WHITE);

        // add PropertyChangeListener to the canvas panel
        // so the canvas can update one the model has changed
        drawController.controlAddListener(this);
        initial();
    }

    /**
     * Method for initializing the frame of the canvas panel.
     */
    private void initial() {
        panel.setFocusable(false); // set the panel non-focusable so the key Listener can listen to the whole frame
        panel.setBackground(Color.WHITE); // distinguish the canvas from other panels
        panel.setPreferredSize(new Dimension(900, 500));
        this.add(panel);
    }

    /**
     * Method for specifying the action every time the property -shapeList change.
     * @param evt when the shapeList of MyModel changed
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        newValue = (ArrayList<MyShape>) evt.getNewValue();
        SwingUtilities.invokeLater(
                () -> paint(panel.getGraphics()));
    }

    /**
     * Method for drawing the shapes created by the user to the canvas.
     * @param g the paintbrush
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (newValue != null) {
            Graphics2D g2d = (Graphics2D) g;

            for (MyShape myShape: newValue) {
                g2d.setColor(myShape.getColor());
                if (myShape.getShapeMode().equals("CROSS")) {
                    // draw the diagonal cross
                    g2d.setStroke(new BasicStroke(40, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL)); // set the stroke looks like a rectangle
                    // draw two symmetrical lines up and down which looks the same as the diagonal cross
                    Line2D line = (Line2D) myShape.getShape();
                    g2d.draw(line);
                    g2d.draw(new Line2D.Double(line.getX1(), line.getY2(), line.getX2(), line.getY1())); // swap ordinates for symmetrical lines
                } else {
                    // draw other normal shapes
                    g2d.setStroke(new BasicStroke(1));
                    g2d.draw(myShape.getShape());
                }
            }
        }
    }
}

package view.guiView;

import controller.DrawController;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

/**
 * Class for ToolsPanel.
 */
public class ToolsPanel extends JPanel {
    private DrawController drawController;
    private JPanel panel;
    private String[] shapeButtons = {"LINE", "RECTANGLE", "ELLIPSE", "CROSS", "TRIANGLE", "HEXAGON"};
    private String shapeMode;

    /**
     * Constructor method for ToolsPanel.
     * @param drawController the controller of the model that GUI view will listen on and that data need to be passed to
     */
    public ToolsPanel(DrawController drawController) {
        this.setFocusable(false); // set this panel non-focusable so the key Listener can listen to the whole frame
        this.drawController = drawController;
        initial();
    }

    /**
     * Method for initializing the frame of the tool panel.
     */
    private void initial() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 5, 5));

        addShapeButtons();
        this.add(panel);
    }

    /**
     * Method for adding shape buttons to the panel.
     */
    private void addShapeButtons() {
        // add button for LINE, RECTANGLE, ELLIPSE, CROSS, TRIANGLE, HEXAGON
        for (String button : shapeButtons) {
            JButton shapeButton = new JButton(button);
            shapeButton.setPreferredSize(new Dimension(80, 50));
            shapeButton.setFocusable(false); // set the button non-focusable so the key Listener can listen to the whole frame
            shapeButton.setActionCommand(button);

            shapeButton.addActionListener(e -> {
                shapeMode = e.getActionCommand();
                drawController.controlSetShapeMode(shapeMode);
                System.out.println("Shape chosen: " + shapeMode);
            });
            panel.add(shapeButton);
        }
    }
}

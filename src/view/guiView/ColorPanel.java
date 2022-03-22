package view.guiView;

import controller.DrawController;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * Class for ColorPanel.
 */
public class ColorPanel extends JPanel {
    private DrawController drawController;

    private JLabel colorStatus;
    private JPanel existColor;
    private JButton chooseColor;
    private Color color = Color.BLACK; // default color

    /**
     * Constructor method for ColorPanel.
     * @param drawController the controller of the model that GUI view will listen on and that data need to be passed to
     */
    public ColorPanel(DrawController drawController) {
        this.setFocusable(false); // set this panel non-focusable so the key Listener can listen to the whole frame
        this.drawController = drawController;
        initial();
    }

    /**
     * Method for initializing the frame of the color panel.
     */
    private void initial() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        // show existing chosen color
        colorStatus = new JLabel("Color Status:");
        existColor = new JPanel();
        existColor.setFocusable(false);
        existColor.setBackground(Color.BLACK); // default color
        existColor.setPreferredSize(new Dimension(30, 30));

        // color chooser
        chooseColor = new JButton("Choose a Color");
        chooseColor.setFocusable(false);
        chooseColor.addActionListener(e -> {
            try {
                color = JColorChooser.showDialog(chooseColor, "Choose a color", color);
                existColor.setBackground(color); // update the latest chosen color
                drawController.controlSetColor(color); // set the color of the model
                System.out.println("Color chosen: " + color);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        this.add(colorStatus);
        this.add(existColor);
        this.add(chooseColor);
    }
}

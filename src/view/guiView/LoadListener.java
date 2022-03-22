package view.guiView;

import controller.DrawController;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class for LoadListener.
 */
public class LoadListener implements ActionListener {
    private DrawController drawController;

    /**
     * Constructor method for class LoadListener.
     * @param drawController the controller of the model that GUI view will listen on and that data need to be passed to
     */
    public LoadListener(DrawController drawController) {
        this.drawController = drawController;
    }

    /**
     * Method for specifying the action when the menu item-load clicked.
     * @param e the menu item-load clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load your drawing");
        int status = fileChooser.showOpenDialog(fileChooser);

        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getPath();
            drawController.controlLoad(path);
            System.out.println("You are loading file from: " + path);
        }
    }
}

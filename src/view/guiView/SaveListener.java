package view.guiView;

import controller.DrawController;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class for SaveListener.
 */
public class SaveListener implements ActionListener {
    private DrawController drawController;

    /**
     * Constructor method for class SaveListener.
     * @param drawController the controller of the model that GUI view will listen on and that data need to be passed to
     */
    public SaveListener(DrawController drawController) {
        this.drawController = drawController;
    }

    /**
     * Method for specifying the action when the menu item-save clicked.
     * @param e the menu item-save clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save your drawing");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("DrawEdit Document", "txt");
        fileChooser.setFileFilter(filter);
        int status = fileChooser.showSaveDialog(fileChooser);

        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getPath();
            drawController.controlSave(path);
            System.out.println("You are saving your drawing at " + path);
        }
    }
}

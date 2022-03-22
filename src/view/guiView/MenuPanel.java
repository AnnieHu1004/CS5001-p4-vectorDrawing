package view.guiView;

import controller.DrawController;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * Class for MenuPanel.
 */
public class MenuPanel extends JPanel {
    private DrawController drawController;
    private JMenuBar menuBar;
    private String editMode;

    private SaveListener saveListener;
    private LoadListener loadListener;

    /**
     * Constructor method for MenuPanel.
     * @param drawController the controller of the model that GUI view will listen on and that data need to be passed to
     */
    public MenuPanel(DrawController drawController) {
        this.setFocusable(false); // set this panel non-focusable so the key Listener can listen to the whole frame
        this.drawController = drawController;
        initial();
    }

    /**
     * Method for initializing the frame of the menu panel.
     */
    private void initial() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(1000, 50));

        JMenu file = new JMenu("FILE");
        file.setPreferredSize(new Dimension(100, 50));

        JMenuItem save = new JMenuItem("SAVE");
        JMenuItem load = new JMenuItem("LOAD");

        saveListener = new SaveListener(drawController);
        loadListener = new LoadListener(drawController);
        save.addActionListener(saveListener);
        load.addActionListener(loadListener);

        file.add(save);
        file.add(load);

        menuBar.add(file);
        addEditButtons();
        this.add(menuBar);
    }

    /**
     * Method for adding edit buttons to the panel.
     */
    private void addEditButtons() {
        String[] editButtons = {"UNDO", "REDO", "CLEAR"};

        // add button for UNDO, REDO, CLEAR
        for (String button : editButtons) {
            JButton editButton = new JButton(button);
            editButton.setFocusable(false);
            editButton.setActionCommand(button);

            editButton.addActionListener(e -> {
                editMode = e.getActionCommand();
                drawController.controlOperate(editMode);
                System.out.println("Edit mode chosen: " + editMode);
            });
            menuBar.add(editButton);
        }
    }
}

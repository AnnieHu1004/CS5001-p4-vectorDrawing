package view.guiView;

import controller.DrawController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class for KeyboardListener.
 */
public class KeyboardListener extends KeyAdapter {
    private DrawController drawController;

    /**
     * Constructor method for class KeyboardListener.
     * @param drawController the controller of the model that GUI view will listen on and that data need to be passed to
     */
    public KeyboardListener(DrawController drawController) {
        this.drawController = drawController;
        System.out.println("I'm listening on keyboard");
    }

    /**
     * Method for specifying the action when certain key is pressed.
     * In this case, when CONTROL is pressed, the aspect ratio is locked
     * @param e the key that is pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("PRESSING");
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            System.out.println("you are pressing: " + e.getKeyCode());
            drawController.controlSetLockRatio(true);
        }
    }

    /**
     * Method for specifying the action when certain key is released.
     * In this case, when CONTROL is released, the aspect ratio is unlocked
     * @param e the key that is released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            System.out.println("you are releasing: " + e.getKeyCode());
            drawController.controlSetLockRatio(false);
        }
    }
}

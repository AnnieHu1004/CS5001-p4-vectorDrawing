package view.guiView;

import controller.DrawController;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Class for the GUI view of vector drawing.
 */
public class VectorGUIView {
    protected static final int DEFAULT_FRAME_WIDTH = 1000;
    protected static final int DEFAULT_FRAME_HEIGHT = 600;

    private DrawController drawController;

    private JFrame mainFrame;
    private MenuPanel myMenu;
    private CanvasPanel myCanvas;
    private ToolsPanel myTools;
    private ColorPanel myColor;

    private CanvasListener canvasListener;
    private KeyboardListener keyboardListener;

    /**
     * Constructor method for class VectorGUIView.
     * @param drawController the controller of the model that GUI view will listen on and that data need to be passed to
     */
    public VectorGUIView(DrawController drawController) {

        this.drawController = drawController;
        initial();
    }

    /**
     * Method for initializing the frame of the GUI view.
     */
    private void initial() {
        mainFrame = new JFrame("Vector Drawing");

        mainFrame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        myMenu = new MenuPanel(drawController);
        myCanvas = new CanvasPanel(drawController);
        myTools = new ToolsPanel(drawController);
        myColor = new ColorPanel(drawController);

        mainFrame.add(myMenu, BorderLayout.NORTH);
        mainFrame.add(myTools, BorderLayout.WEST);
        mainFrame.add(myCanvas, BorderLayout.CENTER);
        mainFrame.add(myColor, BorderLayout.SOUTH);

        // add mouseListener to the canvas to obtain mouse data
//        canvasListener = new CanvasListener(model, drawController);
        canvasListener = new CanvasListener(drawController);
        myCanvas.addMouseListener(canvasListener);

        // add keyListener to the main frame to obtain key data
        keyboardListener = new KeyboardListener(drawController);
        mainFrame.addKeyListener(keyboardListener);
        mainFrame.setFocusable(true); // set the main frame to get focus

        mainFrame.setVisible(true);
        mainFrame.pack();
    }
}

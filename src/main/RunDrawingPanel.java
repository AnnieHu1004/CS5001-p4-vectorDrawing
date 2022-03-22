package main;

import controller.DrawController;
import model.MyModel;
import view.guiView.VectorGUIView;

/**
 * CS5001-p4-vectorDrawing.
 * @author 210005313
 * @version 1.0
 */
public class RunDrawingPanel {
    /**
     * The main method for running the vector drawing panel.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        MyModel myModel = new MyModel();
        DrawController drawController = new DrawController(myModel);
        VectorGUIView vectorGUIView = new VectorGUIView(drawController);
    }
}

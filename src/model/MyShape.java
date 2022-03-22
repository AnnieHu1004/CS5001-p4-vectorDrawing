package model;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * Class for MyShape.
 * Contains methods for creating shapes and getting different attributes / parameters
 */
public class MyShape implements Serializable {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    private Shape shape;
    private Color color;
    private String shapeMode;
    private boolean isLockRatio;


    /**
     * Constructor method for class MyShape.
     * Loading all parameters of the certain shape
     * @param shapeMode   that indicates the shape of the graph
     * @param x1          the horizontal coordinate of the starting point
     * @param y1          the ordinate of the starting point
     * @param x2          the horizontal coordinate of the ending point
     * @param y2          the ordinate of the ending point
     * @param color       the color of the shape
     * @param isLockRatio that indicates whether the aspect ratio is locked or not
     */
    public MyShape(String shapeMode, double x1, double y1, double x2, double y2, Color color, boolean isLockRatio) {
        // to ensure that the drawing can be drawn from all directions
        // the coordinates need to be adjusted
        if (shapeMode.equals("LINE") || shapeMode.equals("CROSS")) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        } else {
            // adjust the coordinates
            // to make sure the coordinates are from left to right and top to bottom
            this.x1 = Math.min(x1, x2);
            this.y1 = Math.min(y1, y2);
            this.x2 = Math.max(x1, x2);
            this.y2 = Math.max(y1, y2);
        }
        this.isLockRatio = isLockRatio;
        this.shapeMode = shapeMode;
        this.shape = createShape(shapeMode);
        this.color = color;
    }

    /**
     * Method for creating different shapes.
     * @param shapeMode that indicates the shape of the graph
     * @return an instance of the class Shape
     */
    private Shape createShape(String shapeMode) {
        // coordinates for the polygon
        int[] xPoints;
        int[] yPoints;

        int x1P = (int) x1;
        int y1P = (int) y1;

        int width = (int) calWidth();
        int height = (int) calHeight();

        // lock the aspect ratio for drawing squares, circles etc.
        int lockRatioSize = Math.max(width, height);
        if (isLockRatio) {
            width = lockRatioSize;
            height = lockRatioSize;
        }

        Shape shapeCreated;

        switch (shapeMode) {
            case "LINE":
                shapeCreated = new Line2D.Double(x1, y1, x2, y2);
                break;
            case "RECTANGLE":
                shapeCreated = new Rectangle2D.Double(x1, y1, width, height);
                break;
            case "ELLIPSE":
                shapeCreated = new Ellipse2D.Double(x1, y1, width, height);
                break;
            case "CROSS":
                shapeCreated = new Line2D.Double(x1, y1, x1 + width, y1 + height);
                break;
            case "TRIANGLE":
                xPoints = new int[]{x1P, (2 * x1P + width) / 2, x1P + width};
                yPoints = new int[]{y1P + height, y1P, y1P + height};
                shapeCreated = new Polygon(xPoints, yPoints, xPoints.length);
                break;
            case "HEXAGON":
                xPoints = new int[]{x1P + width / 4, x1P + width * 3 / 4, x1P + width, x1P + width * 3 / 4, x1P + width / 4, x1P};
                yPoints = new int[]{y1P, y1P, y1P + height / 2, y1P + height, y1P + height, y1P + height / 2};
                shapeCreated = new Polygon(xPoints, yPoints, xPoints.length);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + shapeMode);
        }
        return shapeCreated;
    }

    /**
     * Method for calculating the width of two coordinates.
     * @return  the width of two coordinates
     */
    private double calWidth() {
        return x2 - x1;
    }

    /**
     * Method for calculating the height of two coordinates.
     * @return the height of two coordinates
     */
    private double calHeight() {
        return y2 - y1;
    }

    // methods for testing MyShape

    /**
     * Method for getting the shape.
     * @return the shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Method for getting the color of the shape.
     * @return the color of the shape
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method for getting the name of the shape.
     * @return the name of the shape
     */
    public String getShapeMode() {
        return shapeMode;
    }
}

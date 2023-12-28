package org.example.imageeditor.tools;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.imageeditor.Tool;

/**
 * The Zoom class implements the Tool interface and represents a zoom tool for an image editor.
 * It provides functionality to zoom in and out of an image and a canvas.
 * The zoom operation is performed by dragging the mouse.
 */
public class Zoom implements Tool{
    private final Button button;
    private final ImageView imageView;
    private final Canvas canvas;
    private double initX;
    private double initY;
    private double initScaleX;
    private double initScaleY;

    /**
     * Constructs a new Zoom object with the specified image view, canvas, and button.
     * @param imageView the image view to be zoomed
     * @param canvas the canvas to be zoomed
     * @param button the button associated with this tool
     */
    public Zoom(ImageView imageView, Canvas canvas, Button button){
        this.imageView = imageView;
        this.button = button;
        this.canvas = canvas;
    }

    /**
     * Activates the zoom tool.
     * The cursor is changed to a crosshair and the button is highlighted.
     * Mouse press and drag events are set up to perform the zoom operation.
     */
    @Override
    public void activate(){
        imageView.getScene().setCursor(Cursor.CROSSHAIR);
        button.getStyleClass().add("selected-tool");
        canvas.setOnMousePressed(mouseEvent ->{
            this.initX = mouseEvent.getSceneX();
            this.initY = mouseEvent.getSceneY();
            this.initScaleX = imageView.getScaleX();
            this.initScaleY = imageView.getScaleY();
        });
        canvas.setOnMouseDragged(mouseEvent ->{
            double deltaX = mouseEvent.getSceneX() - initX;
            double deltaY = mouseEvent.getSceneY() - initY;
            double distance = deltaX + deltaY;
            double scaleFactor = distance / 1000;
            imageView.setScaleX(Math.max((initScaleX + scaleFactor), 0.1));
            imageView.setScaleY(Math.max((initScaleY + scaleFactor), 0.1));
            canvas.setScaleX(Math.max((initScaleX + scaleFactor), 0.1));
            canvas.setScaleY(Math.max((initScaleY + scaleFactor), 0.1));
        });

    }

    /**
     * Deactivates the zoom tool.
     * The cursor is changed back to the default and the button is unhighlighted.
     * Mouse press and drag events are removed.
     */
    @Override
    public void deactivate(){
        imageView.getScene().setCursor(Cursor.DEFAULT);
        imageView.setOnMousePressed(null);
        imageView.setOnMouseDragged(null);
        canvas.getScene().setCursor(Cursor.DEFAULT);
        canvas.setOnMousePressed(null);
        canvas.setOnMouseDragged(null);
        button.getStyleClass().remove("selected-tool");
    }

    /**
     * Returns the side menu associated with the zoom tool.
     * In this case, there is no side menu, so null is returned.
     * @return null, because there is no side menu for the zoom tool
     */
    @Override
    public VBox getSideMenu(){
        return null;
    }
}

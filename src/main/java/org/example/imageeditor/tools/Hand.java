package org.example.imageeditor.tools;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.imageeditor.Tool;

/**
 * The Hand class implements the Tool interface and represents a hand tool for an image editor.
 * It provides functionality to move an image and a canvas around within their containers.
 * The moving operation is performed by pressing and dragging the mouse.
 */
public class Hand implements Tool {
    private final Button button;
    private final ImageView imageView;
    private final Canvas canvas;
    private double initX;
    private double initY;


    /**
     * Constructs a new Hand object with the specified image view, canvas, and button.
     * @param imageView the image view to be moved
     * @param canvas the canvas to be moved
     * @param button the button associated with this tool
     */
    public Hand(ImageView imageView, Canvas canvas, Button button) {
        this.imageView = imageView;
        this.button = button;
        this.canvas = canvas;
    }

    /**
     * Activates the hand tool.
     * The cursor is changed to a hand and the button is highlighted.
     * Mouse press and drag events are set up to perform the moving operation.
     */
    @Override
    public void activate(){
        canvas.getScene().setCursor(Cursor.HAND);
        button.getStyleClass().add("selected-tool");
        canvas.setOnMousePressed(mouseEvent -> {
            this.initX = mouseEvent.getSceneX() - imageView.getTranslateX();
            this.initY = mouseEvent.getSceneY() - imageView.getTranslateY();
        });
        canvas.setOnMouseDragged(mouseEvent -> {
            imageView.setTranslateX(mouseEvent.getSceneX() - initX);
            imageView.setTranslateY(mouseEvent.getSceneY() - initY);
            canvas.setTranslateX(mouseEvent.getSceneX() - initX);
            canvas.setTranslateY(mouseEvent.getSceneY() - initY);
        });
    }

    /**
     * Deactivates the hand tool.
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
     * Returns the side menu associated with the hand tool.
     * In this case, there is no side menu, so null is returned.
     * @return null, because there is no side menu for the hand tool
     */
    @Override
    public VBox getSideMenu(){
        return null;
    }
}

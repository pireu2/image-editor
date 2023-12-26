package org.example.imageeditor.tools;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.imageeditor.Tool;

public class Hand implements Tool {
    private final Button button;
    private final ImageView imageView;
    private final Canvas canvas;
    private double initX;
    private double initY;

    public Hand(ImageView imageView, Canvas canvas, Button button) {
        this.imageView = imageView;
        this.button = button;
        this.canvas = canvas;
    }

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

    @Override
    public VBox getSideMenu(){
        return null;
    }
}

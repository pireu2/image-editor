package org.example.imageeditor.tools;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.example.imageeditor.Tool;

public class Hand implements Tool {
    private Button button;
    private ImageView imageView;
    private double initX;
    private double initY;

    public Hand(ImageView imageView, Button button) {
        this.imageView = imageView;
        this.button = button;
    }
    @Override
    public void activate(){
        imageView.getScene().setCursor(Cursor.HAND);
        button.getStyleClass().add("selected-tool");
        imageView.setOnMousePressed(mouseEvent -> {
            this.initX = mouseEvent.getSceneX() - imageView.getTranslateX();
            this.initY = mouseEvent.getSceneY() - imageView.getTranslateY();
        });
        imageView.setOnMouseDragged(mouseEvent -> {
            imageView.setTranslateX(mouseEvent.getSceneX() - initX);
            imageView.setTranslateY(mouseEvent.getSceneY() - initY);
        });
    }

    @Override
    public void deactivate(){
        imageView.getScene().setCursor(Cursor.DEFAULT);
        imageView.setOnMousePressed(null);
        imageView.setOnMouseDragged(null);
        button.getStyleClass().remove("selected-tool");
    }
}

package org.example.imageeditor.tools;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.example.imageeditor.Tool;
public class Zoom implements Tool{
    private Button button;
    private ImageView imageView;
    private Canvas canvas;
    private double initX;
    private double initY;
    private double initScaleX;
    private double initScaleY;

    public Zoom(ImageView imageView, Canvas canvas, Button button){
        this.imageView = imageView;
        this.button = button;
        this.canvas = canvas;
    }

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
}

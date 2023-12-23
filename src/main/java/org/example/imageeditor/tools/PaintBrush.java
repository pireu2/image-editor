package org.example.imageeditor.tools;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.example.imageeditor.Tool;
public class PaintBrush implements Tool{
    private Button button;
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    public PaintBrush(Canvas canvas, Button button){
        this.canvas = canvas;
        this.button = button;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    @Override
    public void activate(){
        System.out.println("Paint brush activated");
        canvas.getScene().setCursor(Cursor.CROSSHAIR);
        button.getStyleClass().add("selected-tool");
        canvas.setOnMousePressed(mouseEvent -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(mouseEvent.getX(), mouseEvent.getY());
            graphicsContext.stroke();
        });
        canvas.setOnMouseDragged(mouseEvent -> {
            System.out.print(mouseEvent.getX());
            System.out.print(" ");
            System.out.println(mouseEvent.getY());
            graphicsContext.lineTo(mouseEvent.getX(), mouseEvent.getY());
            graphicsContext.stroke();
        });
    }

    @Override
    public void deactivate() {
        System.out.println("Paint brush deactivated");
        canvas.getScene().setCursor(Cursor.DEFAULT);
        canvas.setOnMousePressed(null);
        canvas.setOnMouseDragged(null);
        button.getStyleClass().remove("selected-tool");
    }

}

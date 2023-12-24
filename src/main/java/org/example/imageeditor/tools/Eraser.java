package org.example.imageeditor.tools;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.imageeditor.Tool;

public class Eraser implements Tool {
    private Button button;
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    public Eraser(Canvas canvas, Button button){
        this.canvas = canvas;
        this.button = button;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    @Override
    public void activate() {
        canvas.getScene().setCursor(Cursor.CROSSHAIR);
        button.getStyleClass().add("selected-tool");

        canvas.setOnMouseDragged(mouseEvent -> {
            double size = graphicsContext.getLineWidth();
            graphicsContext.clearRect(mouseEvent.getX() - size / 2, mouseEvent.getY() - size / 2, size, size);
        });
    }

    @Override
    public void deactivate() {
        canvas.getScene().setCursor(Cursor.DEFAULT);
        canvas.setOnMousePressed(null);
        canvas.setOnMouseDragged(null);
        button.getStyleClass().remove("selected-tool");
    }

    @Override
    public VBox getSideMenu() {
        VBox spacing = new VBox();
        VBox brushMenu = new VBox();
        Slider slider = new Slider();
        HBox eraserLabel = new HBox();
        Label eraserText = new Label("Size: ");
        TextField eraserInput = new TextField();

        brushMenu.setSpacing(5.0);
        brushMenu.getStyleClass().add("side-menu");
        spacing.getStyleClass().add("spacing");

        eraserInput.setMaxWidth(100);
        eraserInput.setText("1.0");
        eraserInput.setOnAction(event ->{
            try {
                double newValue = Double.parseDouble(eraserInput.getText());
                slider.setValue(Math.min(newValue,1000));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        });
        eraserLabel.getChildren().addAll(eraserText, eraserInput);
        eraserLabel.setAlignment(Pos.CENTER);

        slider.setMax(1000.0);
        slider.setValue(1.0);
        slider.valueProperty().addListener((observable, oldValue, newValue) ->{
            graphicsContext.setLineWidth(newValue.doubleValue());
            eraserInput.setText(String.format("%.2f", newValue.doubleValue()));
        });
        brushMenu.getChildren().addAll(eraserLabel, slider);
        spacing.getChildren().add(brushMenu);
        return spacing;
    }
}

package org.example.imageeditor.tools;


import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.imageeditor.Tool;

public class PaintBrush implements Tool{
    private final Button button;
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    public PaintBrush(Canvas canvas, Button button){
        this.canvas = canvas;
        this.button = button;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    @Override
    public void activate(){
        canvas.getScene().setCursor(Cursor.CROSSHAIR);
        button.getStyleClass().add("selected-tool");

        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(1.0);

        canvas.setOnMousePressed(mouseEvent -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(mouseEvent.getX(), mouseEvent.getY());
            graphicsContext.stroke();
        });
        canvas.setOnMouseDragged(mouseEvent -> {
            double size = graphicsContext.getLineWidth();
            graphicsContext.setFill(graphicsContext.getStroke());
            graphicsContext.fillOval(mouseEvent.getX() - size / 2, mouseEvent.getY() - size / 2, size, size);
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
    public VBox getSideMenu(){
        VBox spacing = new VBox();
        VBox brushMenu = new VBox();
        Label colorLabel = new Label("Color:");
        ColorPicker colorPicker = new ColorPicker();
        Slider slider = new Slider();
        HBox strokeLabel = new HBox();
        Label strokeText = new Label("Size: ");
        TextField strokeInput = new TextField();


        brushMenu.setSpacing(5.0);
        brushMenu.getStyleClass().add("side-menu");
        spacing.getStyleClass().add("spacing");



        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(event ->{
            Color newColor = colorPicker.getValue();
            graphicsContext.setStroke(newColor);
        });
        strokeInput.setMaxWidth(100);
        strokeInput.setText("1.0");
        strokeInput.setOnAction(event ->{
            try {
                double newValue = Double.parseDouble(strokeInput.getText());
                slider.setValue(Math.min(newValue,300));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        });
        strokeLabel.getChildren().addAll(strokeText, strokeInput);
        strokeLabel.setAlignment(Pos.CENTER);


        slider.setMax(300);
        slider.setValue(1.0);
        slider.valueProperty().addListener((observable, oldValue, newValue) ->{
            graphicsContext.setLineWidth(newValue.doubleValue());
            strokeInput.setText(String.format("%.2f", newValue.doubleValue()));
        });
        brushMenu.getChildren().addAll(colorLabel, colorPicker, strokeLabel, slider);
        spacing.getChildren().add(brushMenu);
        return spacing;
    }

}

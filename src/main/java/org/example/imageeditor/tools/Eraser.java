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

/**
 * The Eraser class implements the Tool interface and represents an eraser tool for an image editor.
 * It provides functionality to erase parts of an image on a canvas.
 * The erasing operation is performed by dragging the mouse.
 */
public class Eraser implements Tool {
    private final Button button;
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    /**
     * Constructs a new Eraser object with the specified canvas and button.
     * @param canvas the canvas on which the erasing will be done
     * @param button the button associated with this tool
     */
    public Eraser(Canvas canvas, Button button){
        this.canvas = canvas;
        this.button = button;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    /**
     * Activates the eraser tool.
     * The cursor is changed to a crosshair and the button is highlighted.
     * Mouse drag events are set up to perform the erasing operation.
     */
    @Override
    public void activate() {
        canvas.getScene().setCursor(Cursor.CROSSHAIR);
        button.getStyleClass().add("selected-tool");

        graphicsContext.setLineWidth(1.0);

        canvas.setOnMouseDragged(mouseEvent -> {
            double size = graphicsContext.getLineWidth();
            graphicsContext.clearRect(mouseEvent.getX() - size / 2, mouseEvent.getY() - size / 2, size, size);
        });
    }

    /**
     * Deactivates the eraser tool.
     * The cursor is changed back to the default and the button is unhighlighted.
     * Mouse press and drag events are removed.
     */
    @Override
    public void deactivate() {
        canvas.getScene().setCursor(Cursor.DEFAULT);
        canvas.setOnMousePressed(null);
        canvas.setOnMouseDragged(null);
        button.getStyleClass().remove("selected-tool");
    }

    /**
     * Returns the side menu associated with the eraser tool.
     * The side menu allows the user to choose the size of the eraser.
     * @return a VBox containing the side menu for the eraser tool
     */
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
                slider.setValue(Math.min(newValue,300));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        });
        eraserLabel.getChildren().addAll(eraserText, eraserInput);
        eraserLabel.setAlignment(Pos.CENTER);

        slider.setMax(300);
        slider.setValue(1.0);
        slider.valueProperty().addListener((observable, oldValue, newValue) ->{
            graphicsContext.setLineWidth(newValue.doubleValue());
            eraserInput.setText(String.format("%.2f", newValue.doubleValue()));
        });
        brushMenu.getChildren().addAll(eraserLabel, slider);
        brushMenu.setAlignment(Pos.CENTER);
        spacing.getChildren().add(brushMenu);
        return spacing;
    }
}

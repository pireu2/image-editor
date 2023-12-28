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

/**
 * The PaintBrush class implements the Tool interface and represents a paint brush tool for an image editor.
 * It provides functionality to draw on an image and a canvas.
 * The drawing operation is performed by pressing and dragging the mouse.
 */
public class PaintBrush implements Tool{
    private final Button button;
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    /**
     * Constructs a new PaintBrush object with the specified canvas and button.
     * @param canvas the canvas on which the drawing will be done
     * @param button the button associated with this tool
     */
    public PaintBrush(Canvas canvas, Button button){
        this.canvas = canvas;
        this.button = button;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    /**
     * Activates the paint brush tool.
     * The cursor is changed to a crosshair and the button is highlighted.
     * Mouse press and drag events are set up to perform the drawing operation.
     */
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

    /**
     * Deactivates the paint brush tool.
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
     * Returns the side menu associated with the paint brush tool.
     * The side menu allows the user to choose the color and size of the brush.
     * @return a VBox containing the side menu for the paint brush tool
     */
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
        brushMenu.setAlignment(Pos.CENTER);
        spacing.getChildren().add(brushMenu);
        return spacing;
    }

}

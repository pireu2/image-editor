package org.example.imageeditor.tools;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.example.imageeditor.Tool;

/**
 * The Text class implements the Tool interface and represents a text tool for an image editor.
 * It provides functionality to add text to an image and a canvas.
 * The text operation is performed by clicking the mouse.
 */
public class Text implements Tool {
    private String fontName = "Arial";
    private double fontSize = 12;
    private Color fontColor = Color.BLACK;
    private String text = "";
    final private Button button;
    final private Canvas canvas;
    private final GraphicsContext graphicsContext;

    /**
     * Constructs a new Text object with the specified canvas and button.
     * @param canvas the canvas to which the text will be added
     * @param button the button associated with this tool
     */
    public Text(Canvas canvas, Button button){
        this.canvas = canvas;
        this.button = button;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    /**
     * Activates the text tool.
     * The cursor is changed to a crosshair and the button is highlighted.
     * Mouse press events are set up to add the text to the canvas.
     */
    @Override
    public void activate() {
        canvas.getScene().setCursor(Cursor.CROSSHAIR);
        button.getStyleClass().add("selected-tool");

        graphicsContext.setFont(new Font(fontName, fontSize));
        graphicsContext.setFill(fontColor);
        canvas.setOnMousePressed(mouseEvent -> graphicsContext.fillText(text, mouseEvent.getX(), mouseEvent.getY()));
    }

    /**
     * Deactivates the text tool.
     * The cursor is changed back to the default and the button is unhighlighted.
     * Mouse press events are removed.
     */
    @Override
    public void deactivate() {
        canvas.getScene().setCursor(Cursor.DEFAULT);
        canvas.setOnMousePressed(null);
        button.getStyleClass().remove("selected-tool");
    }

    /**
     * Returns the side menu associated with the text tool.
     * The side menu allows the user to choose the font, size, and color of the text.
     * @return a VBox containing the side menu for the text tool
     */
    @Override
    public VBox getSideMenu(){
        VBox spacing = new VBox();
        VBox fontMenu = new VBox();

        fontMenu.setSpacing(5.0);
        fontMenu.getStyleClass().add("side-menu");
        spacing.getStyleClass().add("spacing");

        ComboBox<String> fontPicker = new ComboBox<>();
        fontPicker.getItems().addAll("Arial", "Times New Roman", "Verdana");
        fontPicker.setValue(fontName);
        fontPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            fontName = newValue;
            graphicsContext.setFont(new Font(fontName, fontSize));
        });


        Slider sizeSlider = new Slider();
        HBox sizeLabel = new HBox();
        Label sizeText = new Label("Size: ");
        TextField sizeInput = new TextField();

        sizeInput.setMaxWidth(100);
        sizeInput.setText("12");
        sizeInput.setOnAction(event ->{
            try {
                int newValue = Integer.parseInt(sizeInput.getText());
                sizeSlider.setValue(Math.min(newValue,1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        });
        sizeLabel.getChildren().addAll(sizeText, sizeInput);
        sizeLabel.setAlignment(Pos.CENTER);


        sizeSlider.setMax(100);
        sizeSlider.setMin(1);
        sizeSlider.setValue(1);
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) ->{
            fontSize = newValue.intValue();
            graphicsContext.setFont(new Font(fontName, fontSize));
            sizeInput.setText(String.valueOf(newValue.intValue()));
        });

        ColorPicker colorPicker = new ColorPicker(fontColor);
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            fontColor = newValue;
            graphicsContext.setFill(fontColor);
        });

        Label colorLabel = new Label("Color:");
        Label fontLabel = new Label("Font:");
        Label textLabel = new Label("Text:");
        TextField textInput = new TextField();
        textInput.textProperty().addListener((observable, oldValue, newValue) -> text = newValue);

        fontMenu.getChildren().addAll(textLabel, textInput, fontLabel, fontPicker,sizeLabel, sizeSlider,colorLabel, colorPicker);
        fontMenu.setAlignment(Pos.CENTER);

        spacing.getChildren().add(fontMenu);
        return spacing;
    }
}

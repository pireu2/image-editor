package org.example.imageeditor.tools;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.imageeditor.Tool;

/**
 * The CustomFilter class implements the Tool interface and represents a custom filter tool for an image editor.
 * It provides functionality to adjust the saturation, hue, contrast, and brightness of an image.
 * The adjustments are performed by moving sliders in the side menu.
 */
public class CustomFilter implements Tool {
    private final ImageView imageView;
    private final Button button;
    private final ColorAdjust colorAdjust = new ColorAdjust();

    /**
     * Constructs a new CustomFilter object with the specified image view and button.
     * @param imageView the image view to be adjusted
     * @param button the button associated with this tool
     */
    public CustomFilter(ImageView imageView, Button button){
        this.button = button;
        this.imageView = imageView;
    }

    /**
     * Activates the custom filter tool.
     * The button is highlighted and the color adjust effect is applied to the image view.
     */
    @Override
    public void activate() {
        button.getStyleClass().add("selected-tool");
        imageView.setEffect(colorAdjust);
        imageView.setEffect(colorAdjust);
    }

    /**
     * Deactivates the custom filter tool.
     * The button is unhighlighted.
     */
    @Override
    public void deactivate() {
        button.getStyleClass().remove("selected-tool");
    }

    /**
     * Returns the side menu associated with the custom filter tool.
     * The side menu allows the user to adjust the saturation, hue, contrast, and brightness of the image.
     * @return a VBox containing the side menu for the custom filter tool
     */
    @Override
    public VBox getSideMenu() {
        VBox spacing = new VBox();
        VBox customFilterMenu = new VBox();

        customFilterMenu.setSpacing(5.0);
        customFilterMenu.getStyleClass().add("side-menu");
        spacing.getStyleClass().add("spacing");

        Slider saturationSlider = new Slider();
        HBox saturationLabel = new HBox();
        Label saturationText = new Label("Saturation: ");
        TextField saturationInput = new TextField();

        saturationInput.setMaxWidth(100);
        saturationInput.setText("1.0");
        saturationInput.setOnAction(event ->{
            try {
                double newValue = Double.parseDouble(saturationInput.getText());
                saturationSlider.setValue(Math.min(newValue,1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        });
        saturationLabel.getChildren().addAll(saturationText, saturationInput);
        saturationLabel.setAlignment(Pos.CENTER);


        saturationSlider.setMax(1);
        saturationSlider.setMin(-1);
        saturationSlider.setValue(0);
        saturationSlider.valueProperty().addListener((observable, oldValue, newValue) ->{
            colorAdjust.setSaturation(newValue.doubleValue());
            saturationInput.setText(String.format("%.2f", newValue.doubleValue()));
        });

        Slider hueSlider = new Slider();
        HBox hueLabel = new HBox();
        Label hueText = new Label("Hue: ");
        TextField hueInput = new TextField();

        hueInput.setMaxWidth(100);
        hueInput.setText("1.0");
        hueInput.setOnAction(event ->{
            try {
                double newValue = Double.parseDouble(hueInput.getText());
                hueSlider.setValue(Math.min(newValue,1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        });
        hueLabel.getChildren().addAll(hueText, hueInput);
        hueLabel.setAlignment(Pos.CENTER);


        hueSlider.setMax(1);
        hueSlider.setMin(-1);
        hueSlider.setValue(0);
        hueSlider.valueProperty().addListener((observable, oldValue, newValue) ->{
            colorAdjust.setHue(newValue.doubleValue());
            hueInput.setText(String.format("%.2f", newValue.doubleValue()));
        });

        Slider contrastSlider = new Slider();
        HBox contrastLabel = new HBox();
        Label contrastText = new Label("Contrast: ");
        TextField contrastInput = new TextField();

        contrastInput.setMaxWidth(100);
        contrastInput.setText("1.0");
        contrastInput.setOnAction(event ->{
            try {
                double newValue = Double.parseDouble(contrastInput.getText());
                contrastSlider.setValue(Math.min(newValue,1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        });
        contrastLabel.getChildren().addAll(contrastText, contrastInput);
        contrastLabel.setAlignment(Pos.CENTER);


        contrastSlider.setMax(1);
        contrastSlider.setMin(-1);
        contrastSlider.setValue(0);
        contrastSlider.valueProperty().addListener((observable, oldValue, newValue) ->{
            colorAdjust.setContrast(newValue.doubleValue());
            contrastInput.setText(String.format("%.2f", newValue.doubleValue()));
        });

        Slider brightnessSlider = new Slider();
        HBox brightnessLabel = new HBox();
        Label brightnessText = new Label("Brightness: ");
        TextField brightnessInput = new TextField();

        brightnessInput.setMaxWidth(100);
        brightnessInput.setText("1.0");
        brightnessInput.setOnAction(event ->{
            try {
                double newValue = Double.parseDouble(brightnessInput.getText());
                brightnessSlider.setValue(Math.min(newValue,1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        });
        brightnessLabel.getChildren().addAll(brightnessText, brightnessInput);
        brightnessLabel.setAlignment(Pos.CENTER);


        brightnessSlider.setMax(1);
        brightnessSlider.setMin(-1);
        brightnessSlider.setValue(0);
        brightnessSlider.valueProperty().addListener((observable, oldValue, newValue) ->{
            colorAdjust.setBrightness(newValue.doubleValue());
            brightnessInput.setText(String.format("%.2f", newValue.doubleValue()));
        });

        Button reset = new Button("Reset");
        reset.setOnAction(event ->{
            saturationSlider.setValue(0);
            hueSlider.setValue(0);
            contrastSlider.setValue(0);
            brightnessSlider.setValue(0);
        });
        VBox resetButtonContainer = new VBox(reset);
        resetButtonContainer.setAlignment(Pos.CENTER);

        customFilterMenu.getChildren().addAll(saturationLabel, saturationSlider,
                hueLabel, hueSlider, contrastLabel, contrastSlider, brightnessLabel,
                brightnessSlider, resetButtonContainer);
        customFilterMenu.setAlignment(Pos.CENTER);

        spacing.getChildren().add(customFilterMenu);
        return spacing;
    }
}

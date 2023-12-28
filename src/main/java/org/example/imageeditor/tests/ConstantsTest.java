package org.example.imageeditor.tests;

import javafx.scene.image.Image;
import org.example.imageeditor.util.Constants;

import java.util.Objects;

public class ConstantsTest {

    public void testOpenImages() {
        try {
            Image applicationIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.APPLICATION_ICON_PATH)));
            Image handIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.HAND_ICON_PATH)));
            Image zoomIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.ZOOM_ICON_PATH)));
            Image paintBrushIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.PAINT_BRUSH_ICON_PATH)));
            Image eraserIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.ERASER_ICON_PATH)));
            Image customFilterIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.CUSTOM_FILTER_ICON_PATH)));
            Image textIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.TEXT_ICON_PATH)));

            System.out.println("Images are correctly opened");
        } catch (Exception e) {
            System.out.println("Failed to open images");
        }
    }

    public static void main(String[] args) {
        ConstantsTest test = new ConstantsTest();
        test.testOpenImages();
    }
}
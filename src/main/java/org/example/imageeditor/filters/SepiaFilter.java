package org.example.imageeditor.filters;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.ImageView;

/**
 * The SepiaFilter class extends the AbstractFilter class and represents a sepia filter for an image editor.
 * It provides functionality to apply a sepia tone effect to an image and a canvas.
 */
public class SepiaFilter extends AbstractFilter{

    /**
     * Applies the sepia filter to the given image view and canvas.
     * The specific behavior depends on the implementation.
     * @param imageView the image view to which the filter will be applied.
     * @param canvas the canvas to which the filter will be applied.
     */
    @Override
    public void apply(ImageView imageView, Canvas canvas){
        SepiaTone sepiaTone = new SepiaTone();
        imageView.setEffect(sepiaTone);
        canvas.setEffect(sepiaTone);
    }
}

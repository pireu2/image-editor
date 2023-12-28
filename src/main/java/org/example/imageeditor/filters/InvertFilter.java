package org.example.imageeditor.filters;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

/**
 * The InvertFilter class extends the AbstractFilter class and represents an invert filter for an image editor.
 * It provides functionality to invert the colors of an image and a canvas.
 */
public class InvertFilter extends AbstractFilter{

    /**
     * Applies the invert filter to the given image view and canvas.
     * The specific behavior depends on the implementation.
     * @param imageView the image view to which the filter will be applied.
     * @param canvas the canvas to which the filter will be applied.
     */
    @Override
    public void apply(ImageView imageView, Canvas canvas) {
        colorAdjust.setHue(0.5);
        super.apply(imageView, canvas);
    }
}

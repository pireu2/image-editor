package org.example.imageeditor.filters;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

/**
 * The GrayscaleFilter class extends the AbstractFilter class and represents a grayscale filter for an image editor.
 * It provides functionality to apply a grayscale effect to an image and a canvas.
 */
public class GrayscaleFilter extends AbstractFilter{

    /**
     * Applies the grayscale filter to the given image view and canvas.
     * The specific behavior depends on the implementation.
     * @param imageView the image view to which the filter will be applied.
     * @param canvas the canvas to which the filter will be applied.
     */
    @Override
    public void apply(ImageView imageView, Canvas canvas) {
        colorAdjust.setSaturation(-1);
        super.apply(imageView, canvas);
    }
}

package org.example.imageeditor.filters;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import org.example.imageeditor.Filter;

/**
 * The AbstractFilter class implements the Filter interface and serves as a base class for other filter classes in the image editor.
 * It provides a basic implementation of the apply method, which applies a color adjust effect to an image view and a canvas.
 * It also provides a static method to deactivate the effect.
 */
public abstract class AbstractFilter implements Filter {
    ColorAdjust colorAdjust = new ColorAdjust();

    /**
     * Applies the color adjust effect to the given image view and canvas.
     * @param imageView the image view to which the effect will be applied.
     * @param canvas the canvas to which the effect will be applied.
     */
    @Override
    public void apply(ImageView imageView, Canvas canvas) {
        imageView.setEffect(colorAdjust);
        canvas.setEffect(colorAdjust);
    }

    /**
     * Deactivates the effect on the given image view and canvas.
     * @param imageView the image view from which the effect will be removed.
     * @param canvas the canvas from which the effect will be removed.
     */
    static public void deactivate(ImageView imageView, Canvas canvas) {
        imageView.setEffect(null);
        canvas.setEffect(null);
    }
}

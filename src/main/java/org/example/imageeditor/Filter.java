package org.example.imageeditor;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

/**
 * The Filter interface represents a filter that can be applied to an image.
 * It has a single method that applies the filter to an image view and a canvas.
 */
public interface Filter {

    /**
     * Applies the filter to the given image view and canvas.
     * The specific behavior depends on the implementation.
     * @param imageView the image view to which the filter will be applied.
     * @param canvas the canvas to which the filter will be applied.
     */
    void apply(ImageView imageView, Canvas canvas);
}

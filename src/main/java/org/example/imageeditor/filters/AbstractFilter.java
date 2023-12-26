package org.example.imageeditor.filters;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import org.example.imageeditor.Filter;

public abstract class AbstractFilter implements Filter {
    ColorAdjust colorAdjust = new ColorAdjust();
    @Override
    public void apply(ImageView imageView, Canvas canvas) {
        imageView.setEffect(colorAdjust);
        canvas.setEffect(colorAdjust);
    }

    static public void deactivate(ImageView imageView, Canvas canvas) {
        imageView.setEffect(null);
        canvas.setEffect(null);
    }
}

package org.example.imageeditor.filters;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

public class GrayscaleFilter extends AbstractFilter{
    @Override
    public void apply(ImageView imageView, Canvas canvas) {
        colorAdjust.setSaturation(-1);
        super.apply(imageView, canvas);
    }
}

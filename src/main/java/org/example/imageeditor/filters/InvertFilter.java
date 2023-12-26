package org.example.imageeditor.filters;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;

public class InvertFilter extends AbstractFilter{

    @Override
    public void apply(ImageView imageView, Canvas canvas) {
        colorAdjust.setHue(0.5);
        super.apply(imageView, canvas);
    }
}

package org.example.imageeditor.filters;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.ImageView;

public class SepiaFilter extends AbstractFilter{
    @Override
    public void apply(ImageView imageView, Canvas canvas){
        SepiaTone sepiaTone = new SepiaTone();
        imageView.setEffect(sepiaTone);
        canvas.setEffect(sepiaTone);
    }
}

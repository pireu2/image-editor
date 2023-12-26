package org.example.imageeditor;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

public interface Filter {
    void apply(ImageView imageView, Canvas canvas);
}

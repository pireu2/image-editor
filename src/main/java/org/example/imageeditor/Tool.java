package org.example.imageeditor;

import javafx.scene.layout.VBox;

public interface Tool {
    void activate();
    void deactivate();
    VBox getSideMenu();
}

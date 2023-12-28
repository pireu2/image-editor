package org.example.imageeditor;

import javafx.scene.layout.VBox;

/**
 * The Tool interface represents a tool that can be used in the image editor.
 * Each tool can be activated or deactivated, and has a side menu associated with it.
 */
public interface Tool {

    /**
     * Activates the tool. The specific behavior depends on the implementation.
     */
    void activate();

    /**
     * Deactivates the tool. The specific behavior depends on the implementation.
     */
    void deactivate();

    /**
     * Returns the side menu associated with the tool.
     * @return a VBox containing the side menu for the tool.
     */
    VBox getSideMenu();
}

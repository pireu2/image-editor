package org.example.imageeditor.util;

/**
 * The Constants class provides a central location for the application's constants.
 * It includes paths for icons, FXML files, CSS files, and keys for data storage.
 * It also includes the application title and the author's name.
 * This class cannot be instantiated.
 */
public final class Constants {
    private Constants() {}

    public static final String AUTHOR = "Duică Sebastian";

    public static final String APPLICATION_TITLE = "Image Editor";
    public static final String APPLICATION_ICON_PATH = "/assets/images/icon.png";
    public static final String HAND_ICON_PATH = "/assets/images/hand.png";
    public static final String ZOOM_ICON_PATH = "/assets/images/zoom.png";
    public static final String PAINT_BRUSH_ICON_PATH = "/assets/images/brush.png";
    public static final String ERASER_ICON_PATH = "/assets/images/eraser.png";
    public static final String CUSTOM_FILTER_ICON_PATH = "/assets/images/custom_filter.png";
    public static final String TEXT_ICON_PATH = "/assets/images/text.png";

    public static final String OPEN_FILE_FXML_PATH = "fxml/OpenFile.fxml";
    public static final String MAIN_FXML_PATH = "fxml/Main.fxml";

    public static final String OPEN_FILE_CSS_PATH = "css/OpenFile.css";
    public static final String MAIN_CSS_PATH = "css/Main.css";

    public static final String PICTURES_DIRECTORY_PATH = System.getProperty("user.home") + "/Pictures";

    public static final String TOOL_KEY = "tool";
    public static final String OPENED_FILE_KEY = "selectedFile";


}

package org.example.imageeditor.util;

public final class Constants {
    private Constants() {}

    public static final String APPLICATION_TITLE = "Image Editor";
    public static final String APPLICATION_ICON_PATH = "assets/images/icon.png";

    public static final String OPEN_FILE_FXML_PATH = "fxml/OpenFile.fxml";
    public static final String MAIN_FXML_PATH = "fxml/Main.fxml";

    public static final String OPEN_FILE_CSS_PATH = "css/OpenFile.css";
    public static final String MAIN_CSS_PATH = "css/Main.css";

    public static final String PICTURES_DIRECTORY_PATH = System.getProperty("user.home") + "/Pictures";
    public static final String OPENED_FILE_KEY = "selectedFile";
}

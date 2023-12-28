package org.example.imageeditor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.imageeditor.util.Constants;

import java.util.Objects;

/**
 * The OpenFile class is responsible for creating a new stage for opening a file.
 * It loads the FXML layout from a specified path and sets the scene for the stage.
 * It also sets the title and stylesheet for the scene.
 */
public class OpenFile {

    /**
     * Returns a new Stage with the scene set to the layout loaded from the FXML file.
     * The title of the stage is set to "Open File".
     * The stylesheet for the scene is loaded from a specified CSS file.
     * An application icon is also set for the stage.
     * @return a Stage object representing the new stage.
     * @throws Exception if there is an error loading the FXML file or the CSS file.
     */
    public static Stage getStage() throws Exception{
        Stage stage = new Stage();
        Parent root  = FXMLLoader.load(Objects.requireNonNull(OpenFile.class.getResource(Constants.OPEN_FILE_FXML_PATH)));
        Scene scene = new Scene(root);

        Image icon;
        try {
            icon = new Image(Objects.requireNonNull(OpenFile.class.getResourceAsStream(Constants.APPLICATION_ICON_PATH)));
            stage.getIcons().add(icon);
        } catch (NullPointerException e) {
            System.err.println("Icon not found");
        }

        stage.setTitle("Open File");
        scene.getStylesheets().add(Objects.requireNonNull(OpenFile.class.getResource(Constants.OPEN_FILE_CSS_PATH)).toExternalForm());
        stage.setScene(scene);
        return stage;
    }

}

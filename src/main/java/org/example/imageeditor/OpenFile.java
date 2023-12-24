package org.example.imageeditor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.imageeditor.util.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OpenFile {

    public static Stage getStage() throws Exception{
        Stage stage = new Stage();
        Parent root  = FXMLLoader.load(OpenFile.class.getResource(Constants.OPEN_FILE_FXML_PATH));
        Scene scene = new Scene(root);

        Image icon;
        try {
            icon = new Image(OpenFile.class.getResourceAsStream(Constants.APPLICATION_ICON_PATH));
            stage.getIcons().add(icon);
        } catch (NullPointerException e) {
            System.err.println("Icon not found");
        }

        stage.setTitle("Open File");
        scene.getStylesheets().add(OpenFile.class.getResource(Constants.OPEN_FILE_CSS_PATH).toExternalForm());
        stage.setScene(scene);
        return stage;
    }

}

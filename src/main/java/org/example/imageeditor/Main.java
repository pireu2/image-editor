package org.example.imageeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.imageeditor.util.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
       OpenFile.getStage().show();
    }

    public static Stage getMainStage() throws Exception{
        Stage stage = new Stage();
        Parent root  = FXMLLoader.load(Main.class.getResource(Constants.MAIN_FXML_PATH));
        Scene scene =  new Scene(root);
        stage.setTitle(Constants.APPLICATION_TITLE);
        Image icon;
        try {
            icon = new Image(new FileInputStream(Constants.APPLICATION_ICON_PATH));
            stage.getIcons().add(icon);
        } catch (FileNotFoundException e) {
            System.err.println("Icon not found");
        }
        scene.getStylesheets().add(Main.class.getResource(Constants.MAIN_CSS_PATH).toExternalForm());

        stage.setMaximized(true);
        //stage.setScene(scene);
        return stage;
    }
}

package org.example.imageeditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.imageeditor.util.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

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
            icon = new Image(Main.class.getResourceAsStream(Constants.APPLICATION_ICON_PATH));
            stage.getIcons().add(icon);
        } catch (NullPointerException e) {
            System.err.println("Icon not found");
        }
        scene.getStylesheets().add(Main.class.getResource(Constants.MAIN_CSS_PATH).toExternalForm());
        stage.setOnCloseRequest(event -> {
            event.consume();
            exit(stage);
        });
        stage.setMaximized(true);
        stage.setScene(scene);
        return stage;
    }

    static public void exit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("All unsaved changes will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            stage.close();
        }
    }
}

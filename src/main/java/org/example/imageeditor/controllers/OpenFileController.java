package org.example.imageeditor.controllers;

import javafx.fxml.FXML;

import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.imageeditor.util.Constants;
import org.example.imageeditor.util.ControllerMediator;
import org.example.imageeditor.Main;

import java.io.File;

public class OpenFileController {

    @FXML
    private VBox vBox;
    private Stage stage;

    public void openFile() throws Exception{
        Stage currentStage = vBox == null ? stage : (Stage) vBox.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(Constants.PICTURES_DIRECTORY_PATH));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.jpeg", "*.png", "*.bmp", "*.gif"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );
        fileChooser.setTitle("Open File");
        File selectedFile = fileChooser.showOpenDialog(currentStage);
        if(selectedFile != null){
            ControllerMediator.getInstance().put(Constants.OPENED_FILE_KEY, selectedFile);
            Main.getMainStage().show();
            currentStage.close();
        }
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
}

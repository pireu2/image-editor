package org.example.imageeditor.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.imageeditor.util.Constants;
import org.example.imageeditor.util.ControllerMediator;
import org.example.imageeditor.Main;

import java.io.File;

public class OpenFileController {

    @FXML
    Button openButton;
    FileChooser fileChooser = new FileChooser();
    private File selectedFile;

    public void openFile() throws Exception{
        Stage currenStage = (Stage) openButton.getScene().getWindow();
        fileChooser.setInitialDirectory(new File(Constants.PICTURES_DIRECTORY_PATH));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.jpeg", "*.png", "*.bmp", "*.gif"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );
        fileChooser.setTitle("Open File");
        selectedFile = fileChooser.showOpenDialog(currenStage);
        if(selectedFile != null){
            ControllerMediator.getInstance().put(Constants.OPENED_FILE_KEY, selectedFile);
            Main.getMainStage().show();
            currenStage.close();
        }
    }


    public File getSelectedFile() {
        System.out.println(selectedFile);
        return selectedFile;
    }
}

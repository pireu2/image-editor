package org.example.imageeditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.imageeditor.util.Constants;
import org.example.imageeditor.util.ControllerMediator;

import java.io.File;

public class MainController {

    @FXML
    ImageView mainImageView;

    @FXML
    public void initialize(){
        System.out.println("MainController initialized");
        File selectedFile = (File) ControllerMediator.getInstance().get(Constants.OPENED_FILE_KEY);
        if(selectedFile != null){
            Image defaultImage = new Image(selectedFile.toURI().toString());
            mainImageView.setImage(defaultImage);
        }
    }
    public void printSelectedFilePath(){
        System.out.println(ControllerMediator.getInstance().get(Constants.OPENED_FILE_KEY));
    }
}

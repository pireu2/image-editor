package org.example.imageeditor.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.imageeditor.Main;
import org.example.imageeditor.util.Constants;
import org.example.imageeditor.util.ControllerMediator;

import java.io.File;
import java.util.Optional;

public class MainController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView mainImageView;
    @FXML
    private Label bottomLabel;
    @FXML
    private MenuItem saveButton;
    @FXML
    private MenuItem exitButton;
    @FXML
    private MenuItem openButton;

    private FileChooser fileChooser;

    @FXML
    public void initialize(){
        File selectedFile = (File) ControllerMediator.getInstance().get(Constants.OPENED_FILE_KEY);
        if(selectedFile != null){
            bottomLabel.setText(selectedFile.toURI().toString());
            Image defaultImage = new Image(selectedFile.toURI().toString());
            mainImageView.setImage(defaultImage);
        }
    }

    public void open() throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Open File");
        alert.setHeaderText("Are you sure you want to open another file?");
        alert.setContentText("All unsaved changes will be lost.");

        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == yesButton){
            OpenFileController openFileController = new OpenFileController();
            openFileController.setStage((Stage) borderPane.getScene().getWindow());
            openFileController.openFile();
        }
    }
    public void save(){
        //TODO implement save
    }
    public void exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("All unsaved changes will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.close();
        }
    }
}

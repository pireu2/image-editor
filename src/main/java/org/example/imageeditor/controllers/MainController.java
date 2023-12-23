package org.example.imageeditor.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.imageeditor.Main;
import org.example.imageeditor.Tool;
import org.example.imageeditor.tools.Hand;
import org.example.imageeditor.tools.PaintBrush;
import org.example.imageeditor.tools.Zoom;
import org.example.imageeditor.util.Constants;
import org.example.imageeditor.util.ControllerMediator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.stream.IntStream;

public class MainController {

    @FXML
    private ImageView handIcon;
    @FXML
    private ImageView zoomIcon;
    @FXML
    private ImageView brushIcon;

    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView mainImageView;
    @FXML
    private Canvas mainCanvas;

    @FXML
    private Label bottomLabel;
    @FXML
    private Button handButton;
    @FXML
    private Button zoomButton;
    @FXML
    private Button brushButton;

    private Image initialImage;

    @FXML
    public void initialize() {
        try{
            handIcon.setImage(new Image(new FileInputStream(Constants.HAND_ICON_PATH)));
            zoomIcon.setImage(new Image(new FileInputStream(Constants.ZOOM_ICON_PATH)));
            brushIcon.setImage(new Image(new FileInputStream(Constants.PAINT_BRUSH_ICON_PATH)));
        }
        catch (FileNotFoundException e) {
            System.err.println("Icons not found");
        }

        File selectedFile = ControllerMediator.getInstance().get(Constants.OPENED_FILE_KEY, File.class);
        if(selectedFile != null){
            bottomLabel.setText(selectedFile.toURI().toString());
            initialImage = new Image(selectedFile.toURI().toString());
            mainImageView.setImage(initialImage);
            mainCanvas.setWidth(initialImage.getWidth());
            mainCanvas.setHeight(initialImage.getHeight());
        }
    }

    public void clickHand(){
        handleTool(new Hand(mainImageView, mainCanvas, handButton));
    }
    public void clickZoom(){
        handleTool(new Zoom(mainImageView, mainCanvas, zoomButton));
    }
    public void clickBrush(){
        handleTool(new PaintBrush(mainCanvas, brushButton));
    }

    public void handleTool(Tool tool){
        Tool currentTool = ControllerMediator.getInstance().get(Constants.TOOL_KEY, Tool.class);
        if(currentTool != null){
            if(currentTool.getClass() == tool.getClass()){
                currentTool.deactivate();
                ControllerMediator.getInstance().put(Constants.TOOL_KEY, null);
                return;
            }
            currentTool.deactivate();
        }
        ControllerMediator.getInstance().put(Constants.TOOL_KEY, tool);
        tool.activate();
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

    public void reset(){
        mainImageView.setImage(initialImage);
        mainImageView.setScaleX(1.0);
        mainImageView.setScaleY(1.0);
        mainImageView.setTranslateX(0.0);
        mainImageView.setTranslateY(0.0);
        mainCanvas.getGraphicsContext2D().clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
        mainCanvas.setScaleX(1.0);
        mainCanvas.setScaleY(1.0);
        mainCanvas.setTranslateX(0.0);
        mainCanvas.setTranslateY(0.0);
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

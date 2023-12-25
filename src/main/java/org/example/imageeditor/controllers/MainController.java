package org.example.imageeditor.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.imageeditor.Main;
import org.example.imageeditor.Tool;
import org.example.imageeditor.tools.Eraser;
import org.example.imageeditor.tools.Hand;
import org.example.imageeditor.tools.PaintBrush;
import org.example.imageeditor.tools.Zoom;
import org.example.imageeditor.util.Constants;
import org.example.imageeditor.util.ControllerMediator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

public class MainController {

    @FXML
    private ImageView handIcon;
    @FXML
    private ImageView zoomIcon;
    @FXML
    private ImageView brushIcon;
    @FXML
    private ImageView eraserIcon;

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
    @FXML
    private Button eraserButton;

    private Image initialImage;



    @FXML
    public void initialize() {
        try{
            handIcon.setImage(new Image(getClass().getResourceAsStream(Constants.HAND_ICON_PATH)));
            zoomIcon.setImage(new Image(getClass().getResourceAsStream(Constants.ZOOM_ICON_PATH)));
            brushIcon.setImage(new Image(getClass().getResourceAsStream(Constants.PAINT_BRUSH_ICON_PATH)));
            eraserIcon.setImage(new Image(getClass().getResourceAsStream(Constants.ERASER_ICON_PATH)));
        }
        catch (NullPointerException e) {
            System.err.println("Icons not found");
        }

        File selectedFile = ControllerMediator.getInstance().get(Constants.OPENED_FILE_KEY, File.class);
        if(selectedFile != null){
            bottomLabel.setText(selectedFile.toURI().toString());
            initialImage = new Image(selectedFile.toURI().toString());
            mainImageView.setImage(initialImage);

            if(initialImage.getWidth() > mainImageView.getFitWidth() || initialImage.getHeight() > mainImageView.getFitHeight()){
                double scaleX = mainImageView.getFitWidth() / initialImage.getWidth();
                double scaleY = mainImageView.getFitHeight() / initialImage.getHeight();
                double scale = Math.min(scaleX, scaleY);
                mainImageView.setFitWidth(initialImage.getWidth() * scale);
                mainImageView.setFitHeight(initialImage.getHeight() * scale);
            }
            else{
                mainImageView.setFitWidth(initialImage.getWidth());
                mainImageView.setFitHeight(initialImage.getHeight());
            }

            mainCanvas.setWidth(mainImageView.getFitWidth());
            mainCanvas.setHeight(mainImageView.getFitHeight());
            mainCanvas.setTranslateX(mainImageView.getTranslateX());
            mainCanvas.setTranslateY(mainImageView.getTranslateY());
            BorderPane.setAlignment(mainImageView, Pos.CENTER);

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

    public void clickEraser(){
        handleTool(new Eraser(mainCanvas, eraserButton));
    }

    public void handleTool(Tool tool){
        Tool currentTool = ControllerMediator.getInstance().get(Constants.TOOL_KEY, Tool.class);
        if(currentTool != null){
            if(currentTool.getClass() == tool.getClass()){
                currentTool.deactivate();
                ControllerMediator.getInstance().put(Constants.TOOL_KEY, null);
                borderPane.setRight(null);
                return;
            }
            currentTool.deactivate();
            borderPane.setRight(null);
        }

        ControllerMediator.getInstance().put(Constants.TOOL_KEY, tool);
        borderPane.setRight(ControllerMediator.getInstance().get(Constants.TOOL_KEY, Tool.class).getSideMenu());
        tool.activate();
    }

    public void open() throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.APPLICATION_ICON_PATH)));
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

    public void about(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.APPLICATION_ICON_PATH)));
        alert.setTitle("About");
        alert.setHeaderText("Image Editor");
        alert.setContentText("This is a simple image editor made by me, " + Constants.AUTHOR + ".\n" +
                "It was made as a project for the Object Oriented Programming course at the Technical University of Cluj-Napoca.\n" +
                "It is free to use and modify.\n" +
                "You can find the source code at: https://github.com/pireu2/image-editor");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
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
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.APPLICATION_ICON_PATH)));
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

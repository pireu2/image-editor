package org.example.imageeditor.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.imageeditor.Filter;
import org.example.imageeditor.Tool;
import org.example.imageeditor.filters.AbstractFilter;
import org.example.imageeditor.filters.GrayscaleFilter;
import org.example.imageeditor.filters.InvertFilter;
import org.example.imageeditor.filters.SepiaFilter;
import org.example.imageeditor.tools.*;
import org.example.imageeditor.util.Constants;
import org.example.imageeditor.util.ControllerMediator;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * The MainController class is responsible for handling the main operations in the image editor.
 * It provides methods to open and save files, apply filters and tools, and handle UI events.
 */
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
    private ImageView customFilterIcon;
    @FXML
    private ImageView textIcon;
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
    @FXML
    private Button customFilterButton;
    @FXML
    private Button textButton;
    @FXML
    private CheckMenuItem noFilterButton;
    @FXML
    private CheckMenuItem grayscaleFilterButton;
    @FXML
    private CheckMenuItem sepiaFilterButton;
    @FXML
    private CheckMenuItem invertFilterButton;

    private Image initialImage;
    private final ArrayList<CheckMenuItem> filterButtons = new ArrayList<>();

    /**
     * Initializes the controller.
     * This method is called after all @FXML annotated members have been injected.
     */
    @FXML
    public void initialize() {
        filterButtons.add(noFilterButton);
        filterButtons.add(grayscaleFilterButton);
        filterButtons.add(sepiaFilterButton);
        filterButtons.add(invertFilterButton);

        try{
            handIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.HAND_ICON_PATH))));
            zoomIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.ZOOM_ICON_PATH))));
            brushIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.PAINT_BRUSH_ICON_PATH))));
            eraserIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.ERASER_ICON_PATH))));
            customFilterIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.CUSTOM_FILTER_ICON_PATH))));
            textIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.TEXT_ICON_PATH))));
        }
        catch (NullPointerException e) {
            System.err.println("Icons not found");
        }

        File selectedFile = ControllerMediator.getInstance().get(Constants.OPENED_FILE_KEY, File.class);
        if(selectedFile != null){
            bottomLabel.setText(selectedFile.toURI().toString());
            initialImage = new Image(selectedFile.toURI().toString());
            mainImageView.setImage(initialImage);
            scaleImage(initialImage, mainImageView, mainCanvas);
            BorderPane.setAlignment(mainImageView, Pos.CENTER);

        }
    }

    /**
     * Opens a new file.
     * @throws Exception if there is an error opening the file.
     */
    public void open() throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.APPLICATION_ICON_PATH))));
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

    /**
     * Saves the current image to a file.
     */
    public void save(){
        undoZoomAndPan(mainImageView, mainCanvas);
        BorderPane.setAlignment(mainImageView, Pos.CENTER);
        WritableImage writableImage = new WritableImage((int) mainImageView.getFitWidth(),
                (int) mainImageView.getFitHeight());
        SnapshotParameters parameters = new SnapshotParameters();

        parameters.setViewport(new  Rectangle2D(mainImageView.getLayoutX() + mainImageView.getParent().getLayoutX(),
                mainImageView.getLayoutY() + mainImageView.getParent().getLayoutY(),
                mainImageView.getFitWidth(), mainImageView.getFitHeight()));
        borderPane.snapshot(parameters, writableImage);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialDirectory(new File(Constants.PICTURES_DIRECTORY_PATH));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = fileChooser.showSaveDialog(borderPane.getScene().getWindow());
        if(file!=null) {
            try {
                if(!file.getAbsolutePath().endsWith(".png")){
                    file = new File(file.getAbsolutePath() + ".png");
                }
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            } catch (IOException e) {
                System.err.println("Failed to save image: " + e);
            }
        }
    }

    /**
     * Shows information about the application.
     */
    public void about(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.APPLICATION_ICON_PATH))));
        alert.setTitle("About");
        alert.setHeaderText("Image Editor v1.0");
        alert.setContentText("This is a simple image editor made by me, " + Constants.AUTHOR + ".\n" +
                "It was made as a project for the Object Oriented Programming course at the Technical University of Cluj-Napoca.\n" +
                "It is free to use and modify.\n" +
                "You can find the source code at: https://github.com/pireu2/image-editor");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Resets the image to its initial state.
     */
    public void reset(){
        mainImageView.setEffect(null);
        mainImageView.setImage(initialImage);
        mainCanvas.setEffect(null);
        mainCanvas.getGraphicsContext2D().clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
        undoZoomAndPan(mainImageView, mainCanvas);
    }

    /**
     * Exits the application.
     */
    public void exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.APPLICATION_ICON_PATH))));
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("All unsaved changes will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Handles the click event of the hand tool button.
     */
    public void clickHand(){
        handleTool(new Hand(mainImageView, mainCanvas, handButton));
    }

    /**
     * Handles the click event of the zoom tool button.
     */
    public void clickZoom(){
        handleTool(new Zoom(mainImageView, mainCanvas, zoomButton));
    }

    /**
     * Handles the click event of the brush tool button.
     */
    public void clickBrush(){
        handleTool(new PaintBrush(mainCanvas, brushButton));
    }

    /**
     * Handles the click event of the eraser tool button.
     */
    public void clickEraser(){
        handleTool(new Eraser(mainCanvas, eraserButton));
    }

    /**
     * Handles the click event of the custom filter tool button.
     */
    public void clickCustomFilter(){
        handleTool(new CustomFilter(mainImageView, customFilterButton));
    }

    /**
     * Handles the click event of the text tool button.
     */
    public void clickText(){
        handleTool(new Text(mainCanvas, textButton));
    }

    /**
     * Applies the no filter to the image.
     */
    public void applyNoFilter() {
        deselectOtherFilterMenuItems(noFilterButton);
        AbstractFilter.deactivate(mainImageView, mainCanvas);
    }

    /**
     * Applies the grayscale filter to the image.
     */
    public void applyGrayscaleFilter() {
        deselectOtherFilterMenuItems(grayscaleFilterButton);
        applyFilter(new GrayscaleFilter());
    }

    /**
     * Applies the sepia filter to the image.
     */
    public void applySepiaFilter(){
        deselectOtherFilterMenuItems(sepiaFilterButton);
        applyFilter(new SepiaFilter());
    }

    /**
     * Applies the invert filter to the image.
     */
    public void applyInvertFilter(){
        deselectOtherFilterMenuItems(sepiaFilterButton);
        applyFilter(new InvertFilter());
    }

    /**
     * Deselects other filter menu items except the selected one.
     * @param selectedFilterButton the selected filter button
     */
    private void deselectOtherFilterMenuItems(CheckMenuItem selectedFilterButton){
        for(CheckMenuItem filterButton : filterButtons){
            if(filterButton != selectedFilterButton){
                filterButton.setSelected(false);
            }
        }
    }

    /**
     * Applies the specified filter to the image.
     * @param filter the filter to be applied
     */
    private void applyFilter(Filter filter) {
        if(filter != null){
            filter.apply(mainImageView, mainCanvas);
        }
        else{
            AbstractFilter.deactivate(mainImageView, mainCanvas);
        }
    }

    /**
     * Handles the tool selection and activation.
     * @param tool the tool to be handled
     */
    private void handleTool(Tool tool){
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

    /**
     * Scales the image to fit within the image view and canvas.
     * @param initialImage the initial image
     * @param imageView the image view
     * @param canvas the canvas
     */
    private void scaleImage(Image initialImage, ImageView imageView, Canvas canvas){
        if(initialImage.getWidth() > imageView.getFitWidth() || initialImage.getHeight() > imageView.getFitHeight()){
            double scaleX = imageView.getFitWidth() / initialImage.getWidth();
            double scaleY = imageView.getFitHeight() / initialImage.getHeight();
            double scale = Math.min(scaleX, scaleY);
            imageView.setFitWidth(initialImage.getWidth() * scale);
            imageView.setFitHeight(initialImage.getHeight() * scale);
        }
        else{
            imageView.setFitWidth(initialImage.getWidth());
            imageView.setFitHeight(initialImage.getHeight());
        }
        canvas.setWidth(imageView.getFitWidth());
        canvas.setHeight(imageView.getFitHeight());
        canvas.setTranslateX(imageView.getTranslateX());
        canvas.setTranslateY(imageView.getTranslateY());
    }

    /**
     * Resets the zoom and pan of the image view and canvas to their initial state.
     * @param imageView the image view
     * @param canvas the canvas
     */
    private void undoZoomAndPan(ImageView imageView, Canvas canvas){
        canvas.setScaleX(1.0);
        canvas.setScaleY(1.0);
        canvas.setTranslateX(0.0);
        canvas.setTranslateY(0.0);
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
        imageView.setTranslateX(0.0);
        imageView.setTranslateY(0.0);
    }
}

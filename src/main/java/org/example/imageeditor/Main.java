package org.example.imageeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.imageeditor.util.Constants;

import java.util.Objects;
import java.util.Optional;

/**
 * The Main class is the entry point of the application.
 * It extends the Application class from JavaFX and overrides the start method.
 * It also provides a method to get the main stage of the application and a method to exit the application.
 */
public class Main extends Application {

    /**
     * The main method of the application.
     * It calls the launch method from the Application class.
     * @param args command line arguments
     */
    public static void main(String[] args){
        Application.launch(args);
    }

    /**
     * The start method of the application.
     * It is called after the init method has returned, and after the system is ready for the application to begin running.
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws Exception if there is an error loading the FXML file or the CSS file.
     */
    @Override
    public void start(Stage stage) throws Exception{
       OpenFile.getStage().show();
    }

    /**
     * Returns the main Stage of the application.
     * The scene for the stage is set to the layout loaded from the FXML file.
     * The title of the stage is set to the application title.
     * An application icon is also set for the stage.
     * @return a Stage object representing the main stage.
     * @throws Exception if there is an error loading the FXML file or the CSS file.
     */
    public static Stage getMainStage() throws Exception{
        Stage stage = new Stage();
        Parent root  = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(Constants.MAIN_FXML_PATH)));
        Scene scene =  new Scene(root);
        stage.setTitle(Constants.APPLICATION_TITLE);
        Image icon;
        try {
            icon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream(Constants.APPLICATION_ICON_PATH)));
            stage.getIcons().add(icon);
        } catch (NullPointerException e) {
            System.err.println("Icon not found");
        }
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource(Constants.MAIN_CSS_PATH)).toExternalForm());
        stage.setOnCloseRequest(event -> {
            event.consume();
            exit(stage);
        });
        stage.setMaximized(true);
        stage.setScene(scene);
        return stage;
    }

    /**
     * Exits the application after showing a confirmation dialog.
     * If the user confirms, the stage is closed.
     * @param stage the stage that will be closed.
     */
    static public void exit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream(Constants.APPLICATION_ICON_PATH))));
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("All unsaved changes will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            stage.close();
        }
    }
}

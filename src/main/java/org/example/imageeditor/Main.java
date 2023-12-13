package org.example.imageeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        Scene scene = new Scene(root);

        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Image Editor");
        stage.setScene(scene);
        stage.show();
    }

    private Stage setupStage(double width, double height) {
        Stage stage = new Stage();
        stage.setTitle("Image Editor");
        Image icon;
        try {
            icon = new Image(new FileInputStream("assets/images/icon.png"));
            stage.getIcons().add(icon);
        } catch (FileNotFoundException e) {
            System.err.println("Icon not found");
        }
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(true);
        return stage;
    }

}

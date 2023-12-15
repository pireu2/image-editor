module org.example.imageeditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.imageeditor to javafx.fxml;
    exports org.example.imageeditor;
    exports org.example.imageeditor.controllers;
    opens org.example.imageeditor.controllers to javafx.fxml;
    exports org.example.imageeditor.util;
    opens org.example.imageeditor.util to javafx.fxml;
}
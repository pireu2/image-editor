# image-editor
This is an image editor application built in Java using JavaFX. It provides various functionalities such as opening and saving files, applying filters and tools, and handling UI events.

## Features

- Open and save image files
- Apply various filters including grayscale, sepia, invert, and custom filters
- Use various tools including hand, zoom, paint brush, eraser, custom filter, and text tools
- Reset the image to its initial state
- Exit the application

## Build
```shell
mvn clean package
mv target/image-editor-1.0-SNAPSHOT.jar target/app/
jpackage --name image-editor --input target/app --main-jar image-editor-1.0-SNAPSHOT.jar --main-class org.example.imageeditor.Main --runtime-image target/app --vendor "Duică Sebastian" --type msi --win-dir-chooser --win-menu --icon src/main/resources/assets/images/ico/icon.ico
```

# image-editor
Image editor made in java

# Build
```shell
mvn clean package
mv target/image-editor-1.0-SNAPSHOT.jar target/app/
jpackage --name image-editor --input target/app --main-jar image-editor-1.0-SNAPSHOT.jar --main-class org.example.imageeditor.Main --runtime-image target/app --type msi --win-dir-chooser --win-menu --icon src/main/resources/assets/images/ico/icon.ico
```

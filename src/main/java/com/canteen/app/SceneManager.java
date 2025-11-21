package com.canteen.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class SceneManager {
    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }





    public static void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/fxml/" + fxmlFile));
            Region root = loader.load();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(SceneManager.class.getResource("/css/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setMaximized(true); // make the new scene fullscreen too
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

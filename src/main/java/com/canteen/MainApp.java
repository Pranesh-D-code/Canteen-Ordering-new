package com.canteen.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // ✅ Set the stage in SceneManager first
        SceneManager.setStage(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setTitle("Smart Canteen");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void switchScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile));
            Scene newScene = new Scene(loader.load());
            newScene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            stage.setScene(newScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.canteen.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // ✅ Set the stage in SceneManager
        SceneManager.setStage(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Region root = loader.load(); // Region supports dynamic resizing
        Scene scene = new Scene(root);

        // ✅ Add your global stylesheet
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        // ✅ Set stage properties
        stage.setTitle("Smart Canteen");
        stage.setScene(scene);
        stage.setResizable(true); // Allow resizing
        stage.setMaximized(true); // Start maximized

        // ✅ Optional: start in fullscreen
        // stage.setFullScreen(true);

        // ✅ Adjust to screen bounds (auto-fit any monitor)
        stage.setWidth(Screen.getPrimary().getBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getBounds().getHeight());

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

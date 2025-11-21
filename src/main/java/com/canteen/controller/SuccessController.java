package com.canteen.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SuccessController {

    @FXML
    private ImageView tickImage;

    @FXML
    private javafx.scene.control.Label orderIdLabel;

    @FXML
    public void initialize() {
        tickImage.setImage(new Image(getClass().getResourceAsStream("/images/tick.gif")));

        int orderId = (int)(Math.random() * 900000 + 100000);
        orderIdLabel.setText("Order ID: #" + orderId);
    }

    @FXML
    private void goHome(javafx.event.ActionEvent event) {
        try {
            Stage stage = (Stage) orderIdLabel.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/menu.fxml")));
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

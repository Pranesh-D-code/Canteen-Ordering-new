package com.canteen.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PaymentDetailsController {

    @FXML private Label titleLabel;

    @FXML private TextField upiField;
    @FXML private TextField bankField;
    @FXML private TextField userIdField;

    @FXML private PasswordField passwordField;

    @FXML private HBox upiBox;       // FIXED (was VBox)
    @FXML private VBox netBankBox;   // correct

    private String method;

    public void setMethod(String method) {
        this.method = method;
        titleLabel.setText(method + " Payment Details");

        // Show fields depending on payment method
        if (method.equals("UPI") || method.equals("Google Pay") || method.equals("Paytm")) {

            upiBox.setVisible(true);
            upiBox.setManaged(true);

        } else {

            netBankBox.setVisible(true);
            netBankBox.setManaged(true);
        }
    }

    @FXML
    private void proceedToQR(javafx.event.ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/qrdisplay.fxml"));
            Scene scene = new Scene(loader.load());

            QRDisplayController controller = loader.getController();

            controller.loadQRData(
                    method,
                    upiField.getText(),
                    bankField.getText(),
                    userIdField.getText()
            );

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

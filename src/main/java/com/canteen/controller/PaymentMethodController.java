package com.canteen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PaymentMethodController {

    @FXML
    private ToggleGroup paymentGroup;

    @FXML
    private RadioButton upiRadio;

    @FXML
    private RadioButton gpayRadio;

    @FXML
    private RadioButton paytmRadio;

    @FXML
    private RadioButton netBankingRadio;

    @FXML
    private void goToPaymentDetails(ActionEvent event) {

        if (paymentGroup.getSelectedToggle() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a payment method.").showAndWait();
            return;
        }

        RadioButton selected = (RadioButton) paymentGroup.getSelectedToggle();
        String method = selected.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/payment_details.fxml"));
            Parent root = loader.load();

            PaymentDetailsController controller = loader.getController();
            controller.setMethod(method);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

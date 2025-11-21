package com.canteen.controller;

import com.canteen.model.CartItem;
import com.canteen.util.CartManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class PaymentController {

    @FXML private ListView<String> billList;
    @FXML private Label totalLabel;
    @FXML private Button payBtn;

    @FXML
    private void initialize() {
        billList.getItems().clear();

        for (CartItem item : CartManager.getItems()) {
            billList.getItems().add(
                    item.getName() + " × " + item.getQuantity() + " = ₹" + item.getTotal()
            );
        }

        totalLabel.setText("₹ " + CartManager.getGrandTotal());
    }

    @FXML

    private void payNow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/payment_method.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) payBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void backToCart(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cart.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

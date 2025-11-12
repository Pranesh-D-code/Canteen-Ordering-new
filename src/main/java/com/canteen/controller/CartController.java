package com.canteen.controller;

import com.canteen.app.AppSession;
import com.canteen.model.MenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

public class CartController {

    @FXML private ListView<String> lvCart;
    @FXML private Label lblTotal;

    @FXML
    public void initialize() {
        refresh();
    }

    private void refresh() {
        lvCart.getItems().clear();
        List<MenuItem> cart = AppSession.getCart();
        double total = 0;
        for (MenuItem m : cart) {
            lvCart.getItems().add(m.getName() + " - ₹" + m.getPrice());
            total += m.getPrice();
        }
        lblTotal.setText("Total: ₹" + total);
    }

    @FXML
    private void removeSelected() {
        int idx = lvCart.getSelectionModel().getSelectedIndex();
        if (idx >= 0) {
            AppSession.getCart().remove(idx);
            refresh();
        }
    }

    @FXML
    private void proceedToPayment() {
        try {
            Stage stage = (Stage) lvCart.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/payment.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    private void backToMenu() {
        try {
            Stage stage = (Stage) lvCart.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }
}

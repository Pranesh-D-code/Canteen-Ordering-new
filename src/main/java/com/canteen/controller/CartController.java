package com.canteen.controller;

import com.canteen.model.CartItem;
import com.canteen.util.CartManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {

    @FXML private TableView<CartItem> cartTable;
    @FXML private TableColumn<CartItem, String> colName;
    @FXML private TableColumn<CartItem, Integer> colQty;
    @FXML private TableColumn<CartItem, Double> colPrice;
    @FXML private TableColumn<CartItem, Double> colTotal;
    @FXML private Label lblGrandTotal;

    @FXML
    public void initialize() {

        // connect table columns to CartItem fields
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadTable();
    }

    private void loadTable() {
        cartTable.getItems().setAll(CartManager.getItems());
        lblGrandTotal.setText("Grand Total: ₹ " + CartManager.getGrandTotal());
    }

    @FXML
    private void removeSelected() {
        CartItem item = cartTable.getSelectionModel().getSelectedItem();
        if (item != null) {
            CartManager.removeItem(item);
            loadTable();
        }
    }

    @FXML
    private void backToMenu(javafx.event.ActionEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/menu.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void proceedToPayment(javafx.event.ActionEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/payment.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

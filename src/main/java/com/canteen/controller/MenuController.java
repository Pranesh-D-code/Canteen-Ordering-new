package com.canteen.controller;

import com.canteen.app.AppSession;
import com.canteen.dao.MenuItemDAO;
import com.canteen.model.MenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

public class MenuController {

    @FXML private ListView<MenuItem> lvMenu;
    private final MenuItemDAO dao = new MenuItemDAO();

    @FXML
    public void initialize() {
        List<MenuItem> items = dao.findAll();
        lvMenu.getItems().addAll(items);
        lvMenu.setOnMouseClicked((MouseEvent ev) -> {
            if (ev.getClickCount() == 2) {
                MenuItem sel = lvMenu.getSelectionModel().getSelectedItem();
                if (sel != null) AppSession.addToCart(sel);
            }
        });
    }

    @FXML
    private void openCart() {
        try {
            Stage stage = (Stage) lvMenu.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/cart.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }
}

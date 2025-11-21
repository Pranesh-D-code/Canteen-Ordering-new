package com.canteen.controller;

import com.canteen.app.AppSession;
import com.canteen.dao.MenuItemDAO;
import com.canteen.model.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.List;

public class MenuController {

    @FXML private FlowPane menuContainer;
    @FXML private Label usernameLabel;

    private final MenuItemDAO dao = new MenuItemDAO();

    @FXML
    public void initialize() {
        // set username
        try {
            if (AppSession.getUser() != null && AppSession.getUser().getUsername() != null) {
                usernameLabel.setText("Welcome, " + AppSession.getUser().getUsername());
            } else {
                usernameLabel.setText("Welcome");
            }
        } catch (Exception ex) {
            usernameLabel.setText("Welcome");
        }

        loadMenuItems();
    }

    private void loadMenuItems() {
        menuContainer.getChildren().clear();
        List<MenuItem> items = dao.getAllItems();
        for (MenuItem m : items) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/food_card.fxml"));
                Node card = loader.load();

                FoodCardController controller = loader.getController();
                controller.setData(m);

                menuContainer.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void openCart() {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/fxml/cart.fxml"));
            menuContainer.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

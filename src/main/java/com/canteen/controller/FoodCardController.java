package com.canteen.controller;

import com.canteen.model.MenuItem;
import com.canteen.util.CartManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class FoodCardController {

    @FXML private ImageView imageView;
    @FXML private Label nameLabel;
    @FXML private Label categoryLabel;
    @FXML private Label priceLabel;
    @FXML private Spinner<Integer> qtySpinner;
    @FXML private Button addBtn;

    private MenuItem item;

    public void setData(MenuItem menuItem) {

        this.item = menuItem;

        nameLabel.setText(menuItem.getName());
        categoryLabel.setText(menuItem.getCategory());
        priceLabel.setText("₹ " + menuItem.getPrice().toPlainString());

        // Quantity spinner default values
        qtySpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1)
        );

        // Load image safely
        Image img;
        try {
            img = new Image(
                    getClass().getResourceAsStream(menuItem.getImagePath()),
                    220, 140, true, true
            );
        } catch (Exception e) {
            img = new Image(getClass().getResourceAsStream("/images/default_food.png"));
        }

        imageView.setImage(img);
    }

    @FXML
    private void addToCart() {
        int qty = qtySpinner.getValue();

        CartManager.addItem(
                item.getName(),
                item.getPrice().doubleValue(),
                qty
        );

        // Confirmation popup
        Alert alert = new Alert(
                Alert.AlertType.INFORMATION,
                item.getName() + " × " + qty + " added to cart",
                ButtonType.OK
        );
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}

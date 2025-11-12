package com.canteen.controller;

import com.canteen.app.AppSession;
import com.canteen.dao.OrderDAO;
import com.canteen.model.MenuItem;
import com.canteen.model.Order;
import com.canteen.util.QRGenerator;
import com.google.zxing.WriterException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentController {

    @FXML private Label lblSummary;
    private final OrderDAO orderDAO = new OrderDAO();

    @FXML
    public void initialize() {
        List<MenuItem> cart = AppSession.getCart();
        String items = cart.stream().map(MenuItem::getName).collect(Collectors.joining(", "));
        double total = cart.stream().mapToDouble(MenuItem::getPrice).sum();
        lblSummary.setText("Items: " + items + "\nTotal: ₹" + total);
    }

    @FXML
    private void payNow() {
        List<MenuItem> cart = AppSession.getCart();
        String items = cart.stream().map(MenuItem::getName).collect(Collectors.joining(", "));
        double total = cart.stream().mapToDouble(MenuItem::getPrice).sum();

        try {
            Order order = new Order();
            order.setUserId(AppSession.getUser().getId());
            order.setItems(items);
            order.setTotalPrice(total);
            order.setQrPath("");
            int id = orderDAO.save(order);

            String qrText = "Order #" + id + " | Items: " + items + " | Total: ₹" + total;
            String qrUri = QRGenerator.generate(qrText, "order_" + id);
            orderDAO.updateQrPath(id, qrUri);

            AppSession.setLastQrText(qrText);
            AppSession.setLastQrUri(qrUri);

            // clear cart
            AppSession.clearCart();

            // go to QR display
            Stage stage = (Stage) lblSummary.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/qrdisplay.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException | WriterException ex) {
            ex.printStackTrace();
        }
    }

    @FXML private void backToCart() {
        try {
            Stage stage = (Stage) lblSummary.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/cart.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }
}

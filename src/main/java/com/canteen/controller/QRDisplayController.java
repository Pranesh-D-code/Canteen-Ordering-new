package com.canteen.controller;

import com.canteen.util.CartManager;
import com.canteen.util.QRGenerator;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QRDisplayController {

    @FXML private ImageView qrImage;
    @FXML private Label billIdLbl, totalLbl, datetimeLbl, paymentMethodLbl, enteredDetailLbl;

    public void loadQRData(String method, String upiId, String bank, String userid) {

        String summary = CartManager.generateSummary();
        String qrText = "ORDER SUMMARY:\n" + summary;

        qrImage.setImage(QRGenerator.generateQR(qrText));

        billIdLbl.setText("Bill ID: " + System.currentTimeMillis());
        totalLbl.setText("Total: ₹ " + CartManager.getGrandTotal());

        datetimeLbl.setText("Time: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        paymentMethodLbl.setText("Payment Method: " + method);

        if (upiId != null && !upiId.isEmpty())
            enteredDetailLbl.setText("UPI ID: " + upiId);
        else if (bank != null && !bank.isEmpty())
            enteredDetailLbl.setText("Bank: " + bank + " (User ID: " + userid + ")");
        else
            enteredDetailLbl.setText("Details: N/A");
    }

    @FXML
    private void backToMenu(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            CartManager.clear();

        } catch (Exception e) { e.printStackTrace(); }
    }
}

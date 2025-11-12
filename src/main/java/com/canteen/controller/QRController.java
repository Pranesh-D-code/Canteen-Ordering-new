package com.canteen.controller;

import com.canteen.app.AppSession;
import com.canteen.util.SpeechUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class QRController {

    @FXML private ImageView imgQR;
    @FXML private TextArea txtDecoded;

    @FXML
    public void initialize() {
        String uri = AppSession.getLastQrUri();
        String text = AppSession.getLastQrText();
        if (uri != null) imgQR.setImage(new Image(uri));
        if (text != null) txtDecoded.setText(text);
    }

    @FXML
    private void speakDecoded() {
        String t = txtDecoded.getText();
        if (t != null && !t.isEmpty()) SpeechUtil.speak(t);
    }

    @FXML
    private void backToMenu() {
        // navigate back to menu
        try {
            javafx.stage.Stage stage = (javafx.stage.Stage) imgQR.getScene().getWindow();
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) { e.printStackTrace(); }
    }
}

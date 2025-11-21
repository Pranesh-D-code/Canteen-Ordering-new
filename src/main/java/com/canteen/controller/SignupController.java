package com.canteen.controller;

import com.canteen.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignupController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleSignup(javafx.event.ActionEvent event) {

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("All fields are required.");
            return;
        }

        boolean ok = userDAO.registerUser(username, password);

        if (!ok) {
            errorLabel.setText("Username already exists.");
            return;
        }

        loadPage(event, "/fxml/login.fxml");
    }

    @FXML
    private void goToLogin(javafx.event.ActionEvent event) {
        loadPage(event, "/fxml/login.fxml");
    }

    private void loadPage(javafx.event.ActionEvent event, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.canteen.controller;

import com.canteen.app.SceneManager;
import com.canteen.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userDAO.validateUser(username, password)) {
            SceneManager.switchScene("menu.fxml");
        } else {
            showAlert("Login Failed", "Invalid username or password!");
        }
    }

    @FXML
    private void handleSignup() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userDAO.registerUser(username, password)) {
            showAlert("Success", "Account created! You can now log in.");
        } else {
            showAlert("Error", "Could not create account. Try again.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

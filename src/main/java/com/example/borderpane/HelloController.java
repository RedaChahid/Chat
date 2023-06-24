package com.example.borderpane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Stack;

public class HelloController {
    @FXML
    private Button id;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    protected void onclick() throws IOException {
        if (user.getText().equals("admin") && password.getText().equals("admin")){
            Stage stage = (Stage) user.getScene().getWindow();
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("Scene2.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load());
            stage.setScene(scene2);
        }
    }
}
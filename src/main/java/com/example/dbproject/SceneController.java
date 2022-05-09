package com.example.dbproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchToRegister(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void Cancel(ActionEvent event) {

    }

    @FXML
    void Login(ActionEvent event) {

    }

}

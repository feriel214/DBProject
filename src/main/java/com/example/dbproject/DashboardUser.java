package com.example.dbproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardUser implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    String login;


    @FXML
    private Button btnFormations;
    @FXML
    private Button btnProfil;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnPasword;
    @FXML
    private AnchorPane content;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            root=FXMLLoader.load(getClass().getResource("profileUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        content.getChildren().removeAll();
        content.getChildren().setAll(root);
    }

    @FXML
    void handleClicks(ActionEvent event) throws IOException {
        if(event.getSource()==btnSignout){
            Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==btnProfil){
            root=FXMLLoader.load(getClass().getResource("profileUser.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }

        if(event.getSource()==btnPasword){
            root=FXMLLoader.load(getClass().getResource("passwordUser.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }
        if(event.getSource()==btnFormations){
            root=FXMLLoader.load(getClass().getResource("FormationsUser.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }

    }







}

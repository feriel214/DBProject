package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {

    @FXML
    private Button cancelbtn;
    @FXML
    private TextField login;
    @FXML
    private Button loginbtn;
    @FXML
    private Label loginerrormsg;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView brandingImageView;

    @FXML
    void Cancel(ActionEvent event) {
      Stage stage=(Stage) cancelbtn.getScene().getWindow();
      stage.close();
    }
    @FXML
    void Login(ActionEvent event) {

     if (login.getText().isBlank() == false && password.getText().isBlank() == false ){
         validateLogin();
     }else {
         loginerrormsg.setText("Please enter login and password ");
     }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile= new File("C:/Users/fzarr/OneDrive/stage/Phone/DCIM/Facebook/FB_IMG_1568140710491.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }
    public void validateLogin(){
        DatabaseConnection connect= new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String verifyLogin= "SELECT count(1) FROM `utilisateur` WHERE Login='"+login.getText()+"' AND Password='"+password.getText()+"'";
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1) ==1){
                    loginerrormsg.setText("Congratulations ! ");
                }else {

                    loginerrormsg.setText("Invalid login,Please try again !");
                }
            }
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

}

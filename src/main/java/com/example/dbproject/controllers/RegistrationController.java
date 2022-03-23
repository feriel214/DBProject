package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    @FXML
    private DatePicker birthdate;
    @FXML
    private PasswordField confirmpwd;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Button registrationBtn;
    @FXML
    private ImageView logoregistraion;
    @FXML
    private ImageView home;
    @FXML
    private Button cancel;
    @FXML
    private Label registrationerrormsg;
    @FXML
    private Label pwdmatchmsg;


    @FXML
    void Registration(ActionEvent event) {
        if(password.getText().equals(confirmpwd.getText())){
            pwdmatchmsg.setText("");
            registerUser();
        }else{
            pwdmatchmsg.setText("Password does not match");
        }

    }
    public void registerUser(){
        validateRegistration();
    }
    @FXML
    void Cancel(ActionEvent event) {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile= new File("C:/Users/fzarr/IdeaProjects/DBProject/src/main/resources/images/reg.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        logoregistraion.setImage(brandingImage);

        File homeFile= new File("C:/Users/fzarr/IdeaProjects/DBProject/src/main/resources/images/home_icon.png");
        Image homeImage = new Image(homeFile.toURI().toString());
        home.setImage(homeImage);
    }
    public void validateRegistration(){
        DatabaseConnection connect= new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        String nom=firstname.getText();
        String prenom=lastname.getText();
        String lgn=login.getText();
        String pwd=password.getText();
        System.out.println("date \n "+birthdate.getValue());
        LocalDate date = birthdate.getValue();

        String insertUser="INSERT INTO user_registration(nom,prenom,login,password,datenaiss) VALUES ('";
        String userValues=nom+"','"+prenom+"','"+lgn+"','"+pwd+"','"+date+"')";
        String insertToRegister=insertUser+userValues;
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(insertToRegister);
            Refresh();
            registrationerrormsg.setText("User has been registred successfully ! ");
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Refresh(){
        this.firstname.setText("");
        this.lastname.setText("");
        this.login.setText("");
        this.password.setText("");
        this.confirmpwd.setText("");
        this.birthdate.setValue(LocalDate.now());
    }

     /*@FXML
    void Registration(ActionEvent event) {
     try{
        Parent root= FXMLLoader.load(getClass().getResource("registration.fxml"));
        Stage registerStage = new Stage();
        registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root,520,400));
        registerStage.show();
     }catch(Exception e){
         e.printStackTrace();
         e.getCause();
     }
    }*/



}

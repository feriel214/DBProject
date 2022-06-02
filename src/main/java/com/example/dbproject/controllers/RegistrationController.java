package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private Label pwdmatchmsg;

    static String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @FXML
    void Registration(ActionEvent event) {
        if(password.getText().equals(confirmpwd.getText())){
            pwdmatchmsg.setText("");
            registerUser();
        }else{
            pwdmatchmsg.setText("Password does not match");
        }

    }

    static public boolean checkEmptyInput(TextField tf) {
        if(tf.getText() == "") {
            tf.getStyleClass().add("error");
            return false;
        }
        tf.getStyleClass().remove("error");
        return true;

    }


    static public boolean checkEmptyInput(PasswordField pf) {
        if(pf.getText() == null) {
            pf.getStyleClass().add("error");
            return false;
        }
        pf.getStyleClass().remove("error");
        return true;

    }

    static public boolean checkConfirmPasswordInput(TextField passwordTf,TextField confirmPasswordTf) {
        if(!checkEmptyInput(passwordTf) || !checkEmptyInput(confirmPasswordTf))
            return false;

        else if(!passwordTf.getText().equals(confirmPasswordTf.getText())) {
            confirmPasswordTf.getStyleClass().add("error");
            return false;
        }
        confirmPasswordTf.getStyleClass().remove("error");
        return true;
    }

    static public boolean checkEmptyInput(DatePicker dp) {
        if(dp.getValue() == null) {
            dp.getStyleClass().add("error");
            return false;
        }
        dp.getStyleClass().remove("error");
        return true;

    }
    public void registerUser(){
        validateRegistration();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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





            checkEmptyInput(firstname);
            checkEmptyInput(lastname);



            String insertUser="INSERT INTO user_registration(nom,prenom,login,password,datenaiss) VALUES ('";
            String userValues=nom+"','"+prenom+"','"+lgn+"','"+pwd+"','"+date+"')";
            String insertToRegister=insertUser+userValues;
            String query2="INSERT INTO `utilisateur`(`Login`, `Password`, `role`) VALUES ('"+lgn+"','"+pwd+"',"+"2)";
            try{
                Statement stmt=connectDB.createStatement();
                stmt.executeUpdate(insertToRegister);
                stmt.execute(query2);
                Refresh();

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



}

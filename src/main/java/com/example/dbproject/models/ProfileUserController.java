package com.example.dbproject.models;

import com.example.dbproject.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ProfileUserController implements Initializable  {
    @FXML
    private DatePicker birthdate;
    @FXML
    private PasswordField confirmpwd;
    @FXML
    private Button editBtn;
    @FXML
    private TextField lastname;
    @FXML
    private TextField firstname;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    User user;

    @FXML
    void EditProfile(ActionEvent event) {
        Integer ad=parseInt(String.valueOf(birthdate.getValue()).substring(0,4));
        Integer md=parseInt(String.valueOf(birthdate.getValue()).substring(5,7));
        Integer jd=parseInt(String.valueOf(birthdate.getValue()).substring(8,10));
        Instant instant1 = Instant.from(this.birthdate.getValue().atStartOfDay(ZoneId.systemDefault()));
        User user=new User( firstname.getText(),lastname.getText(),login.getText(),password.getText(),Date.from(instant1));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getUserByLogin(String log){
     String query="SELECT `id_user`, `nom`, `prenom`, `login`, `password`, `datenaiss` FROM `user_registration` WHERE login = "+log;
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){
              this.user=new User(queryResult.getInt(0),queryResult.getString(1),queryResult.getString(2),queryResult.getString(3),queryResult.getString(4),queryResult.getDate(5));
              lastname.setText(user.getNom());
              firstname.setText(user.getNom());
              login.setText(user.getLogin());
              password.setText(user.getPassword());
                Integer ad=parseInt(String.valueOf(birthdate.getValue()).substring(0,4));
                Integer md=parseInt(String.valueOf(birthdate.getValue()).substring(5,7));
                Integer jd=parseInt(String.valueOf(birthdate.getValue()).substring(8,10));
              birthdate.setValue();
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void refresh(){
         firstname.setText("");
         lastname.setText("");
         login.setText("");
         password.setText("");
         confirmpwd.setText("");
         birthdate.setValue(null);
    }
}

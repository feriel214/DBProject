package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import com.example.dbproject.models.Profile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ParticipantController  implements Initializable  {
    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();

    @FXML
    private Button addParticant;
    @FXML
    private DatePicker birthdateParticant;
    @FXML
    private Button cancel;
    @FXML
    private ImageView home;
    @FXML
    private TextField lastnameParticant;
    @FXML
    private ImageView logoregistraion;
    @FXML
    private TextField matParticant;
    @FXML
    private TextField nameParticant;
    @FXML
    private ComboBox<String> profilParticant= new ComboBox<String>();
    @FXML
    private Label pwdmatchmsg;
    @FXML
    private Label registrationerrormsg;



    @FXML
    void Cancel(ActionEvent event) {

    }

    @FXML
    void Registration(ActionEvent event) {

    }

    public void getProfile(){
         //Declare a SELECT statement
         String request="SELECT * FROM profil";
            //Execute SELECT statement
            try{
                //Get ResultSet from dbExecuteQuery method
                Statement stmt=connectDB.createStatement();
                ResultSet profils  = stmt.executeQuery(request);

                while (profils.next()){
                    this.profilParticant.getItems().add(profils.getString(2));
                }


            }catch(Exception e ){
                e.printStackTrace();
                e.getCause();
            }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.getProfile();
    }
}

package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import com.example.dbproject.models.Profile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProfileController  implements Initializable {
    private Profile profile;

    @FXML
    private Button AddProfileBtn;
    @FXML
    private Button cancel;
    @FXML
    private ImageView home;
    @FXML
    private ImageView logoregistraion;
    @FXML
    private TextField libelleProfile;
    @FXML
    private Label pwdmatchmsg;
    @FXML
    private Label registrationerrormsg;

    @FXML
    void Cancel(ActionEvent event) {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void AddProfile(ActionEvent event) {
        if(libelleProfile.getText().isEmpty() == false){
            PostProfile();
        }else{
            pwdmatchmsg.setText("Password does not match");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void PostProfile(){
        DatabaseConnection connect= new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        profile=new Profile(libelleProfile.getText());

        String insertProfile="INSERT INTO profil (libelle) VALUES ('"+profile.getLibelle()+"')";

        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(insertProfile);
            Refresh();
            registrationerrormsg.setText("Profile has been Added successfully !");
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Refresh(){
        this.libelleProfile.setText("");

    }

}

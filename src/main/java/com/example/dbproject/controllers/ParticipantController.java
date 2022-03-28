package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import com.example.dbproject.models.Formateur;
import com.example.dbproject.models.Participant;
import com.example.dbproject.models.Profile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ParticipantController  implements Initializable  {
    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    Integer SelectedProfil;
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
    private ComboBox profilParticant;
    @FXML
    private Label pwdmatchmsg;
    @FXML
    private Label registrationerrormsg;




    @FXML
    void Registration(ActionEvent event) {
         this.ValidateParticipantForm();
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

    public void ValidateParticipantForm(){
        this.PostParticipant();


    }

    public void PostParticipant() {
        Participant part=new Participant(parseInt(this.matParticant.getText()),this.nameParticant.getText(),this.lastnameParticant.getText(),this.SelectedProfil,this.birthdateParticant.getValue() );

        String query="INSERT INTO participant (matricule_participant,nom,prenom,date_naissance,id_profil) VALUES (";
        String queryValues="'"+part.getMatricule_participant()+"','"+part.getNom()+"','"+part.getPrenom()+"','"+part.getDate_naissance()+"',"+part.getId_profile()+")";
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(query+queryValues);
            Refresh();
            registrationerrormsg.setText("Participant has been addedd successfully !");
            registrationerrormsg.setStyle("color:green");
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void getProfileByID(String profil){

        String query= "SELECT code_profil FROM `profil` WHERE libelle='"+profil+"'";
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){
                this.SelectedProfil=queryResult.getInt(1);
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Refresh(){
        this.matParticant.setText("");
        this.nameParticant.setText("");
        this.lastnameParticant.setText("");
        this.birthdateParticant=new DatePicker(LocalDate.now());
        this.profilParticant=new ComboBox<>();
    }

    @FXML
    void ProfileEvent(ActionEvent event) {
        String s = profilParticant.getSelectionModel().getSelectedItem().toString();
        this.getProfileByID(s);
    }

    @FXML
    void Cancel(ActionEvent event) {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }

}

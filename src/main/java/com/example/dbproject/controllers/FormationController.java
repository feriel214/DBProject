package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import com.example.dbproject.models.Formation;
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
import java.time.Period;
import java.util.Date;
import java.util.ResourceBundle;

public class FormationController implements Initializable {

    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    Integer SelectedTrainer,SelectedDomain;
    private Integer annee,mois,nombre_jours;
    @FXML
    private ComboBox Domaine;
    @FXML
    private DatePicker EndDate;
    @FXML
    private ComboBox Trainer;
    @FXML
    private Button addTraining;
    @FXML
    private Button cancel;
    @FXML
    private ImageView home;
    @FXML
    private ImageView logoregistraion;
    @FXML
    private Label pwdmatchmsg;
    @FXML
    private Label registrationerrormsg;
    @FXML
    private DatePicker startDate;
    @FXML
    private TextField trainingName;




    @FXML
    void Registration(ActionEvent event) {
         this.PostTraining();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       this.getDomaine();
       this.getTrainers();
    }
    public void Refresh(){
        this.trainingName.setText("");
        this.startDate= new DatePicker();
        this.EndDate= new DatePicker();
        this.Domaine=new ComboBox();
        this.Trainer=new ComboBox();
    }
    public void PostTraining(){
        LocalDate sdate=this.startDate.getValue();
        LocalDate  edate=this.EndDate.getValue();
        Period period=Period.between(sdate,edate);

        Formation formation=new Formation(trainingName.getText(),this.SelectedDomain,period.getDays(),period.getYears(),period.getMonths(),this.SelectedTrainer);

         System.out.println(trainingName.getText()+this.SelectedDomain+period.getDays()+period.getYears()+period.getMonths()+this.SelectedTrainer);
        String query="INSERT INTO formation ( intitule,domaine,nombre_jours,annee,mois,formateur) VALUES (";
        String queryValues="'"+formation.getIntitule()+"',"+formation.getDomaine()+","+formation.getNombre_jours()+","+formation.getAnnee()+","+formation.getMois()+","+formation.getFormateur()+")";
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(query+queryValues);
            Refresh();
            registrationerrormsg.setText("Training has been addedd successfully !");
            registrationerrormsg.setStyle("color:green");
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }



    }
    public void getTrainerByID(String trainer){
        String query= "SELECT Code_formateur FROM `formateur` WHERE nom='"+trainer+"'";
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){
                this.SelectedTrainer=queryResult.getInt(1);
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void getDomainByID(String domain){
        System.out.println("nom domaine "+domain);
        String query= "SELECT code_domaine FROM `domaine` WHERE libelle='"+domain+"'";
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){
                this.SelectedDomain=queryResult.getInt(1);
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }


    @FXML
    void Cancel(ActionEvent event) {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }


    @FXML
    void DomainEvent(ActionEvent event) {
        String s = Domaine.getSelectionModel().getSelectedItem().toString();
        this.getDomainByID(s);

    }

    @FXML
    void TrainerEvent(ActionEvent event) {
        String s = Trainer.getSelectionModel().getSelectedItem().toString();
        this.getTrainerByID(s);

    }

    public void getDomaine(){
        //Declare a SELECT statement
        String request="SELECT * FROM domaine";
        //Execute SELECT statement
        try{
            //Get ResultSet from dbExecuteQuery method
            Statement stmt=connectDB.createStatement();
            ResultSet domaines  = stmt.executeQuery(request);

            while (domaines.next()){
                this.Domaine.getItems().add(domaines.getString(2));
            }
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void getTrainers(){
        //Declare a SELECT statement
        String request="SELECT * FROM formateur";
        //Execute SELECT statement
        try{
            //Get ResultSet from dbExecuteQuery method
            Statement stmt=connectDB.createStatement();
            ResultSet formateurs  = stmt.executeQuery(request);

            while (formateurs.next()){
                this.Trainer.getItems().add(formateurs.getString(2));
            }
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }




}

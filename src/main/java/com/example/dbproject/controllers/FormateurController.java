package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import com.example.dbproject.models.Formateur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class FormateurController   implements Initializable  {

    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();

    private Integer SelectedDomain;
    @FXML
    private Button addTrainer;
    @FXML
    private Button cancel;
    @FXML
    private ComboBox<String> domaines = new ComboBox<String>();
    @FXML
    private ImageView home;
    @FXML
    private TextField lastnameTrainer;
    @FXML
    private ImageView logoregistraion;
    @FXML
    private TextField mailTrainer;
    @FXML
    private Label registrationerrormsg;
    @FXML
    private TextField telTrainer;
    @FXML
    private TextField trainerName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile= new File("C:/Users/fzarr/IdeaProjects/DBProject/src/main/resources/images/reg.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        logoregistraion.setImage(brandingImage);

        File homeFile= new File("C:/Users/fzarr/IdeaProjects/DBProject/src/main/resources/images/home_icon.png");
        Image homeImage = new Image(homeFile.toURI().toString());
        home.setImage(homeImage);

        this.getDomains();
    }


    @FXML
    void AddTrainer(ActionEvent event) {
       this.ValidateTrainerForm();
    }


    public void PostTrainer(){

       Formateur trainer=new Formateur( this.trainerName.getText(), this.lastnameTrainer.getText(),this.mailTrainer.getText(),parseInt(this.telTrainer.getText()),this.SelectedDomain);
       String query="INSERT INTO formateur (nom,prenom,email,tel,code_domaine) VALUES ('";
       String queryValues="'"+trainer.getNom()+"',"+trainer.getPrenom()+"',"+trainer.getEmail()+"',"+trainer.getTel()+"',"+trainer.getCode_domaine()+"')";
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(query+queryValues);
            Refresh();
            registrationerrormsg.setText("Trainer has been addedd successfully !");
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void ValidateTrainerForm(){
        this.PostTrainer();
      /* if( isMailValid(mailTrainer.getText()) &&   trainerName.getText().isBlank() == false
               && lastnameTrainer.getText().isBlank() ==false && isTelValid(telTrainer.getText())){
               this.PostTrainer();
       }else{
           registrationerrormsg.setText("Please check your Data !");
       }
       */

    }
    public static boolean isTelValid(String s)
    {

        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 7 or 8 or 9.
        // 3) Then contains 9 digits
        Pattern p = Pattern.compile("(0|91)?[7-9][0-9]{8}");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    public static boolean isMailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public void getDomains(){
        //Declare a SELECT statement
        String request="SELECT * FROM domaine";
        //Execute SELECT statement
        try{
            //Get ResultSet from dbExecuteQuery method
            Statement stmt=connectDB.createStatement();
            ResultSet domaines  = stmt.executeQuery(request);

            while (domaines.next()){
                this.domaines.getItems().add(domaines.getString(2));
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void Refresh(){
        this.trainerName.setText("");
        this.lastnameTrainer.setText("");
        this.telTrainer.setText("");
        this.mailTrainer.setText("");
        this.domaines=new ComboBox<String>();
    }

    @FXML
    void event(ActionEvent event) {
       // this.SelectedDomain= domaines.getSelectionModel().getSelectedItem();
        System.out.println(domaines.getSelectionModel().getSelectedItem());
    }

    @FXML
    void Cancel(ActionEvent event) {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }


}

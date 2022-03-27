package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import com.example.dbproject.models.Domaine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DomaineController  implements Initializable {

    @FXML
    private Button AddDomaineBtn;
    @FXML
    private Button cancel;
    @FXML
    private TextField domaineLibelle;
    @FXML
    private ImageView home;
    @FXML
    private ImageView logoregistraion;
    @FXML
    private Label domaineaddmsg;


    @FXML
    void AddDomaine(ActionEvent event) {
      this.PostDomaine();
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

    public void PostDomaine(){
        DatabaseConnection connect= new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        Domaine domaine=new Domaine(domaineLibelle.getText());

        String insertDomaine="INSERT INTO domaine (libelle) VALUES ('"+domaine.getLibelle()+"')";

        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(insertDomaine);
            Refresh();
            domaineaddmsg.setText("Domaine has been registred successfully !");
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Refresh(){
        this.domaineLibelle.setText("");

    }
}

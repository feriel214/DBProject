package com.example.dbproject;

import com.example.dbproject.models.Formateur;
import com.example.dbproject.models.Participant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class FormateurTableView implements Initializable {

    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    private Stage stage;
    private Scene scene;
    private Parent root;
    Integer SelectedDomain;
    @FXML
    private Button addformateur;
    @FXML
    private ComboBox domaines;
    @FXML
    private TextField lastnameTrainer;
    @FXML
    private TextField mailTrainer;
    @FXML
    private TextField telTrainer;
    @FXML
    private TextField trainerName;
    @FXML
    private Button suppBtn;
    @FXML
    private Button editBtn;
    @FXML
    private TableView<Formateur> FormateuresTable;
    @FXML
    private TableColumn<Formateur, Integer> domaineCol;
    @FXML
    private TableColumn<Formateur, String> emailCol;
    @FXML
    private TableColumn<Formateur,Integer> idCol;
    @FXML
    private TableColumn<Formateur, String> nomCol;
    @FXML
    private TableColumn<Formateur, String> prenomCol;
    @FXML
    private TableColumn<Formateur,Integer> telCol;
    @FXML
    private TableColumn<Formateur, String> ActionsCol;
    @FXML
    private TextField idFormat;
    @FXML
    ObservableList<Formateur> formateurList = FXCollections.observableArrayList();
    Formateur formateur = null ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editBtn.setVisible(false);
        suppBtn.setVisible(false);
        idFormat.setVisible(false);
        this.getDomains();
        loadForamteurData();
    }

    @FXML
    void Addformateur(ActionEvent event) throws IOException {
        Formateur trainer=new Formateur( this.trainerName.getText(), this.lastnameTrainer.getText(),this.mailTrainer.getText(),parseInt(this.telTrainer.getText()),this.SelectedDomain);
        String query="INSERT INTO formateur (nom,prenom,email,tel,code_domaine) VALUES (";
        String queryValues="'"+trainer.getNom()+"','"+trainer.getPrenom()+"','"+trainer.getEmail()+"',"+trainer.getTel()+","+trainer.getCode_domaine()+")";
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(query+queryValues);
            Refresh();
            refershTable();




        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void loadForamteurData(){
        this.refershTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("code_formateur"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        domaineCol.setCellValueFactory(new PropertyValueFactory<>("code_domaine"));


    }

    public void refershTable(){

        String query= "SELECT * FROM `formateur`";
        try{
            formateurList.clear();
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){

                formateurList.add(new  Formateur(
                        queryResult.getInt("Code_formateur"),
                        queryResult.getString("nom"),
                        queryResult.getString("prenom"),
                        queryResult.getString("email"),
                        queryResult.getInt("tel"),
                        queryResult.getInt("code_domaine")));

                this.FormateuresTable.setItems(formateurList);
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }




    @FXML
    void editFormateur(ActionEvent event) {
        Formateur clickedom=FormateuresTable.getSelectionModel().getSelectedItem();
        if(this.SelectedDomain==null){
            this.SelectedDomain=parseInt(String.valueOf(clickedom.getCode_domaine()));
        }

        String updateFormation="UPDATE formateur SET`nom`='"+trainerName.getText()+"',`prenom`='"+lastnameTrainer.getText()+"',`email`='"+mailTrainer.getText()+"',`tel`="+telTrainer.getText()+",`code_domaine`="+SelectedDomain+" WHERE `Code_formateur`= "+parseInt(idFormat.getText());

        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(updateFormation);
            Refresh();
            this.refershTable();
            this.editBtn.setVisible(false);
            this.suppBtn.setVisible(false);
            this.addformateur.setVisible(true);
            this.idFormat.setVisible(false);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void event(ActionEvent event) {
        String s =(String) domaines.getSelectionModel().getSelectedItem();
        this.getDomaineID(s);
    }

    @FXML
    void suppItem(ActionEvent event) {
        String deleteFormation="DELETE FROM `formateur` WHERE `Code_formateur`= "+parseInt(idFormat.getText());
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(deleteFormation);
            Refresh();
            this.refershTable();
            this.editBtn.setVisible(false);
            this.suppBtn.setVisible(false);
            this.addformateur.setVisible(true);
            this.idFormat.setVisible(false);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
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
            stmt.close();
            domaines.close();

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
        this.domaines.setValue(null);
        this.getDomains();
    }
    public void getDomaineID(String domain){
        System.out.println("code domaine: "+domain);
        String query= "SELECT code_domaine FROM `domaine` WHERE libelle='"+domain+"'";
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){
                System.out.println("queryResult.getInt(1) : "+queryResult.getInt(1));
                this.SelectedDomain=queryResult.getInt(1);
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void rowClicked(javafx.scene.input.MouseEvent mouseEvent) {

        Formateur clickedom=FormateuresTable.getSelectionModel().getSelectedItem();

        this.trainerName.setText(String.valueOf(clickedom.getNom()));
        this.lastnameTrainer.setText(String.valueOf(clickedom.getPrenom()));
        this.telTrainer.setText(String.valueOf(clickedom.getTel()));
        this.mailTrainer.setText(String.valueOf(clickedom.getEmail()));
        this.domaines.setValue(String.valueOf(clickedom.getCode_domaine()));
        this.idFormat.setText((clickedom.getCode_formateur()).toString());
        addformateur.setVisible(true);
        editBtn.setVisible(true);
        suppBtn.setVisible(true);
        this.idFormat.setVisible(true);

    }



}

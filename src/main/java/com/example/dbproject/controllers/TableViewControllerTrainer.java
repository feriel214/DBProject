package com.example.dbproject.controllers;

import com.example.dbproject.DatabaseConnection;
import com.example.dbproject.models.Formateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TableViewControllerTrainer implements Initializable {
    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    Integer SelectedDomain;
    Formateur formateur =new Formateur();
    ObservableList<Formateur> formateurList= FXCollections.observableArrayList();
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
    private TableColumn<Formateur, ?> ActionsCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    private void loadData() {
        this.refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("code_formateur"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        domaineCol.setCellValueFactory(new PropertyValueFactory<>("code_domaine"));
       // ActionsCol.setCellValueFactory(new PropertyValueFactory<>("Actions"));


    }


    private void refreshTable(){
        formateurList.clear();
        this.getTrainers();

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
                formateurList.add(new Formateur(
                        formateurs.getInt("Code_formateur"),
                        formateurs.getString("nom"),
                        formateurs.getString("prenom"),
                        formateurs.getString("email"),
                        formateurs.getInt("tel"),
                        formateurs.getInt("code_domaine")));
                FormateuresTable.setItems(formateurList);

            }
            stmt.close();
            formateurs.close();

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }


}

package com.example.dbproject;

import com.example.dbproject.models.Domaine;
import com.example.dbproject.models.Formateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
public class DomaineTableView implements Initializable  {



    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    @FXML
    private Button adddomaine;
    @FXML
    private TableView<Domaine> domainesTable;
    @FXML
    private TableColumn<Domaine, Integer> idCol;
    @FXML
    private TableColumn<Domaine,String> libCol;
    ObservableList<Domaine> domainesList = FXCollections.observableArrayList();
    Domaine domain = null ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    private void loadData() {
        this.refershTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("code_domaine"));
        libCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));

    }

    public void refershTable() {

        String query = "SELECT * FROM `domaine` ";
        try {
            domainesList.clear();
            Statement stmt = connectDB.createStatement();
            ResultSet queryResult = stmt.executeQuery(query);

            while (queryResult.next()) {
                domainesList.add(new Domaine(
                        queryResult.getInt("code_domaine"),
                        queryResult.getString("libelle")));
                this.domainesTable.setItems(domainesList);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }


    @FXML
    void Adddomaine(ActionEvent event) {

    }


}

package com.example.dbproject;

import com.example.dbproject.models.Domaine;
import com.example.dbproject.models.Formation;
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

public class FormationTableView implements Initializable {


    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    @FXML
    private TableView<Formation> FormationTable;
    @FXML
    private TableColumn<Formation, String> actionsCol;
    @FXML
    private Button addFormation;
    @FXML
    private TableColumn<Formation, Integer> domaineCol;
    @FXML
    private TableColumn<Formation, Integer> formateurCol;
    @FXML
    private TableColumn<Formation, Integer> idCol;
    @FXML
    private TableColumn<Formation, Integer> jrCol;
    @FXML
    private TableColumn<Formation, String> libCol;
    @FXML
    private TableColumn<Formation, Integer> moisCol;
    @FXML
    private TableColumn<Formation, Integer> yearCol;
    ObservableList<Formation> formationsList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      loadData();
    }

    private void loadData() {
        this.refershTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("code_formation"));
        libCol.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        domaineCol.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        jrCol.setCellValueFactory(new PropertyValueFactory<>("nombre_jours"));
        moisCol.setCellValueFactory(new PropertyValueFactory<>("mois"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("annee"));
        formateurCol.setCellValueFactory(new PropertyValueFactory<>("formateur"));



    }

    public void refershTable() {

        String query = "SELECT * FROM `formation` ";
        try {
            formationsList.clear();
            Statement stmt = connectDB.createStatement();
            ResultSet queryResult = stmt.executeQuery(query);

            while (queryResult.next()) {


                formationsList.add(new Formation(
                        queryResult.getInt("code_formation"),
                        queryResult.getString("intitule"),
                        queryResult.getInt("domaine"),
                        queryResult.getInt("nombre_jours"),
                        queryResult.getInt("annee"),
                        queryResult.getInt("mois"),
                        queryResult.getInt("formateur")));
                this.FormationTable.setItems(formationsList);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }


    @FXML
    void AddFormation(ActionEvent event) {

    }
}

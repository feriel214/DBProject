package com.example.dbproject;

import com.example.dbproject.models.Formation;
import com.example.dbproject.models.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class FormationUserController implements Initializable {


    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();


    @FXML
    private TableView<Formation> FormationTable;

    @FXML
    private TableColumn<Formation, Integer> domaineCol;
    @FXML
    private TableColumn<Formation, Integer> formateurCol;
    @FXML
    private TableColumn<Formation, Integer> idCol;
    @FXML
    private TableColumn<Formation, LocalDate> date_debutCol;
    @FXML
    private TableColumn<Formation, String> libCol;
    @FXML
    private TableColumn<Formation, LocalDate> date_finCol;
    private Label label;
    ObservableList<Formation> formationsList = FXCollections.observableArrayList();
    Integer mat_user;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();

    }



    private void loadData() {
        this.refershTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("code_formation"));
        libCol.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        domaineCol.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        formateurCol.setCellValueFactory(new PropertyValueFactory<>("formateur"));
        date_debutCol.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_finCol.setCellValueFactory(new PropertyValueFactory<>("date_fin"));





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
                        queryResult.getInt("formateur"),
                        queryResult.getDate("date_debut"),
                        queryResult.getDate("date_fin")));
                this.FormationTable.setItems(formationsList);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    public void rowClicked(javafx.scene.input.MouseEvent mouseEvent) {

        Formation clickedom=FormationTable.getSelectionModel().getSelectedItem();


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setHeaderText("Voulez-vous participer ?");


            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {
                this.label.setText("pas de selection");
            } else if (option.get() == ButtonType.OK) {
                this.label.setText("vous avez participé avec succées!");
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


                String query="INSERT INTO `formation_participants`(`code_formation`, `matricule_participant`, `date_inscription`) VALUES ("+clickedom.getCode_formation()+","+this.mat_user+",'"+date.format(formatter)+"'";




            } else if (option.get() == ButtonType.CANCEL) {
                this.label.setText("Annulé!");
            } else {
                this.label.setText("-");
            }

        Integer ad=parseInt(String.valueOf(clickedom.getDate_fin()).substring(0,4));
        Integer af=parseInt(String.valueOf(clickedom.getDate_fin()).substring(0,4));
        Integer md=parseInt(String.valueOf(clickedom.getDate_fin()).substring(5,7));
        Integer mf=parseInt(String.valueOf(clickedom.getDate_fin()).substring(5,7));
        Integer jd=parseInt(String.valueOf(clickedom.getDate_fin()).substring(8,10));
        Integer jf=parseInt(String.valueOf(clickedom.getDate_fin()).substring(8,10));



    }

    public Integer getMatUser(String mat) {
        Integer matri=0;
        String query="SELECT `matricule_participant` FROM `participant` WHERE nom='"+mat+"'";
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){
               matri=queryResult.getInt("matricule_participant");
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
        return matri;
    }


}

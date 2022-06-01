package com.example.dbproject;

import com.example.dbproject.models.Formation;
import com.example.dbproject.models.Participant;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ParticipantTableView implements Initializable {

    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    @FXML
    private TableView<Participant> ParticipantsTable;
    @FXML
    private Button addParticipant;
    @FXML
    private TableColumn<Participant, Date> birthCol;
    @FXML
    private TableColumn<Participant, Integer> matCol;
    @FXML
    private TableColumn<Participant,String> nomCol;
    @FXML
    private TableColumn<Participant, String> prenomCol;
    @FXML
    private TableColumn<Participant,Integer> profilCol;
    ObservableList<Participant>  participantsList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }


    private void loadData() {
        this.refershTable();

        matCol.setCellValueFactory(new PropertyValueFactory<>("matricule_participant"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        birthCol.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        profilCol.setCellValueFactory(new PropertyValueFactory<>("id_profile"));


    }

    public void refershTable() {

        String query = "SELECT * FROM `participant` ";
        try {
            participantsList.clear();
            Statement stmt = connectDB.createStatement();
            ResultSet queryResult = stmt.executeQuery(query);

            while (queryResult.next()) {


                participantsList.add(new Participant(
                        queryResult.getInt("matricule_participant"),
                        queryResult.getString("nom"),
                        queryResult.getString("prenom"),
                        queryResult.getInt("id_profil"),
                        queryResult.getDate("date_naissance").toLocalDate()));
                this.ParticipantsTable.setItems(participantsList);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }
    @FXML
    void AddParticipant(ActionEvent event) {

    }
    @FXML
    void ProfileEvent(ActionEvent event) {

    }
}

package com.example.dbproject;

import com.example.dbproject.models.Profile;
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

public class ProfilTableView  implements Initializable {


    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    @FXML
    private Button addProfil;
    @FXML
    private TableView<Profile> profilesTable;
    @FXML
    private TableColumn<Profile,Integer> idCol;
    @FXML
    private TableColumn<Profile,String> libCol;
    ObservableList<Profile> profilesList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
    private void loadData() {
        this.refershTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("code_profile"));
        libCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));

    }

    public void refershTable() {

        String query = "SELECT * FROM `profil` ";
        try {
            profilesList.clear();
            Statement stmt = connectDB.createStatement();
            ResultSet queryResult = stmt.executeQuery(query);

            while (queryResult.next()) {
                profilesList.add(new Profile(
                        queryResult.getString("libelle"),
                        queryResult.getInt("code_profil")
                                       ));
                this.profilesTable.setItems(profilesList);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }




    @FXML
    void AddProfil(ActionEvent event) {

    }
}

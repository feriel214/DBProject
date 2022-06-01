package com.example.dbproject;

import com.example.dbproject.models.Domaine;
import com.example.dbproject.models.Profile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ProfilTableView  implements Initializable {


    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    @FXML
    private TextField Idprofil;
    @FXML
    private Button addProfil;
    @FXML
    private Button editBtn;
    @FXML
    private Button suppBtn;
    @FXML
    private TextField profLib;
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
        Idprofil.setVisible(false);
        suppBtn.setVisible(false);
        editBtn.setVisible(false);
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
        DatabaseConnection connect= new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        Domaine domaine=new Domaine(profLib.getText());

        String insertDomaine="INSERT INTO domaine (libelle) VALUES ('"+domaine.getLibelle()+"')";

        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(insertDomaine);
            Refresh();
            this.refershTable();
            //domaineaddmsg.setText("Domaine Ajouter Avec succées!");
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void editProfil(ActionEvent event) {
        String updateDomaine="UPDATE profil SET `libelle`='"+profLib.getText()+"' WHERE `code_profil`= "+parseInt(Idprofil.getText());

        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(updateDomaine);
            Refresh();
            this.refershTable();
            this.editBtn.setVisible(false);
            this.Idprofil.setVisible(false);
            suppBtn.setVisible(false);
            this.addProfil.setVisible(true);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void Refresh(){
        this.Idprofil.setText("");
        this.profLib.setText("");

    }


    @FXML
    void suppItem(ActionEvent event) {
        String deleteFormation="DELETE FROM `profil` WHERE `code_profil`= "+parseInt(Idprofil.getText());
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(deleteFormation);
            Refresh();
            this.refershTable();
            this.editBtn.setVisible(false);
            this.suppBtn.setVisible(false);
            this.addProfil.setVisible(true);
            this.Idprofil.setVisible(false);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void rowClicked(javafx.scene.input.MouseEvent mouseEvent) {
        Profile clickedom=profilesTable.getSelectionModel().getSelectedItem();
        this.profLib.setText(String.valueOf(clickedom.getLibelle()));
        this.Idprofil.setText((clickedom.getCode_profile()).toString());
        addProfil.setVisible(true);
        editBtn.setVisible(true);
        suppBtn.setVisible(true);
        this.Idprofil.setVisible(true);

    }


}

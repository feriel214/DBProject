package com.example.dbproject;

import com.example.dbproject.models.Domaine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class DomaineTableView implements Initializable  {

    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    @FXML
    private Button AddDomaineBtn;
    @FXML
    private TableView<Domaine> domainesTable;
    @FXML
    private TableColumn<Domaine, Integer> idCol;
    @FXML
    private TableColumn<Domaine,String> libCol;
    @FXML
    private TextField IdDom;
    @FXML
    private Button editBtn;
    @FXML
    private Button suppBtn;
    @FXML
    private TextField domaineLibelle;
    ObservableList<Domaine> domainesList = FXCollections.observableArrayList();
    Domaine domain = null ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("code_domaine"));
        libCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        editBtn.setVisible(false);
        this.IdDom.setVisible(false);
        this.suppBtn.setVisible(false);
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
    void AddDomaine(ActionEvent event) {
        this.PostDomaine();
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
            this.refershTable();
            //domaineaddmsg.setText("Domaine Ajouter Avec succées!");
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }



    public void Refresh(){
        this.IdDom.setText("");
        this.domaineLibelle.setText("");

    }


    public void rowClicked(javafx.scene.input.MouseEvent mouseEvent) {
        Domaine clickedom=domainesTable.getSelectionModel().getSelectedItem();
        this.domaineLibelle.setText(String.valueOf(clickedom.getLibelle()));
        this.IdDom.setText((clickedom.getCode_domaine()).toString());
        AddDomaineBtn.setVisible(true);
        editBtn.setVisible(true);
        suppBtn.setVisible(true);
        this.IdDom.setVisible(true);

    }

    @FXML
    void editDomaine(ActionEvent event) {

        String updateDomaine="UPDATE domaine SET `libelle`='"+domaineLibelle.getText()+"' WHERE `code_domaine`= "+parseInt(IdDom.getText());

        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(updateDomaine);
            Refresh();
            this.refershTable();
            this.editBtn.setVisible(false);
            this.IdDom.setVisible(false);
            suppBtn.setVisible(false);
            this.AddDomaineBtn.setVisible(true);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }

    }


    @FXML
    void suppItem(ActionEvent event) {
        String deleteFormation="DELETE FROM `domaine` WHERE `code_domaine`= "+parseInt(IdDom.getText());
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(deleteFormation);
            Refresh();
            this.refershTable();
            this.editBtn.setVisible(false);
            this.suppBtn.setVisible(false);
            this.AddDomaineBtn.setVisible(true);
            this.IdDom.setVisible(false);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }
}

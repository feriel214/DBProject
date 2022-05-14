package com.example.dbproject;

import com.example.dbproject.models.Formateur;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormateurTableView implements Initializable {

    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    ObservableList<Formateur> formateurList = FXCollections.observableArrayList();
    Formateur formateur = null ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     loadForamteurData();
    }

    @FXML
    void Addformateur(ActionEvent event) throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("add-formateur.fxml"));
        stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

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

}

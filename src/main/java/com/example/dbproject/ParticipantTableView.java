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
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ParticipantTableView implements Initializable {

    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();


    @FXML
    private Button editBtn;
    @FXML
    private Button suppBtn;
    @FXML
    private TextField idPart;
    @FXML
    private DatePicker birthdateParticant;
    @FXML
    private TextField lastnameParticant;
    @FXML
    private TextField matParticant;
    @FXML
    private TextField nameParticant;
    @FXML
    private ComboBox<String> profilParticant;
    Integer SelectedProfil;
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
    @FXML
    private PasswordField password;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editBtn.setVisible(false);
        suppBtn.setVisible(false);
        idPart.setVisible(false);
        loadData();
    }


    private void loadData() {
        this.refershTable();
        this.getProfile();
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


    public void rowClicked(javafx.scene.input.MouseEvent mouseEvent) {

        Participant clickedom=ParticipantsTable.getSelectionModel().getSelectedItem();
        Integer ad=parseInt(String.valueOf(clickedom.getDate_naissance()).substring(0,4));
        Integer md=parseInt(String.valueOf(clickedom.getDate_naissance()).substring(5,7));
        Integer jd=parseInt(String.valueOf(clickedom.getDate_naissance()).substring(8,10));

        this.nameParticant.setText(String.valueOf(clickedom.getNom()));
        this.lastnameParticant.setText(String.valueOf(clickedom.getPrenom()));
        this.birthdateParticant.setValue(LocalDate.of(ad,md,jd));
        this.profilParticant.setValue(String.valueOf(clickedom.getId_profile()));
        this.idPart.setText((clickedom.getMatricule_participant()).toString());
        addParticipant.setVisible(true);
        editBtn.setVisible(true);
        suppBtn.setVisible(true);
        this.idPart.setVisible(true);

    }

    @FXML
    void editParticipant(ActionEvent event) {
        Participant clickedom=ParticipantsTable.getSelectionModel().getSelectedItem();
        if(this.SelectedProfil==null){
            this.SelectedProfil=parseInt(String.valueOf(clickedom.getId_profile()));
        }

        String updateFormation="UPDATE participant SET `nom`='"+nameParticant.getText()+"',`prenom`='"+lastnameParticant.getText()+"',`date_naissance`='"+birthdateParticant.getValue()+"',`id_profil`='"+this.SelectedProfil+"' WHERE `matricule_participant`= "+parseInt(idPart.getText());

        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(updateFormation);
            Refresh();
            this.refershTable();
            this.editBtn.setVisible(false);
           this.suppBtn.setVisible(false);
            this.addParticipant.setVisible(true);
            this.idPart.setVisible(false);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }



    @FXML
    void suppItem(ActionEvent event) {
        String deleteFormation="DELETE FROM `participant` WHERE `matricule_participant`= "+parseInt(idPart.getText());
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(deleteFormation);
            Refresh();
            this.refershTable();
            this.editBtn.setVisible(false);
            this.suppBtn.setVisible(false);
            this.addParticipant.setVisible(true);
            this.idPart.setVisible(false);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Refresh(){
        this.nameParticant.setText("");
        this.lastnameParticant.setText("");
        this.profilParticant.setValue(null);
        this.matParticant.setText("");
        this.birthdateParticant.setValue(null);
        this.password.setText("");
    }

    @FXML
    void AddParticipant(ActionEvent event) {
        Participant part=new Participant(parseInt(this.matParticant.getText()),this.nameParticant.getText(),this.lastnameParticant.getText(),this.SelectedProfil,this.birthdateParticant.getValue(),password.getText() );
        System.out.println("Participant "+part.getDate_naissance()+part.getMatricule_participant());
        String query="INSERT INTO participant (matricule_participant,nom,prenom,date_naissance,id_profil,password) VALUES (";
        String queryValues="'"+part.getMatricule_participant()+"','"+part.getNom()+"','"+part.getPrenom()+"','"+part.getDate_naissance()+"',"+part.getId_profile()+",password='"+part.getPassword()+"')";
        String query2="INSERT INTO `utilisateur`(`Login`, `Password`, `role`) VALUES ('"+part.getNom()+"','"+part.getPassword()+"',"+"2)";
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(query+queryValues);
            stmt.execute(query2);
            Refresh();
            refershTable();
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void getProfileByID(String profil){

        String query= "SELECT code_profil FROM `profil` WHERE libelle='"+profil+"'";
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){
                this.SelectedProfil=queryResult.getInt(1);
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void getProfile(){
        //Declare a SELECT statement
        String request="SELECT * FROM profil";
        //Execute SELECT statement
        try{
            //Get ResultSet from dbExecuteQuery method
            Statement stmt=connectDB.createStatement();
            ResultSet profils  = stmt.executeQuery(request);

            while (profils.next()){
                this.profilParticant.getItems().add(profils.getString(2));
            }
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void ProfileEvent(ActionEvent event) {
        String s = profilParticant.getSelectionModel().getSelectedItem().toString();
        this.getProfileByID(s);
    }
}

package com.example.dbproject;

import com.example.dbproject.models.Domaine;
import com.example.dbproject.models.Formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class FormationTableView implements Initializable {


    DatabaseConnection connect= new DatabaseConnection();
    Connection connectDB = connect.getConnection();
    Integer SelectedTrainer,SelectedDomain;
    private Integer annee,mois,nombre_jours;
    @FXML
    private ComboBox Domaine;
    @FXML
    private DatePicker EndDate;
    @FXML
    private ComboBox Trainer;
    @FXML
    private Button addTraining;
    @FXML
    private Button editBtn;
    @FXML
    private Button suppBtn;
    @FXML
    private TextField idForm;
    @FXML
    private Label registrationerrormsg;
    @FXML
    private DatePicker startDate;
    @FXML
    private TextField trainingName;
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
    private TableColumn<Formation, LocalDate> date_debutCol;
    @FXML
    private TableColumn<Formation, String> libCol;
    @FXML
    private TableColumn<Formation, LocalDate> date_finCol;
    @FXML
    private Button editFormation;
    ObservableList<Formation> formationsList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.getDomaine();
        this.getTrainers();
        this.idForm.setVisible(false);
        editBtn.setVisible(false);
        suppBtn.setVisible(false);
        loadData();
    }


    @FXML
    void Registration(ActionEvent event) {
        this.PostTraining();
    }
    public void PostTraining(){

        Instant instant1 = Instant.from(this.startDate.getValue().atStartOfDay(ZoneId.systemDefault()));
        Instant instant2 = Instant.from(this.EndDate.getValue().atStartOfDay(ZoneId.systemDefault()));


        Formation formation=new Formation(trainingName.getText(),this.SelectedDomain,this.SelectedTrainer,Date.from(instant1),Date.from(instant2));
        System.out.println("formation object "+formation.getDate_fin()+"   "+formation.getDate_debut());


        String query="INSERT INTO formation (intitule,domaine,formateur,date_debut,date_fin) VALUES (";
        String queryValues="'"+formation.getIntitule()+"',"+formation.getDomaine()+","+formation.getFormateur()+",'"+formation.getDate_debut()+"','"+formation.getDate_fin()+"')";
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(query+queryValues);
            Refresh();
            //refershTable();
            registrationerrormsg.setText("Training has been addedd successfully !");
            registrationerrormsg.setStyle("color:green");

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }



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


    @FXML
    void AddFormation(ActionEvent event) {

    }



    @FXML
    void DomainEvent(ActionEvent event) {
        String s = Domaine.getSelectionModel().getSelectedItem().toString();
        this.getDomainByID(s);

    }

    @FXML
    void TrainerEvent(ActionEvent event) {
        String s = Trainer.getSelectionModel().getSelectedItem().toString();
        this.getTrainerByID(s);

    }

    public void getDomaine(){
        //Declare a SELECT statement
        String request="SELECT * FROM domaine";
        //Execute SELECT statement
        try{
            //Get ResultSet from dbExecuteQuery method
            Statement stmt=connectDB.createStatement();
            ResultSet domaines  = stmt.executeQuery(request);

            while (domaines.next()){
                this.Domaine.getItems().add(domaines.getString(2));
            }
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
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
                this.Trainer.getItems().add(formateurs.getString(2));
            }
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void getTrainerByID(String trainer){
        String query= "SELECT Code_formateur FROM `formateur` WHERE nom='"+trainer+"'";
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){
                this.SelectedTrainer=queryResult.getInt(1);
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void getDomainByID(String domain){
        System.out.println("nom domaine "+domain);
        String query= "SELECT code_domaine FROM `domaine` WHERE libelle='"+domain+"'";
        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(query);

            while (queryResult.next()){
                this.SelectedDomain=queryResult.getInt(1);
            }

        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void Refresh(){
        this.trainingName.setText("");
        this.startDate.setValue(null);
        this.EndDate.setValue(null);
        this.Domaine.setValue(null);
        this.Trainer.setValue(null);
    }

    public void rowClicked(javafx.scene.input.MouseEvent mouseEvent) {

        Formation clickedom=FormationTable.getSelectionModel().getSelectedItem();
        Integer ad=parseInt(String.valueOf(clickedom.getDate_fin()).substring(0,4));
        Integer af=parseInt(String.valueOf(clickedom.getDate_fin()).substring(0,4));
        Integer md=parseInt(String.valueOf(clickedom.getDate_fin()).substring(5,7));
        Integer mf=parseInt(String.valueOf(clickedom.getDate_fin()).substring(5,7));
        Integer jd=parseInt(String.valueOf(clickedom.getDate_fin()).substring(8,10));
        Integer jf=parseInt(String.valueOf(clickedom.getDate_fin()).substring(8,10));

        this.trainingName.setText(String.valueOf(clickedom.getIntitule()));
        this.startDate.setValue(LocalDate.of(ad,md,jd));
        this.EndDate.setValue(LocalDate.of(af,mf,jf));
        this.Domaine.setValue(String.valueOf(clickedom.getDomaine()));
        this.Trainer.setValue(String.valueOf(clickedom.getFormateur()));
        this.idForm.setText((clickedom.getCode_formation()).toString());
        addTraining.setVisible(true);
        editBtn.setVisible(true);
        suppBtn.setVisible(true);
        this.idForm.setVisible(true);

    }


    @FXML
    void editFormation(ActionEvent event) {
        Formation clickedom=FormationTable.getSelectionModel().getSelectedItem();

        if(this.SelectedDomain==null){

            this.SelectedDomain=parseInt(String.valueOf(clickedom.getDomaine()));
        }
        if(this.SelectedTrainer==null){

            this.SelectedTrainer=parseInt(String.valueOf(clickedom.getFormateur()));
        }
        String updateFormation="UPDATE formation SET `intitule`='"+trainingName.getText()+"',domaine="+this.SelectedDomain+",formateur="+this.SelectedTrainer+",date_debut='"+startDate.getValue()+"',date_fin='"+EndDate.getValue()+"'WHERE `code_formation`= "+parseInt(idForm.getText());

        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(updateFormation);
            Refresh();
            this.refershTable();
            //domaineaddmsg.setText("Domaine Modifié Avec succées!");
            this.editBtn.setVisible(false);
            this.suppBtn.setVisible(false);
            this.addTraining.setVisible(true);
            this.idForm.setVisible(false);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }



    @FXML
    void suppFormation(ActionEvent event) {
        String deleteFormation="DELETE FROM `formation` WHERE `code_formation`= "+parseInt(idForm.getText());
        try{
            Statement stmt=connectDB.createStatement();
            stmt.executeUpdate(deleteFormation);
            Refresh();
            this.refershTable();
            this.editBtn.setVisible(false);
            this.suppBtn.setVisible(false);
            this.addTraining.setVisible(true);
            this.idForm.setVisible(false);
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

}

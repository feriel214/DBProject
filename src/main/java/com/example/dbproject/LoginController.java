package com.example.dbproject;

import com.example.dbproject.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button cancelbtn;
    @FXML
    private TextField login;
    @FXML
    private Button loginbtn;
    @FXML
    private Label loginerrormsg;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView brandingImageView;
    User user;
    @FXML
    void Cancel(ActionEvent event) {
      Stage stage=(Stage) cancelbtn.getScene().getWindow();
      stage.close();
    }
    @FXML
    void Login(ActionEvent event) {
     if (login.getText().isBlank() == false && password.getText().isBlank() == false ){
         validateLogin(event);
     }else {
         loginerrormsg.setText("Please enter login and password ");
     }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void validateLogin(ActionEvent event){
        DatabaseConnection connect= new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String verifyLogin= "SELECT * FROM `utilisateur` WHERE Login='"+login.getText()+"' AND Password='"+password.getText()+"'";

        try{
            Statement stmt=connectDB.createStatement();
            ResultSet queryResult= stmt.executeQuery(verifyLogin);

            while (queryResult.next()){

                    if(queryResult.getInt("role")==2){


                        FXMLLoader loader=new FXMLLoader(getClass().getResource("profileUser.fxml"));
                        root=loader.load();
                        ProfileUserController profileUserController=loader.getController();
                        profileUserController.loginuser=queryResult.getString("login");
                        System.out.println(" profileUserController.loginuser "+ profileUserController.loginuser);
                        profileUserController.getUserByLogin();
                        switchToDashboardUser(event);
                        //send matricule user
                        FXMLLoader loader2=new FXMLLoader(getClass().getResource("FormationsUser.fxml"));
                        root=loader2.load();
                        FormationUserController formationUserController=loader2.getController();
                        formationUserController.getMatUser(queryResult.getString("login"));
                        formationUserController.mat_user=formationUserController.getMatUser(queryResult.getString("login"));

                    }else if(queryResult.getInt("role")==1){
                        switchToDashboard(event);
                    } else {

                    loginerrormsg.setText("Invalid login,Please try again !");
                }
            }
        }catch(Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }


    @FXML
    void CreateAccount(ActionEvent event)throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToDashboard(ActionEvent event)throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToDashboardUser(ActionEvent event)throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("dashboardUser.fxml"));
        stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}

package com.example.dbproject;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.layout.AnchorPane;
        import javafx.stage.Stage;
        import org.w3c.dom.events.MouseEvent;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnDomaines;
    @FXML
    private Button btnFormateurs;
    @FXML
    private Button btnFormations;
    @FXML
    private Button btnParticipants;
    @FXML
    private Button btnProfil;
    @FXML
    private Button btnSignout;
    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void handleClicks(ActionEvent event) throws IOException{
         if(event.getSource()==btnSignout){
             Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
             stage=(Stage)((Node) event.getSource()).getScene().getWindow();
             Scene scene=new Scene(root);
             stage.setScene(scene);
             stage.show();
         }

         if(event.getSource()==btnProfil){

         }
        if(event.getSource()==btnFormateurs){
            root=FXMLLoader.load(getClass().getResource("Formateur.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }
        if(event.getSource()==btnParticipants){

        }
        if(event.getSource()==btnFormations){

        }
        if(event.getSource()==btnDomaines){

        }
    }






}


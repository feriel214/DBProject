package com.example.dbproject.controllers;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.ScrollPane;
        import javafx.scene.image.Image;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;

        import java.io.File;
        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnFormateurs;
    @FXML
    private Button btnParticipants;
    @FXML
    private Button btnFormations;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnDomaines;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Pane pnlMenus;
    @FXML
    private ScrollPane pnlItems;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
                //give the items some effect
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnParticipants) {
            //participants();
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }

        if (actionEvent.getSource() == btnFormations) {

            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnFormateurs)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
    }


    /*public void participants() throws IOException {
       Stage primaryStage = new Stage();
       Parent root = FXMLLoader.load(DashboardController.class.getClass().getResource("com.example.dbproject.participant.fxml"));
       primaryStage.setTitle("Participants");
       primaryStage.setScene(new Scene(root,520,400));
       primaryStage.show();

    }*/
}


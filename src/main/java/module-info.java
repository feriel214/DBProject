module com.example.dbproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens com.example.dbproject to javafx.fxml;
    exports com.example.dbproject;
    exports com.example.dbproject.controllers;
    opens com.example.dbproject.controllers to javafx.fxml;
}
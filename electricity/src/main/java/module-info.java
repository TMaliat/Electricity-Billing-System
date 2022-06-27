module com.example.electricity {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires AnimateFX;


    opens com.example.electricity to javafx.fxml;
    exports com.example.electricity;
}
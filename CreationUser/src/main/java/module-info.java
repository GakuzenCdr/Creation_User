module com.example.creationuser {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.creationuser to javafx.fxml;
    exports com.example.creationuser;
}
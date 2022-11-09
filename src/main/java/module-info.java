module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.json;

    //opens com.juego to javafx.fxml;
    exports com.controller;
    opens com.controller to javafx.fxml;
    exports com;
    opens com to javafx.fxml;
}
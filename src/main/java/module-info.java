module com.juego {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.json;

    opens com.juego to javafx.fxml;
    exports com.juego;
}
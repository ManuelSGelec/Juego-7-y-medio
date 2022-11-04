module com.juego.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.juego.demo to javafx.fxml;
    exports com.juego.demo;
}
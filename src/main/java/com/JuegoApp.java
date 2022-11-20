package com;

import com.controller.CommController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JuegoApp extends Application {
    CommController comm = new CommController();

    /**
     * Al arrancar la aplicación leemos el fichero .json donde guardamos la información de los jugadores si el fichero existe.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        if (!comm.pruebaFichero()){
            comm.readFromFile();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(JuegoApp.class.getResource("/com/vistas/vistaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Play the game");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Al cerra la aplicación guardamos la partida
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        comm.guardarPartidas();

    }
}
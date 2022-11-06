package com.juego;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class JuegoApp extends Application {
    CommController comm = new CommController();

    @Override
    public void start(Stage stage) throws IOException {
        if (!comm.pruebaFichero()){
            comm.readFromFile();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(JuegoApp.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Play the game");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        comm.guardarPartidas();

    }
}
package com.juego.demo;

import com.juego.demo.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    Player jugador1;
    public static String parameters;
    @FXML
    TextField txtNombre ;
    @FXML
    private void  onLogin(ActionEvent event){
        System.out.println(txtNombre.getText());
        FXMLLoader loader;

        try {

            Stage stage = (Stage) txtNombre.getScene().getWindow();
            loader = new FXMLLoader (LoginController.class.getResource("vistajuego.fxml"));
            Parent root = loader.load();
            JuegoController juegoController = loader.getController();
            juegoController.pedirNombreJugador(txtNombre.getText());
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

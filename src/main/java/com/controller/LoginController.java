package com.controller;

import com.model.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class LoginController {
    @FXML
    TextField txtNombre;

    /**
     * Capturamos el evento del botón
     * @param a
     */
    @FXML
    private void onLogin(Event a) {
        String nombre = txtNombre.getText();
        if (nombre == null || nombre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("El campo nombre esta vacio");
            alert.showAndWait();
        } else {
            FXMLLoader loader;
            Player jugador = loginJugador(txtNombre.getText());
            try {
                Stage stage = (Stage) txtNombre.getScene().getWindow();
                loader = new FXMLLoader(LoginController.class.getResource("/com/vistas/vistajuego.fxml"));
                Parent root = loader.load();
                JuegoController juegoController = loader.getController();
                juegoController.pedirJugador(jugador);
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Capturamos el String de textfield  y comprobamos si en el arraylist ya está guardados ese jugador , si aun no esta en el arraylist lo creamos de nuevo.
     * Si el jugador este devolvemos ese jugador.
     * @param nombreJugador
     * @return jugador de la partida
     */
    private Player loginJugador(String nombreJugador) {
        CommController comm = new CommController();
        ArrayList<Player> listaJugadores = new ArrayList<>();

        if (comm.pruebaFichero()) {
            listaJugadores.add(new Player(nombreJugador));
            comm.setDatosJugadores(listaJugadores);
            return listaJugadores.get((listaJugadores.size() - 1));
        }

        listaJugadores = comm.getDatosJugadores();

        for (Player player : listaJugadores) {
            if (player.getName().equals(nombreJugador)) {
                return player;
            }
        }

        listaJugadores.add(new Player(nombreJugador));
        comm.setDatosJugadores(listaJugadores);
        return listaJugadores.get((listaJugadores.size() - 1));

    }


}

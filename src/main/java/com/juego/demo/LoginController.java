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
import java.util.ArrayList;


public class LoginController {
    @FXML
    TextField txtNombre ;
    @FXML
    private void  onLogin(ActionEvent event) throws IOException {
        System.out.println(txtNombre.getText());
        FXMLLoader loader;
        Player jugador=loginJugador(txtNombre.getText());
        System.out.println(jugador.getName() +" ogin");


        try {
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            loader = new FXMLLoader (LoginController.class.getResource("vistajuego.fxml"));
            Parent root = loader.load();
            JuegoController juegoController = loader.getController();
            juegoController.pedirJugador(jugador);
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
    private Player loginJugador (String nombreJugador) throws IOException {

        CommController comm =new CommController();
        ArrayList <Player> listaJugadores =new ArrayList<>();


        if (comm.pruebaFichero()){
            System.out.println("ficchero vasi");
            listaJugadores.add(new Player(nombreJugador));
            comm.setDatosJugadores(listaJugadores);
            return listaJugadores.get((listaJugadores.size()-1));

        }

        listaJugadores =  comm.getDatosJugadores();
        System.out.println(113);


        for (Player player: listaJugadores) {
            if(player.getName().equals(nombreJugador)){

                System.out.println("1");
            return player;
            }

        }
        listaJugadores.add(new Player(nombreJugador));
        comm.setDatosJugadores(listaJugadores);
        System.out.println("2");
        listaJugadores.forEach(player -> System.out.println(player.getName()));
        return listaJugadores.get((listaJugadores.size()-1));


    }



}

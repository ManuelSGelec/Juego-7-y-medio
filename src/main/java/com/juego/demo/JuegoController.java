package com.juego.demo;

import com.juego.demo.model.Game;
import com.juego.demo.model.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JuegoController {
    Game juego;
    Player jugador;
    ArrayList<Player> todosJugadores = new ArrayList<>();

    ArrayList<ImageView> imagenesJugador = new ArrayList<>();
    ArrayList<ImageView> imagenesBank = new ArrayList<>();
    CommController comm = new CommController();

    @FXML
    private ImageView ima1, ima2, ima3, ima4, ima5, ima6, ima7, ima8, ima9;

    @FXML
    private ImageView imabank1, imabank2, imabank3, imabank4, imabank5, imabank6, imabank7, imabank8, imabank9, imabank10;

    @FXML
    private Label puntosJugador, puntosBanck, lbGanador, lbPerdidos, lbGanados, lbNumJuegos, lbNombre;
    @FXML
    private Button btnReset, btnCambiaUsuario, btnStand, btncard;


    public JuegoController() {
    }


    public void pedirJugador(Player jugador) {
        this.jugador = jugador;
        System.out.println(jugador.getName());
        reset();

    }

    public void reset() {

        juego = new Game(jugador);
        updPartida();
        puntosBanck.setText(String.valueOf(juego.getBankerHand().getValue()));
        puntosJugador.setText(String.valueOf(juego.getPlayerHand().getValue()));
        cargarImagen();

    }

    public void initialize() {
        imagenesJugador.add(ima1);
        imagenesJugador.add(ima2);
        imagenesJugador.add(ima3);
        imagenesJugador.add(ima4);
        imagenesJugador.add(ima5);
        imagenesJugador.add(ima6);
        imagenesJugador.add(ima7);
        imagenesJugador.add(ima8);
        imagenesJugador.add(ima9);
        imagenesBank.add(imabank1);
        imagenesBank.add(imabank2);
        imagenesBank.add(imabank3);
        imagenesBank.add(imabank4);
        imagenesBank.add(imabank5);
        imagenesBank.add(imabank6);
        imagenesBank.add(imabank7);
        imagenesBank.add(imabank8);
        imagenesBank.add(imabank9);
        imagenesBank.add(imabank10);
    }

    @FXML
    protected void onHelloButtonClick(Event a) {
        juego.playerTurn();
        cargarImagen();
        if (juego.getGameOver()) {
            btnReset.setVisible(true);
            btnCambiaUsuario.setVisible(true);
            btncard.setDisable(true);
            btnStand.setDisable(true);
            lbGanador.setText(String.format("%s wins, %s loses", juego.getWinner(), juego.getLoser()));
            updPartida();
        }

    }


    private void cargarImagen() {
        for (int i = 0; i < juego.getPlayerHand().getCards().size(); i++) {
            imagenesJugador.get(i).setImage(new Image(new File(String.format("src/main/resources/com/juego/images/%s.png", juego.getPlayerHand().getCards().get(i).getCardCode())).toURI().toString()));
        }
        for (int i = 0; i < juego.getBankerHand().getCards().size(); i++) {
            imagenesBank.get(i).setImage(new Image(new File(String.format("src/main/resources/com/juego/images/%s.png", juego.getBankerHand().getCards().get(i).getCardCode())).toURI().toString()));
        }
        puntosBanck.setText(String.valueOf(juego.getBankerHand().getValue()));
        puntosJugador.setText(String.valueOf(juego.getPlayerHand().getValue()));

    }

    @FXML
    protected void onStandButtonClick(ActionEvent actionEvent) {
        System.out.println("stand");
        juego.bankerTurn();
        cargarImagen();
        if (juego.getGameOver()) {
            btnReset.setVisible(true);
            btnCambiaUsuario.setVisible(true);
            btncard.setDisable(true);
            btnStand.setDisable(true);
            updPartida();
            lbGanador.setText(String.format("%s wins, %s loses", juego.getWinner(), juego.getLoser()));

        }
    }

    @FXML
    protected void onResetButtonClick(ActionEvent actionEvent) {
        System.out.println("reset");
        for (ImageView a : imagenesBank) {
            a.setImage(null);
        }
        for (ImageView a : imagenesJugador) {
            a.setImage(null);
        }
        btnReset.setVisible(false);
        btnCambiaUsuario.setVisible(false);
        btncard.setDisable(false);
        btnStand.setDisable(false);
        lbGanador.setText("");
        reset();


    }

    @FXML
    protected void onclickCambiaUsuario(ActionEvent event) {
        FXMLLoader loader;
        try {

            Stage stage = (Stage) imabank1.getScene().getWindow();
            loader = new FXMLLoader(LoginController.class.getResource("Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void updPartida() {
        lbGanados.setText(String.valueOf(jugador.getWonGames()));
        lbNombre.setText(juego.getPlayerName());
        lbPerdidos.setText(String.valueOf(jugador.getLostGames()));
        lbNumJuegos.setText(String.valueOf(jugador.getPlayedGames()));

    }

    public ArrayList<Player> getTodosJugadores() {

        return todosJugadores;
    }
}

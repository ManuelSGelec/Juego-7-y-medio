package com.juego.demo;

import com.juego.demo.model.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class CommController {

    ArrayList<String> datosJugadorRecuperado = new ArrayList();


    public ArrayList<String> recuperarDatosJugador() {
        return datosJugadorRecuperado;
    }

    public ArrayList<Player> recuperaJugadores() {
        ArrayList<Player> jugadores = new ArrayList<>();
        Path path = Paths.get("src/main/resources/com/juego/ficheros/users.txt");
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path, java.nio.charset.StandardCharsets.UTF_8);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] userData = linea.split(";");
                String userName = userData[0];
                int playedGames= Integer.parseInt(userData[1]);
                int wonGames= Integer.parseInt(userData[2]);
                int lostGames= Integer.parseInt(userData[3]);
                jugadores.add(new Player(userName,playedGames,wonGames,lostGames));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jugadores;

    }





    public boolean buscarUsuarioFichero(String usuario) {
        Path path = Paths.get("src/main/resources/com/juego/ficheros/users.txt");
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path, java.nio.charset.StandardCharsets.UTF_8);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] userData = linea.split(";");
                String uname = userData[0];
                if (usuario.equals(uname)) {
                    datosJugadorRecuperado.add(userData[0]);
                    datosJugadorRecuperado.add(userData[1]);
                    datosJugadorRecuperado.add(userData[2]);
                    datosJugadorRecuperado.add(userData[3]);
                    return true;
                }
            }
        } catch (IOException ex) {
            System.err.println("I/O Error: " + ex);
        }
        return false;
    }

    public void escribirFichero(Player jugador) {

        Path path = Paths.get("src/main/resources/com/juego/ficheros/users.txt");
        BufferedWriter bufferWriter = null;
        try {
            if (new File(String.valueOf(path)).exists())
                bufferWriter = Files.newBufferedWriter(path, java.nio.charset.StandardCharsets.UTF_8, StandardOpenOption.APPEND);

            else
                bufferWriter = Files.newBufferedWriter(path, java.nio.charset.StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
            String jugadorGuardar = jugador.getName() + ";" + jugador.getPlayedGames() + ";" + jugador.getWonGames() + ";" + jugador.getLostGames();
            bufferWriter.write(jugadorGuardar);
            bufferWriter.newLine();
            bufferWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void vaciarFichero() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("src/main/resources/com/juego/ficheros/users.txt"));
            bw.write("");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}


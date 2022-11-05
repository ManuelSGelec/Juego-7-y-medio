package com.juego.demo;

import com.juego.demo.model.Player;

import javax.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class CommController {
    static Path path = Paths.get("users.json");

    private static ArrayList<Player> datosJugadores = new ArrayList();


    private void writeInFile(Path path) {
        JsonArray json_array = null;
        JsonArrayBuilder js = Json.createArrayBuilder();
        JsonWriter jsonWriter;
        OutputStream os = null;


        for (int i = 0; i < datosJugadores.size(); i++) {

            js.add(convert_to_json_object(i));
        }

        try {
            os = new FileOutputStream(path.toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }

        jsonWriter = Json.createWriter(os);
        jsonWriter.writeArray(js.build());
        jsonWriter.close();

        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFromFile() {

        InputStream is;
        try {
            is = new FileInputStream(path.toFile());
            JsonReader jc = Json.createReader(is);
            JsonArray json_array = jc.readArray();
            jc.close();
            if (json_array.isEmpty()) {
                System.out.println("ta Vacio");
            } else {

                for (JsonValue row : json_array) {
                    JsonObject json_object = row.asJsonObject();
                    System.out.print("name: " + json_object.getString("name"));
                    System.out.print(" playedGames: " + json_object.getInt("playedGames"));
                    System.out.print(" wonGames: " + json_object.getInt("wonGames"));
                    System.out.print(" lostGames: " + json_object.getInt("lostGames"));
                    datosJugadores.add(new Player(json_object.getString("name"), json_object.getInt("playedGames"), json_object.getInt("wonGames"), json_object.getInt("lostGames")));

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public JsonObject convert_to_json_object(int i) {
        JsonObjectBuilder json_object_builder = Json.createObjectBuilder();

        json_object_builder.add("name", datosJugadores.get(i).getName());
        json_object_builder.add("playedGames", datosJugadores.get(i).getPlayedGames());
        json_object_builder.add("wonGames", datosJugadores.get(i).getWonGames());
        json_object_builder.add("lostGames", datosJugadores.get(i).getLostGames());


        return json_object_builder.build();
    }

    public ArrayList<Player> getDatosJugadores() {
        return datosJugadores;
    }

    public void setDatosJugadores(ArrayList<Player> datosJugadores) {
        this.datosJugadores = datosJugadores;

    }

    public void GuardarPartidas() {
        writeInFile(path);
    }

    public boolean pruebaFichero() throws IOException {
        File documetoNuevo = new File(path.toUri());
        if (!documetoNuevo.exists()) {
            BufferedWriter bufferWriter;
            try {
                bufferWriter = Files.newBufferedWriter(path, java.nio.charset.StandardCharsets.UTF_8, StandardOpenOption.CREATE);
                bufferWriter.write("[]");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                bufferWriter.flush();
                bufferWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return true;
        }
        return false;
    }



}
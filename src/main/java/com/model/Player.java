package com.model;
public class Player {
    private String name;
    private int playedGames =0;
    private int wonGames =0;
    private int lostGames =0;

    /**
     * Constructor per crear un jugador a partir del seu nom
     * @param nom --> Nom del nou Jugador
     */
    public Player(String nom) {
        this.name = nom;
    }

    public Player(String name, int playedGames, int wonGames, int lostGames) {
        this.name = name;
        this.playedGames = playedGames;
        this.wonGames = wonGames;
        this.lostGames = lostGames;
    }

    /**
     * Getter del Nom del Jugador
     * @return --> Nom
     */
    public String getName() {
        return name;
    }

    /**
     * Metode per cada cop que el jugador jugui una partida, s'incrementi el valor de partides jugades
     */


    public void gameWon( ) {
        this.playedGames++;
        this.wonGames++;
    }

    /**
     * Metode per cada cop que el jugador perdi una partida, s'incrementi el valor de partides perdudes
     */
    public void lostGame( ) {
        this.playedGames++;
        this.lostGames++;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public int getWonGames() {
        return wonGames;
    }

    public int getLostGames() {
        return lostGames;
    }


    /**
     * Metode equals per que no hi hagin jugadors repetits
     * @param obj --> Nou jugador que vulguis crear
     * @return --> Si existeix o no (true or false)
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}

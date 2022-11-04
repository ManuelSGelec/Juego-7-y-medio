package com.juego.demo.model;

public class Game {

    private CardsDeck cardsDeck;
    private Hand handPlayer;
    private Hand handBanker;
    private Player player;
    private boolean gameOver = false;
    private String loser ="";
    private String winner ="";

    public Game(Player player) {
        this.player = player;
        cardsDeck = new CardsDeck();
        handPlayer = new Hand();
        handBanker = new Hand();

        Card card = cardsDeck.getCardFromDeck();
        handPlayer.addCard(card);

        card = cardsDeck.getCardFromDeck();
        handBanker.addCard(card);
    }


    public Card playerTurn() {
        Card card = cardsDeck.getCardFromDeck();
        handPlayer.addCard(card);
        if (handPlayer.getValue() > 7.5f) {
            this.gameOver = true;
            checkWinner();
        }
        return card;
    }

    public void bankerTurn() {
        while(!this.getGameOver()){
            if (handBanker.getValue() >= handPlayer.getValue()) {
                this.gameOver = true;
            }else{
                Card card = cardsDeck.getCardFromDeck();
                handBanker.addCard(card);
            }
        }
        checkWinner();
    }

    private void checkWinner() {
        if (this.handPlayer.getValue() > 7.5 ){
            this.winner = "Banker";
            this.loser = player.getName();
            player.lostGame();
            return;
        }
        if (this.handBanker.getValue() > 7.5 ){
            this.loser = "Banker";
            this.winner = player.getName();
            player.gameWon();
            return;
        }
        if(this.handBanker.getValue() >= this.handPlayer.getValue()){
            this.winner = "Banker";
            this.loser = player.getName();
            player.lostGame();
        }else{
            this.loser = "Banker";
            this.winner = player.getName();
            player.gameWon();
        }
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public String getLoser() {
        return this.loser;
    }

    public Hand getPlayerHand() {
        return this.handPlayer;
    }

    public Hand getBankerHand() {
        return this.handBanker;
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public String getWinner() {
        return this.winner;
    }

}

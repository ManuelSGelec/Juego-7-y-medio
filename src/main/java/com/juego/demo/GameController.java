package com.juego.demo;

import com.juego.demo.model.Card;
import com.juego.demo.model.Game;
import com.juego.demo.model.Hand;
import com.juego.demo.model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
ArrayList<Player> players=new ArrayList<>();

    void start() {
        System.out.println("Wellcome to Seven And a Half Game");
        System.out.println(" -You should get as closer to 7.5 without exceed it.");
        System.out.println(" -Banker will try as well.");
        System.out.println(" -Who exceeds 7.5 will lose the game.");
        System.out.println(" -To win you must get closer to 7.5 than the Banker");
        System.out.println("Let's play!!!");
        Player player = getPlayer();

        do {
            Game game = new Game(player);
            boolean stand = false;
            while (!game.getGameOver() && !stand) {
                displayHand(player.getName(), game.getPlayerHand());
                displayHand("Banker", game.getBankerHand());
                stand = hitOrStand();
                if (!stand) {
                    game.playerTurn();
                }
            }

            if (!game.getGameOver()) {
                game.bankerTurn();
            }
            gameSummary(game);
        }while(keepPlaying());
        System.out.println("-Games played: " + player.getPlayedGames());
        System.out.println("-Games won:    " + player.getWonGames());
        System.out.println("-Games lost:   " + player.getLostGames());
        System.out.println(String.format("Bye %s, have a nice day!!",player.getName()));

    }

    private boolean keepPlaying() {

        return askBoolean("Play again?[Y]yes [N]Not","Y","N");
    }

    private boolean hitOrStand() {
        return askBoolean("[H]Hit or [S]Stand","S","H");
    }

    private boolean askBoolean(String question, String answerTrue, String answerFalse){
        Scanner scan = new Scanner(System.in);
        String answer = "";
        while (!answer.equalsIgnoreCase(answerFalse) && !answer.equalsIgnoreCase(answerTrue)) {
            System.out.println(question);
            answer = scan.nextLine();
        }
        if (answer.equalsIgnoreCase(answerFalse))
            return false;
        else
            return true;
    }

    private void gameSummary(Game game) {
        displayHand(game.getPlayerName(), game.getPlayerHand());
        displayHand("Banker", game.getBankerHand());
        if(game.getGameOver()){
            System.out.println(String.format("%s wins, %s loses",game.getWinner(), game.getLoser()));
        }
    }

    private void displayHand(String nom, Hand hand) {
        System.out.println(String.format("%s's hand---------------", nom));
        for (Card card : hand.getCards()) {
            System.out.println(card);
        }
        System.out.println("        --------------------");
        System.out.println(String.format("       Total hand value: %.1f",hand.getValue()));
        System.out.println("----------------------------");
    }

    private Player getPlayer() {
        Scanner scan = new Scanner(System.in);
        String playerName = "";
        boolean exit = false;
        while (!exit) {
            System.out.println("Please enter your name:");
            playerName = scan.nextLine();
            if (!playerName.equals("")) {
                exit = true;
            }
        }
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        Player player = new Player(playerName);
        players.add(player);
        return player;
    }
}

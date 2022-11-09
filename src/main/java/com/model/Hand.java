package com.model;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private float handValue;

    /**
     * Constructor per crear un ArrayList de Cartes (Ma)
     */
    public Hand(){
        cards = new ArrayList<Card>();
    }

    /**
     * Getter per saber el valor de la Ma
     * @return --> Valor Ma
     */
    public float getValue() {
        return handValue;
    }

    /**
     * Metode per afegir carta a la ma i calcular de nou el valor de la ma
     * @param carta --> Carta donada
     */
    public void addCard(Card carta) {
        cards.add(carta);
        calculateHandValue();
    }

    /**
     * Metode helpper per calcular el valor de la ma
     */
    private void calculateHandValue (){
        float x = 0.0f;
        for (int i = 0; i < cards.size(); i++) {
            x = handValue+ cards.get(i).getValue();
        }
        this.handValue =x;
    }



    /**
     * Metode ToString per veure la ma i el valor de la propia
     * @return --> Ma sencera i el seu valor
     */
    @Override
    public String toString() {
        return " " + cards +
                "\n  Valor de la Ma: " + handValue ;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}

package com.juego.model;


public abstract class Card {
    protected CardSuit suit;
    protected float value;
    public float getValue() {
        return value;
    }

    protected String toString(String numberOrFace) {
       return String.format("%7s of %6s, value %.1f",numberOrFace,suit, value);
    }

    public abstract String getCardCode();

}

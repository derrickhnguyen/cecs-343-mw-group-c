package com.example.micha.illuminice;

/**
 * Created by micha on 4/9/2017.
 */

public class Player {
    private String          username;
    private IlluminatiCard  illuminatiCard;

    public Player(String username, IlluminatiCard illuminatiCard) {
        this.username       = username;
        this.illuminatiCard = illuminatiCard;
    }

    public IlluminatiCard getIlluminatiCard() {

        return illuminatiCard;
    }

    public int rollDice() {
        DiceRoll diceRoll = new DiceRoll();
        return diceRoll.getDiceSum();
    }

    public String getUsername() {
        return username;
    }
}

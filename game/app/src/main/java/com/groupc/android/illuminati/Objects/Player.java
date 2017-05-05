package com.groupc.android.illuminati.Objects;

import android.os.Parcelable;
import android.widget.GridLayout;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by micha on 4/9/2017.
 */

public class Player implements Serializable {
    private String          username;
    private PowerStructure powerStructure;
    private ArrayList<SpecialCard> hand;
    private boolean ownsSurvivalists;
    private IlluminatiCard illuminatiCard;
    private int actionsTaken;

    public Player(String username, IlluminatiCard illuminatiCard) {
        this.username       = username;
        this.illuminatiCard = illuminatiCard;
        powerStructure = new PowerStructure(illuminatiCard);
        hand = new ArrayList<SpecialCard>();
        ownsSurvivalists = false;
        actionsTaken = 0;
    }

    public IlluminatiCard getIlluminatiCard() {

        return illuminatiCard;
    }

    public PowerStructure getPowerStructure()
    {
        return powerStructure;
    }

    public int rollDice() {
        DiceRoll diceRoll = new DiceRoll();
        return diceRoll.getDiceSum();
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<SpecialCard> getHand()
    {
        return hand;
    }

    public void addCardToHand(SpecialCard specialCard)
    {
        hand.add(specialCard);
    }

    public void removeCardFromHand(SpecialCard specialCard)
    {
        hand.remove(specialCard);
    }

    public boolean handContains(SpecialCard specialCard)
    {
        return hand.contains(specialCard);
    }

    public boolean ownsSurvivalists() {
      return ownsSurvivalists;
    }

    public int actionsTaken() {
        return actionsTaken;
    }

    public void takeAction() {
        actionsTaken++;
    }

    public void resetActionsTaken() {
        actionsTaken = 0;
    }

    public void setOwnsSurvivalists(boolean ownsSurvivalists) {
      this.ownsSurvivalists = ownsSurvivalists;
    }

}

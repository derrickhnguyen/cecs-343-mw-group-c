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

    public Player(String username, IlluminatiCard illuminatiCard) {
        this.username       = username;
        powerStructure = new PowerStructure(illuminatiCard);
        hand = new ArrayList<SpecialCard>();
        ownsSurvivalists = false;
    }

    public IlluminatiCard getIlluminatiCard() {

        return powerStructure.getIlluminatiCard();
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

    public void addCardToHand(SpecialCard sc)
    {
        hand.add(sc);
    }

    public void removeCardFromHand(SpecialCard sc)
    {
        hand.remove(sc);
    }

    public boolean handContains(SpecialCard sc)
    {
        return hand.contains(sc);
    }

    public boolean ownsSurvivalists() {
      return ownsSurvivalists;
    }

    public void setOwnsSurvivalists(boolean ownsSurvivalists) {
      this.ownsSurvivalists = ownsSurvivalists;
    }
}

package com.groupc.android.illuminati.Objects;

import java.util.Random;

/**
 * Created by micha on 4/9/2017.
 */

public class DiceRoll {
    int die1Value;
    int die2Value;
    int diceSum;

    public DiceRoll() {
        Random rand = new Random();
        die1Value = rand.nextInt(6) + 1;
        die2Value = rand.nextInt(6) + 1;
        diceSum = die1Value + die2Value;
    }

    public int getDiceSum() {
        return diceSum;
    }

    public boolean lost() {
        if(diceSum > 10) return true;
        else return false;
    }

    public void setDiceSum(int diceSum) {
        this.diceSum = diceSum;
    }
}

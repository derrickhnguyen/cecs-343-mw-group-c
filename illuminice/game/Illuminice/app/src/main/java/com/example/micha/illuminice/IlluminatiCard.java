package com.example.micha.illuminice;

import com.example.micha.illuminice.Table.IlluminatiCardEnum;

/**
 * Created by micha on 4/9/2017.
 */

public class IlluminatiCard {
    IlluminatiCardEnum illuminati;

    public IlluminatiCard (IlluminatiCardEnum illuminati) {
        this.illuminati = illuminati;
    }

    public boolean checkGenericGoal() {
        return false;
    }

    public boolean checkSpecialGoal() {
        return false;
    }

    public String getName() {
        return illuminati.getName();
    }
}

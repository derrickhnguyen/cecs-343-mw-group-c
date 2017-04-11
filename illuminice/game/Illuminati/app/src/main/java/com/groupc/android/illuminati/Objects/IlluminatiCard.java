package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Table.IlluminatiCardEnum;

/**
 * Created by micha on 4/9/2017.
 */

public class IlluminatiCard extends Card {
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

package com.groupc.android.illuminati.Objects;

import android.util.Pair;

import java.security.acl.Group;

/**
 * Created by cman4_000 on 4/10/2017.
 */

public class NonSpecialCard {
    private int power,
                transferablePower,
                resistance,
                income,
                groupTreasury;
    private enum alignment{};
    private enum specialAbility{};
    private Arrow[] arrows;

    public NonSpecialCard(int power, int transferablePower, int resistance, int income, boolean hasTopArrow, boolean hasRightArrow, boolean hasBottomArrow, boolean hasLeftArrow)
    {
        //do enums
        this.power = power;
        this.transferablePower = transferablePower;
        this.resistance = resistance;
        this.income = income;
        this.groupTreasury = 0;
        //set alighments
        //set grouptreasury
        arrows = new Arrow[4];

        //always have to check if null first
        if(hasTopArrow) { arrows[0] = new Arrow(); }
        if(hasRightArrow) { arrows[1] = new Arrow(); }
        if(hasBottomArrow) { arrows[2] = new Arrow(); }
        if(hasLeftArrow) { arrows[3] = new Arrow(); }
    }


}

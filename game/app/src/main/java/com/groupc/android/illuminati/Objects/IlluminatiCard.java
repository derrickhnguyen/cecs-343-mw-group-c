package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Table.CardTypeEnum;
import com.groupc.android.illuminati.Objects.Table.AlignmentEnum;
import com.groupc.android.illuminati.Objects.Table.IlluminatiCardEnum;

/**
 * Created by micha on 4/9/2017.
 */

public class IlluminatiCard extends Card {
    IlluminatiCardEnum illuminati;

    public IlluminatiCard extends NonSpecialCard (
                                  String cardName,
                                  AlignmentEnum alignment,
                                  int power,
                                  int transferablePower,
                                  int resistance,
                                  int income,
                                  AlignmentEnum alignment,
                                  SpecialAbilityEnum specialAbility,
                                  IlluminatiCardEnum illuminati) {
        super.super.cardName = cardName;
        super.super.type = type;

        super.power = power;
        super.transferablePower = transferablePower;
        super.resistance = resistance;
        super.income = income;
        super.alignment = alignment;
        super.specialAbility = specialAbility;

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

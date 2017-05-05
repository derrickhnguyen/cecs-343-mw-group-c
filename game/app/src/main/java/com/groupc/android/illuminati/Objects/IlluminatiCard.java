package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Table.CardTypeEnum;
import com.groupc.android.illuminati.Objects.Table.AlignmentEnum;
import com.groupc.android.illuminati.Objects.Table.IlluminatiCardEnum;
import com.groupc.android.illuminati.Objects.Table.SpecialAbilityEnum;

/**
 * Created by micha on 4/9/2017.
 */

public class IlluminatiCard extends NonSpecialCard {
    IlluminatiCardEnum illuminatiEnum;

    public IlluminatiCard (
                                  String cardName,
                                  CardTypeEnum type,
                                  int power,
                                  int transferablePower,
                                  int resistance,
                                  int income,
                                  SpecialAbilityEnum specialAbility,
                                  IlluminatiCardEnum illuminatiEnum
                                  ) {
        super(cardName, type, power, transferablePower, resistance, income, specialAbility);
        this.illuminatiEnum = illuminatiEnum;
    }


    public boolean checkGenericGoal() {
        return false;
    }

    public boolean checkSpecialGoal() {
        return false;
    }

    public String getName() {
        return illuminatiEnum.getName();
    }

    public String getCardName() {
        return super.getCardName();
    }

}

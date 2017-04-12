package com.groupc.android.illuminati.Objects;

/**
 * Created by micha on 4/9/2017.
 */ 
import com.groupc.android.illuminati.Objects.Table.CardTypeEnum;
import com.groupc.android.illuminati.Objects.Table.AlignmentEnum;

public class GroupCard {
    private GroupCard puppetMaster;
    private boolean puppetFlag;
    private boolean isAttackable;

    public GroupCard extends NonSpecialCard (
                            String cardName,
                            AlignmentEnum alignment,
                            int power,
                            int transferablePower,
                            int resistance,
                            int income,
                            AlignmentEnum alignment,
                            SpecialAbilityEnum specialAbility) {
        super.super.cardName = cardName;
        super.super.type = type;

        super.power = power;
        super.transferablePower = transferablePower;
        super.resistance = resistance;
        super.income = income;
        super.alignment = alignment;
        super.specialAbility = specialAbility;

        puppetMaster = null;
        puppetFlag = false;

        if(resistance == 0) isAttackable = false;
        else isAttackable = true;
    }

    public GroupCard getPuppetMaster() {
        return puppetMaster;
    }

    public void setPuppetMaster(GroupCard puppetMaster) {
        this.puppetMaster = puppetMaster;
    }

    public boolean getPuppetFlag() {
        return puppetFlag;
    }

    public void setPuppetFlag(boolean puppetFlag) {
        this.puppetFlag = puppetFlag;
    }

    public boolean isAttackable() {
        return isAttackable;
    }

    public void setAttackable(boolean isAttackable) {
        this.isAttackable = isAttackable;
    }
}

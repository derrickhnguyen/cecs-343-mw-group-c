package com.groupc.android.illuminati.Objects;

/**
 * Created by micha on 4/9/2017.
 */ 
import com.groupc.android.illuminati.Objects.Table.CardTypeEnum;
import com.groupc.android.illuminati.Objects.Table.AlignmentEnum;

public class GroupCard extends NonSpecialCard {
    private GroupCard puppetMaster;
    private boolean puppetFlag;
    private boolean isAttackable;
    private AlignmentEnum[] alignments;

    public GroupCard(
                            String cardName,
                            CardTypeEnum type,
                            int power,
                            int transferablePower,
                            int resistance,
                            int income,
                            AlignmentEnum[] alignments,
                            Table.SpecialAbilityEnum specialAbility
                            ) {
        super(cardName, type, power, transferablePower, resistance, income, specialAbility);

        puppetMaster = null;
        puppetFlag = false;
        this.alignments = alignments;

        if(resistance == 0) isAttackable = false;
        else isAttackable = true;
    }

    public String getCardName() {
        return super.getCardName();
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

    public boolean setPuppetFlag(boolean puppetFlag) {
        this.puppetFlag = puppetFlag;
        return true;
    }

    public boolean isAttackable() {
        return isAttackable;
    }

    public boolean setAttackable(boolean isAttackable) {
        this.isAttackable = isAttackable;
        return true;
    }

    public void removePuppet(NonSpecialCard nonSpecialCard) {

    }
}

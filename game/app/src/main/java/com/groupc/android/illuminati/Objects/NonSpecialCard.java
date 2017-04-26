package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Table.AlignmentEnum;
import com.groupc.android.illuminati.Objects.Table.CardTypeEnum;
import com.groupc.android.illuminati.Objects.Table.SpecialAbilityEnum;

/**
 * Created by cman4_000 on 4/10/2017.
 */

public class NonSpecialCard extends Card {
    private int   power,
            transferablePower,
            resistance,
            income,
            groupTreasury;
    private AlignmentEnum alignment;
    private SpecialAbilityEnum specialAbility;
    private GroupCard[] connectedCards;
    private boolean[] isConnected;
    private Bank bank;
    private int orientation;
    //index 0: north
    //index 1: east
    //index 2: south
    //index 3: west

    public NonSpecialCard(  String cardName,
                            CardTypeEnum type,
                            int power,
                            int transferablePower,
                            int resistance,
                            int income,
                            SpecialAbilityEnum specialAbility
    ) {
        super(cardName, type);

        this.power = power;
        this.transferablePower = transferablePower;
        this.resistance = resistance;
        this.income = income;
        this.specialAbility = specialAbility;

        groupTreasury = 0;
        connectedCards = new GroupCard[4];
        //0 = top, 1 = right, 2 = bottom, 3 = left
        isConnected = new boolean[4];
        orientation = 0;
    }

    public void collectIncome() {
        bank.withdraw(income);
        groupTreasury += income;
    }

    public void attack(NonSpecialCard otherCard) {

    }

    public int spendMoney() {
        int spentMoney = 0;

        return spentMoney;
    }



    //getters and setters
    public String getCardName() {
        return super.getCardName();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTransferablePower() {
        return transferablePower;
    }

    public void setTransferablePower(int transferablePower) {
        this.transferablePower = transferablePower;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getGroupTreasury() {
        return groupTreasury;
    }

    public void setGroupTreasury(int groupTreasury) {
        this.groupTreasury = groupTreasury;
    }

    public AlignmentEnum getAlignment() {
        return alignment;
    }

    public void setAlignment(AlignmentEnum alignment) {
        this.alignment = alignment;
    }

    public SpecialAbilityEnum getSpecialAbilityEnum() {
        return specialAbility;
    }

    public void setSpecialAbility(SpecialAbilityEnum specialAbility) {
        this.specialAbility = specialAbility;
    }

    public GroupCard[] getConnectedCards() {
        return connectedCards;
    }

    public void setConnectedCards(GroupCard[] connectedCards) {
        this.connectedCards = connectedCards;
    }

    public boolean[] getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(boolean[] isConnected) {
        this.isConnected = isConnected;
    }

    public void setOrientation(int o)
    {
        //0 = normal, 1 = 90 degrees right, 2 = 180 degrees, 3 = 270 degrees right
        orientation = o;
    }

    public int getOrientation()
    {
        return orientation;
    }

    public boolean attachCard(GroupCard card, int direction)
    {
        if(isConnected[direction])
        {
            return false;
        } else {
            isConnected[direction] = true;
            connectedCards[direction] = card;
            return true;
        }
    }

}

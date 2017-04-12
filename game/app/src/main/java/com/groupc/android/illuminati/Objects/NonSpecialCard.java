package com.groupc.android.illuminati.Objects;

import android.util.Pair; //mike - idk what this is for

import java.security.acl.Group; //mike - idk what this is for

import com.groupc.android.illuminati.Objects.Table.SpecialAbilityEnum;
import com.groupc.android.illuminati.Objects.Table.CardTypeEnum;
import com.groupc.android.illuminati.Objects.Table.AlignmentEnum;
import com.groupc.android.illuminati.Objects.Table.bank;

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
    private NonSpecialCard[] connectedCards;
    private boolean[] isConnected;
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
                            AlignmentEnum alignment,
                            SpecialAbilityEnum specialAbility) {
      super.cardName = cardName;
      super.type = type;

      this.power = power;
      this.transferablePower = transferablePower;
      this.resistance = resistance;
      this.income = income;
      this.alignment = alignment;
      this.specialAbility = specialAbility;

      groupTreasury = 0;
      connectedCards = new NonSpecialCard[4];
      isConnected = new boolean[4];
    }

    public void collectIncome() {
      bank.withdraw(income);
      groupTreasury += income;
    }

    public void attack(NonSpecialCard otherCard) {

    }



    //getters and setters

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
      return getGroupTreasury;
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

    public SpecialAbilityEnum getSpecialAbility() {
      return specialAbility.getName();
    }

    public void setSpecialAbility(SpecialAbilityEnum specialAbility) {
      this.specialAbility = specialAbility;
    }

    public NonSpecialCard[] getConnectedCards() {
      return connectedCards;
    }

    public void setConnectedCards(NonSpecialCard[] connectedCards) {
      this.connectedCards = connectedCards;
    }

    public boolean[] getIsConnected() {
      return isConnected;
    }

    public void setIsConnected(boolean[] isConnected) {
      this.isConnected = isConnected;
    }
}

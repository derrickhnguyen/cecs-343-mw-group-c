package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.NonSpecialCard;
import com.groupc.android.illuminati.Objects.Player;

/**
 * Created by micha on 4/14/2017.
 */

 public class AttackAnnouncement {
   private Player attackingPlayer;
   private NonSpecialCard attackingGroup;
   private Player defendingPlayer;
   private NonSpecialCard defendingGroup;

   private int alignmentBonus;
   private int powerStructurePositionBonus;
   private int specialPowerBonus;

   private int attackMoneySpentBonus;
   private int defenderMoneySpentBonus;

   public AttackAnnouncement(
                   Player attackingPlayer,
                   NonSpecialCard attackingGroup,
                   Player defendingPlayer,
                   NonSpecialCard defendingGroup) {
    this.attackingPlayer = attackingPlayer;
    this.attackingGroup = attackingGroup;
    this.defendingPlayer = defendingPlayer;
    this.defendingGroup = defendingGroup;
   }

   public int getAlignmentBonus() {
     return alignmentBonus;
   }

   public void setAlignmentBonus(int alignmentBonus) {
     this.alignmentBonus = alignmentBonus;
   }

   public int getPowerStructurePositionBonus() {
     return powerStructurePositionBonus;
   }

   public void setPowerStructurePositionBonus(int powerStructurePositionBonus) {
     this.powerStructurePositionBonus = powerStructurePositionBonus;
   }

   public int getSpecialPowerBonus() {
     return specialPowerBonus;
   }

   public void setSpecialPowerBonus(int specialPowerBonus) {
     this.specialPowerBonus = specialPowerBonus;
   }

   public int getAttackMoneySpentBonus() {
     return attackMoneySpentBonus;
   }

   public void setAttackerMoneyBonus() {
     int moneySpent = attackingGroup.spendMoney();
     attackMoneySpentBonus += moneySpent;
   }

   public int getDefenderMoneySpentBonuse() {
     return defenderMoneySpentBonus;
   }

   public void setDefenderMoneyBonus() {
     int moneySpent = defendingGroup.spendMoney();
     defenderMoneySpentBonus += moneySpent;
   }

   public void send(Player player) {
     //send attack announcement to player
   }

   public boolean betting() {
     boolean attackerIsDoneBetting = false;
     boolean defenderIsDoneBetting = false;

     //ask attacker if they are done betting and get input
     //ask defender if they are done betting and get input

     if(attackerIsDoneBetting && defenderIsDoneBetting) return false;
     else return true;
   }

   public boolean isAccepted() {
     //hook up to sending and accepting
    return false;
   }

   public int getScore() {
     int score = 0;

     return score;
   }

 }

package com.groupc.android.illuminati.Objects;

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

   private int attackerMoneySpentBonus;
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

   public int getAttackerMoneySpentBonus() {
     return attackerMoneySpentBonus;
   }

   public void setAttackerMoneySpentBonus() {
     int moneySpent = attackingGroup.spendMoney();
     attackerMoneySpentBonus += moneySpent;
   }

   public int getDefenderMoneySpentBonus() {
     return defenderMoneySpentBonus;
   }

   public void setDefenderMoneySpentBonus() {
     int moneySpent = defendingGroup.spendMoney();
     defenderMoneySpentBonus += moneySpent;
   }

   public void send(Player player) {
     if(player == null)
     {

     }
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
    return true;
   }

   public int getScore() {
     int score =
             alignmentBonus
             + powerStructurePositionBonus
             + specialPowerBonus
             + attackerMoneySpentBonus
             - defenderMoneySpentBonus
             + attackingGroup.getPower()
             - defendingGroup.getResistance();
     return score;
   }

 }

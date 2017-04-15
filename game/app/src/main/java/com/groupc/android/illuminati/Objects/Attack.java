package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Table.AttackEnum;

/**
 * Created by micha on 4/11/2017.
 */

 public class Attack {
    Player attackingPlayer;
    Player defendingPlayer;
    AttackEnum attackType;
    Table table;
    Center center;
    DestroyedCards destroyedCards;

   public Attack(Player attackingPlayer, AttackEnum attackType, Table table) {
       this.attackingPlayer = attackingPlayer;
       this.table = table;
       this.attackType = attackType;
       center = table.getCenter();
       destroyedCards = table.getDestroyedCards();
       setUpAttackAnnouncement();
   }

   private void setUpAttackAnnouncement() {
       NonSpecialCard attackingGroup = null;
       defendingPlayer = null;
       NonSpecialCard defendingGroup = null;
       //attackingGroup = get this from user
       //defendingPlayer = get this from user
       //defendingGroup = get this from user
       AttackAnnouncement announcement = new AttackAnnouncement(
               attackingPlayer,
               attackingGroup,
               defendingPlayer,
               defendingGroup);
       announcement.setAlignmentBonus(new AlignmentBonus(attackingGroup, defendingGroup).getAlignmentBonus());
       announcement.setPowerStructurePositionBonus(new PowerStructurePositionBonus(defendingPlayer, defendingGroup).getPowerStructurePositionBonus());
       announcement.setSpecialPowerBonus(new SpecialPowerBonus(attackingGroup, defendingGroup, this).getSpecialPowerBonus());
       announcement.setAttackerMoneyBonus();
       announcement.send(defendingPlayer);

       boolean isAttacker = true;
       while (announcement.betting()) {
           isAttacker = !isAttacker;
           if (isAttacker) {
               announcement.setAttackerMoneyBonus();
               announcement.send(defendingPlayer);
           } else {
               announcement.setDefenderMoneyBonus();
               announcement.send(attackingPlayer);
           }
       }

       if (announcement.isAccepted()) {
           DiceRoll diceRoll = new DiceRoll();
           if (diceRoll.getDiceSum() - announcement.getScore() > 0)
               attackIsSuccesful((GroupCard) defendingGroup);
           else endAttack();
       } else endAttack();
   }


   public void attackIsSuccesful(GroupCard defendingGroup) {
     if(defendingGroup.getCardName().equals("Survivalists")) {
       if(defendingPlayer!=null) defendingPlayer.setOwnsSurvivalists(false);
       attackingPlayer.setOwnsSurvivalists(true);
     }

     if(attackType == AttackEnum.CONTROL) {
       if(attackingPlayer.getPowerStructure().hasRoom(defendingGroup)) {
         attackingPlayer.getPowerStructure().addToPowerStructure(defendingGroup);
       }
       else {
           for(int i = 0; i < defendingGroup.getConnectedCards().length; i++) {
               if (defendingGroup.getConnectedCards()[i] != null) {
                   GroupCard puppet = (GroupCard) defendingGroup.getConnectedCards()[i];
                   defendingGroup.removePuppet(defendingGroup.getConnectedCards()[i]);
                   center.addGroupToCenter(puppet);
               }
           }
         if(attackingPlayer.getPowerStructure().hasRoom(defendingGroup)) {
           attackingPlayer.getPowerStructure().addToPowerStructure(defendingGroup);
         }
         else return;
       }
     }

     if(attackType == AttackEnum.NEUTRALIZE) {
       for(int i = 0; i < defendingGroup.getConnectedCards().length; i++) {
         if(defendingGroup.getConnectedCards()[i] != null) {
           GroupCard puppet = (GroupCard) defendingGroup.getConnectedCards()[i];
           defendingGroup.removePuppet(defendingGroup.getConnectedCards()[i]);
           center.addGroupToCenter(puppet);
         }
       }
       center.addGroupToCenter(defendingGroup);
     }

     if(attackType == AttackEnum.DESTROY) {
       for(int i = 0; i < defendingGroup.getConnectedCards().length; i++) {
         if(defendingGroup.getConnectedCards()[i] != null) {
           GroupCard puppet = (GroupCard) defendingGroup.getConnectedCards()[i];
           defendingGroup.removePuppet(defendingGroup.getConnectedCards()[i]);
           center.addGroupToCenter(puppet);
         }
       }
       destroyedCards.addDestroyedCard(defendingGroup);
     }
   }

   public void endAttack() {
     return;
   }

   public AttackEnum getAttackType() {
     return attackType;
   }

   public void setAttackType(AttackEnum attackType) {
     this.attackType = attackType;
   }

 }

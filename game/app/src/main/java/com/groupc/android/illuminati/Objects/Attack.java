package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Table.AttackEnum;
/**
 * Created by micha on 4/11/2017.
 */

 public class Attack {
   Player attackingPlayer;
   AttackEnum attackType;

   public Attack(Player attackingPlayer, AttackEnum attackType) {
     this.attackingPlayer = attackingPlayer;
     this.attackType = attackType;
     if(setUpAttackAnnouncement())
      attack();
   }

   private boolean setUpAttackAnnouncement() {
     NonSpecialCard attackingGroup;
     Player defendingPlayer;
     NonSpecialCard defendingGroup;
     //attackingGroup = get this from user
     //defendingPlayer = get this from user
     //defendingGroup = get this from user
     AttackAnnouncement attackAnnouncement(
                                    Player attackingPlayer,
                                    NonSpecialCard attackingGroup,
                                    Player defendingPlayer,
                                    NonSpecialCard defendingGroup);
    AttackAnnouncement.setAlignmentBonus(new AlignmentBonus(attackingGroup, defendingGroup).getAlignmentBonus());
    AttackAnnouncement.setPowerStructurePositionBonus(new PowerStructurePositionBonus(defendingPlayer, defendingGroup).getPowerStructurePositionBonus());
    AttackAnnouncement.setSpecialPowerBonus(new SpecialPowerBonus(attackingGroup, defendingGroup).getSpecialPowerBonus(), this);

   }

   public void attackIsSuccesful(GroupCard defendingGroup) {
     if(defendingGroup.getName().equals("Survivalists")) {
       if(defendingPlayer!=null) defendingPlayer.setOwnsSurvivalists(false);
       attackingPlayer.setOwnsSurvivalists(true);
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

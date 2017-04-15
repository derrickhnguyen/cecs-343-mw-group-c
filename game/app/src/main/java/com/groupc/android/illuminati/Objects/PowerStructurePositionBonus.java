package com.groupc.android.illuminati.Objects;

/**
 * Created by micha on 4/11/2017.
 */

 public class PowerStructurePositionBonus{
   Player defendingPlayer;
   NonSpecialCard defendingGroup;

   public PowerStructurePositionBonus(Player defendingPlayer, NonSpecialCard defendingGroup) {
     this.defendingPlayer = defendingPlayer;
     this.defendingGroup = defendingGroup;
   }

   public int getPowerStructurePositionBonus() {
     for(int i = 0; i < 4; i++) {
       if((Card) defendingGroup.getConnectedCards()[i] == (Card) defendingPlayer.getIlluminatiCard()) return 10;
       for(int j = 0; j < 4; j++) {
         if((Card) defendingGroup.getConnectedCards()[j] == (Card) defendingPlayer.getIlluminatiCard()) return 5;
         for(int k = 0; k < 4; k++) {
           if((Card) defendingGroup.getConnectedCards()[k] == (Card) defendingPlayer.getIlluminatiCard()) return 2;
         }
       }
     }
     return 0;
   }
 }

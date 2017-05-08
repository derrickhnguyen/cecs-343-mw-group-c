package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Table.AlignmentEnum;
import com.groupc.android.illuminati.Objects.Table.AttackEnum;
import com.groupc.android.illuminati.Objects.Table.SpecialAbilityEnum;

/**
 * Created by micha on 4/11/2017.
 */

 public class SpecialPowerBonus {
   NonSpecialCard attackingGroup;
   NonSpecialCard defendingGroup;
   Attack attack;

   public SpecialPowerBonus(NonSpecialCard attackingGroup, NonSpecialCard defendingGroup, Attack attack) {
     this.attackingGroup = attackingGroup;
     this.defendingGroup = defendingGroup;
       this.attack = attack;
   }

    public int getSpecialPowerBonus() {
       SpecialAbilityEnum special = attackingGroup.getSpecialAbilityEnum();
     switch(special) {
        case PLUSFOURONANYATTEMPTTONEUTRALIZEANYGROUP:
            if(attack.getAttackType() == AttackEnum.NEUTRALIZE) return 4;
         case PLUSFOURONANYATTEMPTTOCONTROLWEIRDGROUPSIMMUNETOATTACKSFROMGOVERNMENTORSTRAIGHTGROUPS:
        if(attackingGroup.getAlignment() == AlignmentEnum.GOVERNMENT || attackingGroup.getAlignment() == AlignmentEnum.STRAIGHT)
          attack.endAttack();
        else if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.WEIRD)
          return 4;
         case PLUSTWOONANYATTEMPTTODESTROYANYGROUP:
        if(attack.getAttackType() == AttackEnum.DESTROY) return 2;
         case PLUSTWOONANYATTEMPTTODESTROYNUCLEARPOWERCOMPANIES:
        if(attack.getAttackType() == AttackEnum.DESTROY && defendingGroup.getCardName().equals("Nuclear Power Companies")) return 2;
         case TREATTHISGROUPASGOVERNMENTWHENITATTEMPTSTOCONTROLAGOVERNMENTGROUP:
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.GOVERNMENT)
          attackingGroup.setAlignment(AlignmentEnum.GOVERNMENT);
          return 0;
         case PLUSTHREEONANYATTEMPTTODESTROYANYGROUP:
        if(attack.getAttackType() == AttackEnum.DESTROY) return 3;
         case PLUS2ONANYATTEMPTTODESTROYANYGROUP:
        if(attack.getAttackType() == AttackEnum.DESTROY) return 2;
         case PLUSFOURFORANYATTEMPTTOCONTROLNEUTRALIZEORDESTROYTHEORBITALMINDCONTROLLASERS:
        if(defendingGroup.getCardName().equals("Orbital Mind Control Lasers")) return 4;
         case PLUSTHREEONANYATTEMPTTOCONTROLANYLIBERALGROUP:
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.LIBERAL) return 3;
         case PLUSTHREEONANYATTEMPTTONEUTRALIZEANYGROUP:
        if(attack.getAttackType() == AttackEnum.NEUTRALIZE) return 3;
         case PLUSTWOONANYATTEMPTTOCONTROLANTINUCLEARACTIVISTS:
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getCardName().equals("Anti-NuclearActivists")) return 2;
         case PLUSFOURONANYATTEMPTTOCONTROLPUNKROCKERSCYCLEGANGSORHOLLYWOOD:
        if(attack.getAttackType() == AttackEnum.CONTROL && (defendingGroup.getCardName().equals("Punk Rockers") || defendingGroup.getCardName().equals("Cycle Gangs") || defendingGroup.getCardName().equals("Hollywood"))) return 4;
         case PLUSTHREEONANYATTEMPTTOCONTROLANYCOMMUNISTGROUP:
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.COMMUNIST) return 3;
         case PLUSFOURONANYATTEMPTTOCONTROLTHEPOSTOFFICE:
          if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getCardName().equals("Post Office")) return 4;
         case PLUSFOURFORDIRECTCONTROLNEUTRALIZATIONORDESTRUCTIONOFORBITALMINDCONTROLLASERS:
        for(int i = 0; i < 4; i++) {
          if(attackingGroup.getConnectedCards()[i]!= null && attackingGroup.getConnectedCards()[i].getCardName().equals("Orbital Mind Control Lasers")) return 4;
        }
         case PLUSFIVEONANYATTEMPTTOCONTROLBIGMEDIAOREMPTYVEE:
        if(attack.getAttackType() == AttackEnum.CONTROL && (defendingGroup.getCardName().equals("Big Media") || defendingGroup.getCardName().equals("Empty Vee"))) return 5;
         case PLUSTHREEFORDIRECTCONTROLFORANYCRIMINALGROUP:
      for(int i = 0; i < 4; i++) {
        if(attackingGroup.getConnectedCards()[i]!= null && attackingGroup.getConnectedCards()[i].getAlignment() == AlignmentEnum.CRIMINAL) return 4;
      }
         case PLUSSIXONANYATTEMPTTODESTROYANYCOMMUNISTGROUP:
        if(attack.getAttackType() == AttackEnum.DESTROY && defendingGroup.getAlignment() == AlignmentEnum.COMMUNIST) return 6;
         case PLUSTHREEONANYATTEMPTTOCONTROLNEUTRALIZEORDESTORYTHEPHONECOMPANY:
        if(defendingGroup.getCardName().equals("Phone Company")) return 3;
         case PLUSTWOONANYATTEMPTTOCONTROLANYWEIRDGROUP:
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.WEIRD) return 2;
         case PLUSONEONANYATTEMPTTODESTROYANYGROUP:
        if(attack.getAttackType() == AttackEnum.DESTROY) return 1;
         case PLUSFIVEFORDIRECTCONTROLOFSFFANSPLUSTWOFORDIRECTCONTROLOFTREKKIES:
        int bonus = 0;
        for(int i = 0; i < 4; i++) {
          if(attackingGroup.getConnectedCards()[i]!= null && attackingGroup.getConnectedCards()[i].getCardName().equals("S.F. Fans")) bonus += 5;
        }
        for(int i = 0; i < 4; i++) {
          if(attackingGroup.getConnectedCards()[i]!= null && attackingGroup.getConnectedCards()[i].getCardName().equals("Trekkies")) bonus += 2;
        }
        return bonus;
         case PLUSTHREEFORDIRECTCONTROLOFCONVENIENCESTORES:
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getCardName().equals("Convenience Stores")) return 3;
         case PLUSTHREEFORDIRECTCONTROLOFTHEMORALMINORITY:
        for(int i = 0; i < 4; i++) {
          if(attackingGroup.getConnectedCards()[i]!= null && attackingGroup.getConnectedCards()[i].getCardName().equals("Moral Minority")) return 3;
        }
           default:
     }

     if(defendingGroup.getCardName().equals("Gun Lobby") && (attackingGroup.getAlignment() == AlignmentEnum.LIBERAL || attackingGroup.getAlignment() == AlignmentEnum.COMMUNIST || attackingGroup.getAlignment() == AlignmentEnum.WEIRD))
      defendingGroup.setResistance(10);
     else if(defendingGroup.getCardName().equals("Gun Lobby")) defendingGroup.setResistance(3);
     //else if(defendingPlayer.ownsSurvivalists()) {
     //  defendingGroup.setResistance(defendingGroup.getResistance() + 2);
     //}
     return 0;
   }
 }

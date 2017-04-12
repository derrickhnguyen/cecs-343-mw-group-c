package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Table.AlignmentEnum;
import com.groupc.android.illuminati.Objects.Table.AttackEnum;
/**
 * Created by micha on 4/11/2017.
 */

 public class SpecialPowerBonus {
   NonSpecialCard attackingGroup;
   NonSpecialCard defendingGroup;
   Attack attack

   public SpecialPowerBonus(NonSpecialCard attackingGroup, NonSpecialCard defendingGroup, Attack attack) {
     this.attackingGroup = attackingGroup;
     this.defendingGroup = defendingGroup;
     this.attack = attack;
   }

   public int getSpecialPowerBonus() {
     swtich(attackingGroup.getSpecialAbilityEnum()) {
       case PLUSFOURONANYATTEMPTTONEUTRALIZEANYGROUP:
        if(attack.getAttackType() == AttackEnum.NEUTRALIZE) return 4;
       case PLUSFOURONANYATTEMPTTOCONTROLWEIRDGROUPSIMMUNETOATTACKSFROMGOVERNMENTORSTRAIGHTGROUPS
        if(attackingGroup.getAlignment() == AlignmentEnum.GOVERNMENT || attackingGroup.getAlignment() == AlignmentEnum.STRAIGHT)
          attack.endAttack();
        else if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.WEIRD)
          return 4;
      case PLUSTWOONANYATTEMPTTODESTROYANYGROUP
        if(attack.getAttackType() == AttackEnum.DESTROY) return 2;
      case PLUS2ONANYATTEMPTTODESTROYNUCLEARPOWERCOMPANIE
        if(attack.getAttackType() == AttackEnum.DESTROY && defendingGroup.getName().equals("Nuclear Power Companies")) return 2;
      case TREATTHISGROUPASGOVERNMENTWHENITATTEMPTSTOCONTROLAGOVERNMENTGROUP
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.GOVERNMENT)
          attackingGroup.setAlignment(AttackEnum.GOVERNMENT);
          return;
      case PLUSTHREEONANYATTEMPTTODESTROYANYGROUP
        if(attack.getAttackType() == AttackEnum.DESTROY) return 3;
      case PLUS2ONANYATTEMPTTODESTROYANYGROUP
        if(attack.getAttackType() == AttackEnum.DESTROY) return 2;
      case PLUSFOURFORANYATTEMPTTOCONTROLNEUTRALIZEORDESTROYTHEORBITALMINDCONTROLLASERS
        if(defendingGroup.getName().equals("Orbital Mind Control Lasers")) return 4;
      case PLUSTHREEONANYATTEMPTTOCONTROLANYLIBERALGROUP
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.LIBERAL) return 3;
      case PLUSTHREEEONANYATTEMPTTONEUTRALIZEANYGROUP
        if(attack.getAttackType() == AttackEnum.NEUTRALIZE) return 3;
      case PLUSTWOONANYATTEMPTTOCONTROLANTINUCLEARACTIVISTS
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getName().equals("Anti-NuclearActivists")) return 2;
      case PLUSFOURONANYATTEMPTTOCONTROLPUNKROCKERSCYCLEGANGSORHOLLYWOOD
        if(attack.getAttackType() == AttackEnum.CONTROL && (defendingGroup.getName().equals("Punk Rockers") || defendingGroup.getName().equals("Cycle Gangs") || defendingGroup.getName().equals("Hollywood"))) return 4;
      case PLUSTHREEONANYATTEMPTTOCONTROLANYCOMMUNISTGROUP
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.COMMUNIST) return 3;
      case PLUSFOURONANYATTEMPTTOCONTROLTHEPOSTOFFICE
          if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getName().equals("Post Office")) return 4;
      case PLUSFOURFORDIRECTCONTROLNEUTRALIZATIONORDESTRUCTIONOFORBITALMINDCONTROLLASERS
        for(int i = 0; i < 4; i++) {
          if(attackingGroup.getConnectedCards()[i].getName().equals("Orbital Mind Control Lasers")) return 4;
        }
      case PLUSFIVEONANYATTEMPTTOCONTROLBIGMEDIAOREMPTYVEE
        if(attack.getAttackType() == AttackEnum.CONTROL && (defendingGroup.getName().equals("Big Media") || defendingGroup.getName().equals("Empty Vee"))) return 5;
      case PLUSTHREEFORDIRECTCONTROLFORANYCRIMINALGROUP
      for(int i = 0; i < 4; i++) {
        if(attackingGroup.getConnectedCards()[i].getAlignment() == AlignmentEnum.CRIMINAL) return 4;
      }
      case PLUSSIXONANYATTEMPTTODESTROYANYCOMMUNISTGROUP
        if(attack.getAttackType() == AttackEnum.DESTROY && defendingGroup.getAlignment() == AlignmentEnum.COMMUNIST) return 6;
      case PLUSTHREEONANYATTEMPTTOCONTROLNEUTRALIZEORDESTORYTHEPHONECOMPANY
        if(defendingGroup.getName().equals("Phone Company")) return 3;
      case PLUSTWOONANYATTEMPTTOCONTROLANYWEIRDGROUP
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getAlignment() == AlignmentEnum.WEIRD) return 2;
      case PLUSONEONANYATTEMPTTODESTROYANYGROUP
        if(attack.getAttackType() == AttackEnum.DESTROY) return 1;
      case PLUSFIVEFORDIRECTCONTROLOFSFFANSPLUSTWOFORDIRECTCONTROLOFTREKKIES
        int bonus = 0;
        for(int i = 0; i < 4; i++) {
          if(attackingGroup.getConnectedCards()[i].getName().equals("S.F. Fans")) bonus += 5;
        }
        for(int i = 0; i < 4; i++) {
          if(attackingGroup.getConnectedCards()[i].getName().equals("Trekkies")) bonus += 2;
        }
        return bonus;
      case PLUSTHREEFORDIRECTCONTROLOFCONVENIENCESTORES
        if(attack.getAttackType() == AttackEnum.CONTROL && defendingGroup.getName().equals("Convenience Stores")) return 3;
      case PLUSTHREEFORDIRECTCONTROLOFTHEMORALMINORITY
        for(int i = 0; i < 4; i++) {
          if(attackingGroup.getConnectedCards()[i].getName().equals("Moral Minority")) return 3;
        }
      default:
        break;
     }

     if(defendingGroup.getName().equals("Gun Lobby") && (attackingGroup.getAlignment() == AlignmentEnum.LIBERAL || attackingGroup.getAlignment() == AlignmentEnum.COMMUNIST || attackingGroup.getAlignment() == AlignmentEnum.WEIRD))
      defendingGroup.setResistance(10);
     else if(defendingGroup.getName().equals("Gun Lobby")) defendingGroup.setResistance(3);
     else if(defendingPlayer.ownsSurvivalists()) {
       defendingGroup.setResistance(defendingGroup.getResistance() + 2);
     }
     return 0;
   }
 }

package com.groupc.android.illuminati.Objects;

/**
 * Created by micha on 4/11/2017.
 */
import com.groupc.android.illuminati.Objects.Table.AlignmentEnum;

 public class AlignmentBonus {
   private AlignmentEnum attackerAlignment;
   private AlignmentEnum defenderAlignment;

   public AlignmentBonus(NonSpecialCard attackingGroup, NonSpecialCard defendingGroup) {
       if(!attackingGroup.getType().equals(Table.CardTypeEnum.ILLUMINATI))
       {
           attackerAlignment = attackingGroup.getAlignment();
           defenderAlignment = defendingGroup.getAlignment();
       } else {
           attackerAlignment = null;
           defenderAlignment = null;
       }

   }

   public int getAlignmentBonus() {
       if(attackerAlignment == null)
       {
           return 0;
       }
       if (attackerAlignment == defenderAlignment) return 4;
       else if (
               attackerAlignment == AlignmentEnum.GOVERNMENT && defenderAlignment == AlignmentEnum.COMMUNIST ||
                       attackerAlignment == AlignmentEnum.COMMUNIST && defenderAlignment == AlignmentEnum.GOVERNMENT ||
                       attackerAlignment == AlignmentEnum.LIBERAL && defenderAlignment == AlignmentEnum.CONSERVATIVE ||
                       attackerAlignment == AlignmentEnum.CONSERVATIVE && defenderAlignment == AlignmentEnum.LIBERAL ||
                       attackerAlignment == AlignmentEnum.PEACEFULL && defenderAlignment == AlignmentEnum.VIOLENT ||
                       attackerAlignment == AlignmentEnum.VIOLENT && defenderAlignment == AlignmentEnum.PEACEFULL ||
                       attackerAlignment == AlignmentEnum.STRAIGHT && defenderAlignment == AlignmentEnum.WEIRD ||
                       attackerAlignment == AlignmentEnum.WEIRD && defenderAlignment == AlignmentEnum.STRAIGHT ||
                       attackerAlignment == AlignmentEnum.FANATIC && defenderAlignment == AlignmentEnum.FANATIC) {
           return -4;
       }
       return 0;
   }
 }

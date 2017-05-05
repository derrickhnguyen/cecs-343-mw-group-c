package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.NonSpecialCard;

/**
 * Created by micha on 4/14/2017.
 */
 public class TransferMoney{
   private NonSpecialCard sendingNonSpecialCard;
   private NonSpecialCard receivingNonSpecialCard;

   public TransferMoney() {
     sendingNonSpecialCard = chooseSendingNonSpecialCard();
     receivingNonSpecialCard = chooseReceivingNonSpecialCard();
     transferMoney();
   }

   private NonSpecialCard chooseSendingNonSpecialCard() {
      NonSpecialCard sendingNonSpecialCard = null;

      return sendingNonSpecialCard;
   }

   private NonSpecialCard chooseReceivingNonSpecialCard() {
      NonSpecialCard receivingNonSpecialCard = null;

      return receivingNonSpecialCard;
   }

   private boolean transferMoney() {
     int moneyTransfer = sendingNonSpecialCard.spendMoney();
     receivingNonSpecialCard.setGroupTreasury(receivingNonSpecialCard.getGroupTreasury() + moneyTransfer);
       return true;
   }
 }

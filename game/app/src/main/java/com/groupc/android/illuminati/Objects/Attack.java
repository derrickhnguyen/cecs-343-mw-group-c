package com.groupc.android.illuminati.Objects;

import android.util.Log;

import com.groupc.android.illuminati.MainScreen;
import com.groupc.android.illuminati.Objects.Table.AttackEnum;

import java.security.acl.Group;

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
    NonSpecialCard attackingCard;
    GroupCard defendingCard;
    boolean isAttackingAPlayer;
    boolean isSuccessful;

    //    public Attack(Player attackingPlayer, Table table) {
//        this.attackingPlayer = attackingPlayer;
//        this.table = table;
//        //this.attackType = attackType;
//        center = table.getCenter();
//        destroyedCards = table.getDestroyedCards();
//        //setUpAttackAnnouncement();
//    }
    public Attack(Player attackingPlayer, NonSpecialCard attackingCard, Player defendingPlayer, GroupCard defendingCard, boolean isAttackingAPlayer, AttackEnum attackType)
    {
        this.attackingPlayer = attackingPlayer;
        this.attackingCard = attackingCard;
        this.defendingCard = defendingCard;
        if(isAttackingAPlayer)
        {
            this.isAttackingAPlayer = true;
            this.defendingPlayer = defendingPlayer;
        } else {
            this.isAttackingAPlayer = false;
            //player is attacking the center pile
        }
        this.attackType = attackType;
        table = MainScreen.getTable();
        center = table.getCenter();
    }

    public void setUpAttackAnnouncement() {
        //NonSpecialCard attackingGroup = null;
        //defendingPlayer = null;
        //NonSpecialCard defendingGroup = null;
        //attackingGroup = get this from user
        //defendingPlayer = get this from user
        //defendingGroup = get this from user
        AttackAnnouncement announcement = new AttackAnnouncement(
                attackingPlayer,
                attackingCard,
                defendingPlayer,
                defendingCard);
        announcement.setAlignmentBonus(new AlignmentBonus(attackingCard, defendingCard).getAlignmentBonus());
        announcement.setPowerStructurePositionBonus(new PowerStructurePositionBonus(defendingPlayer, defendingCard).getPowerStructurePositionBonus());
//        announcement.setSpecialPowerBonus(new SpecialPowerBonus(attackingCard, defendingCard, this).getSpecialPowerBonus());
        //announcement.setAttackerMoneyBonus();
        announcement.send(defendingPlayer);

        boolean isAttacker = true;
        /*while (announcement.betting()) {
            isAttacker = !isAttacker;
            if (isAttacker) {
                announcement.setAttackerMoneyBonus();
                announcement.send(defendingPlayer);
            } else {
                announcement.setDefenderMoneyBonus();
                announcement.send(attackingPlayer);
            }
        }*/

        if (announcement.isAccepted()) {
            DiceRoll diceRoll = new DiceRoll();
            int sum = diceRoll.getDiceSum();
            Log.d("DICE ROLL SUM", sum + "");
            Log.d("OTHER SCORE", announcement.getScore() + "");
            if (sum - announcement.getScore() > 0)
                Log.d("ATTACKING CARD", attackingCard.getCardName());
            attackIsSuccesful(attackingCard);
            //else endAttack();
            //else endAttack();
        } else endAttack();
    }


    public void attackIsSuccesful(NonSpecialCard puppet) {
        isSuccessful = true;
        if (defendingCard.getCardName().equals("Survivalists")) {
            if (defendingPlayer != null) defendingPlayer.setOwnsSurvivalists(false);
            attackingPlayer.setOwnsSurvivalists(true);
        }

        if (attackType == AttackEnum.CONTROL) {
            if (attackingPlayer.getPowerStructure().hasRoom(defendingCard)) {
                attackingPlayer.getPowerStructure().addToPowerStructure(puppet, defendingCard);
                center.removeGroupFromCenter(defendingCard);
            } else {
                for (int i = 0; i < defendingCard.getConnectedCards().length; i++) {
                    if (defendingCard.getConnectedCards()[i] != null) {
                        GroupCard topCard = (GroupCard) defendingCard.getConnectedCards()[i];
                        defendingCard.removePuppet(topCard);
                        center.addGroupToCenter(topCard);
                    }
                }
                if (attackingPlayer.getPowerStructure().hasRoom(defendingCard)) {
                    attackingPlayer.getPowerStructure().addToPowerStructure(puppet, defendingCard);
                    center.removeGroupFromCenter(defendingCard);
                } else return;
            }
        }

        if (attackType == AttackEnum.NEUTRALIZE) {
            for (int i = 0; i < defendingCard.getConnectedCards().length; i++) {
                if (defendingCard.getConnectedCards()[i] != null) {
                    GroupCard topCard = (GroupCard) defendingCard.getConnectedCards()[i];
                    defendingCard.removePuppet(topCard);
                    center.addGroupToCenter(topCard);
                }
            }
            center.addGroupToCenter(defendingCard);
        }

        if (attackType == AttackEnum.DESTROY) {
            for (int i = 0; i < defendingCard.getConnectedCards().length; i++) {
                if (defendingCard.getConnectedCards()[i] != null) {
                    GroupCard topCard = (GroupCard) defendingCard.getConnectedCards()[i];
                    defendingCard.removePuppet(topCard);
                    center.addGroupToCenter(topCard);
                }
            }
            destroyedCards.addDestroyedCard(defendingCard);
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

    public boolean isSuccessful()
    {
        return isSuccessful;
    }

//    public void setAttack(Player attackingPlayer, NonSpecialCard attackingCard, Player defendingPlayer, NonSpecialCard defendingCard, boolean isAttackingAPlayer, AttackEnum attackType)
//    {
//        this.attackingPlayer = attackingPlayer;
//        this.attackingCard = attackingCard;
//        this.defendingCard = defendingCard;
//        if(isAttackingAPlayer)
//        {
//            this.isAttackingAPlayer = true;
//            this.defendingPlayer = defendingPlayer;
//        } else {
//            this.isAttackingAPlayer = false;
//            //player is attacking the center pile
//        }
//        this.attackType = attackType;
//    }
}

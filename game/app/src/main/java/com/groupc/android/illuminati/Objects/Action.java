package com.groupc.android.illuminati.Objects;

import java.util.Scanner;

/**
 * Created by micha on 4/9/2017.
 */

public class Action {
    private Player  player;
    private int     actionsTaken;

    public Action(Player player) {
        this.player = player;
        int choice = displayActions();
        takeAction(choice);
    }

    public int getActionsTaken() {
        return actionsTaken;
    }

    public int displayActions() {
        System.out.println("Choose an action:\n" +
                "Regular Actions:\n" +
                "1. Attack a Group\n" +
                "2. Transfer Money\n" +
                "3. Move a Group\n" +
                "4. Give a Group Away\n" +
                "\n" +
                "Free Actions\n" +
                "5. Drop a Group\n" +
                "6. Give Away Money\n" +
                "7. Give Away Special\n" +
                "8. Use a Special\n" +
                "9. Special Power Action\n" +
                "\n" +
                "10. Pass");
        //int choice = number from user choice
        return choice;
    }

    public void takeAction(int choice) {
      switch(choice) {
        case 1: Attack attack = new Attack(player);
                //find a way to reset chinese campaign donors alignment (rule enforcement)
                //find a way to reset defending groups resistance if defender owns survivalists (rule enforcement)
                actionsTaken++;
                break;
        case 2: TransferMoney transferMoney = new TransferMoney();
                actionsTaken++;
                break;
        case 3: MoveAGroup moveAGroup = new MoveAGroup();
                actionsTaken++;
                break;
        case 4: GiveAGroupAway giveAGroupAway = new GiveAGroupAway();
                actionsTaken++;
                break;
        case 5: DropAGroup dropAGroup = new DropAGroup();
                break;
        case 6: GiveAwayMoney giveAwayMoney = new GiveAwayMoney();
                break;
        case 7: GiveAwaySpecial giveAwaySpecial = new GiveAwaySpecial();
                break;
        case 8: UseASpecial useASpecial = new UseASpecial();
                break;
        case 9: SpecialPowerAction specialPowerAction = new SpecialPowerAction();
                break;
        case 10: Pass pass = new Pass();
                break;
        default:
      }
    }
}

package com.example.micha.illuminice;

import java.util.Scanner;

/**
 * Created by micha on 4/9/2017.
 */

public class Action {
    private Player  player;
    private int     actionsTaken;

    public Action(Player player) {
        this.player = player;
    }

    public int getActionsTaken() {
        return actionsTaken;
    }

    public int displayActionsTaken() {
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
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        return choice;
    }
}

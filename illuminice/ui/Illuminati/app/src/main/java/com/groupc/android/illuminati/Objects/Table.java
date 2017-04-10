package com.groupc.android.illuminati.Objects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by micha on 4/9/2017.
 */

public class Table {
    private Bank    bank;
    private Center  center;
    private Deck    deck;

    private int                         numberOfPlayers;
    private Queue<Player>               players;
    private ArrayList<PowerStructure>   powerStructures;

    private Player  currentPlayer;
    private Player  winner;
    private boolean gameIsActive;

    public enum IlluminatiCardEnum {
        BAVARIANILLUMINATI("The Bavarian Illuminati"),
        BERMUTATRIANGE("The Bermuta Triangle"),
        DISCORDIANSOCIETY("The Discordian Society"),
        GNOMESOFZURICH("The Gnomes of Zurich"),
        NETWORK("The Network"),
        SERVANTSOFCTHULHU("The Servants of Cthulu"),
        SOCIETYOFASSASSINS("The Society of Assassins"),
        UFOS("The UFO's");

        private final String name;

        private IlluminatiCardEnum(String s) {
            name = s;
        }

        public String getName() {
            return name;
        }
    };
    private int numberOfIlluminatiCards = 8;
    private boolean[] isTaken; //for illuminati card choosing

    public Table() {
        bank    = new Bank();
        center  = new Center();
        deck    = new Deck();

        players         = new LinkedList<>();
        powerStructures = new ArrayList<PowerStructure>();

        gameIsActive = true;
        isTaken = new boolean[numberOfIlluminatiCards];

        initializePlayers();
        addCardsToCenter();
        seeWhoGoesFirst();
        turn();
    }

    private void initializePlayers() {
        System.out.println("Welcome. Please enter the number of players:");
        Scanner in = new Scanner(System.in);
        numberOfPlayers = in.nextInt();

        String          username;
        Player          player;
        IlluminatiCard  illuminatiCard;
        for(int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Please enter username for user " + (i + 1));
            username = in.next();
            illuminatiCard = chooseIlluminatiCard();
            player = new Player(username, illuminatiCard);
            players.add(player);
            System.out.println("Congratulations " + username + "!\n" +
                    "You got " + illuminatiCard.getName());
        }
    }

    private IlluminatiCard chooseIlluminatiCard() {
        int randInt;
        do {
            Random rand = new Random();
            randInt = rand.nextInt(numberOfIlluminatiCards);
        } while(isTaken[randInt]);

        IlluminatiCardEnum illuminati = IlluminatiCardEnum.values()[randInt];
        IlluminatiCard illuminatiCard = new IlluminatiCard(illuminati);
        isTaken[randInt] = true;

        return illuminatiCard;
    }

    private void addCardsToCenter() {
        int numberOfOriginalGroupCards = 4;
        for(int i = 0; i < numberOfOriginalGroupCards; i++) {
            center.addGroupToCenter(deck.drawGroupCard());
        }
    }

    private void seeWhoGoesFirst() {
        int maxDiceRoll = 0;
        Player firstPlayer = null;
        for(int i = 0; i < numberOfPlayers; i++) {
            currentPlayer = players.poll();
            if(currentPlayer.rollDice() > maxDiceRoll) {
                maxDiceRoll = currentPlayer.rollDice();
                firstPlayer = currentPlayer;
            }
            players.add(currentPlayer);
        }
        for(int i = 0; i < numberOfPlayers; i++) {
            currentPlayer = players.peek();
            if(currentPlayer == firstPlayer) {
                break;
            }
            else {
                currentPlayer = players.poll();
                players.add(currentPlayer);
            }
        }
        System.out.println("Player " + firstPlayer.getUsername() + " goes first!");
    }

    public boolean turn() {
        while(gameIsActive == true) {
            currentPlayer = players.poll();
            int maxActionsTaken = 2;
            int actionsTaken = 0;
            while(actionsTaken < maxActionsTaken) {
                Action action = new Action(currentPlayer);
                actionsTaken = action.getActionsTaken();
            }

            if(checkIfWon(currentPlayer)) break;
            players.add(currentPlayer);

            break; //temporary so it doesn't run forever when testing
        }

        return true;
    }

    private boolean checkIfWon(Player player) {
        if(player.getIlluminatiCard().checkGenericGoal()) return true;
        if(player.getIlluminatiCard().checkSpecialGoal()) return true;
        return false;
    }

    public static void main(String[] args) {
        Table table = new Table();
    }
}

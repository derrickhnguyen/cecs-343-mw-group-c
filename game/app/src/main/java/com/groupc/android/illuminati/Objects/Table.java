package com.groupc.android.illuminati.Objects;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by micha on 4/9/2017.
 */

public class Table {
    private Bank            bank;
    private Center          center;
    private Deck            deck;
    private DestroyedCards  destroyedCards;

    private int                         numberOfPlayers;
    private int                         playerCount;
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

    public enum AlignmentEnum {
      GOVERNMENT("Government"),
      COMMUNIST("Communist"),
      LIBERAL("Liberal"),
      CONSERVATIVE("Conservative"),
      PEACEFULL("Peacefull"),
      VIOLENT("Violent"),
      STRAIGHT("Straight"),
      WEIRD("Weird"),
      CRIMINAL("Criminal"),
      FANATIC("Fanatic"),
      NONE("none");

      private final String name;

      private AlignmentEnum(String s) {
        name = s;
      }

      public String getName() {
        return name;
      }
    };

    public enum SpecialAbilityEnum {
      PLUSFOURONANYATTEMPTTONEUTRALIZEANYGROUP("Plus Four For Any Attempt To Neutralize Any Group"),
      TURNSOVERTWOCARDSATBEGGININGOFGAME("Turns Over Two Cards At Beggining Of Turn"), //notinattack
      PLUSFOURONANYATTEMPTTOCONTROLWEIRDGROUPSIMMUNETOATTACKSFROMGOVERNMENTORSTRAIGHTGROUPS("Plus Four On Any Attempt To Control Weird Groups Immune To Any Attacks From Government Or Straight Groups"),
      ILLUMINATIGROUPMAYPARTICIPATEINTWOATTACKSPERTURN("Illuminati Group May Participate In Two Attacks Per Turn"), //notinattack
      PLUSTWOONANYATTEMPTTODESTROYANYGROUP("Plus Two On Any Attempt To Destroy Any Group"),
      MAYMOVEMONEYFREELYATENDOFTURN("May Move Money Freely At End Of Turn"), //notinattack
      MAYMAKEONEPRIVILEGEDATTACKEACHTURNATACOSTOFFIVEMEGABUCKS("May Make One Privileged Attack Each Turn At A Cost Of Five Mega Bucks"), //notinattack
      MAYREORGANIZEGROUPSFREELYATENDOFTURN("May Reorganize Groups Freely At End Of Turn"), //notinattack
      PLUSTWOONANYATTEMPTTODESTROYNUCLEARPOWERCOMPANIES("Plus Two On Attempt To Destroy Nuclear Power Companies"),
      TREATTHISGROUPASGOVERNMENTWHENITATTEMPTSTOCONTROLAGOVERNMENTGROUP("Treat This Group As Government When It Attempts To Control A Government Group"),
      PLUSTHREEONANYATTEMPTTODESTROYANYGROUP("Plus Three On Any Attempt To Destroy Any Group"),
      PLUS2ONANYATTEMPTTODESTROYANYGROUP("Plus Two On Any Attempt To Destroy Any Group"),
      PLUSFOURFORANYATTEMPTTOCONTROLNEUTRALIZEORDESTROYTHEORBITALMINDCONTROLLASERS("Plus Four For Any Attempt To Control Neutralize Or Destroy The Orbital Mind Control Lasers"),
      WHENITTRANSFERSMONEYTHATMONEYCANGOTOANYGROUPINTHESAMEPOWERSTRUCTURE("When It Transfers Money That Money Can Go To Any Group In The Same Power Strucutre"), //notinattack
      PLUSTHREEONANYATTEMPTTOCONTROLANYLIBERALGROUP("Plus Three On Any Attempt To Control Any Liberal Group"),
      NORMALRESISTANCETHREEAGAINSTANYLIBERALCOMMUNISTORWEIRDGROUPRESISTANCETEN("Normal Resitance Three Against Any Liberal Communist Or Weird Group Resistance Ten"), //takencareofinattackbutnotaddresseddirectly
      PLUSTHREEONANYATTEMPTTONEUTRALIZEANYGROUP("Plus Three On Any Attempt To Neutralize Any Group"),
      PLUSTWOONANYATTEMPTTOCONTROLANTINUCLEARACTIVISTS("Plus Two On Any Attempt To Control Anti Neuclear Activists"),
      PLUSFOURONANYATTEMPTTOCONTROLPUNKROCKERSCYCLEGANGSORHOLLYWOOD("Plus Four On Any Attempt To Control Punk Rockers Cycle Gangs Or Hollywood"),
      PLUSTHREEONANYATTEMPTTOCONTROLANYCOMMUNISTGROUP("Plus Three On Any Attempt To Control Any Communist Group"),
      OWNINGPLAYERMAYTAXEACHOPPONENTTWOMEGABUCKSONHISOWNICOMEPHASETAXMAYCOMEFROMANYGROUPIFAPLAYERHASNOMONEYHEOWESNOTAX("Owning Player May Tax Each Opponent Two Mega Bucks On His Own Income Phase Tax May Come From Any Group If A Player Has No Money He Owes No Tax"), //notinattack
      PLUSFOURONANYATTEMPTTOCONTROLTHEPOSTOFFICE("Plus Four On Any Attempt To Control The Post Office"),
      PLUSFOURFORDIRECTCONTROLNEUTRALIZATIONORDESTRUCTIONOFORBITALMINDCONTROLLASERS("Plus Four For Direct Control Neutralization Or Destruction Of Orbital Mind Control Lasers"),
      PLUSFIVEONANYATTEMPTTOCONTROLBIGMEDIAOREMPTYVEE("Plus Five On Any Attempt To Control Big Media Or Empty Vee"),
      PLUSTHREEFORDIRECTCONTROLFORANYCRIMINALGROUP("Plus Three For Direct Control Of Any Criminal Group"),
      PLUSSIXONANYATTEMPTTODESTROYANYCOMMUNISTGROUP("Plus Six For Any Attempt To Destroy Any Communist Group"),
      ONHISTURNOWNERCANADDREMOVEORREVERSEANALIGNMENTOFANYONEOTHERGROUPINPLAYCHANGELASTSFORTHATTURNONLY("On His Turn Owner Can Add Remove Or Reverse An Alignment Of Any One Other Grup In Play Change Lasts For That Turn Only"), //notinattack
      PLUSTHREEONANYATTEMPTTOCONTROLNEUTRALIZEORDESTORYTHEPHONECOMPANY("Plus Three On Any Attempt To Control Neutralize Or Destroy The Phone Company"),
      PAYFIVEMEGABUCKSFORTHISGROUPTODRAWANEXTRACARDONYOURTURNTHISTURNISNOTANACTION("Pay Five Extra Mega Bucks From This Group To Draw An Extra Card On Your Turn This Is Not An Action"), //notinattack
      PLUSTWOONANYATTEMPTTOCONTROLANYWEIRDGROUP("Plus Two On Any Attempt To Control Any Weird Group"),
      PLUSONEONANYATTEMPTTODESTROYANYGROUP("Plus One On Any Attempt To Destroy Any Group"),
      PLUSFIVEFORDIRECTCONTROLOFSFFANSPLUSTWOFORDIRECTCONTROLOFTREKKIES("Plus Five For Direct Control Of SF Fans Plus Two For Direct Control Of Trekkies"),
      PLUSTWORESISTANCETOALLOWNERSOTHERGROUPS("Plus Two Resistance To All Owners Other Groups"), //takencareofinattackbutnotaddresseddirectly
      PLUSTHREEFORDIRECTCONTROLOFCONVENIENCESTORES("Plus Three For Direct Control Of Convenience Stores"),
      PLUSTHREEFORDIRECTCONTROLOFTHEMORALMINORITY("Plus Three For Direct Control Of The Moral Minority"),
      NONE("None");


      private final String name;

      private SpecialAbilityEnum(String s) {
        name = s;
      }

      public String getName() {
        return name;
      }
    };

    public enum CardTypeEnum {
      GROUP("Group"),
      ILLUMINATI("Illuminati"),
      SPECIAL("Special");

      private final String name;

      private CardTypeEnum(String s) {
        name = s;
      }

      public String getName() {
        return name;
      }
    };

    public enum AttackEnum {
        CONTROL("Control"),
        NEUTRALIZE("Neutralize"),
        DESTROY("Destroy");

        private final String name;

        private AttackEnum(String s) {
            name = s;
        }

        public String getName() {
            return name;
        }
    };

    private int numberOfIlluminatiCards = 8;
    private boolean[] isTaken;

    public Table() {
        bank            = new Bank();
        center          = new Center();
        deck            = new Deck();
        destroyedCards  = new DestroyedCards();

        players         = new LinkedList<>();
        powerStructures = new ArrayList<PowerStructure>();
        playerCount     = 0;

        gameIsActive = true;
        isTaken = new boolean[numberOfIlluminatiCards];

    }

    public void addPlayer() {
        playerCount++;
        String          username = "Player " + playerCount;
        Player          player;
        IlluminatiCard  illuminatiCard;
        illuminatiCard = chooseIlluminatiCard();
        player = new Player(username, illuminatiCard);
        players.add(player);
    }

    public LinkedList<Player> getPlayers() {
        return (LinkedList<Player>) players;
    }

    private IlluminatiCard chooseIlluminatiCard() {
        int randInt;
        int power = 0; //get power from database
        int transferablePower = 0; //get transferable power from database
        int resistance = 0; //get resistance from database
        int income = 0; //get income from database;
        AlignmentEnum alignment = null; //get alignment from database
        SpecialAbilityEnum special = null; //get special ability from database

        do {
            Random rand = new Random();
            randInt = rand.nextInt(numberOfIlluminatiCards);
        } while(isTaken[randInt]);

        IlluminatiCardEnum illuminati = IlluminatiCardEnum.values()[randInt];
        IlluminatiCard illuminatiCard = new IlluminatiCard(
                illuminati.getName(),
                CardTypeEnum.ILLUMINATI,
                power,
                transferablePower,
                resistance,
                income,
                special,
                illuminati
                );
        isTaken[randInt] = true;

        return illuminatiCard;
    }

    public void addCardsToCenter() {
        int numberOfOriginalGroupCards = 4;
        for(int i = 0; i < numberOfOriginalGroupCards; i++) {
            Card card = deck.draw();
            if(card.getType() == CardTypeEnum.GROUP) center.addGroupToCenter((GroupCard) card);
            else deck.returnToDeck(card);
        }
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = Integer.parseInt(numberOfPlayers);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
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
    }

    public boolean turn() {
        while(gameIsActive == true) {
            currentPlayer = players.poll();
            int maxActionsTaken = 2;
            int actionsTaken = 0;
            while(actionsTaken < maxActionsTaken) {
                Action action = new Action(currentPlayer, this);
                actionsTaken += action.getActionsTaken();
            }

            if(checkIfWon(currentPlayer)) break;
            players.add(currentPlayer);
        }

        return true;
    }

    private boolean checkIfWon(Player player) {
        if(player.getIlluminatiCard().checkGenericGoal()) return true;
        if(player.getIlluminatiCard().checkSpecialGoal()) return true;
        return false;
    }

    public Center getCenter() {
        return center;
    }

    public DestroyedCards getDestroyedCards() {
        return destroyedCards;
    }
}

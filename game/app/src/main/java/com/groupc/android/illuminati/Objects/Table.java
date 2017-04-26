package com.groupc.android.illuminati.Objects;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Table {
    private Center          center;
    private Deck            deck;
    private DestroyedCards  destroyedCards;

    private int                         numberOfPlayers;
    private int                         playerCount;
    private Queue<Player>               players;
    private ArrayList<IlluminatiCard>   cards;

    private Player  currentPlayer;
    private boolean gameIsActive;
    private boolean actionInitialized;

    private Action action;

    private int numberOfIlluminatiCards = 8;
    private boolean[] isTaken;

    public Table() {
        center          = new Center();
        deck            = new Deck();
        destroyedCards  = new DestroyedCards();

        players         = new LinkedList<>();
        playerCount     = 0;

        gameIsActive = true;
        isTaken = new boolean[numberOfIlluminatiCards];

        cards = new ArrayList<>();
        initializeIlluminatiCards();
        actionInitialized = false;
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

    public LinkedList<Player> getPlayers() { return (LinkedList<Player>) players; }

    private IlluminatiCard chooseIlluminatiCard() {
        int randInt;

        do {
            Random rand = new Random();
            randInt = rand.nextInt(numberOfIlluminatiCards);
        } while(isTaken[randInt]);

        IlluminatiCard illuminati = cards.get(randInt);
        isTaken[randInt] = true;

        return illuminati;
    }

    public void addCardsToCenter() {
        int numberOfOriginalGroupCards = 3;
        for(int i = 0; i <= numberOfOriginalGroupCards; i++) {
            Card card = deck.draw();
            if(card.getType() == CardTypeEnum.GROUP) center.addGroupToCenter((GroupCard) card);
            else {
                deck.returnToDeck(card);
                i--;
            }
            System.out.println(card.getCardName());
        }
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = Integer.parseInt(numberOfPlayers);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public ArrayList<Integer> seeWhoGoesFirst() {
        int maxDiceRoll = 0;
        Player firstPlayer = null;
        ArrayList<Integer> diceRolls = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) {
            currentPlayer = players.poll();
            int diceRoll = currentPlayer.rollDice();
            if(diceRoll > maxDiceRoll) {
                maxDiceRoll = currentPlayer.rollDice();
                firstPlayer = currentPlayer;
            }
            diceRolls.add(diceRoll);
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
        return diceRolls;
    }

    public boolean newTurn() {
        currentPlayer.resetAcitonsTaken();
            currentPlayer = players.poll();
            //if(checkIfWon(currentPlayer))
            players.add(currentPlayer);
        currentPlayer = players.peek();

        if(center.getAllGroupCards().size() < 3) {
            ArrayList<GroupCard> cards = new ArrayList<GroupCard>();
            while(cards.size() < 2) {
                Card card = deck.draw();
                if(card.getType() == CardTypeEnum.GROUP) cards.add((GroupCard) card);
            }
            center.addGroupToCenter(cards.get(0));
            center.addGroupToCenter(cards.get(1));

            for(int i = 0; i < currentPlayer.getPowerStructure().getPowerStructureCards().size(); i++) {
                int income = currentPlayer.getPowerStructure().getPowerStructureCards().get(i).getIncome();
                int treasury = currentPlayer.getPowerStructure().getPowerStructureCards().get(i).getGroupTreasury();
                currentPlayer.getPowerStructure().getPowerStructureCards().get(i).setGroupTreasury(income + treasury);
            }
        }

        return true;
    }

    public void newAction() {
        action = new Action(currentPlayer, this);
        actionInitialized = true;
    }
    public Action getAction() {
        return action;
    }

    public boolean actionInitialized() {
        if(actionInitialized == true) return true;
        else return false;
    }

    private boolean checkIfWon(Player player) {
        return player.getIlluminatiCard().checkGenericGoal() || player.getIlluminatiCard().checkSpecialGoal();
    }

    public Center getCenter() { return center; }

    DestroyedCards getDestroyedCards() { return destroyedCards; }

    public Deck getDeck() { return deck; }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    NonSpecialCard giver;

    public void setGiver(NonSpecialCard giver) { this.giver = giver; }

    public NonSpecialCard getGiver() { return giver; }

    NonSpecialCard receiver;

    public void setReceiver(NonSpecialCard receiver) { this.receiver = receiver; }

    public NonSpecialCard getReceiver() { return receiver; }

    public Player getSpecificPlayer(String username) {
        Player returnPlayer = null;
        for(int i = 0; i < players.size(); i++) {
            Player player = players.poll();
            if(player.getUsername().contains(username)) {
                returnPlayer = player;
            }
            players.add(player);
        }
        return returnPlayer;
    }

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

        AlignmentEnum(String s) { name = s; }

        public String getName() { return name; }
    }

    public enum AttackEnum {

        CONTROL("Control"),
        NEUTRALIZE("Neutralize"),
        DESTROY("Destroy");

        private final String name;

        AttackEnum(String s) { name = s; }

        public String getName() {return name; }
    }

    public enum CardTypeEnum {

        GROUP("Group"),
        ILLUMINATI("Illuminati"),
        SPECIAL("Special");

        private final String name;

        CardTypeEnum(String s) { name = s; }

        public String getName() { return name; }
    }

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

        IlluminatiCardEnum(String s) { name = s; }

        public String getName() { return name; }
    }

    public enum SpecialAbilityEnum {

        PLUSFOURONANYATTEMPTTONEUTRALIZEANYGROUP("Plus Four For Any Attempt To Neutralize Any Group"),
        TURNSOVERTWOCARDSATBEGGININGOFGAME("Turns Over Two Cards At Beggining Of Turn"), //notinattack
        PLUSFOURONANYATTEMPTTOCONTROLWEIRDGROUPSIMMUNETOATTACKSFROMGOVERNMENTORSTRAIGHTGROUPS("Plus Four On Any Attempt To Control Weird Groups Immune To Any Attacks From Government Or Straight Groups"),
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
        ILLUMINATIGROUPMAYPARTICIPATEINTWOATTACKSPERTURN("Illuminati Group May Participate In Two Attacks Per Turn"),
        NONE("None");

        private final String name;

        SpecialAbilityEnum(String s) { name = s; }

        public String getName() { return name; }
    }

    private void initializeIlluminatiCards() {
        IlluminatiCard societyOfAssins = new IlluminatiCard(
                "The Society of Assassins",
                Table.CardTypeEnum.ILLUMINATI,
                8, 0, 8, 8,
                Table.SpecialAbilityEnum.PLUSFOURONANYATTEMPTTONEUTRALIZEANYGROUP,
                Table.IlluminatiCardEnum.SOCIETYOFASSASSINS
        );
        cards.add(societyOfAssins);

        IlluminatiCard network = new IlluminatiCard(
                "The Network",
                Table.CardTypeEnum.ILLUMINATI,
                7, 0, 7, 9,
                Table.SpecialAbilityEnum.TURNSOVERTWOCARDSATBEGGININGOFGAME,
                Table.IlluminatiCardEnum.NETWORK
        );
        cards.add(network);

        IlluminatiCard discordianSociety = new IlluminatiCard(
                "The Discordian Society",
                Table.CardTypeEnum.ILLUMINATI,
                8, 0, 8, 8,
                Table.SpecialAbilityEnum.PLUSFOURONANYATTEMPTTOCONTROLWEIRDGROUPSIMMUNETOATTACKSFROMGOVERNMENTORSTRAIGHTGROUPS,
                Table.IlluminatiCardEnum.DISCORDIANSOCIETY
        );
        cards.add(discordianSociety);

        IlluminatiCard ufos = new IlluminatiCard(
                "The ufos",
                Table.CardTypeEnum.ILLUMINATI,
                6, 0, 6, 8,
                chooseUFOsSpecialAbilityEnum(),
                Table.IlluminatiCardEnum.UFOS
        );
        cards.add(ufos);

        IlluminatiCard servantsOfCthulhu = new IlluminatiCard(
                "The Servants of Cthulhu",
                Table.CardTypeEnum.ILLUMINATI,
                9, 0, 9, 7,
                Table.SpecialAbilityEnum.PLUSTWOONANYATTEMPTTODESTROYANYGROUP,
                Table.IlluminatiCardEnum.SERVANTSOFCTHULHU
        );
        cards.add(servantsOfCthulhu);

        IlluminatiCard gnomesOfZurich = new IlluminatiCard(
                "The Gnomes of Zurich",
                Table.CardTypeEnum.ILLUMINATI,
                7, 0, 7, 12,
                Table.SpecialAbilityEnum.MAYMOVEMONEYFREELYATENDOFTURN,
                Table.IlluminatiCardEnum.GNOMESOFZURICH
        );
        cards.add(gnomesOfZurich);

        IlluminatiCard baravianIlluminati = new IlluminatiCard(
                "The Bavarian Illuminati",
                Table.CardTypeEnum.ILLUMINATI,
                10, 0, 10, 9,
                Table.SpecialAbilityEnum.MAYMAKEONEPRIVILEGEDATTACKEACHTURNATACOSTOFFIVEMEGABUCKS,
                Table.IlluminatiCardEnum.BAVARIANILLUMINATI
        );
        cards.add(baravianIlluminati);

        IlluminatiCard bermudaTriangle = new IlluminatiCard(
                "The Bermuda Triangle",
                Table.CardTypeEnum.ILLUMINATI,
                8, 0, 8, 9,
                Table.SpecialAbilityEnum.MAYREORGANIZEGROUPSFREELYATENDOFTURN,
                Table.IlluminatiCardEnum.BERMUTATRIANGE
        );
        cards.add(bermudaTriangle);
    }

    private Table.SpecialAbilityEnum chooseUFOsSpecialAbilityEnum() { return Table.SpecialAbilityEnum.MAYMAKEONEPRIVILEGEDATTACKEACHTURNATACOSTOFFIVEMEGABUCKS; }
}

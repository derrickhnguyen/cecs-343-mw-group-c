package com.groupc.android.illuminati.Objects;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by micha on 4/9/2017.
 */

public class Deck {
    ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        initializeCards();
        shuffle();
    }

    private void initializeCards() {
      initializeIlluminatiCards();

      initializeSpecialCards();
    }

    private void shuffle() {
      ArrayList<Card> temp = new ArrayList<Card>();
      while(!cards.isEmpty()) {
          int x = (int) (Math.random()*cards.size());
          temp.add(cards.get(x));
          cards.remove(x);
      }
      cards = temp;
    }

    public Card draw() {
        return cards.remove(0);
    }

    public void returnToDeck(Card card) {
        cards.add(card);
        shuffle();
    }

    public void initializeIlluminatiCards() {
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
                "The UFOs",
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
                "The Gonomes of Zurich",
                Table.CardTypeEnum.ILLUMINATI,
                7, 0, 7, 12,
                Table.SpecialAbilityEnum.MAYMOVEMONEYFREELYATENDOFTURN,
                Table.IlluminatiCardEnum.GNOMESOFZURICH
        );
        cards.add(ufos);

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

    private void initializeSpecialCards() {
        SpecialCard assassination = new SpecialCard(
                "Assassination",
                Table.CardTypeEnum.SPECIAL,
                "Play this card immediately after the dice are rolled on nay attempt (by andy palyer) to destroy, conrtol, or neutralize. That roll is immediately changed, retroactively to 2"
        );
        cards.add(assassination);

        SpecialCard bribery = new SpecialCard(
                "Bribery",
                Table.CardTypeEnum.SPECIAL,
                "Play this card during your turn to automatically take control of any one uncontrolled group. Playing this card counts as an action"
        );
        cards.add(bribery);

        SpecialCard computerEspionage = new SpecialCard(
                "Computer Espionage",
                Table.CardTypeEnum.SPECIAL,
                "Play this card at any time to either count the money on any one group card OR examine all of one player's special cards"
        );
        cards.add(computerEspionage);

        SpecialCard deepAgent = new SpecialCard(
                "Deep Agent",
                Table.CardTypeEnum.SPECIAL,
                "Play this card after privilege has been invoked. The privilege is totally abolished. That attack cannot be made privileged."
        );
        cards.add(deepAgent);

        SpecialCard interference1 = new SpecialCard(
                "Interference",
                Table.CardTypeEnum.SPECIAL,
                "You may interfere with one privileged attack. No other players may interfere."
        );
        cards.add(interference1);

        SpecialCard interference2 = new SpecialCard(
                "Interference",
                Table.CardTypeEnum.SPECIAL,
                "You may interfere with one privileged attack. No other players may interfere."
        );
        cards.add(interference2);

        SpecialCard marketManipulation = new SpecialCard(
                "Market Manipulation",
                Table.CardTypeEnum.SPECIAL,
                "Play this card during your income phase to double all your groups' incomes, for that turn only. This card does not allow the I.R.S. to collect twice, or require that the Post Office to pay twice."
        );
        cards.add(marketManipulation);

        SpecialCard mediaCampaign = new SpecialCard(
                "Media Campaign",
                Table.CardTypeEnum.SPECIAL,
                "Play this card at any time to revive a group from the 'dead' pile. It becomes uncontrolled. (If the Servants of Cthulhu destroyed the group, it still counts as a destroyed group for victory. If they destroy it again, it counts again!)"
        );
        cards.add(mediaCampaign);

        SpecialCard murphysLaw = new SpecialCard(
                "Murphy's Law",
                Table.CardTypeEnum.SPECIAL,
                "Play this card immediately after the dice are rolled on any attempt (by any player) to destroy, control, or neutralize. That roll is immediately changed, retroactively to 12."
        );
        cards.add(murphysLaw);

        SpecialCard secretsManWasNotMeantToKnow = new SpecialCard(
                "Secrets Man Was Not Meant To Know",
                Table.CardTypeEnum.SPECIAL,
                "Play this card when any other Special card is played, for ANY purpose. That card is immediately neutralized; it has no affect. Both cards are discarded"
        );
        cards.add(secretsManWasNotMeantToKnow);

        SpecialCard senateInvestigatingCommittee = new SpecialCard(
                "Senate Investigating Commiteee",
                Table.CardTypeEnum.SPECIAL,
                "Play this card at the beginning of any other player's turn. That player loses his turn completely."
        );
        cards.add(senateInvestigatingCommittee);

        SpecialCard slushFund = new SpecialCard(
                "Slush Fund",
                Table.CardTypeEnum.SPECIAL,
                "Exchange this card, at any time, for 15MB to be placed in your Illuminati treasury"
        );
        cards.add(slushFund);

        SpecialCard swissBankAccount = new SpecialCard(
                "Swiss Bank Account",
                Table.CardTypeEnum.SPECIAL,
                "Exchange this card, at any time, for 25MB to be placed in your Illuminati treasury"
        );
        cards.add(swissBankAccount);

        SpecialCard whisperingCampaign = new SpecialCard(
                "Whispering Campaing",
                Table.CardTypeEnum.SPECIAL,
                "You may attempt to destroy a single group with Power 0. Roll attacking power vs. defending resistance, but a successful attack destroys the target. Playing this card is not an action, but the attack itself is an action"
        );
        cards.add(whisperingCampaign);

        SpecialCard whiteCollarCrime = new SpecialCard(
                "White Collar Crime",
                Table.CardTypeEnum.SPECIAL,
                "Play this card at any time to reorganize all your money freely - that is, any amount(s) may be moved between any groups. You also get any extra 5MB which may be placed anywhere"
        );
        cards.add(whiteCollarCrime);
    }

    private Table.SpecialAbilityEnum chooseUFOsSpecialAbilityEnum() {
        return Table.SpecialAbilityEnum.MAYMAKEONEPRIVILEGEDATTACKEACHTURNATACOSTOFFIVEMEGABUCKS;
    }
}

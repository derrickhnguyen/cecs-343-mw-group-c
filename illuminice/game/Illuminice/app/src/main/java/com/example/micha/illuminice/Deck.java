package com.example.micha.illuminice;

import java.util.ArrayList;

/**
 * Created by micha on 4/9/2017.
 */

public class Deck {
    ArrayList<GroupCard> groupCards;
    ArrayList<SpecialCard> specialCards;
    public Deck() {
        groupCards = new ArrayList<GroupCard>();
        specialCards = new ArrayList<SpecialCard>();
        //get all cards' info from data base
        shuffle();
    }

    private void shuffle() {

    }

    public GroupCard drawGroupCard() {
        GroupCard groupCard = groupCards.get(0);
        groupCards.remove(groupCards.get(0));
        return groupCard;
    }

    public SpecialCard drawSpecialCard() {
        SpecialCard specialCard = specialCards.get(0);
        specialCards.remove(specialCards.get(0));
        return specialCard;
    }
}

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
      //get info from data base for each card
    }

    private void shuffle() {
      ArrayList<Card> temp = new ArrayList<Card>();
      while(!cards.isEmpty()) {
          int x = (int) (Math.random()*cards.size());
          temp.add(cards.get(x));
          cards.remove(x;
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
}

package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Card;

import java.util.ArrayList;
/**
 * Created by micha on 4/14/2017.
 */
public class DestroyedCards {
  private ArrayList<Card> destroyedCards;

  public DestroyedCards() {
    destroyedCards = new ArrayList<Card>();
  }

  public void addDestroyedCard(Card card) {
    destroyedCards.add(card);
  }

  public ArrayList<Card> getAllDestroyedCards() {
    return destroyedCards;
  }

  public Card removeDestroyedCard(Card card) {
    if(destroyedCards.contains(card)) {
      destroyedCards.remove(card);
      return card;
    }
    else return null;
  }
}

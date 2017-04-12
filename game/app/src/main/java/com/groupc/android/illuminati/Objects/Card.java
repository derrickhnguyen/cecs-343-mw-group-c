package com.groupc.android.illuminati.Objects;

import com.groupc.android.illuminati.Objects.Table.CardTypeEnum;
/**
 * Created by micha on 4/9/2017.
 */

public class Card {
  private String cardName;
  private CardTypeEnum type;

    public Card(String cardName, CardTypeEnum type) {
      this.cardName = cardName;
      this.type = type;
    }

    public String getCardName() {
      return cardName;
    }

    public void setCardName(String cardName) {
      this.cardName = cardName;
    }

    public CardTypeEnum getType() {
      return type;
    }

    public void setType(CardTypeEnum type) {
      this.type = type;
    }

}

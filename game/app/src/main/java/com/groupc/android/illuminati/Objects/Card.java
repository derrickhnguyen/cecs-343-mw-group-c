package com.groupc.android.illuminati.Objects;

import android.content.res.Resources;

import com.groupc.android.illuminati.Objects.Table.CardTypeEnum;

import java.io.Serializable;

/**
 * Created by micha on 4/9/2017.
 */

public class Card implements Serializable{
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

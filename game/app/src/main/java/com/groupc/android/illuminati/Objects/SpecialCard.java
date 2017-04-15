package com.groupc.android.illuminati.Objects;

/**
 * Created by micha on 4/9/2017.
 */
import com.groupc.android.illuminati.Objects.Table.CardTypeEnum;

public class SpecialCard extends Card {
    String description;

    public SpecialCard(String cardName, CardTypeEnum type, String description) {
        super(cardName, type);

      this.description = description;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
}

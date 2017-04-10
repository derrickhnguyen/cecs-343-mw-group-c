package com.groupc.android.illuminati.Objects;

/**
 * Created by micha on 4/9/2017.
 */

public class PowerStructure {

    private IlluminatiCard card;
    public PowerStructure(IlluminatiCard card) {
        this.card = card;
    }

    public IlluminatiCard getIlluminatiCard()
    {
        return card;
    }

    public void setIlluminatiCard(IlluminatiCard card)
    {
        this.card = card;
    }


}

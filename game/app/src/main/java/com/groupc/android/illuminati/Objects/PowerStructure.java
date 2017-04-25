package com.groupc.android.illuminati.Objects;

/**
 * Created by micha on 4/9/2017.
 */

public class PowerStructure {
    private IlluminatiCard illuminatiCard;

    public PowerStructure(IlluminatiCard illuminatiCard) {
        this.illuminatiCard = illuminatiCard;
    }

    public IlluminatiCard getIlluminatiCard()
    {
        return illuminatiCard;
    }

    public void setIlluminatiCard(IlluminatiCard illuminatiCard)
    {
        this.illuminatiCard = illuminatiCard;
    }


    public boolean hasRoom(GroupCard defendingGroup) {
        return true; //fix
    }

    public void addToPowerStructure(GroupCard defendingGroup) {
        illuminatiCard.setConnectedCards(new NonSpecialCard[]{null, defendingGroup, null, null});
        illuminatiCard.setIsConnected(new boolean[]{false, true, false, false});
    }
}

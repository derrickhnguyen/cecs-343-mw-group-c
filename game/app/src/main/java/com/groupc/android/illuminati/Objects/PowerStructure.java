package com.groupc.android.illuminati.Objects;

import java.util.ArrayList;

/**
 * Created by micha on 4/9/2017.
 */

public class PowerStructure {
    private IlluminatiCard illuminatiCard;
    private ArrayList<NonSpecialCard> powerStructureCards;

    public PowerStructure(IlluminatiCard illuminatiCard) {
        this.illuminatiCard = illuminatiCard;
        powerStructureCards = new ArrayList<NonSpecialCard>();
        powerStructureCards.add(illuminatiCard);
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
        powerStructureCards.add(defendingGroup);
    }

    public ArrayList<NonSpecialCard> getPowerStructureCards() {
        return powerStructureCards;
    }
}

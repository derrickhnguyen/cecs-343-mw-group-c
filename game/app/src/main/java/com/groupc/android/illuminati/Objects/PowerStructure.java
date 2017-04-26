package com.groupc.android.illuminati.Objects;

import android.util.Log;

import com.groupc.android.illuminati.MainScreen;

import java.security.acl.Group;
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

    public void addToPowerStructure(NonSpecialCard parentCard, GroupCard defendingGroup, int puppetArrow) {
        Log.d("parent", parentCard.getCardName());
        Log.d("defending", defendingGroup.getCardName());
        //find(parentCard, defendingGroup).attachCard(defendingGroup, 0);
        parentCard.attachCard(defendingGroup, puppetArrow);
        //Log.d("CARDSDSDSDSD", MainScreen.getTable().getCurrentPlayer().getPowerStructure().getIlluminatiCard().getConnectedCards()[1].getCardName());
        powerStructureCards.add(defendingGroup);
    }

    public NonSpecialCard find(NonSpecialCard parent, NonSpecialCard searched)
    {
        NonSpecialCard found = null;
        if(illuminatiCard.equals(parent))
        {
            return illuminatiCard;
        }
        for(int i = 0; i < 4; i++)
        {
            if(parent.getIsConnected()[i])
            {
                if(parent.getConnectedCards()[i].equals(searched))
                {
                    found = parent.getConnectedCards()[i];
                } else {
                    if(found == null)
                    {
                        found = find(parent.getConnectedCards()[i], searched);
                    }
                }
            }
        }
        return found;
    }

    public ArrayList<NonSpecialCard> getPowerStructureCards()
    {
        return powerStructureCards;
    }

    public GroupCard removeCard(GroupCard card) {
        for(int i = 0; i < powerStructureCards.size(); i++){
            for(int j = 0; j < powerStructureCards.get(i).getConnectedCards().length; j++) {
                if(powerStructureCards.get(i).getConnectedCards()[j] == card)
                    powerStructureCards.get(i).getConnectedCards()[j] = null;
                    powerStructureCards.get(i).getIsConnected()[j] = false;
            }
        }
        powerStructureCards.remove(card);
        return card;
    }
}

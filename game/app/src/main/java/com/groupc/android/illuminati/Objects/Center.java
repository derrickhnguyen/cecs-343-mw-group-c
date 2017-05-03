package com.groupc.android.illuminati.Objects;

import java.util.ArrayList;

public class Center {
    private ArrayList<GroupCard> uncontrolledGroupCards;
    private int count;

    public Center() {
        uncontrolledGroupCards = new ArrayList<GroupCard>();
        count = 0;
    }

    public void addGroupToCenter(GroupCard groupCard) {
        uncontrolledGroupCards.add(groupCard);
        count++;
    }

    public GroupCard removeGroupFromCenter(GroupCard groupCard) {
        if(uncontrolledGroupCards.contains(groupCard)) {
            uncontrolledGroupCards.remove(groupCard);
            count--;
            return groupCard;
        }
        else return null;
    }

    public ArrayList<GroupCard> getAllGroupCards() {
        return uncontrolledGroupCards;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

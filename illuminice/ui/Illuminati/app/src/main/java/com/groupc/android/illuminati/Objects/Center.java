package com.groupc.android.illuminati.Objects;

import java.util.ArrayList;

/**
 * Created by micha on 4/9/2017.
 */

public class Center {
    ArrayList<GroupCard> uncontrolledGroupCards;
    public Center() {
        uncontrolledGroupCards = new ArrayList<GroupCard>();
    }

    public void addGroupToCenter(GroupCard groupCard) {
        uncontrolledGroupCards.add(groupCard);
    }
}

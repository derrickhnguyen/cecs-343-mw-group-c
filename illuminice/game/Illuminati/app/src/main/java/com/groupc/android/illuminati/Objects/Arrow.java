package com.groupc.android.illuminati.Objects;

/**
 * Created by cman4_000 on 4/10/2017.
 */

public class Arrow {
    private GroupCard card;

    public Arrow()
    {
        card = null;
    }

    public void setArrow(GroupCard c)
    {
        card = c;
    }

    public void removeCard()
    {
        card = null;
    }

    public GroupCard getCard()
    {
        return card;
    }
}

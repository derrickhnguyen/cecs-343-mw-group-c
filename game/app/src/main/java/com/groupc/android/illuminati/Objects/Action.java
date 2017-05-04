package com.groupc.android.illuminati.Objects;

public class Action {
    private Attack  attack;

    public Action() {}

    public Attack getAttack() { return attack; }

    public void setAttack(Attack attack)
    {
        this.attack = attack;
        attack.setUpAttackAnnouncement();
    }
}

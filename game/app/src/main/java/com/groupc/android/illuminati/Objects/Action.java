package com.groupc.android.illuminati.Objects;

public class Action {
    private Attack  attack;
    private TransferMoney transferMoney;
    private GiveAwayMoney giveAwayMoney;
    private GiveAGroupAway giveAGroupAway;
    private GiveAwaySpecial giveAwaySpecial;
    private MoveAGroup moveAGroup;
    private Pass pass;
    private UseASpecial useASpecial;
    private DropAGroup dropAGroup;

    public Action() {}

    public Attack getAttack() { return attack; }
    public TransferMoney getTransferMoney() { return transferMoney; }
    public GiveAwaySpecial getGiveAwaySpecial() { return giveAwaySpecial; }
    public GiveAGroupAway getGiveAGroupAway() { return giveAGroupAway; }
    public GiveAwayMoney getGiveAwayMoney() { return giveAwayMoney; }
    public MoveAGroup getMoveAGroup() { return moveAGroup; }
    public Pass getPass() { return pass; }
    public UseASpecial getUseASpecial() { return useASpecial; }
    public DropAGroup getDropAGroup() { return dropAGroup; }

    public void setAttack(Attack attack)
    {
        this.attack = attack;
        attack.setUpAttackAnnouncement();
    }

    public void setTransferMoney(TransferMoney transferMoney) { this.transferMoney = transferMoney; }
    public void setGiveAwaySpecial(GiveAwaySpecial giveAwaySpecial) { this.giveAwaySpecial = giveAwaySpecial; }
    public void setGiveAGroupAway(GiveAGroupAway giveAGroupAway) { this.giveAGroupAway = giveAGroupAway; }
    public void setGiveAwayMoney(GiveAwayMoney giveAwayMoney) { this.giveAwayMoney = giveAwayMoney; }
    public void setMoveAGroup(MoveAGroup moveAGroup) { this.moveAGroup = moveAGroup; }
    public void setPass(Pass pass) { this.pass = pass; }
    public void setUseASpecial(UseASpecial useASpecial) { this.useASpecial = useASpecial; }
    public void setDropAGroup(DropAGroup dropAGroup) { this.dropAGroup = dropAGroup; }
}

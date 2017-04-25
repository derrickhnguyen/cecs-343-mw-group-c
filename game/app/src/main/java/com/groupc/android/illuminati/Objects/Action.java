package com.groupc.android.illuminati.Objects;

public class Action {
    private Player  player;
    private Table   table;
    private int     choice;
    private int     actionsTaken;
    private Attack  attack;

    public Action(Player player, Table table) {
        this.player = player;
        this.table = table;
    }

    public int getActionsTaken() { return actionsTaken; }

     /*public void takeAction() {
      switch(choice) {
        case 1: attack = new Attack(player, table);

                //find a way to reset chinese campaign donors alignment (rule enforcement)
                //find a way to reset defending groups resistance if defender owns survivalists (rule enforcement)
                actionsTaken++;
                break;
        case 2: TransferMoney transferMoney = new TransferMoney();
                actionsTaken++;
                break;
        case 3: MoveAGroup moveAGroup = new MoveAGroup();
                actionsTaken++;
                break;
        case 4: GiveAGroupAway giveAGroupAway = new GiveAGroupAway();
                actionsTaken++;
                break;
        case 5: DropAGroup dropAGroup = new DropAGroup();
                break;
        case 6: GiveAwayMoney giveAwayMoney = new GiveAwayMoney();
                break;
        case 7: GiveAwaySpecial giveAwaySpecial = new GiveAwaySpecial();
                break;
        case 8: UseASpecial useASpecial = new UseASpecial();
                break;
        case 9: SpecialPowerAction specialPowerAction = new SpecialPowerAction();
                break;
        case 10: Pass pass = new Pass();
                break;
        default:
      }
    }
    */
    private Table.AttackEnum chooseAttackType() {
        Table.AttackEnum attackType = null;
        return attackType;
    }

    public void setChoice(int choice) { this.choice = choice; }

    public Attack getAttack() { return attack; }

    public void setAttack(Attack attack)
    {
        this.attack = attack;
        attack.setUpAttackAnnouncement();
    }
}

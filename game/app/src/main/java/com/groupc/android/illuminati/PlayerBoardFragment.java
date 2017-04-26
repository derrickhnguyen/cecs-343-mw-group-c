package com.groupc.android.illuminati;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.groupc.android.illuminati.Objects.Action;
import com.groupc.android.illuminati.Objects.Attack;
import com.groupc.android.illuminati.Objects.AttackAnnouncement;
import com.groupc.android.illuminati.Objects.Card;
import com.groupc.android.illuminati.Objects.GroupCard;
import com.groupc.android.illuminati.Objects.IlluminatiCard;
import com.groupc.android.illuminati.Objects.NonSpecialCard;
import com.groupc.android.illuminati.Objects.Player;
import com.groupc.android.illuminati.Objects.Table;

import org.xmlpull.v1.XmlPullParser;

import static android.R.attr.name;

public class PlayerBoardFragment extends Fragment {

    View view_a; //a view
    IlluminatiCard illuminatiCard; //card in which the board is built from
    RelativeLayout ll; //android layout
    Bundle bundle;
    String type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //illuminatiCard = (IlluminatiCard) getArguments().getSerializable("card");
        //for when we get the input from an intent
        Intent playerIntent = getActivity().getIntent();
        bundle = getArguments();
        Player player = (Player) bundle.getSerializable("player");
        type = bundle.getString("type");
        if(type.equals("transfer_money")) transferMoney();
        illuminatiCard = player.getIlluminatiCard();
//        NonSpecialCard aCard = new NonSpecialCard("name", Table.CardTypeEnum.GROUP,
//                5,
//                5,
//                5,
//                5,
//                Table.SpecialAbilityEnum.ILLUMINATIGROUPMAYPARTICIPATEINTWOATTACKSPERTURN); //created a random card
//
//        illuminatiCard = new IlluminatiCard("card",
//                Table.CardTypeEnum.ILLUMINATI,
//                4,
//                4,
//                4,
//                4,
//                Table.SpecialAbilityEnum.MAYMAKEONEPRIVILEGEDATTACKEACHTURNATACOSTOFFIVEMEGABUCKS,
//                Table.IlluminatiCardEnum.BAVARIANILLUMINATI); //created a random illuminati card
//
//        //manually connect the card to the right of the illuminati card
//        illuminatiCard.setConnectedCards(new NonSpecialCard[]{null, aCard, null, null});
//        illuminatiCard.setIsConnected(new boolean[]{false, true, false, false});
//        aCard.setConnectedCards(new NonSpecialCard[]{null, illuminatiCard, null, null});

        //set up the layouts to fit their parents
        ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        ScrollView sc = new ScrollView(getActivity()); //let the activity scroll vertically

        HorizontalScrollView sv = new HorizontalScrollView(getActivity()); //let the activity scroll horizontally

        ll = new RelativeLayout(getActivity()); //instantiate main layout

        sc.setLayoutParams(p); //make fill parent
        sv.setLayoutParams(p); //make fill parent
        ll.setLayoutParams(p); //make fill parent

        //put cards onto the screen
//        ImageView card1 = new ImageView(getActivity());
//        card1.setImageResource(R.drawable.card);
//        card1.setId(View.generateViewId());
//
        ImageView illCard = new ImageView(getActivity()); //create image for illuminati card
        //illCard.setImageResource(R.drawable.card); //set it to the feminist pic (only one we have so far)
        illCard.setImageResource(getResources().getIdentifier(illuminatiCard.getCardName().toLowerCase(), "drawable", getActivity().getPackageName()));

        illCard.setId(View.generateViewId()); //for the layout to work, each imageview needs an ID,
        //this generates a random ID for the view and set it to it
        //the actual ID doesn't matter since we'll always use the getID() method

//        layout for center card
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        rp.addRule(RelativeLayout.CENTER_VERTICAL); //put card in center (buggy)

        illCard.setLayoutParams(rp); //picture for illuminati card
        ll.addView(illCard); //add card to relative layout
        attach(illuminatiCard, illCard); //run sequence to make board
//
//        card1.setLayoutParams(rp);
//
//        //layout for card being attached
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        lp.addRule(RelativeLayout.RIGHT_OF, card1.getId()); //put card right of the first card
//
//        card2.setLayoutParams(lp); //set the layout params to the card

        //add cards to view


        //finish creating other views
        sv.addView(ll); //add layout to the scroll
        sc.addView(sv); //add scrolls together
        view_a = sc; //view to make view

        return view_a; //send main view out
    }

    private void attach(final NonSpecialCard c, ImageView cardImage)
    {
        boolean[] connected = c.getIsConnected(); //get boolean for cards connected to card
        NonSpecialCard[] connectedCards = c.getConnectedCards(); //get cards connected to card
        //set image to card's image, will implement later
        cardImage.setImageResource(getResources().getIdentifier(c.getCardName().toLowerCase().replace(" ", ""), "drawable", getActivity().getPackageName()));
        Log.d("cardName", c.getCardName().toLowerCase().replace(" ", ""));
        cardImage.setId(View.generateViewId()); //probably don't need to do this
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getArguments().getBoolean("isSuccessful"))
                {
                    //MainScreen.getTable().getCurrentPlayer().getPowerStructure().addToPowerStructure((GroupCard) bundle.getSerializable("attackedCard"));
                    //return;
                } else {
                    int attackedCardID = bundle.getInt("attackedCardID");
                    GroupCard attackedCard = (GroupCard) bundle.getSerializable("attackedCard");
                    if (attackedCard != null) {
                        bundle.putSerializable("attackingCard", c);
                        //do attack stuff
                        Log.d("ATTACKING CARD NAME", c.getCardName());
                        Log.d("ATTACKED CARD NAME", attackedCard.getCardName());
                        Log.d("ATTACK TYPE", type);
                        Table.AttackEnum attackType = null;
                        if (type.equals("neutralize")) {
                            attackType = Table.AttackEnum.NEUTRALIZE;
                        } else if (type.equals("destroy")) {
                            attackType = Table.AttackEnum.DESTROY;
                        } else if (type.equals("control")) {
                            attackType = Table.AttackEnum.CONTROL;
                        } else {
                            //error
                        }
                        Table table = MainScreen.getTable();
                        Attack attack = new Attack(table.getCurrentPlayer(), c, null, attackedCard, false, attackType);

                        table.getAction().setAttack(attack);

                        getAttackResult();

                        if (attack.isSuccessful()) {
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            //ListFragment playerListFrag = new ListFragment();
                            Fragment playerBoardFragment = new PlayerBoardFragment();
                            Player currentPlayer = table.getCurrentPlayer();
                            playerBoardFragment.setArguments(bundle);
                            bundle.putBoolean("isSuccessful", true);
                            ft.replace(R.id.contentframe, playerBoardFragment);
                            ft.commit();
                        }
                    } else if (type != null) {
                        Log.d("ATTACK TYPE", type);
                        switch (type) {
                            case "control":
                                break;
                            case "neutralize":
                                break;
                            case "destroy":
                                break;
                        }
                    } else {
                        //idk
                    }
                }
            }
        });

        for(int i = 0; i < 4; i++) { //checks each side of card
            if(connected[i]) { //if connected card on that side
                int attachedArrow = 0; //arrow which it's attached on
                NonSpecialCard attachedCard = connectedCards[i]; //get the connected card
                NonSpecialCard[] attachedCards = attachedCard.getConnectedCards(); //get the cards the the cards connects to
                for(int j = 0; j < 4; j++) //check all sides
                {
                    if(attachedCards[j] != null && attachedCards[j].equals(c)) //if the new card's connected card equals main card
                    {
                        attachedArrow = j; //then that's the arrow that it's attached on
                    }
                }
                attachedCard.setOrientation(attachedArrow); //set orientation based on arrow (might need adjustments)


                ImageView newCardImage = new ImageView(getActivity()); //new image for card
                newCardImage.setRotation(attachedCard.getOrientation()); //roate to match the orientation

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT); //stock layout

                //depending on orientation and arrow position, add the card on a certain side
                if((i + c.getOrientation()) % 4 == 0){
                    lp.addRule(RelativeLayout.ABOVE, cardImage.getId());
                    //newCardImage.setRotation(attachedCard.getOrientation()*90);
                } else if((i + c.getOrientation()) % 4 == 1){
                    lp.addRule(RelativeLayout.RIGHT_OF, cardImage.getId());
                    //newCardImage.setRotation(attachedCard.getOrientation()*90 + 90);
                } else if((i + c.getOrientation()) % 4 == 2){
                    lp.addRule(RelativeLayout.BELOW, cardImage.getId());
                    //newCardImage.setRotation(attachedCard.getOrientation()*90 + 180);
                } else if((i + c.getOrientation()) % 4 == 3){
                    lp.addRule(RelativeLayout.LEFT_OF, cardImage.getId());
                    //newCardImage.setRotation(attachedCard.getOrientation()*90 + 270);
                } else {
                    Log.d("ERROR", "ORIENTATION WRONG");
                }

                //add the layout params
                newCardImage.setLayoutParams(lp);
                //add card to view
                Log.d("SHOW THE REST OF", "TRUE");
                ll.addView(newCardImage);
                //recursively call the check
                attach(attachedCard, newCardImage);
            }
        }
    }

    private void getAttackResult() {
        AttackAnnouncement announcement = MainScreen.table.getAction().getAttack().getAttackAnnouncement();
        String attackName = MainScreen.table.getAction().getAttack().getAttackName();
        String defendName = MainScreen.table.getAction().getAttack().getDefendName();
        int power = MainScreen.table.getAction().getAttack().getAttackPower();
        int resistance = MainScreen.table.getAction().getAttack().getDefendingResistance();
        int alignmentBonus = announcement.getAlignmentBonus();
        int powerStructurePositionBonus = announcement.getPowerStructurePositionBonus();
        int specialPowerBonus = announcement.getSpecialPowerBonus();
        //int attackerMoneySpent = announcement.getAttackMoneySpentBonus();
        //int defenderMoneySpent = announcement.getDefenderMoneySpentBonus();
        int scoreNeeded = MainScreen.getTable().getAction().getAttack().getAttackAnnouncement().getScore();
        int roll = MainScreen.getTable().getAction().getAttack().getDiceSum();
        String won;
        if(MainScreen.table.getAction().getAttack().isSuccessful()) won = attackName + " won!";
        else won = attackName + " lost!";

        CharSequence text =
                attackName + "'s Power - " + power +
                        "\n" + defendName + "'s Resistance - " + resistance +
                        "\nAlignment Bonus - " + alignmentBonus +
                        "\nPower Structure Position Bonus - " + powerStructurePositionBonus +
                        "\nSpecial Power Bonus - " + specialPowerBonus +
                        "\nScore Needed - " + scoreNeeded +
                        "\nScore Rolled - " + roll +
                        "\n" + won;

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void transferMoney() {

    }
}
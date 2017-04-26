package com.groupc.android.illuminati;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.security.acl.Group;
import java.util.ArrayList;

import static android.R.attr.name;

public class PlayerBoardFragment extends Fragment {

    View view_a; //a view
    IlluminatiCard illuminatiCard; //card in which the board is built from
    RelativeLayout ll; //android layout
    Bundle bundle;
    String type;
    ArrayList<ImageView> views;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //illuminatiCard = (IlluminatiCard) getArguments().getSerializable("card");
        //for when we get the input from an intent
        Intent playerIntent = getActivity().getIntent();
        bundle = getArguments();
        Player player = (Player) bundle.getSerializable("player");
        type = bundle.getString("type");
        illuminatiCard = player.getIlluminatiCard();
        views = new ArrayList<ImageView>();

        //set up the layouts to fit their parents
        ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        ScrollView sc = new ScrollView(getActivity()); //let the activity scroll vertically

        HorizontalScrollView sv = new HorizontalScrollView(getActivity()); //let the activity scroll horizontally

        ll = new RelativeLayout(getActivity()); //instantiate main layout

        sc.setLayoutParams(p); //make fill parent
        sv.setLayoutParams(p); //make fill parent
        ll.setLayoutParams(p); //make fill parent

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
       // rp.addRule(RelativeLayout.CENTER_VERTICAL); //put card in center (buggy)
       // rp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        illCard.setLayoutParams(rp); //picture for illuminati card
        views.add(illCard);
        attach(illuminatiCard, illCard); //run sequence to make board
        //ll.addView(illCard); //add card to relative layout
        for(ImageView iv : views)
        {
            ll.addView(iv);
        }


        //finish creating other views
        sv.addView(ll); //add layout to the scroll
        sc.addView(sv); //add scrolls together
        view_a = sc; //view to make view

        if(type != null) {
            if(type.equals("transfer_money")){
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Choose giving group";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        return view_a; //send main view out
    }

    NonSpecialCard givingCard, receivingCard;
    private void attach(final NonSpecialCard c, ImageView cardImage)
    {
        boolean[] connected = c.getIsConnected(); //get boolean for cards connected to card
        NonSpecialCard[] connectedCards = c.getConnectedCards(); //get cards connected to card
        //set image to card's image, will implement later
        cardImage.setImageResource(getResources().getIdentifier(c.getCardName().toLowerCase().replace(" ", ""), "drawable", getActivity().getPackageName()));
        Log.d("cardName", c.getCardName().toLowerCase().replace(" ", ""));
        cardImage.setId(View.generateViewId()); //probably don't need to do this
//        cardImage.setScaleX(0.4f);
//        cardImage.setScaleY(0.4f);
//        cardImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type == null)
                {
                    //do nothing
                } else if(type.equals("transfer_money")) {
                    bundle.putSerializable("givingCard", c);
                    givingCard = c;
                } else if(type.equals("transfer_money2")) {
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Choose receiving group";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    bundle.putSerializable("receivingCard", c);
                    receivingCard = c;

                    final int[] mb = new int[1];

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("How many MB's?");

                    final EditText input = new EditText(getActivity());

                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mb[0] = Integer.parseInt(input.getText().toString());
                        }
                    });

                    builder.show();

                    givingCard.giveMoney(receivingCard, mb[0]);

                    context = getActivity().getApplicationContext();
                    text = givingCard.getCardName() + " gave " + receivingCard.getCardName() + " " + mb[0] + " MB";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();


                } else if(type.equals("attack")) {
                    if(bundle.getSerializable("puppetCard") == null)
                    {
                        bundle.putSerializable("puppetCard", c);

                        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                        b.setTitle("Choose which arrow to attach to");
                        String[] types = {"Top", "Right", "Bottom", "Left"};
                        b.setItems(types, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                switch(which){
                                    case 0:
                                        bundle.putInt("connectingArrow", 0);
                                        break;
                                    case 1:
                                        bundle.putInt("connectingArrow", 1);
                                        break;
                                    case 2:
                                        bundle.putInt("connectingArrow", 2);
                                        break;
                                    case 3:
                                        bundle.putInt("connectingArrow", 3);
                                        break;
                                    default:
                                        //something
                                        break;
                                }
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                //ListFragment playerListFrag = new ListFragment();
                                Fragment playerBoardFragment = new PlayerBoardFragment();
                                playerBoardFragment.setArguments(bundle);
                                ft.replace(R.id.contentframe, playerBoardFragment);
                                ft.commit();
                            }
                        });

                        b.show();
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
                            String attackTypeString = bundle.getString("attackType");
                            if (attackTypeString.equals("neutralize")) {
                                attackType = Table.AttackEnum.NEUTRALIZE;
                            } else if (attackTypeString.equals("destroy")) {
                                attackType = Table.AttackEnum.DESTROY;
                            } else if (attackTypeString.equals("control")) {
                                attackType = Table.AttackEnum.CONTROL;
                            } else {
                                //error
                            }
                            NonSpecialCard puppetCard = (NonSpecialCard) bundle.getSerializable("puppetCard");
                            int puppetArrow = bundle.getInt("connectingArrow");
                            Table table = MainScreen.getTable();
                            Attack attack = new Attack(table.getCurrentPlayer(), c, null, attackedCard, puppetCard, puppetArrow, false, attackType);

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
                newCardImage.setId(View.generateViewId());
                newCardImage.setRotation(attachedCard.getOrientation()); //roate to match the orientation

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT); //stock layout
                RelativeLayout.LayoutParams op = (RelativeLayout.LayoutParams) cardImage.getLayoutParams();
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
                    //op.addRule(RelativeLayout.RIGHT_OF, newCardImage.getId());
                    //newCardImage.setRotation(attachedCard.getOrientation()*90 + 270);
                } else {
                    Log.d("ERROR", "ORIENTATION WRONG");
                }

                //add the layout params
                //views.remove(cardImage);
                //cardImage.setLayoutParams(op);
                //views.add(cardImage);
                newCardImage.setLayoutParams(lp);

                //add card to view
                Log.d("SHOW THE REST OF", "TRUE");
                views.add(newCardImage);
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
}
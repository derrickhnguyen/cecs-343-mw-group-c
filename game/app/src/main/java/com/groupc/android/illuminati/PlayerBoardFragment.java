package com.groupc.android.illuminati;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.groupc.android.illuminati.Objects.Attack;
import com.groupc.android.illuminati.Objects.AttackAnnouncement;
import com.groupc.android.illuminati.Objects.GroupCard;
import com.groupc.android.illuminati.Objects.IlluminatiCard;
import com.groupc.android.illuminati.Objects.NonSpecialCard;
import com.groupc.android.illuminati.Objects.Player;
import com.groupc.android.illuminati.Objects.PowerStructure;
import com.groupc.android.illuminati.Objects.Table;

import java.util.ArrayList;

import static com.groupc.android.illuminati.MainScreen.table;

public class PlayerBoardFragment extends Fragment {
    FragmentManager fm;
    View view_a; //a view
    IlluminatiCard illuminatiCard; //card in which the board is built from
    RelativeLayout ll; //android layout
    Bundle bundle;
    String type;
    String type2;
    ArrayList<ImageView> views;
    int xPad = 0, yPad = 0;
    int cardWidth = 800, cardHeight = 400;
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

        //ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(1000,1000);

        ScrollView sc = new ScrollView(getActivity()); //let the activity scroll vertically

        HorizontalScrollView sv = new HorizontalScrollView(getActivity()); //let the activity scroll horizontally

        ll = new RelativeLayout(getActivity()); //instantiate main layout

        sc.setLayoutParams(p); //make fill parent
        sv.setLayoutParams(p); //make fill parent
        ll.setLayoutParams(p); //make fill parent

        ImageView backGround = new ImageView(getActivity());
        backGround.setId(View.generateViewId());
        ImageView illCard = new ImageView(getActivity()); //create image for illuminati card
        //illCard.setImageResource(R.drawable.card); //set it to the feminist pic (only one we have so far)
        illCard.setImageResource(getResources().getIdentifier(illuminatiCard.getCardName().toLowerCase(), "drawable", getActivity().getPackageName()));
        illCard.setId(View.generateViewId()); //for the layout to work, each imageview needs an ID,
        //this generates a random ID for the view and set it to it
        //the actual ID doesn't matter since we'll always use the getID() method

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

//        layout for center card
//        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(cardWidth,cardHeight);
        rp.addRule(RelativeLayout.ALIGN_RIGHT, backGround.getId());
        rp.addRule(RelativeLayout.ALIGN_BOTTOM, backGround.getId());

        // rp.addRule(RelativeLayout.CENTER_VERTICAL); //put card in center (buggy)
       // rp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        illCard.setLayoutParams(rp); //picture for illuminati card
        views.add(backGround);
        views.add(illCard);
        //ll.addView(illCard);
        //ll.addView(illCard);
        attach(illuminatiCard, illCard, new Point(0,0)); //run sequence to make board
        RelativeLayout.LayoutParams bg = new RelativeLayout.LayoutParams((xPad+2)/2*cardWidth, (yPad+2)/2*cardHeight);
        bg.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        bg.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        backGround.setLayoutParams(bg);
        for(ImageView iv : views)
        {
            ll.addView(iv);
        }
        //ll.addView(backGround);
        //ll.addView(illCard); //add card to relative layout



        //finish creating other views
        sv.addView(ll); //add layout to the scroll
        sc.addView(sv); //add scrolls together
        view_a = sc; //view to make view

        if(type != null) {
            if(type.equals("transfer_money")){
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Choose receiving group";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else if(type.equals("drop_group")) {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Choose group to drop";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else if(type.equals("move_group")) {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Choose group to move";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        return view_a; //send main view out
    }

    NonSpecialCard givingCard, receivingCard;
    private void attach(final NonSpecialCard c, ImageView cardImage, Point loc)
    {
        boolean[] connected = c.getIsConnected(); //get boolean for cards connected to card
        final NonSpecialCard[] connectedCards = c.getConnectedCards(); //get cards connected to card
        //set image to card's image, will implement later
        cardImage.setImageResource(getResources().getIdentifier(c.getCardName().toLowerCase().replace(" ", ""), "drawable", getActivity().getPackageName()));

        Log.d("cardName", c.getCardName().toLowerCase().replace(" ", ""));
        //cardImage.setId(View.generateViewId()); //probably don't need to do this
//        cardImage.setScaleX(0.4f);
//        cardImage.setScaleY(0.4f);
//        cardImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        final String[]connectedNames = new String[4];

        for(int i = 0; i < 4; i++)
        {
            if(connectedCards[i]!=null)
            {
                connectedNames[i] = connectedCards[i].getCardName();
            } else {
                connectedNames[i] = "empty";
            }
        }
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Top: " + connectedNames[0] + "\nRight: " + connectedNames[1] + "\nBottom: " + connectedNames[2] + "\nLeft: " + connectedNames[3], Toast.LENGTH_SHORT).show();
                if(type == null)
                {
                    //do nothing
                } else if(type.equals("transfer_money")) {
                    bundle.putSerializable("receivingCard", c);
                    receivingCard = c;
                    table.setGiver(givingCard);
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
                                        table.getCurrentPlayer().getIlluminatiCard().giveMoney(receivingCard, mb[0]);

                                        Context context = getActivity();
                                        CharSequence text = table.getCurrentPlayer().getIlluminatiCard().getCardName() + " gave " + receivingCard.getCardName() + " " + mb[0] + " MB";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                });

                                builder.show();

                } else if(type.equals("drop_group")) {
                    bundle.putSerializable("droppedCard", c);
                    if(c.getType() == Table.CardTypeEnum.ILLUMINATI) {
                        Context context = getActivity();
                        CharSequence text = "Can't drop illuminati group!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        table.getCenter().addGroupToCenter(
                                table.getCurrentPlayer().getPowerStructure().removeCard((GroupCard) c)
                        );
                        Context context = getActivity();
                        CharSequence text = c.getCardName() + " dropped!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("player", MainScreen.table.getCurrentPlayer());
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        //ListFragment playerListFrag = new ListFragment();
                        Fragment playerBoardFragment = new PlayerBoardFragment();
                        playerBoardFragment.setArguments(bundle);
                        ft.replace(R.id.contentframe, playerBoardFragment);
                        ft.commit();
                    }

                } else if(type.equals("move_group")){
                    if(bundle.getSerializable("movedCard") == null) {
                        bundle.putSerializable("movedCard", c);
                        Context context = getActivity();
                        CharSequence text = "Choose where you want to move it to";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        PlayerBoardFragment playerBoardFragment = new PlayerBoardFragment();
                        playerBoardFragment.setArguments(bundle);
                        ft.replace(R.id.contentframe, playerBoardFragment);
                        ft.commit();

                    } else {
                        bundle.putSerializable("whereToMove", c);
                        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                        b.setTitle("Choose which arrow to attach to");
                        int[] outArrows = c.getOutArrows();
                        boolean[] isConnected = c.getIsConnected();
                        ArrayList<Integer> openArrows = new ArrayList<Integer>();
                        for(int i = 0; i < outArrows.length; i++)
                        {
                            if(!isConnected[outArrows[i]])
                            {
                                openArrows.add(outArrows[i]);
                            }
                        }
                        final String[] types = new String[openArrows.size()];
                        for(int i = 0; i < types.length; i++)
                        {
                            if(openArrows.get(i) == 0)
                            {
                                types[i] = "Top";
                            } else if(openArrows.get(i) == 1)
                            {
                                types[i] = "Right";
                            } else if(openArrows.get(i) == 2)
                            {
                                types[i] = "Bottom";
                            } else if(openArrows.get(i) == 3)
                            {
                                types[i] = "Left";
                            } else {
                                //problem
                            }
                        }
                        b.setItems(types, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                switch (which) {
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

                                GroupCard movedCard = null;
                                try {
                                    movedCard = (GroupCard) bundle.getSerializable("movedCard");
                                } catch(Exception e)
                                {
                                    //it's the illuminati card
                                }

                                if(movedCard != null && !c.equals(movedCard)) {
                                    PowerStructure powerStructure = MainScreen.table.getCurrentPlayer().getPowerStructure();
                                    powerStructure.removeCard(movedCard);
                                    powerStructure.addToPowerStructure(c, movedCard, bundle.getInt("connectingArrow"));
                                }
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("player", MainScreen.table.getCurrentPlayer());
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


                    }



                } else if(type.equals("attack")) {
                    if(bundle.getSerializable("puppetCard") == null)
                    {
                        bundle.putSerializable("puppetCard", c);

                        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                        b.setTitle("Choose which arrow to attach to");
                        int[] outArrows = c.getOutArrows();
                        boolean[] isConnected = c.getIsConnected();
                        ArrayList<Integer> openArrows = new ArrayList<Integer>();
                        for(int i = 0; i < outArrows.length; i++)
                        {
                            if(!isConnected[outArrows[i]])
                            {
                                openArrows.add(outArrows[i]);
                            }
                        }
                        final String[] types = new String[openArrows.size()];
                        for(int i = 0; i < types.length; i++)
                        {
                            if(openArrows.get(i) == 0)
                            {
                                types[i] = "Top";
                            } else if(openArrows.get(i) == 1)
                            {
                                types[i] = "Right";
                            } else if(openArrows.get(i) == 2)
                            {
                                types[i] = "Bottom";
                            } else if(openArrows.get(i) == 3)
                            {
                                types[i] = "Left";
                            } else {
                                //problem
                            }
                        }
                        b.setItems(types, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                String choice = types[which];
                                switch(choice){
                                    case "Top":
                                        bundle.putInt("connectingArrow", 0);
                                        break;
                                    case "Right":
                                        bundle.putInt("connectingArrow", 1);
                                        break;
                                    case "Bottom":
                                        bundle.putInt("connectingArrow", 2);
                                        break;
                                    case "Left":
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

//                            if (attack.isSuccessful()) {
//                                FragmentManager fm = getFragmentManager();
//                                FragmentTransaction ft = fm.beginTransaction();
//                                //ListFragment playerListFrag = new ListFragment();
//                                Fragment playerBoardFragment = new PlayerBoardFragment();
//                                Player currentPlayer = table.getCurrentPlayer();
//                                playerBoardFragment.setArguments(bundle);
//                                ft.replace(R.id.contentframe, playerBoardFragment);
//                                ft.commit();
//                            } else {
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            //ListFragment playerListFrag = new ListFragment();
                            Fragment playerBoardFragment = new PlayerBoardFragment();
                            Player currentPlayer = table.getCurrentPlayer();
                            Bundle newBundle = new Bundle();
                            newBundle.putSerializable("player", currentPlayer);
                            playerBoardFragment.setArguments(newBundle);
                            ft.replace(R.id.contentframe, playerBoardFragment);
                            ft.commit();
                        //}
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
                newCardImage.setImageResource(getResources().getIdentifier(attachedCard.getCardName().toLowerCase().replace(" ", ""), "drawable", getActivity().getPackageName()));

                //newCardImage.setRotation(attachedCard.getOrientation()); //roate to match the orientation

//                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//                        RelativeLayout.LayoutParams.WRAP_CONTENT,
//                        RelativeLayout.LayoutParams.WRAP_CONTENT); //stock layout
                RelativeLayout.LayoutParams lp = null;

                //RelativeLayout.LayoutParams op = (RelativeLayout.LayoutParams) cardImage.getLayoutParams();
                //depending on orientation and arrow position, add the card on a certain side
                Point newLoc = new Point(loc.x, loc.y);
                if((i + c.getOrientation()) % 4 == 0){
                    lp = new RelativeLayout.LayoutParams(cardWidth,cardHeight);
                    newLoc.y = newLoc.y + 2;
                    lp.addRule(RelativeLayout.ABOVE, cardImage.getId());
                    lp.addRule(RelativeLayout.ALIGN_LEFT, cardImage.getId());
                    //newCardImage.setRotation(attachedCard.getOrientation()*90);
                } else if((i + c.getOrientation()) % 4 == 1){
                    lp = new RelativeLayout.LayoutParams(cardWidth, cardHeight);
                    newLoc.x = newLoc.x - 2;
                    //newLoc.y = newLoc.y + 1;
//                    Matrix matrix = new Matrix();
//                    newCardImage.setScaleType(ImageView.ScaleType.MATRIX);   //required
//                    matrix.postRotate((float) 90, newCardImage.getDrawable().getBounds().height()/2, newCardImage.getDrawable().getBounds().height()/2);
//                    newCardImage.setImageMatrix(matrix);
                    //newCardImage.setAdjustViewBounds(true);
                    lp.addRule(RelativeLayout.RIGHT_OF, cardImage.getId());
                    //lp.addRule(RelativeLayout.ALIGN_BOTTOM, cardImage.getId());
                    lp.addRule(RelativeLayout.ALIGN_TOP, cardImage.getId());
                    //newCardImage.setAdjustViewBounds(true);
                    //newCardImage.setRotationX(newCardImage.getWidth()/2);
                    //newCardImage.setRotationY(newCardImage.getHeight()/2);

//                    newCardImage.setRotation(90);
//                    newCardImage.setScaleType(ImageView.ScaleType);

                    //newCardImage.setScaleX(2f);
                    //newCardImage.setScaleY(2f);
//



                    //newCardImage.setRotation(attachedCard.getOrientation()*90 + 90);
                    //lp = new RelativeLayout.LayoutParams(cardHeight, cardWidth);
                    //lp.addRule(RelativeLayout.CENTER_IN_PARENT, cardImage.getId());

                } else if((i + c.getOrientation()) % 4 == 2){
                    lp = new RelativeLayout.LayoutParams(cardWidth,cardHeight);
                    newLoc.y = newLoc.y - 2;
                    lp.addRule(RelativeLayout.BELOW, cardImage.getId());
                    lp.addRule(RelativeLayout.ALIGN_LEFT, cardImage.getId());
                    //lp.addRule(RelativeLayout.ALIGN_START, cardImage.getId());
                    //newCardImage.setRotation(attachedCard.getOrientation()*90 + 180);
                } else if((i + c.getOrientation()) % 4 == 3){
                    lp = new RelativeLayout.LayoutParams(cardWidth,cardHeight);
                    newLoc.x = newLoc.x + 2;
                    lp.addRule(RelativeLayout.LEFT_OF, cardImage.getId());
                    lp.addRule(RelativeLayout.ALIGN_BOTTOM, cardImage.getId());
                    //newCardImage.setRotation(attachedCard.getOrientation()*90 + 270);
                } else {
                    Log.d("ERROR", "ORIENTATION WRONG");
                }

                //add the layout params
                //views.remove(cardImage);
                //cardImage.setLayoutParams(op);
                //views.add(cardImage);
                newCardImage.setLayoutParams(lp);
                //cardImage.setLayoutParams(op);

                //add card to view
                Log.d("SHOW THE REST OF", "TRUE");
                views.add(newCardImage);
                //ll.addView(newCardImage);
                //recursively call the check
                if(newLoc.y > (yPad))
                {
                    yPad = newLoc.y;
                }
                if(newLoc.x > (xPad))
                {
                    xPad = newLoc.x;
                }
                Log.d("POINT " + attachedCard.getCardName(), newLoc.x + "," + newLoc.y);
                attach(attachedCard, newCardImage, newLoc);
            }
        }
    }

    private void getAttackResult() {
        AttackAnnouncement announcement = table.getAction().getAttack().getAttackAnnouncement();
        String attackName = table.getAction().getAttack().getAttackName();
        String defendName = table.getAction().getAttack().getDefendName();
        int power = table.getAction().getAttack().getAttackPower();
        int resistance = table.getAction().getAttack().getDefendingResistance();
        int alignmentBonus = announcement.getAlignmentBonus();
        int powerStructurePositionBonus = announcement.getPowerStructurePositionBonus();
        int specialPowerBonus = announcement.getSpecialPowerBonus();
        //int attackerMoneySpent = announcement.getAttackMoneySpentBonus();
        //int defenderMoneySpent = announcement.getDefenderMoneySpentBonus();
        int scoreNeeded = MainScreen.getTable().getAction().getAttack().getAttackAnnouncement().getScore();
        int roll = MainScreen.getTable().getAction().getAttack().getDiceSum();
        String won;
        if(table.getAction().getAttack().isSuccessful()) won = attackName + " won!";
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
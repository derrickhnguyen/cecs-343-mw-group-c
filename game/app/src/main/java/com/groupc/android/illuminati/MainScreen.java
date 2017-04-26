package com.groupc.android.illuminati;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.groupc.android.illuminati.Objects.AttackAnnouncement;
import com.groupc.android.illuminati.Objects.Card;
import com.groupc.android.illuminati.Objects.GroupCard;
import com.groupc.android.illuminati.Objects.IlluminatiCard;
import com.groupc.android.illuminati.Objects.NonSpecialCard;
import com.groupc.android.illuminati.Objects.Player;
import com.groupc.android.illuminati.Objects.PowerStructure;
import com.groupc.android.illuminati.Objects.SpecialCard;
import com.groupc.android.illuminati.Objects.Table;

import java.util.ArrayList;

import static com.groupc.android.illuminati.R.id.mb;

public class MainScreen extends AppCompatActivity {

    FragmentManager fm;
    Player[] players;
    private String numberOfPlayers;
    static Table table;
    boolean pass;
    public static int cheatInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        fm = getFragmentManager();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("How many players?");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                numberOfPlayers = input.getText().toString();
                beginGame();
            }
        });

        builder.show();


        Button home = (Button) findViewById(R.id.homebutton);
        Button otherPlayers = (Button) findViewById(R.id.otherplayersbutton);
        Button playerProfile = (Button) findViewById(R.id.profilebutton);
        Button displaySkillCards = (Button) findViewById(R.id.skillbutton);
        Button centerPile = (Button) findViewById(R.id.centerpilebutton);
        final Button takeAction = (Button) findViewById(R.id.take_action);
        Button mb = (Button) findViewById(R.id.mb);

        otherPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle names = new Bundle();
                names.putSerializable("names", players);

                int[] IDs;
                IDs = new int[table.getNumberOfPlayers()];
                String name;
                for (int i = 0; i < IDs.length; i++) {
                    name = table.getPlayers().get(i).getIlluminatiCard().getCardName();
                    name = name.replaceAll("\\s+", "");
                    Log.d("name", name);
                    IDs[i] = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
                    Log.d("id",IDs[i] +"");
                }
                names.putIntArray("cardNames", IDs);

                FragmentTransaction ft = fm.beginTransaction();
                ListFragment playerListFrag = new PlayerListFragment();
                playerListFrag.setArguments(names);
                ft.replace(R.id.contentframe, playerListFrag);
                ft.commit();
            }
        });

        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle names = new Bundle();
                ArrayList<NonSpecialCard> powerStructureCards = table.getCurrentPlayer().getPowerStructure().getPowerStructureCards();
                NonSpecialCard[] cards = new NonSpecialCard[powerStructureCards.size()];
                for(int i = 0; i < cards.length; i++) {
                    cards[i] = powerStructureCards.get(i); //.getCardName() + " MB - " + powerStructureCards.get(i).getGroupTreasury();
                }
                names.putSerializable("names", cards);

                int[] IDs;
                IDs = new int[cards.length];
                String name;
                for (int i = 0; i < IDs.length; i++) {
                    name = table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getCardName();
                    name = name.replaceAll("\\s+", "");
                    Log.d("name", name);
                    IDs[i] = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
                    Log.d("id",IDs[i] +"");
                }
                names.putIntArray("cardNames", IDs);

                FragmentTransaction ft = fm.beginTransaction();
                ListFragment mbListFrag = new MBListFragment();
                mbListFrag.setArguments(names);
                ft.replace(R.id.contentframe, mbListFrag);
                ft.commit();
            }
        });

        mb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(cheatInt != 1000) {
                    cheatInt = 1000;
                    Toast.makeText(getBaseContext(), "Cheat mode enabled! :D", Toast.LENGTH_SHORT).show();
                } else {
                    cheatInt = 0;
                    Toast.makeText(getBaseContext(), "Cheat mode disabled! :'(", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        playerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle player = new Bundle();
                player.putString("name", "My Player Name");

                FragmentTransaction ft = fm.beginTransaction();
                Fragment playerProfileFragment = new PlayerProfileFragment();
                playerProfileFragment.setArguments(player);
                ft.replace(R.id.contentframe, playerProfileFragment);
                ft.commit();
            }
        });

        displaySkillCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle cardIDs = new Bundle();

                if(table.getCurrentPlayer().getHand().size() == 0) {
                    table.newTurn();
                    Context context = getApplicationContext();
                    CharSequence text = "No Specials Yet";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                if(table.getCurrentPlayer().getHand().size() > 0) {
                    int[] IDs;
                    IDs = new int[table.getCurrentPlayer().getHand().size()];
                    String name;
                    for (int i = 0; i < IDs.length; i++) {
                        name = table.getCurrentPlayer().getHand().get(i).getCardName();
                        name = name.replaceAll("\\s+","");
                        IDs[i] = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
                    }
                    cardIDs.putIntArray("cardNames", IDs);

                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment CardListFragment = new CardListFragment();
                    CardListFragment.setArguments(cardIDs);
                    ft.replace(R.id.contentframe, CardListFragment);
                    ft.commit();
                }
            }
        });

        centerPile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle centerCardIDs = new Bundle();
                int[] IDs = new int[table.getCenter().getCount()];
                String name;

                for(int i = 0; i < IDs.length; i++) {
                    name = table.getCenter().getAllGroupCards().get(i).getCardName();
                    name = name.replaceAll("\\s+","");
                    IDs[i] = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
                    Log.d("GRP ", table.getCenter().getAllGroupCards().get(i).getCardName());
                }
                centerCardIDs.putIntArray("cardNames", IDs);

                FragmentTransaction ft = fm.beginTransaction();
                Fragment CardListFragment = new CardListFragment();
                CardListFragment.setArguments(centerCardIDs);
                ft.replace(R.id.contentframe, CardListFragment);
                ft.commit();
            }
        });

        takeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(MainScreen.this, takeAction);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainScreen.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();

                        if(! table.actionInitialized()) table.newAction();

                        ListFragment playerListFrag = null;
                        final Fragment[] playerBoardFragment = {null};
                        final Bundle[] bundle = {null};
                        final FragmentTransaction[] ft = {null};

                        switch(item.getItemId()) {
                            case R.id.attack_to_control:
                                table.getCurrentPlayer().takeAction();
                                bundle[0] = new Bundle();
                                bundle[0].putSerializable("names", players);

                                int[] IDs;
                                IDs = new int[table.getNumberOfPlayers()];
                                String name;
                                for (int j = 0; j < IDs.length; j++) {
                                    name = table.getPlayers().get(j).getIlluminatiCard().getCardName();
                                    name = name.replaceAll("\\s+", "");
                                    Log.d("name", name);

                                    IDs[j] = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
                                    Log.d("id",IDs[j] +"");
                                }

                                bundle[0].putIntArray("cardNames", IDs);

                                playerListFrag = new PlayerListFragment();
                                ft[0] = fm.beginTransaction();
                                bundle[0].putString("type", "attack");
                                bundle[0].putString("attackType", "control");
                                playerListFrag.setArguments(bundle[0]);
                                ft[0].replace(R.id.contentframe, playerListFrag);
                                ft[0].commit();
                                if(table.getCurrentPlayer().actionsTaken() > 2) endTurn();
                                break;
                            case R.id.attack_to_neutralize:
                                table.getCurrentPlayer().takeAction();
                                table.getAction().getAttack().setAttackType(Table.AttackEnum.NEUTRALIZE);
                                bundle[0] = new Bundle();
                                bundle[0].putSerializable("names", players);

                                IDs = new int[table.getNumberOfPlayers()];
                                for (int j = 0; j < IDs.length; j++) {
                                    name = table.getPlayers().get(j).getIlluminatiCard().getCardName();
                                    name = name.replaceAll("\\s+", "");
                                    Log.d("name", name);
                                    IDs[j] = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
                                    Log.d("id",IDs[j] +"");
                                }
                                bundle[0].putIntArray("cardNames", IDs);

                                playerListFrag = new PlayerListFragment();
                                ft[0] = fm.beginTransaction();
                                bundle[0].putString("type", "attack");
                                bundle[0].putString("attackType", "neutralize");
                                playerListFrag.setArguments(bundle[0]);
                                ft[0].replace(R.id.contentframe, playerListFrag);
                                ft[0].commit();
                                if(table.getCurrentPlayer().actionsTaken() > 2) endTurn();
                                break;
                            case R.id.attack_to_destroy:
                                table.getCurrentPlayer().takeAction();
                                table.getAction().getAttack().setAttackType(Table.AttackEnum.DESTROY);
                                bundle[0] = new Bundle();
                                bundle[0].putSerializable("names", players);

                                IDs = new int[table.getNumberOfPlayers()];
                                for (int j = 0; j < IDs.length; j++) {
                                    name = table.getPlayers().get(j).getIlluminatiCard().getCardName();
                                    name = name.replaceAll("\\s+", "");
                                    Log.d("name", name);
                                    IDs[j] = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
                                    Log.d("id",IDs[j] +"");
                                }
                                bundle[0].putIntArray("cardNames", IDs);
                                playerListFrag = new PlayerListFragment();
                                ft[0] = fm.beginTransaction();
                                bundle[0].putString("type", "attack");
                                bundle[0].putString("attackType", "destroy");
                                playerListFrag.setArguments(bundle[0]);
                                ft[0].replace(R.id.contentframe, playerListFrag);
                                ft[0].commit();
                                if(table.getCurrentPlayer().actionsTaken() > 2) endTurn();
                                break;
                            case R.id.transfer_money:
                                table.getCurrentPlayer().takeAction();
                                String type = "transfer_money";
                                fm = getFragmentManager();
                                ft[0] = fm.beginTransaction();
                                playerBoardFragment[0] = new PlayerBoardFragment();
                                final Player[] currentPlayer = {table.getCurrentPlayer()};
                                bundle[0] = new Bundle();
                                bundle[0].putSerializable("player", currentPlayer[0]);
                                bundle[0].putString("type", type);
                                playerBoardFragment[0].setArguments(bundle[0]);
                                ft[0].replace(R.id.contentframe, playerBoardFragment[0]);
                                ft[0].commit();

                                if(table.getCurrentPlayer().actionsTaken() > 2) endTurn();
                                break;
                            case R.id.move_a_group:
                                table.getCurrentPlayer().takeAction();
                                if(table.getCurrentPlayer().actionsTaken() > 2) endTurn();
                                break;
                            case R.id.give_a_group_away:
                                table.getCurrentPlayer().takeAction();
                                if(table.getCurrentPlayer().actionsTaken() > 2) endTurn();
                                break;
                            case R.id.drop_a_group:
                                fm = getFragmentManager();
                                ft[0] = fm.beginTransaction();
                                playerBoardFragment[0] = new PlayerBoardFragment();
                                bundle[0] = new Bundle();
                                bundle[0].putSerializable("player", table.getCurrentPlayer());
                                bundle[0].putString("type", "drop_group");
                                playerBoardFragment[0].setArguments(bundle[0]);
                                ft[0].replace(R.id.contentframe, playerBoardFragment[0]);
                                ft[0].commit();
                                break;
                            case R.id.give_away_money:
                                bundle[0] = new Bundle();
                                bundle[0].putSerializable("names", players);

                                IDs = new int[table.getNumberOfPlayers()];
                                for (int j = 0; j < IDs.length; j++) {
                                    name = table.getPlayers().get(j).getIlluminatiCard().getCardName();
                                    name = name.replaceAll("\\s+", "");
                                    Log.d("name", name);

                                    IDs[j] = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
                                    Log.d("id",IDs[j] +"");
                                }

                                bundle[0].putIntArray("cardNames", IDs);

                                playerListFrag = new PlayerListFragment();
                                ft[0] = fm.beginTransaction();
                                bundle[0].putString("type", "give_money");
                                playerListFrag.setArguments(bundle[0]);
                                ft[0].replace(R.id.contentframe, playerListFrag);
                                ft[0].commit();
                                break;
                            case R.id.give_away_special:
                                break;
                            case R.id.use_a_special:
                                break;
                            case R.id.special_power_action:
                                break;
                            case R.id.pass:
                                pass = true;
                                endTurn();
                                break;
                            default:
                                return onOptionsItemSelected(item);
                        }

                        return true;
                    }
                });

                popupMenu.show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fm.beginTransaction();
                //ListFragment playerListFrag = new ListFragment();
                Fragment playerBoardFragment = new PlayerBoardFragment();
                Player currentPlayer = table.getCurrentPlayer();
                Bundle bundle = new Bundle();
                bundle.putSerializable("player", currentPlayer);
                playerBoardFragment.setArguments(bundle);
                ft.replace(R.id.contentframe, playerBoardFragment);
                ft.commit();
            }
        });
    }

    String tag = "SYSTEM";
    public void onStart()
    {
        super.onStart();
        Log.d(tag, "In the onStart() event");
    }
    public void onRestart()
    {
        super.onRestart();
        Log.d(tag, "In the onRestart() event");
    }
    public void onResume()
    {
        super.onResume();
        Log.d(tag, "In the onResume() event");
    }
    public void onPause()
    {
        super.onPause();
        Log.d(tag, "In the onPause() event");
    }
    public void onStop()
    {
        super.onStop();
        Log.d(tag, "In the onStop() event");
    }
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(tag, "In the onDestroy() event");
    }
    private void endTurn() {
        table.newTurn();

        CharSequence text;

        if(pass) {
            text = table.getPlayers().get(table.getPlayers().size() - 1).getUsername()  + " passed and collected 2MB" +
                    "\nPass to " + table.getCurrentPlayer().getUsername() + "!";
            table.getPlayers().get(table.getPlayers().size() - 1).getIlluminatiCard().setGroupTreasury(
                    table.getPlayers().get(table.getPlayers().size() - 1).getIlluminatiCard().getGroupTreasury() + 2
            );
            pass = false;
        }

        else text = "Too many actions!\nPass to " + table.getCurrentPlayer().getUsername() + "!";

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        for(int i = 0; i < table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().size(); i++) {
            String name = table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getCardName();
            int income = table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getIncome();
            context = getApplicationContext();
            text = name + " collecting an income of " + income;
            duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, text, duration);
            toast.show();

            table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).setGroupTreasury(
                    table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getGroupTreasury()
                            + table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getIncome()
            );
        }

        drawCard();
    }

    private void beginGame() {
        table = new Table();
        if(numberOfPlayers != null) table.setNumberOfPlayers(numberOfPlayers);

        for(int i = 0; i < table.getNumberOfPlayers(); i++) {
            table.addPlayer();
        }

        players = new Player[table.getNumberOfPlayers()];

        for(int i = 0; i < table.getNumberOfPlayers(); i++) {
            players[i] = table.getPlayers().get(i);
        }

        table.addCardsToCenter();

        ArrayList<Integer> diceRolls = table.seeWhoGoesFirst();

        for(int i = diceRolls.size() - 1; i >= 0 ; i--) {
            Context context = getApplicationContext();
            CharSequence text = "Player " + (i + 1) + " rolled " + diceRolls.get(i);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        Context context = getApplicationContext();
        CharSequence text = table.getCurrentPlayer().getUsername() + " goes first!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        for(int i = 0; i < table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().size(); i++) {
            String name = table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getCardName();
            int income = table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getIncome();
            context = getApplicationContext();
            text = name + " collecting an income of " + income;
            duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, text, duration);
            toast.show();

            table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).setGroupTreasury(
                    table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getGroupTreasury()
                    + table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getIncome()
            );

            drawCard();
        }
    }

    public static Table getTable()
    {
        return table;
    }

    private void drawCard() {
        Card card;
        card = table.getDeck().draw();
        Context context = getApplicationContext();
        CharSequence text = table.getCurrentPlayer().getUsername() + " drew " + card.getCardName();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        if(card.getType() == Table.CardTypeEnum.GROUP) {
            text = card.getCardName() + " added to center";
            table.getCenter().addGroupToCenter((GroupCard) card);
        }
        else {
            text = card.getCardName() + " added to specials";
            table.getCurrentPlayer().addCardToHand((SpecialCard) card);
        }

        toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

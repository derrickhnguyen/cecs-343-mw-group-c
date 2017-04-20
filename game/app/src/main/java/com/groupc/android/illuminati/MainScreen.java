package com.groupc.android.illuminati;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.groupc.android.illuminati.Objects.IlluminatiCard;
import com.groupc.android.illuminati.Objects.Player;
import com.groupc.android.illuminati.Objects.Table;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    FragmentManager fm;
    Player[] players;
    private String numberOfPlayers;
    Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        fm = getFragmentManager();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("How many players?");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
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

                if(table.getCurrentPlayer().getHand() != null) {
                    int[] IDs;
                    IDs = new int[table.getCurrentPlayer().getHand().size()];
                    String name;
                    for (int i = 0; i < IDs.length; i++) {
                        name = table.getCurrentPlayer().getHand().get(i).getCardName();
                        name = name.replaceAll("\\s}", "");
                        IDs[i] = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageCodePath());
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
                    Log.d("id",IDs[i] +"");
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
                ft.replace(R.id.contentframe, playerBoardFragment);
                ft.commit();
            }
        });
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

        table.seeWhoGoesFirst();
    }
}

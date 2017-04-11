package com.groupc.android.illuminati;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.groupc.android.illuminati.Objects.IlluminatiCard;
import com.groupc.android.illuminati.Objects.Player;
import com.groupc.android.illuminati.Objects.Table;

public class MainScreen extends AppCompatActivity {

    FragmentManager fm;
    Player[] players = {new Player("Christian", new IlluminatiCard(Table.IlluminatiCardEnum.BAVARIANILLUMINATI)),
            new Player("Mike", new IlluminatiCard(Table.IlluminatiCardEnum.DISCORDIANSOCIETY)),
            new Player("Derrick", new IlluminatiCard(Table.IlluminatiCardEnum.SOCIETYOFASSASSINS))};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        fm = getFragmentManager();

        Button home = (Button) findViewById(R.id.homebutton);
        Button otherPlayers = (Button) findViewById(R.id.otherplayersbutton);
        Button playerProfile = (Button) findViewById(R.id.profilebutton);
        Button displaySkillCards = (Button) findViewById(R.id.skillbutton);

        otherPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle names = new Bundle();
                names.putSerializable("names", players);

                FragmentTransaction ft = fm.beginTransaction();
                //ListFragment playerListFrag = new ListFragment();
                ListFragment playerListFrag = new PlayerListFragment();
                playerListFrag.setArguments(names);
                ft.replace(R.id.contentframe, playerListFrag);
                ft.commit();
//                Fragment playerListFrag = new PlayerListFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//                transaction.replace(R.id.contentfragment, playerListFrag);
//                transaction.addToBackStack(null);
//
//                transaction.commit();
            }
        });

        playerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle player = new Bundle();
                player.putString("name", "My Player Name");

                FragmentTransaction ft = fm.beginTransaction();
                //ListFragment playerListFrag = new ListFragment();
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
                int[]IDs = new int[]{R.drawable.card, R.drawable.card, R.drawable.card};
                cardIDs.putIntArray("cardNames", IDs);

                FragmentTransaction ft = fm.beginTransaction();
                //ListFragment playerListFrag = new ListFragment();
                Fragment CardListFragment = new CardListFragment();
                CardListFragment.setArguments(cardIDs);
                ft.replace(R.id.contentframe, CardListFragment);
                ft.commit();
            }
        });


    }
}

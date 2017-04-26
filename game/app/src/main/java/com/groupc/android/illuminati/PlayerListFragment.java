package com.groupc.android.illuminati;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.groupc.android.illuminati.Objects.GroupCard;
import com.groupc.android.illuminati.Objects.Player;
import com.groupc.android.illuminati.Objects.Table;

public class PlayerListFragment extends ListFragment {

    Player[] players;
    int[] cardNames;
    FragmentManager fm;
    String type;

//    int[] brandsImages = new int[] { R.drawable.ic_chrome,
//            R.drawable.ic_eclipse, R.drawable.ic_google_plus,
//            R.drawable.ic_skype, R.drawable.ic_twitter, R.drawable.ic_ubuntu };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        type = getArguments().getString("type");

        List<HashMap<String, ?>> aList = new ArrayList<HashMap<String, ?>>();

        cardNames = getArguments().getIntArray("cardNames");

        players = new Player[MainScreen.table.getPlayers().size()];
        for(int i = 0; i < players.length; i++) {
            players[i] = MainScreen.table.getPlayers().get(i);
        }

        for (int i = 0; i < players.length; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("player_name", players[i].getUsername());

            map.put("brand_images", cardNames[i] + "");
            aList.add(map);
        }
        if(type == null)
        {
            //do nothing
        } else if(type.equals("attack"))
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("player_name", "Center Pile");
            map.put("brand_images", getResources().getIdentifier("illuminati_logo", "drawable", getContext().getPackageName()) + "");
            aList.add(map);
        }
        // Keys used in Hashmap
       String[] from = { "player_name", "brand_images"};

        // Ids of views in listview_layout
        int[] to = {R.id.playerlisttext, R.id.icon};
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                .getBaseContext(), aList, R.layout.list_single_player, from, to);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id)
    {
        if(type == null)
        {
            //do nothing
        } else if(type.equals("attack")){
            String attackType = getArguments().getString("attackType");
            if(attackType.equals("control")) {
                Log.d("position", pos + "");

                if (pos == players.length) {
                    Bundle centerCardIDs = getArguments();
                    Table table = MainScreen.getTable();
                    int[] IDs = new int[table.getCenter().getCount()];
                    String name;
                    ArrayList<GroupCard> cards = table.getCenter().getAllGroupCards();
                    //GroupCard[] cardArray = (GroupCard[]) cards.toArray();
                    //centerCardIDs.putSerializable("cardOjbects", cardArray);
                    centerCardIDs.putSerializable("cardObjects", cards);
                    Log.d("CARDS", cards.get(0).getCardName());
                    for (int i = 0; i < IDs.length; i++) {
                        name = table.getCenter().getAllGroupCards().get(i).getCardName();
                        name = name.replaceAll("\\s+", "");
                        IDs[i] = getResources().getIdentifier(name.toLowerCase(), "drawable", getContext().getPackageName());
                        Log.d("id", IDs[i] + "");
                    }
                    centerCardIDs.putIntArray("cardNames", IDs);
                    centerCardIDs.putString("type", type);
                    fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment CardListFragment = new CardListFragment();
                    CardListFragment.setArguments(centerCardIDs);
                    ft.replace(R.id.contentframe, CardListFragment);
                    ft.commit();
                } else {
                    fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    //ListFragment playerListFrag = new ListFragment();
                    Fragment playerBoardFragment = new PlayerBoardFragment();
                    Player currentPlayer = players[pos];
                    Bundle bundle = getArguments();
                    bundle.putSerializable("player", currentPlayer);
                    bundle.putString("type", type);
                    playerBoardFragment.setArguments(bundle);
                    ft.replace(R.id.contentframe, playerBoardFragment);
                    ft.commit();
                }
            }
        }
        super.onListItemClick(l, v, pos, id);
    }
}
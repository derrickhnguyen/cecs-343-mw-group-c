package com.groupc.android.illuminati;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.groupc.android.illuminati.Objects.Player;

public class PlayerListFragment extends ListFragment {

    Player[] players;
    int[] cardNames;

//    int[] brandsImages = new int[] { R.drawable.ic_chrome,
//            R.drawable.ic_eclipse, R.drawable.ic_google_plus,
//            R.drawable.ic_skype, R.drawable.ic_twitter, R.drawable.ic_ubuntu };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<HashMap<String, ?>> aList = new ArrayList<HashMap<String, ?>>();

        cardNames = getArguments().getIntArray("cardNames");

        players = (Player[]) getArguments().getSerializable("names");

        for (int i = 0; i < players.length; i++) {







            HashMap<String, String> map = new HashMap<String, String>();
            map.put("player_name", players[i].getUsername());

            map.put("brand_images", cardNames[i] + "");
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
}
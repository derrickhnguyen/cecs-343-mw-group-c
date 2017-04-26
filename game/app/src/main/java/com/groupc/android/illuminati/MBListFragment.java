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
import com.groupc.android.illuminati.Objects.NonSpecialCard;
import com.groupc.android.illuminati.Objects.Player;
import com.groupc.android.illuminati.Objects.Table;

import android.app.Activity;

/**
 * Created by micha on 4/25/2017.
 */
public class MBListFragment extends ListFragment {

    NonSpecialCard[] cards;
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

        cards = new NonSpecialCard[MainScreen.table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().size()];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = MainScreen.table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i);
        }

        for (int i = 0; i < cards.length; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("player_name", cards[i].getCardName() + " Income - " + MainScreen.table.getCurrentPlayer().getPowerStructure().getPowerStructureCards().get(i).getGroupTreasury());

            map.put("brand_images", cardNames[i] + "");
            aList.add(map);
        }

        if (type != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("player_name", "Center Pile");
            map.put("brand_images", getResources().getIdentifier("illuminati_logo", "drawable", getContext().getPackageName()) + "");
            aList.add(map);
        }
        // Keys used in Hashmap
        String[] from = {"player_name", "brand_images"};

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
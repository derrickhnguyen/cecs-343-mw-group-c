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

public class CardListFragment extends ListFragment {

    int [] cardNames;

//    int[] brandsImages = new int[] { R.drawable.ic_chrome,
//            R.drawable.ic_eclipse, R.drawable.ic_google_plus,
//            R.drawable.ic_skype, R.drawable.ic_twitter, R.drawable.ic_ubuntu };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<HashMap<String, Integer>> aList = new ArrayList<HashMap<String, Integer>>();

        cardNames = getArguments().getIntArray("cardNames");


        for (int i = 0; i < cardNames.length; i++) {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("cardNames", cardNames[i]);
            aList.add(map);
        }

        // Keys used in Hashmap
        String[] from = { "cardNames"};

        // Ids of views in listview_layout
        int[] to = {R.id.cardflag};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                .getBaseContext(), aList, R.layout.list_single_card, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
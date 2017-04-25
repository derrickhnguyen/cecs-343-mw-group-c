package com.groupc.android.illuminati;

import java.security.acl.Group;
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

import com.groupc.android.illuminati.Objects.GroupCard;
import com.groupc.android.illuminati.Objects.Player;

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


    @Override
    public void onListItemClick(ListView l, View v, int pos, long id)
    {
        Bundle bundle = getArguments();
        String type = bundle.getString("type");

        if(type != null)
        {
            ArrayList<GroupCard> cardArray = (ArrayList<GroupCard>) bundle.getSerializable("cardObjects");
            Log.d("CARD AGAGIN", cardArray.get(0).getCardName());
            bundle.putSerializable("attackedCard", cardArray.get(pos));
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            //ListFragment playerListFrag = new ListFragment();
            Fragment playerBoardFragment = new PlayerBoardFragment();
            Player currentPlayer = MainScreen.getTable().getCurrentPlayer();
            bundle.putSerializable("player", currentPlayer);
            playerBoardFragment.setArguments(bundle);
            ft.replace(R.id.contentframe, playerBoardFragment);
            ft.commit();
        }
        super.onListItemClick(l, v, pos, id);
    }
}
package com.myapplicationdev.android.pd4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FavFriend extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Card> cardal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_friend);

        lv = findViewById(R.id.listview);
        cardal = new ArrayList<Card>();
        ArrayList<Card> favFriendsal = new ArrayList<Card>();

        aa = new CustomListAdapter(this, R.layout.listview_layout, cardal);
        DBHelper db = new DBHelper(FavFriend.this);
        ArrayList<Card> data = db.getAllFriendDetails();

        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getFav().equalsIgnoreCase("Yes")) {
                favFriendsal.add(data.get(i));
            }
        }
        aa = new CustomListAdapter(this, R.layout.listview_layout, favFriendsal);
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();

    }
}

package com.myapplicationdev.android.pd4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class friends extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Card> cardal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        lv = findViewById(R.id.listview);
        cardal = new ArrayList<Card>();
        ArrayList<Card> nonFavFriendsal = new ArrayList<Card>();


        aa = new CustomListAdapter(this, R.layout.listview_layout, cardal);
        DBHelper db = new DBHelper(friends.this);
        ArrayList<Card> data = db.getAllFriendDetails();

        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getFav().equalsIgnoreCase("No")) {
                nonFavFriendsal.add(data.get(i));
            }
        }

        aa = new CustomListAdapter(this, R.layout.listview_layout, nonFavFriendsal);
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();

    }
}

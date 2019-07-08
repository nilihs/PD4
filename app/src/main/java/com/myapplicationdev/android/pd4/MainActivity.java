package com.myapplicationdev.android.pd4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    CardView cvFav, cvFriends, cvEvents, cvAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        cvFav = findViewById(R.id.CardViewFavFriends);
        cvFriends = findViewById(R.id.CardViewFriends);
        cvEvents = findViewById(R.id.CardViewEvent);
        cvAdd = findViewById(R.id.CardViewAdd);

        cvFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavFriend.class);
                startActivity(intent);
            }
        });

        cvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFriend.class);
                startActivity(intent);
            }
        });

        cvFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, friends.class);
                startActivity(intent);
            }
        });

        cvEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, showDates.class);
                startActivity(intent);
            }
        });

    }
}

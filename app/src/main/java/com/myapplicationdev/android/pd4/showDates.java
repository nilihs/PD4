package com.myapplicationdev.android.pd4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class showDates extends AppCompatActivity {

    ListView lvDates;
    ArrayAdapter aaDates;
    ArrayList<Date> dateal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dates);

        lvDates = findViewById(R.id.listviewDates);
        dateal = new ArrayList<Date>();

        aaDates = new CustomDateListAdapter(this, R.layout.listviewdates_layout, dateal);

        DBHelperDate db = new DBHelperDate(showDates.this);
        ArrayList<Date> data = db.getAllDateDetail();

        aaDates = new CustomDateListAdapter(this, R.layout.listview_layout, data);
        lvDates.setAdapter(aaDates);
        aaDates.notifyDataSetChanged();

    }
}

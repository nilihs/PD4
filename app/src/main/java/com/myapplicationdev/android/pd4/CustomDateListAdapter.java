package com.myapplicationdev.android.pd4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomDateListAdapter extends ArrayAdapter<Date>{

    private ArrayList<Date> alDateDetails;
    private Context context;
    private TextView tvDateDetails;
    private ImageView ivDatePic;
    private CardView cd;

    public CustomDateListAdapter(Context context, int resource, ArrayList<Date> objects){
        super(context, resource, objects);
        alDateDetails = objects;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listviewdates_layout, parent, false);

        tvDateDetails = rowView.findViewById(R.id.textViewDate);
        ivDatePic = rowView.findViewById(R.id.imageViewDateDetails);
        cd = rowView.findViewById(R.id.CardViewDateDetail);


        final Date currentDate = alDateDetails.get(position);

        tvDateDetails.setText(currentDate.getDateName() + "\n" + currentDate.getDateTitle()
                + "\n" + currentDate.getDate());

        ivDatePic.setImageResource(R.drawable.hangingout);

//        cd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, EditFriendDetail.class);
//
//                intent.putExtra("id", currentFriend.getId());
//
//                Friend target = new Friend(currentFriend.getId(), currentFriend.getName(), currentFriend.getBirthday(), currentFriend.getHoroscope(), currentFriend.getPhone(), currentFriend.getAddress(), currentFriend.getFav());
//                intent.putExtra("data", target);
//
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//
//
//            }
//        });

        return rowView;
    }

}

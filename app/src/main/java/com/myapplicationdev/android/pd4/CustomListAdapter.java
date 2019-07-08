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

public class CustomListAdapter extends ArrayAdapter<Card>{

    private ArrayList<Card> alDetails;
    private Context context;
    private TextView tvDetails;
    private ImageView ivProfile;
    private CardView cd;

    public CustomListAdapter(Context context, int resource, ArrayList<Card> objects){
        super(context, resource, objects);
        alDetails = objects;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listview_layout, parent, false);

        tvDetails = rowView.findViewById(R.id.textViewDetail);
        ivProfile = rowView.findViewById(R.id.imageViewDetail);
        cd = rowView.findViewById(R.id.CardViewDetail);


        final Card currentFriend = alDetails.get(position);

        tvDetails.setText(currentFriend.getName() + "\n" + currentFriend.getPhone()
        + "\n" + currentFriend.getAddress());

        ivProfile.setImageResource(R.drawable.profile);

        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditFriendDetail.class);

                intent.putExtra("id", currentFriend.getId());

                Friend target = new Friend(currentFriend.getId(), currentFriend.getName(), currentFriend.getBirthday(), currentFriend.getHoroscope(), currentFriend.getPhone(), currentFriend.getAddress(), currentFriend.getFav());
                intent.putExtra("data", target);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

        return rowView;
    }

}

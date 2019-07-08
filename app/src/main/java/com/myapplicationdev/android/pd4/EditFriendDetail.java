package com.myapplicationdev.android.pd4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class EditFriendDetail extends AppCompatActivity {


    TextView tvUpdateSelectDate;
    EditText etUpdateName, etUpdatePhone, etUpdateAddress;
    Spinner spinnerUpdateHoroscope;
    Button btnUpdate, btnDelete, btnDate;
    RadioGroup rg;
    RadioButton rb1, rb2, rb;
    Friend theData;

    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friend_detail);

        tvUpdateSelectDate = findViewById(R.id.textViewUpdateSelectDate);
        etUpdateName = findViewById(R.id.editTextUpdateName);
        etUpdatePhone = findViewById(R.id.editTextUpdatePhone);
        etUpdateAddress = findViewById(R.id.editTextUpdateAddress);
        spinnerUpdateHoroscope = findViewById(R.id.spinnerHoroscopeUpdate);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnDate = findViewById(R.id.buttonDate);
        rg = findViewById(R.id.radioGroupUpdate);


        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);

        Intent i = getIntent();
        theData = (Friend) i.getSerializableExtra("data");


        DBHelper db = new DBHelper(EditFriendDetail.this);
        ArrayList<Friend> data = db.getFriendDetail();

        etUpdateName.setText(theData.getName());
        etUpdateAddress.setText(theData.getAddress());
        etUpdatePhone.setText(Integer.toString(theData.getPhone()));
        tvUpdateSelectDate.setText(theData.getBirthday());
        spinnerUpdateHoroscope.setSelection(theData.getHoroscope());


        rb1 = findViewById(R.id.radioButtonUpdate);
        rb2 = findViewById(R.id.radioButtonUpdate2);

        String favUpdate = theData.getFav();

        if(favUpdate.equalsIgnoreCase("Yes")) {
            rb1.setChecked(true);
            rb2.setChecked(false);
        }
        else {
            rb1.setChecked(false);
            rb2.setChecked(true);
        }


        tvUpdateSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditFriendDetail.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                tvUpdateSelectDate.setText(date);

            }
        };



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(EditFriendDetail.this);

                int selectedButton = rg.getCheckedRadioButtonId();
                rb = findViewById(selectedButton);


                theData.setName(etUpdateName.getText().toString());
                theData.setAddress(etUpdateAddress.getText().toString());
                theData.setPhone(Integer.parseInt(etUpdatePhone.getText().toString()));
                theData.setHoroscope(spinnerUpdateHoroscope.getSelectedItemPosition());
                theData.setBirthday(tvUpdateSelectDate.getText().toString());
                theData.setFav(rb.getText().toString().trim());


                dbh.updateFriend(theData);
                dbh.close();

                finish();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(EditFriendDetail.this);
                dbh.deleteFriend(theData.getId());
                dbh.close();

                finish();

            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditFriendDetail.this, SetDate.class);
                intent.putExtra("name", etUpdateName.getText().toString());

                startActivity(intent);

            }
        });

    }


}


package com.myapplicationdev.android.pd4;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddFriend extends AppCompatActivity {

    TextView tvSelectDate;
    EditText etName, etPhone, etAddress;
    Spinner spinnerHoroscope;
    Button btnAdd;
    RadioGroup rg;
    RadioButton rb;

    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);


        tvSelectDate = findViewById(R.id.textViewSelectDate);
        etName = findViewById(R.id.editTextName);
        etPhone = findViewById(R.id.editTextPhone);
        etAddress = findViewById(R.id.editTextAddress);
        spinnerHoroscope = findViewById(R.id.spinnerHoroscope);
        btnAdd = findViewById(R.id.buttonAdd);

        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddFriend.this,
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
                tvSelectDate.setText(date);

            }
        };


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString().trim();
                String birthday = tvSelectDate.getText().toString().trim();
                int horoscope = spinnerHoroscope.getSelectedItemPosition();
                int phone = Integer.parseInt(etPhone.getText().toString().trim());
                String address = etAddress.getText().toString().trim();

                rg = findViewById(R.id.radioGroup);
                int selectedButton = rg.getCheckedRadioButtonId();
                rb = findViewById(selectedButton);
                String fav = rb.getText().toString().trim();

                DBHelper db = new DBHelper(AddFriend.this);
                long row_affected = db.insertFriend(name, birthday, horoscope, phone, address, fav);
                db.close();

                if (row_affected != -1) {
                    Toast.makeText(AddFriend.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }


                etName.setText("");
                tvSelectDate.setText("Click to select date");
                spinnerHoroscope.setSelection(0);
                etPhone.setText("");
                etAddress.setText("");
                rb.setChecked(false);

            }

        });


    }
}

package com.myapplicationdev.android.pd4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SetDate extends AppCompatActivity {

    TextView tvDateName, tvDate;
    EditText etDateTitle;
    Button btnDate;

    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);

        tvDateName = findViewById(R.id.textViewDateName);
        tvDate = findViewById(R.id.textViewDate);
        etDateTitle = findViewById(R.id.editTextDateTitle);
        btnDate = findViewById(R.id.buttonDate);

        Intent intent = getIntent();
        String dateName = intent.getStringExtra("name");
        tvDateName.setText(dateName);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SetDate.this,
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
                tvDate.setText(date);

            }
        };

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dateName = tvDateName.getText().toString();
                String dateTitle = etDateTitle.getText().toString();
                String date = tvDate.getText().toString();

                DBHelperDate db = new DBHelperDate(SetDate.this);
                long row_affected = db.insertDate(dateName, dateTitle, date);
                db.close();

                if (row_affected != -1) {
                    Toast.makeText(SetDate.this, "You have set a date" + " for " + dateTitle + " with " + dateName + " on " + date,
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}

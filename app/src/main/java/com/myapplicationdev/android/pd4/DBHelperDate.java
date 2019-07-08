package com.myapplicationdev.android.pd4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelperDate extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dates.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_DATES = "date";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATENAME = "datename";
    private static final String COLUMN_DATETITLE = "datetitle";
    private static final String COLUMN_DATE = "date";

    public DBHelperDate(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDateTableSql = "CREATE TABLE " + TABLE_DATES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATENAME + " TEXT ," + COLUMN_DATETITLE + " TEXT, " + COLUMN_DATE
                + " TEXT )";

        db.execSQL(createDateTableSql);
        Log.i("info", "created tables");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "DATES");
        onCreate(db);

    }



    public long insertDate(String dateName, String dateTitle,String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATENAME, dateName);
        values.put(COLUMN_DATETITLE, dateTitle);
        values.put(COLUMN_DATE, date);

        long result = db.insert(TABLE_DATES, null, values);

        //check if record is inserted successfully
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }

        db.close();
        Log.d("SQL Insert ", "" + result); //id returned, shouldn’t be -1
        return result;
    }



    public int updateDate(Date data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATENAME, data.getDateName());
        values.put(COLUMN_DATETITLE, data.getDateTitle());
        values.put(COLUMN_DATE, data.getDate());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};

        int result = db.update(TABLE_DATES, values, condition, args);

        //check if record is updated successfully if the affected record is 1
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }


        db.close();
        return result;
    }


    public int deleteDate(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};

        int result = db.delete(TABLE_DATES, condition, args);

        //check if record is successfully deleted if the affected record is 1
        if (result < 1){
            Log.d("DBHelper", "Delete failed");
        }

        db.close();
        return result;
    }




    public ArrayList<Date> getDateDetail() {
        // Create an ArrayList that holds String objects
        ArrayList<Date> dateDetail = new ArrayList<Date>();

        // Select all the tasks' description
        String selectQuery = "SELECT * FROM " + TABLE_DATES;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();

        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);

        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(0);
                String dateName = cursor.getString(1);
                String dateTitle = cursor.getString(2);
                String date = cursor.getString(3);

                Date obj = new Date(id, dateName, dateTitle, date);

                dateDetail.add(obj);

            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return dateDetail;
    }



    public ArrayList<Date> getAllDateDetail() {
        ArrayList<Date> allDatesDetail = new ArrayList<Date>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_DATENAME + "," + COLUMN_DATETITLE + "," + COLUMN_DATE + " FROM " + TABLE_DATES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);
                String dateName = cursor.getString(1);
                String dateTitle = cursor.getString(2);
                String date = cursor.getString(3);

                Date obj = new Date(id, dateName, dateTitle, date);

                allDatesDetail.add(obj);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allDatesDetail;
    }

}

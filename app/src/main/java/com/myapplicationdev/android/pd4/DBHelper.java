package com.myapplicationdev.android.pd4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "friends.db";
    private static final int DATABASE_VERSION = 5;
    private static final String TABLE_FRIENDS = "friend";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_HOROSCOPE = "horoscope";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_FAV = "fav";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_FRIENDS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT ," + COLUMN_BIRTHDAY + " TEXT, " + COLUMN_HOROSCOPE
                + " INTEGER, " + COLUMN_PHONE + " INTEGER, " + COLUMN_ADDRESS + " TEXT, " + COLUMN_FAV + " TEXT )";

        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "FRIENDS");
        onCreate(db);

    }



    public long insertFriend(String name, String birthday, int horoscope, int phone, String address, String fav) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_BIRTHDAY, birthday);
        values.put(COLUMN_HOROSCOPE, horoscope);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_FAV, fav);

        long result = db.insert(TABLE_FRIENDS, null, values);

        //check if record is inserted successfully
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }

        db.close();
        Log.d("SQL Insert ", "" + result); //id returned, shouldn’t be -1
        return result;
    }



    public int updateFriend(Friend data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_BIRTHDAY, data.getBirthday());
        values.put(COLUMN_HOROSCOPE, data.getHoroscope());
        values.put(COLUMN_PHONE, data.getPhone());
        values.put(COLUMN_ADDRESS, data.getAddress());
        values.put(COLUMN_FAV, data.getFav());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};

        int result = db.update(TABLE_FRIENDS, values, condition, args);

        //check if record is updated successfully if the affected record is 1
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }


        db.close();
        return result;
    }


    public int deleteFriend(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};

        int result = db.delete(TABLE_FRIENDS, condition, args);

        //check if record is successfully deleted if the affected record is 1
        if (result < 1){
            Log.d("DBHelper", "Delete failed");
        }

        db.close();
        return result;
    }




    public ArrayList<Friend> getFriendDetail() {
        // Create an ArrayList that holds String objects
        ArrayList<Friend> friendDetail = new ArrayList<Friend>();

        // Select all the tasks' description
        String selectQuery = "SELECT * FROM " + TABLE_FRIENDS;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();

        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);

        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String birthday = cursor.getString(2);
                int horoscope = cursor.getInt(3);
                int phone = cursor.getInt(4);
                String address = cursor.getString(5);
                String fav = cursor.getString(6);
                Friend obj = new Friend(id, name, birthday, horoscope, phone, address, fav);

                friendDetail.add(obj);

            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return friendDetail;
    }



    public ArrayList<Card> getAllFriendDetails() {
        ArrayList<Card> friendDetails = new ArrayList<Card>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_PHONE + "," + COLUMN_ADDRESS + "," + COLUMN_BIRTHDAY + "," + COLUMN_HOROSCOPE + "," + COLUMN_FAV + " FROM " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int phone = cursor.getInt(2);
                String address = cursor.getString(3);
                String birthday = cursor.getString(4);
                int horoscope = cursor.getInt(5);
                String fav = cursor.getString(6);
                Card obj = new Card(id, name, birthday, horoscope, phone, address, fav);

                friendDetails.add(obj);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return friendDetails;
    }



}

package com.example.android.firstapp;

/**
 * Created by Yatch on 11/18/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "currency.db";
    public static final String TABLE_CURRENCY = "currencies";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CURRENCYNAME = "currencyname";

    public dbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CURRENCY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CURRENCYNAME + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENCY);
        onCreate(db);
    }

    //Add a new row to the database
    public void addCurrency(Currency currency){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CURRENCYNAME, currency.getCurrencyName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CURRENCY, null, values);
        db.close();
    }

    //Delete a currency from the database
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CURRENCY + " WHERE " + COLUMN_CURRENCYNAME + "=\"" + productName + "\";" );
    }


    /*
    //Print out the database as a string
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CURRENCY + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("productname"))!= null){
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
    */

}

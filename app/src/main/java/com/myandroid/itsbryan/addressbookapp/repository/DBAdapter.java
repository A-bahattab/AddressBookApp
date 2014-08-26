package com.myandroid.itsbryan.addressbookapp.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by its.Bryan on 8/20/2014.
 */
public class DBAdapter extends SQLiteOpenHelper{
    /*DATABASE COLUMN NAMES*/
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_EMAIL_ADDRESS = "emailAddress";
    public static final String COLUMN_PHONE_NUMBER = "phoneNumber";
    public static final String COLUMN_HOME_ADDRESS = "homeAddress";

    /*DATABASE NAME AND VERSION VARIABLES*/
    public static final String TABLE_CONTACTS = "contacts";
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    /*DATABASE CREATION STRING*/
    public static final String CREATE_CONTACTS_TABLE = "create table IF NOT EXISTS "
            + TABLE_CONTACTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_FIRST_NAME + "text not null, "
            + COLUMN_LAST_NAME + " text not null, "
            + COLUMN_PHONE_NUMBER + " text not null, "
            + COLUMN_EMAIL_ADDRESS + " , "
            + COLUMN_HOME_ADDRESS + " ); ";

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(
                DBAdapter.class.getName(),
                "Upgrading database from " + oldVersion + " to " + newVersion + ", which will destroy all old data"
        );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
}

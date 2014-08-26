package com.myandroid.itsbryan.addressbookapp.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.myandroid.itsbryan.addressbookapp.domain.Contact;
import com.myandroid.itsbryan.addressbookapp.repository.DBAdapter;
import com.myandroid.itsbryan.addressbookapp.repository.DataSourceDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by its.Bryan on 8/20/2014.
 */
public class DataSourceDAOImpl implements DataSourceDAO{
    private SQLiteDatabase database;
    private DBAdapter dbHelper;
    private int numberOfContacts = 0;

  public DataSourceDAOImpl(Context context){
        dbHelper = new DBAdapter(context);
    }

    public void open()throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    @Override
    public void createContact(Contact contact) {
        try {
            open();
            ContentValues values = new ContentValues();
            Log.w("Inside The Create Contact Method: ", " Binding variable values to 'values' object");
            values.put(DBAdapter.COLUMN_FIRST_NAME, contact.getName());
            values.put(DBAdapter.COLUMN_LAST_NAME, contact.getSurname());
            values.put(DBAdapter.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
            values.put(DBAdapter.COLUMN_EMAIL_ADDRESS, contact.getEmailAddress());
            values.put(DBAdapter.COLUMN_HOME_ADDRESS, contact.getHomeAddress());

            Log.w("Inserting into DB: ", " Create Contact");
            database.insert(DBAdapter.TABLE_CONTACTS, null, values);
            close();
        }
        catch (SQLException sqlEx){
            Log.w("Error Occurred Whilst Inserting New Contact: ", sqlEx.getErrorCode() + " " + sqlEx.getMessage());
            close();
        }

    }

    @Override
    public void updateContact(Contact contact) {

    }

    @Override
    public Contact findContactDetailsByID(int id) {
        try {
            open();
            Cursor contactsList = database.query(
                    DBAdapter.TABLE_CONTACTS, new String[]{
                            DBAdapter.COLUMN_ID,
                            DBAdapter.COLUMN_FIRST_NAME,
                            DBAdapter.COLUMN_LAST_NAME,
                            DBAdapter.COLUMN_PHONE_NUMBER,
                            DBAdapter.COLUMN_EMAIL_ADDRESS,
                            DBAdapter.COLUMN_HOME_ADDRESS},
                            DBAdapter.COLUMN_ID + " =? "
                    , new String[]{String.valueOf(id)}
                    , null, null, null, null);


            if (contactsList == null)
                return null;

            contactsList.moveToFirst();
            Contact contact = new Contact.Builder(contactsList.getString(2))
                    .setId(contactsList.getInt(0))
                    .setName(contactsList.getString(1))
                    .setSurname(contactsList.getString(3))
                    .setEmailAddress(contactsList.getString(4))
                    .setHomeAddress(contactsList.getString(5))
                    .build();
            contactsList.moveToLast();

            numberOfContacts = contactsList.getCount();
            close();
            return contact;
        }
        catch (SQLException sqlEx){
            Log.w("Error Occurred Whilst Inserting New Contact: ", sqlEx.getErrorCode() + " - " + sqlEx.getMessage());
            close();
            return null;
        }

    }

    @Override
    public void deleteContact(Contact contact) {

    }

    @Override
    public Contact getContact() {
        return null;
    }

    @Override
    public List<Contact> getAllContactsList() {
        return null;
    }

    @Override
    public int getNumberOfContacts() {
        String getAllContacts = "SELECT * FROM " + DBAdapter.TABLE_CONTACTS;
        try {
            open();
            Cursor cursor = database.rawQuery(getAllContacts, null);
            numberOfContacts = cursor.getCount();
            close();
        }
        catch (SQLException sqlEx){
            Log.w("Error Occurred Whilst Inserting New Contact: ", sqlEx.getErrorCode() + " - " + sqlEx.getMessage());
            close();
        }
        return numberOfContacts;
    }
}

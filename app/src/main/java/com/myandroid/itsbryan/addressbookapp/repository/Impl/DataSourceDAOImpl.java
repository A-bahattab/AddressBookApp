package com.myandroid.itsbryan.addressbookapp.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.myandroid.itsbryan.addressbookapp.domain.Contact;
import com.myandroid.itsbryan.addressbookapp.repository.DBAdapter;
import com.myandroid.itsbryan.addressbookapp.repository.DataSourceDAO;

import java.sql.SQLException;
import java.util.ArrayList;
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
        try {
            open();
            ContentValues values = new ContentValues();
            values.put(DBAdapter.COLUMN_FIRST_NAME, contact.getName());
            values.put(DBAdapter.COLUMN_LAST_NAME, contact.getSurname());
            values.put(DBAdapter.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
            values.put(DBAdapter.COLUMN_EMAIL_ADDRESS, contact.getEmailAddress());
            values.put(DBAdapter.COLUMN_HOME_ADDRESS, contact.getHomeAddress());
            database.update(DBAdapter.TABLE_CONTACTS, values, DBAdapter.COLUMN_ID + " = ?", new String[] {String.valueOf(contact.getId())});
            close();
        }
        catch(SQLException sqlEx) {
            Log.w("Error Occurred Whilst Inserting New Contact: ", sqlEx.getErrorCode() + " " + sqlEx.getMessage());
            close();
        }
    }

    @Override
    public Contact findContactByID(int id) {
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
            Contact contact = new Contact.Builder(contactsList.getString(3))
                    .setId(contactsList.getInt(0))
                    .name(contactsList.getString(1))
                    .surname(contactsList.getString(2))
                    .emailAddress(contactsList.getString(4))
                    .homeAddress(contactsList.getString(5))
                    .build();
            contactsList.moveToLast();

            numberOfContacts = contactsList.getCount();
            close();
            return contact;
        }
        catch (SQLException sqlEx){
            Log.w("Error Occurred Whilst Updating Contact: ", sqlEx.getErrorCode() + " - " + sqlEx.getMessage());
            close();
            return null;
        }

    }

    @Override
    public void deleteContact(Contact contact) {
        try {
            open();
            database.delete(DBAdapter.TABLE_CONTACTS, DBAdapter.COLUMN_ID + " = ?", new String[] {String.valueOf(contact.getId())});
            close();
        }
        catch (SQLException sqlEx){
            Log.w("Error Occurred Whilst Deleting Contact: ", sqlEx.getErrorCode() + " - " + sqlEx.getMessage());
            close();
        }
    }

    @Override
    public Contact getContact() {
       return null;
    }

    @Override
    public List<Contact> getAllContactsList() {
        String getAllContacts = "SELECT * FROM " + DBAdapter.TABLE_CONTACTS;
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = null;
        try {
            open();
            Cursor contactsList = database.rawQuery(getAllContacts, null);
            if(contactsList.moveToFirst()) {
                do {
                     contact = new Contact.Builder(contactsList.getString(3))
                            .setId(contactsList.getInt(0))
                            .name(contactsList.getString(1))
                            .surname(contactsList.getString(2))
                            .emailAddress(contactsList.getString(4))
                            .homeAddress(contactsList.getString(5))
                            .build();
                     contacts.add(contact);
                    Log.d("ID:  ", contact.getId() + "");
                }while(contactsList.moveToNext());
            }
            close();
        }
        catch (SQLException sqlEx){
            Log.w("Error Occurred Whilst Retrieving Contacts: ", sqlEx.getErrorCode() + " - " + sqlEx.getMessage());
            close();
        }
        return contacts;
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

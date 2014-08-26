package com.myandroid.itsbryan.addressbookapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.myandroid.itsbryan.addressbookapp.R;
import com.myandroid.itsbryan.addressbookapp.repository.ContactListAdapter;


public class MainActivity extends Activity {
  /*  private ListView contactsListView;
    private ContactListAdapter contactsAdapter;
    private EditText name_et = (EditText)findViewById(R.id.name_edittext);
    private EditText surname_et = (EditText)findViewById(R.id.surname_edittext);
    private EditText phone_et = (EditText)findViewById(R.id.phone_edittext);
    private EditText email_et = (EditText)findViewById(R.id.email_edittext);
    private EditText address_et = (EditText)findViewById(R.id.homeaddress_edittext);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*RETRIEVE DATA FROM DATABASE TO LIST*/


        /*BIND ADAPTER TO LIST*
        contactsListView = (ListView)findViewById(R.id.main_lv);
        contactsAdapter = new ContactListAdapter(this, null, getLayoutInflater());
        contactsListView.setAdapter(contactsAdapter);*/

        Button addNewContact = (Button)findViewById(R.id.add_new_contact);
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*GET VALUES FROM COMPONENTS AND VALIDATE THEM*/
             /*   if (name_et.getText().length() == 0 || surname_et.getText().length() == 0 || phone_et.getText().length() == 0 )
                {
                    Toast.makeText(MainActivity.this, "Please make sure all the required fields have been filled", Toast.LENGTH_LONG).show();
                    return;
                }*/
                final Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

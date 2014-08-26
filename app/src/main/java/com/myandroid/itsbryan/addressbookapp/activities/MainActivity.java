package com.myandroid.itsbryan.addressbookapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.myandroid.itsbryan.addressbookapp.R;
import com.myandroid.itsbryan.addressbookapp.repository.ContactListAdapter;
import com.myandroid.itsbryan.addressbookapp.repository.DBAdapter;


public class MainActivity extends Activity {
    private ListView contactsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*BIND ADAPTER TO LIST*/
        contactsListView = (ListView)findViewById(R.id.main_lv);
        contactsListView.setAdapter(new ContactListAdapter(this));
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    TextView contactId = (TextView) view.findViewById(R.id.lv_id);
                    int _id = Integer.parseInt(contactId.getText().toString());
                    Log.d("ID in Main Activity : ", _id + " ColumnIndex:  " + _id );
                    Intent intent = new Intent(getApplication(), ContactDetailsActivity.class);
                    intent.putExtra("contactID", _id);
                    startActivity(intent);
                    finish();
                }
                catch(Exception ex){
                    Toast.makeText(MainActivity.this, "An error occurred, could not load contact details", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        Button addNewContact = (Button)findViewById(R.id.add_new_contact);
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*GET VALUES FROM COMPONENTS AND VALIDATE THEM*/
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

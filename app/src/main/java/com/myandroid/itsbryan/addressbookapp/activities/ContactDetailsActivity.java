package com.myandroid.itsbryan.addressbookapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myandroid.itsbryan.addressbookapp.R;
import com.myandroid.itsbryan.addressbookapp.domain.Contact;
import com.myandroid.itsbryan.addressbookapp.repository.DataSourceDAO;
import com.myandroid.itsbryan.addressbookapp.repository.Impl.DataSourceDAOImpl;

public class ContactDetailsActivity extends Activity {
    private DataSourceDAO dao = new DataSourceDAOImpl(this);
    private TextView nameTV;
    private TextView surnameTV;
    private TextView phoneTV;
    private TextView emailTV;
    private TextView addressTV;
    private int contactId = 0;
    String email = "";
    String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        /*GET CONTACT FROM DB*/
        try
        {
            contactId = this.getIntent().getExtras().getInt("contactID");
        }
        catch (Exception ex){
            Toast.makeText(this, "An error occurred, could not retrieve contact details", Toast.LENGTH_LONG).show();
            return;
        }

        final Contact db_contact = dao.findContactByID(contactId);

        /*BIND IDs TO TEXT VIEW OBJECTS*/
        nameTV = (TextView)findViewById(R.id.cd_name_tv);
        surnameTV = (TextView)findViewById(R.id.cd_surname_tv);
        phoneTV = (TextView)findViewById(R.id.cd_phone_tv);
        emailTV = (TextView)findViewById(R.id.cd_email_tv);
        addressTV = (TextView)findViewById(R.id.cd_address_tv);

        email = db_contact.getEmailAddress().toString().length() > 0 ? db_contact.getEmailAddress().toString() : "Email Address" ;
        address = db_contact.getHomeAddress().toString().length() > 0 ? db_contact.getHomeAddress().toString() : "Home Address" ;

        /*SET VALUES IN TEXT VIEWS*/
        nameTV.setText(db_contact.getName());
        surnameTV.setText(db_contact.getSurname());
        phoneTV.setText(db_contact.getPhoneNumber());
        emailTV.setText(email);
        addressTV.setText(address);

        Button backToList = (Button)findViewById(R.id.cd_back1);
        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(ContactDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button backToList1 = (Button)findViewById(R.id.cd_back2);
        backToList1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(ContactDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnDelete = (Button)findViewById(R.id.cd_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.deleteContact(db_contact);
                Toast.makeText(ContactDetailsActivity.this, "Contact successfully deleted!", Toast.LENGTH_LONG).show();
                final Intent intent = new Intent(ContactDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_details, menu);
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

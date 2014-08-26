package com.myandroid.itsbryan.addressbookapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myandroid.itsbryan.addressbookapp.R;
import com.myandroid.itsbryan.addressbookapp.domain.Contact;
import com.myandroid.itsbryan.addressbookapp.repository.DataSourceDAO;
import com.myandroid.itsbryan.addressbookapp.repository.Impl.DataSourceDAOImpl;

public class NewContactActivity extends Activity {
    private DataSourceDAO dao = new DataSourceDAOImpl(this);
    private EditText name_et;
    private EditText surname_et;
    private EditText phone_et;
    private EditText email_et;
    private EditText address_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        /*MAP TEXT_VIEW IDs TO VARIABLES*/
        name_et = (EditText)findViewById(R.id.name_edittext);
        surname_et = (EditText)findViewById(R.id.surname_edittext);
        phone_et = (EditText)findViewById(R.id.phone_edittext);
        email_et = (EditText)findViewById(R.id.email_edittext);
        address_et = (EditText)findViewById(R.id.homeaddress_edittext);

        Button cancelBtn = (Button)findViewById(R.id.nc_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(NewContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Button addContact = (Button)findViewById(R.id.nc_add_contact);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name_et.getText().length() == 0 || surname_et.getText().length() == 0 || phone_et.getText().length() == 0 )
                {
                    Toast.makeText(NewContactActivity.this, "Missing Information \nPlease make sure all the required fields have been filled in", Toast.LENGTH_LONG).show();
                    return;
                }

                /*GET VALUES AND ADD OBJECT TO DB*/
                Contact contact = new Contact.Builder(phone_et.getText().toString())
                        .name(name_et.getText().toString())
                        .surname(surname_et.getText().toString())
                        .emailAddress(email_et.getText().toString())
                        .homeAddress(address_et.getText().toString())
                        .build();

                /*PERSIST TO THE DB*/
                dao.createContact(contact);

                /*TEST IF DATA WAS ADDED*/
                int size = dao.getNumberOfContacts();
                Contact db_contact = dao.findContactByID(size);

                Toast.makeText(
                        NewContactActivity.this,
                        "Contact successfully saved!"
                                + db_contact.getId() + "\n"
                                + db_contact.getName() + " "
                                + db_contact.getSurname() + "\n"
                                + db_contact.getPhoneNumber() + "\n"
                                + db_contact.getEmailAddress() + "\n"
                                + db_contact.getHomeAddress() + "\n",
                        Toast.LENGTH_LONG).show();

                final Intent intent = new Intent(NewContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button backBtn = (Button)findViewById(R.id.nc_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(NewContactActivity.this, ContactDetailsActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_contact, menu);
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

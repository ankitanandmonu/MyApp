package org.drupalchamp.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.drupalchamp.myapp.activity.TipCalculatorActivity;
import org.drupalchamp.myapp.model.Contact;

import java.util.List;

/**
 * Created by ANKIT ANAND
 * Date: 3/4/2016
 * Time: 4:00 PM
 */
public class DatabaseActivity extends AppCompatActivity {
    private ListView listView;
    private Button btnAdd;
    private List<Contact> contacts;
    private DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){
            Toast.makeText(this, "Handle Exception", Toast.LENGTH_LONG).show();
        }

        this.databaseAccess = DatabaseAccess.getInstance(getApplicationContext());


        // Find the GUI components
        this.listView = (ListView) findViewById(R.id.listView);
        this.btnAdd = (Button) findViewById(R.id.btnAdd);

        //Set event listener to Button
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
        btnAdd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(getApplicationContext(),TipCalculatorActivity.class));
                return false;
            }
        });

        // Set event listener to ListView
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateContact(position);
            }
        });
    }

    /**
     * Read all the contacts
     *
     * @return List of Contacts
     */
    private List<Contact> getContacts() {
        databaseAccess.open();
        List<Contact> list = databaseAccess.getContacts();
            databaseAccess.close();
            return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }

    private void updateListView() {
        this.contacts = getContacts();

        // Create the adapter and assign to ListView
        ContactAdapter adapter = new ContactAdapter(this, contacts);
        this.listView.setAdapter(adapter);
    }

    /**
     * Start ViewActivity to add new Contact.
     */
    private void addContact() {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    /**
     * Start ViewActivity to update a Contact.
     *
     * @param index the index of the contact
     */
    private void updateContact(int index) {
        Contact contact = contacts.get(index);
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("CONTACT", contact);
        startActivity(intent);
    }

    /**
     * Custom ArrayAdapter for Contacts.
     */
    private class ContactAdapter extends ArrayAdapter<Contact> {


        public ContactAdapter(Context context, List<Contact> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.database_listitem, parent, false);
            }
            TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
            TextView txtPhone = (TextView) convertView.findViewById(R.id.txtPhone);
            Contact contact = contacts.get(position);
            txtName.setText(contact.getFirstName());
            txtPhone.setText(contact.getPhone());
            return convertView;
        }
    }
}

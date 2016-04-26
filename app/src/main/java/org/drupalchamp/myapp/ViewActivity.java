package org.drupalchamp.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.drupalchamp.myapp.model.Contact;

/**
 * Created by ANKIT ANAND
 * Date: 3/4/2016
 * Time: 3:58 PM
 */
public class ViewActivity extends ActionBarActivity {
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhone;
    private EditText etEmail;
    private Button btnSave;
    private Button btnDelete;
    private Contact contact;
    private DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){
            Toast.makeText(this, "Handle Exception", Toast.LENGTH_LONG).show();
        }

        this.databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        // Find the GUI components
        findViews();

        checkIntentForContact();

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact == null) {
                    insertContact();
                } else {
                    updateContact();
                }
            }
        });

        this.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
            }
        });
    }

    private void updateContact() {
        databaseAccess.open();
        Contact newContact = new Contact();
        newContact.setFirstName(etFirstName.getText().toString());
        newContact.setLastName(etLastName.getText().toString());
        newContact.setPhone(etPhone.getText().toString());
        newContact.setEmail(etEmail.getText().toString());

        databaseAccess.updateContact(contact, newContact);
        databaseAccess.close();
        this.finish();
    }

    private void insertContact() {
        databaseAccess.open();
        Contact newContact = new Contact();
        newContact.setFirstName(etFirstName.getText().toString());
        newContact.setLastName(etLastName.getText().toString());
        newContact.setPhone(etPhone.getText().toString());
        newContact.setEmail(etEmail.getText().toString());

        databaseAccess.insertContact(newContact);
        databaseAccess.close();
        this.finish();
    }

    private void deleteContact() {
        databaseAccess.open();
        databaseAccess.deleteContact(contact);
        databaseAccess.close();
        this.finish();
    }

    private void findViews() {
        this.etFirstName = (EditText) findViewById(R.id.etFirstName);
        this.etLastName = (EditText) findViewById(R.id.etLastName);
        this.etPhone = (EditText) findViewById(R.id.etPhone);
        this.etEmail = (EditText) findViewById(R.id.etEmail);
        this.btnSave = (Button) findViewById(R.id.btnSave);
        this.btnDelete = (Button) findViewById(R.id.btnDelete);
    }

    private void checkIntentForContact() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        btnDelete.setEnabled(false);
        if (bundle != null) {
            contact = (Contact) bundle.get("CONTACT");
            if (contact != null) {
                this.etFirstName.setText(contact.getFirstName());
                this.etLastName.setText(contact.getLastName());
                this.etPhone.setText(contact.getPhone());
                this.etEmail.setText(contact.getEmail());
                btnDelete.setEnabled(true);
            }
        }
    }
}

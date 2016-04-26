package org.drupalchamp.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import org.drupalchamp.myapp.R;


public class NextActivity extends AppCompatActivity {
    TextView txtrank;
    TextView txtcountry;

    String location;
    String time;

    int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){
            Toast.makeText(this, "Handle Exception", Toast.LENGTH_LONG).show();
        }
        // Retrieve data from MainActivity on listview item click
        Intent i = getIntent();
        // Get the listview item click position
        position = i.getExtras().getInt("position");
        // Get the list of rank
        location = i.getStringExtra("cityName");
        // Get the list of country
        time = i.getStringExtra("abc");
        // Get the list of population


        // Locate the TextViews in singleitemview.xml
        txtrank = (TextView) findViewById(R.id.rank);
        txtcountry = (TextView) findViewById(R.id.country);


        // Load the text into the TextViews followed by the position
        txtrank.setText(location);
        txtcountry.setText(time);

    }
}

package org.drupalchamp.myapp.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.drupalchamp.myapp.R;
import org.drupalchamp.myapp.location.GpsTracker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button gpsTracker;
    TextView lattitude, longitude, location,textView;
    GpsTracker gps;
    ListView updatedLocation;
    TextView abc;
    String formattedDate;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    String cityName = "N/A";

    // Identifier for the permission request
    private static final int LOCATION_PERMISSIONS_REQUEST = 1;

    //private final int SELECT_PHOTO = 1;
    //private final int SELECT_VIDEO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getPermissionForCamera();

        updatedLocation = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_layout,R.id.title,arrayList);
        gpsTracker = (Button) findViewById(R.id.button);
        lattitude = (TextView) findViewById(R.id.textView2);
        longitude = (TextView) findViewById(R.id.textView4);
        location = (TextView) findViewById(R.id.city);
        textView = (TextView) findViewById(R.id.textView);


        updatedLocation.setAdapter(adapter);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),CameraActivity.class));
            }
        });


        /*Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videoClickerIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                startActivityForResult(videoClickerIntent, SELECT_VIDEO);
            }
        });
        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoClickerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(photoClickerIntent, SELECT_PHOTO);
            }
        });*/
        updatedLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),cityName+position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),NextActivity.class);
                intent.putExtra("cityName",cityName);
                intent.putExtra("abc",formattedDate);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

        gpsTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
                formattedDate = df.format(c.getTime());
                gps = new GpsTracker(MainActivity.this);

                if (gps.canGetLocation()) {
                    double latitudes = gps.getLatitude();
                    double longitudes = gps.getLongitude();
                    String ankit = String.valueOf(latitudes);
                    String anand = String.valueOf(longitudes);
                    lattitude.setText(ankit);
                    longitude.setText(anand);
                    Log.v("Location", "latitude" + latitudes + "," + "longitude" + longitudes);



                    if (!ankit.equals("0.0") && !anand.equals(0.0)) {
                        Geocoder gcd = new Geocoder(MainActivity.this,
                                Locale.getDefault());
                        List<Address> addresses;
                        try {
                            Log.v("Within", "Try");
                            addresses = gcd.getFromLocation(latitudes, longitudes, 1);
                            Log.v("addresses", "" + addresses.toString());
                            if (addresses.size() > 0) {
                                Address address = addresses.get(0);
                                System.out.println(addresses.get(0).getLocality  ());
                                cityName = String.format("%s, %s, %s, %s, %s",
                                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                                        address.getLocality(),
                                        address.getSubLocality(),
                                        address.getAdminArea(),
                                        address.getCountryName(),
                                        address.getPostalCode());
                            }
                            location.setText(cityName);
                            abc =new TextView(MainActivity.this);
                            abc.setText(formattedDate);
                            arrayList.add(location.getText().toString() + "\n"+abc.getText().toString());
                            adapter.notifyDataSetChanged();
                            Log.v("cityName", "" + cityName);
                        } catch (IOException e) {
                            Log.v("Within", "Catch");
                            location.setText("Not Detected");
                            e.printStackTrace();
                        }
                    }
                } else {
                    gps.showSettingsAlert();
                }
            }
        });


    }

    private void getPermissionForCamera() {

    }


}

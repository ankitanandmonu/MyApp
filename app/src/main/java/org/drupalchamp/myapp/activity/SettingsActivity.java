package org.drupalchamp.myapp.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import org.drupalchamp.myapp.R;

/**
 * Created by ANKIT ANAND
 * Date: 3/15/2016
 * Time: 12:20 PM
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.new_xml);
    }
}

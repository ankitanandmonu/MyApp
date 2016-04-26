package org.drupalchamp.myapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by ANKIT ANAND
 * Date: 3/15/2016
 * Time: 4:04 PM
 */
public class MyFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.new_xml);
    }
}

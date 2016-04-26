package org.drupalchamp.myapp.activity;

import android.app.Activity;
import android.os.Bundle;

import org.drupalchamp.myapp.R;

/**
 * Created by ANKIT ANAND
 * Date: 2/29/2016
 * Time: 12:11 PM
 */
public class NotificationView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_details);
    }
}

package org.drupalchamp.myapp.activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.drupalchamp.myapp.R;

/**
 * Created by ANKIT ANAND
 * Date: 2/23/2016
 * Time: 10:50 AM
 */
public class DragDropActivity extends AppCompatActivity {
    ImageView img;
    private static final String IMAGEVIEW_TAG = "Logo";
    String msg;
    private RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_drop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){
            Toast.makeText(this, "Handle Exception", Toast.LENGTH_LONG).show();
        }


        img = (ImageView) findViewById(R.id.iv_logo);

        img.setTag(IMAGEVIEW_TAG);

       img.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

               String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
               ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
               // Instantiates the drag shadow builder.
               View.DragShadowBuilder myShadow = new View.DragShadowBuilder(img);

               // Starts the drag
               v.startDrag(dragData, // the data to be dragged
                       myShadow,    // the drag shadow builder
                       null, 0);   // no need to use local data
               return true;
           }
       });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
            }
        });
        // Create and set the drag event listener for the View
        img.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction())
                {
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (RelativeLayout.LayoutParams)
                                v.getLayoutParams();
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
                        // Do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_EXITED :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION  :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED   :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                        // Do nothing
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(msg, "ACTION_DROP event");
                        // Do nothing
                        break;
                    default: break;
                }
                return true;
            }
        });

    }
}

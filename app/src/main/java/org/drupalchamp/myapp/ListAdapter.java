package org.drupalchamp.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ANKIT ANAND
 * Date: 2/22/2016
 * Time: 5:22 PM
 */
public class ListAdapter extends BaseAdapter {
    Context context;
    String[] location;
    String[] time;

    LayoutInflater inflater;
    public ListAdapter(Context context,String location[],String time[]){
        this.context = context;
        this.location = location;
        this.time = time;

    }



    @Override
    public int getCount() {
        return location.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtrank;
        TextView txtcountry;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.listview_item, parent, false);

        txtrank = (TextView) itemView.findViewById(R.id.rank);
        txtcountry = (TextView) itemView.findViewById(R.id.country);

        txtcountry.setText(time[position]);
        txtrank.setText(location[position]);
        return itemView;
    }
}

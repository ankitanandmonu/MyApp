package org.drupalchamp.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ANKIT ANAND
 * Date: 3/4/2016
 * Time: 4:51 PM
 */
public class LocationDatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static LocationDatabaseAccess instance;

    public LocationDatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static LocationDatabaseAccess getInstance(Context context) {
        if (instance == null){
            instance = new LocationDatabaseAccess(context);
        }
        return instance;
    }
    //open the database
    public void open(){
        this.database = openHelper.getWritableDatabase();
    }
    //close the database
    public void close(){
        if (database != null){
            this.database.close();
        }
    }
    public void insertLocation(Location location){
        ContentValues values = new ContentValues();
        values.put("address",location.getAddress());
        values.put("time",location.getTime());
        database.insert("Location",null,values);
    }
    public List<Location> getLocation(){
        List<Location> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Location", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Location location = new Location();
            location.setAddress(cursor.getString(0));
            location.setTime(cursor.getString(1));

            list.add(location);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void updateLocation(Location oldLocation, Location newLocation) {
        ContentValues values = new ContentValues();
        values.put("address", newLocation.getAddress());
        values.put("time", newLocation.getTime());
        database.update("Location", values, "time = ?", new String[]{oldLocation.getTime()});
    }

    public void deleteContact(Location location) {
        database.delete("Location", "time = ?", new String[]{location.getTime()});
        database.close();
    }
}

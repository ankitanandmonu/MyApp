package org.drupalchamp.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ANKIT ANAND
 * Date: 3/4/2016
 * Time: 4:47 PM
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "location_database.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Location(address TEXT, time TEXT PRIMARY KEY);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

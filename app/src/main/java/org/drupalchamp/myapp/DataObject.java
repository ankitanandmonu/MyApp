package org.drupalchamp.myapp;

/**
 * Created by ANKIT ANAND
 * Date: 3/16/2016
 * Time: 10:18 AM
 */
public class DataObject {


    String name;
    String version;
    int id_;
    int image;

    public DataObject(String name, String version, int id_, int image) {
        this.name = name;
        this.version = version;
        this.id_ = id_;
        this.image=image;
    }


    public String getName() {
        return name;
    }


    public String getVersion() {
        return version;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}

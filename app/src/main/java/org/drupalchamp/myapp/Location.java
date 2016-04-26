package org.drupalchamp.myapp;

import java.io.Serializable;

/**
 * Created by ANKIT ANAND
 * Date: 3/4/2016
 * Time: 4:51 PM
 */
public class Location implements Serializable{
    private String address;
    private String time;

    public String getAddress() {
        return address;
    }

    public String getTime() {
        return time;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return address + "" + time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!time.equals(location.time)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}

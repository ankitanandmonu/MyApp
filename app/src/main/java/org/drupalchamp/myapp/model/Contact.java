package org.drupalchamp.myapp.model;

import java.io.Serializable;

/**
 * Created by ANKIT ANAND
 * Date: 3/4/2016
 * Time: 3:57 PM
 */
public class Contact implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!phone.equals(contact.phone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }
}

package com.example.chris.coursework.data.entities;

import com.example.chris.coursework.common.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Chris on 04/02/2018.
 */

public class Patient {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = Utils.createCalendarFrom(dob);
    }

    public void setDob(Calendar cal) {
        this.dob = cal;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    private int id = -1;
    private String firstName = "";
    private String lastName = "";
    private String otherNames = "";
    private Calendar dob = null;
    private String address1 = "";
    private String address2 = "";
    private String city = "";
    private String postcode = "";
    private String nhsNumber = "";

    // Existing sessions
    private List<Session> sessions = null;
}

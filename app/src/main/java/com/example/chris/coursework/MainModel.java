package com.example.chris.coursework;

import android.content.Context;
import android.provider.ContactsContract;

import com.example.chris.coursework.data.DAO;
import com.example.chris.coursework.data.entities.Attending;
import com.example.chris.coursework.data.entities.Patient;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.data.entities.Therapist;

/**
 * Created by Chris on 03/02/2018.
 */

public class MainModel {
    private static MainModel instance = null;

    private DAO database = null;
    private Context context;

    private Therapist therapist;
    private Patient patient;
    private Session session;
    private Attending attending;

    public MainModel(Context context) {
        this.context = context;
        database = new DAO(this.context);
    }

    public static MainModel getInstance(Context context) {
        if(instance == null) {
            instance = new MainModel(context);
        }
        return instance;
    }

    public DAO getDAO() {
        return new DAO(this.context);
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public void setCurrentPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getCurrentPatient() {
        return this.patient;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getCurrentSession() {
        return this.session;
    }

    public Attending getAttending() {
        return attending;
    }

    public void setAttending(Attending attending) {
        this.attending = attending;
    }
}

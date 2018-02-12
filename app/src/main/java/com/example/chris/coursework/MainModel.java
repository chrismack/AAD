package com.example.chris.coursework;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    private static Context context;
    private static AppCompatActivity view;

    private Therapist therapist;
    private Patient patient;
    private Session session;
    private Attending attending;

    public MainModel(AppCompatActivity view) {
        this.view = view;
        this.context = view.getApplicationContext();
        database = new DAO(view);
    }

    public static MainModel getInstance(AppCompatActivity lview) {
        if(instance == null) {
            instance = new MainModel(lview);
        } else {
            view = lview;
            context = view.getApplicationContext();
        }
        return instance;
    }

    public DAO getDAO() {
        return new DAO(this.view);
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

    public Session updateSession(Session session) {
        return this.getDAO().updateSession(session);
    }

}

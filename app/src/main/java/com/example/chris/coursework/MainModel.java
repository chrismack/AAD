package com.example.chris.coursework;

import android.content.Context;

import com.example.chris.coursework.data.DAO;
import com.example.chris.coursework.data.entities.Patient;
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


}

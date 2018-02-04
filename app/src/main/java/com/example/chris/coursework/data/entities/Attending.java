package com.example.chris.coursework.data.entities;

/**
 * Created by Chris on 04/02/2018.
 */

public class Attending {
    private Therapist therapist;
    private Patient patient;
    private Session session;

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}

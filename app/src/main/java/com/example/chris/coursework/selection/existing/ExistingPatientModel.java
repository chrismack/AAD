package com.example.chris.coursework.selection.existing;

import android.widget.TableRow;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.data.DAO;
import com.example.chris.coursework.data.entities.Attending;
import com.example.chris.coursework.data.entities.Patient;
import com.example.chris.coursework.data.entities.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 05/02/2018.
 */

public class ExistingPatientModel {

    private ExistingPatientPresenter presenter;
    private List<Patient> patients;
    private DAO dao;
    private Session selectedSession;

    private TableRow currentRow;
    private TableRow sessionRow;


    public ExistingPatientModel(ExistingPatientPresenter presenter) {
        this.presenter = presenter;
        this.dao = MainModel.getInstance(this.presenter.getContext()).getDAO();
        this.currentRow = null;
    }

    private List<Patient> getPatientsFromDatabase() {
        List<Patient> patients;
        patients = this.dao.getAllPatients();
        return patients;
    }

    public List<Session> getPatientSessions(int id) {
        return this.dao.getAllPatientSessions(id);
    }

    public List<Patient> getPatients() {
        if(patients == null) {
            patients = getPatientsFromDatabase();
        }
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public TableRow getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(TableRow currentRow) {
        this.currentRow = currentRow;
    }

    public TableRow getSessionRow() {
        return sessionRow;
    }

    public void setSessionRow(TableRow sessionRow) {
        this.sessionRow = sessionRow;
    }

    public Session getSelectedSession() {
        return selectedSession;
    }

    public void setSelectedSession(Session selectedSession) {
        this.selectedSession = selectedSession;
    }

    public Session saveSession(Session session) {
        return this.dao.createSession(session);
    }

    public Session updateSession(Session session) {
        return this.dao.updateSession(session);
    }

    public Attending createAttending(Attending attending) {
        return this.dao.createNewMeeting(attending);
    }

    public boolean attendingExists(Attending attending) {
        return this.dao.attendingExists(attending);
    }
}

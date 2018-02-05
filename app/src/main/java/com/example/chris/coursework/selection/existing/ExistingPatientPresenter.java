package com.example.chris.coursework.selection.existing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.R;
import com.example.chris.coursework.common.Utils;
import com.example.chris.coursework.data.entities.Attending;
import com.example.chris.coursework.data.entities.Patient;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.selection.tests.TestSelectionView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Chris on 05/02/2018.
 */

public class ExistingPatientPresenter implements ExistingPatientContract.IExstingPatientPresnter {

    private ExistingPatientView view;
    private ExistingPatientModel model;

    private Patient selectedPatient;

    // Previous sessions headers
    TableRow sessionHeaders;
    TextView tv_SessionId;
    TextView tv_sessionCreated;
    TextView tv_sessionLastAttempted;

    public ExistingPatientPresenter(ExistingPatientView view) {
        this.view = view;
        this.model = new ExistingPatientModel(this);
        this.setupSessiontableHeaders();
    }

    @Override
    public void setupSessiontableHeaders() {
        this.sessionHeaders = new TableRow(this.getContext());
        this.tv_SessionId = new TextView(this.getContext());
        this.tv_sessionCreated = new TextView(this.getContext());
        this.tv_sessionLastAttempted = new TextView(this.getContext());

        this.tv_SessionId.setText("ID");
        this.tv_SessionId.setTextSize(22.0f);
        this.tv_SessionId.setWidth(200);

        this.tv_sessionCreated.setText("Created");
        this.tv_sessionCreated.setTextSize(22.0f);
        this.tv_sessionCreated.setWidth(200);

        this.tv_sessionLastAttempted.setText("Last Attempted");
        this.tv_sessionLastAttempted.setTextSize(22.0f);
        this.tv_sessionLastAttempted.setWidth(400);

        this.sessionHeaders.addView(tv_SessionId);
        this.sessionHeaders.addView(tv_sessionCreated);
        this.sessionHeaders.addView(tv_sessionLastAttempted);
    }

    @Override
    public void loadPatients() {
        List<Patient> patients = this.model.getPatients();
        for(int i = 0; i < patients.size(); i++) {
            createPatientRow(this.view.getTableLayout(), patients.get(i));
        }
    }

    @Override
    public void createPatientRow(TableLayout layout, Patient patient) {
        TableRow row = new TableRow(this.view);
        row.setTag(patient);

        TextView firstName = new TextView(this.view);
        TextView lastName = new TextView(this.view);
        TextView dob = new TextView(this.view);

        firstName.setText(patient.getFirstName());
        firstName.setWidth(300);
        firstName.setTextSize(20.0f);
        lastName.setText(patient.getLastName());
        lastName.setWidth(300);
        lastName.setTextSize(20.0f);
        dob.setText(Utils.calendarToString(patient.getDob()));
        dob.setWidth(300);
        dob.setTextSize(20.0f);

        row.addView(firstName);
        row.addView(lastName);
        row.addView(dob);

        row.setOnClickListener(patientRowSelected);

        layout.addView(row);
    }

    @Override
    public void createSessionsTable(List<Session> sessions) {
        //Set the header
        this.view.getSessionsTable().addView(sessionHeaders);

        TableRow row;
        TextView id;
        TextView created;
        TextView attempted;

        for(Session session : sessions) {
            row = new TableRow(this.getContext());
            id = new TextView(this.getContext());
            created = new TextView(this.getContext());
            attempted = new TextView(this.getContext());

            id.setText(String.valueOf(session.getSessionId()));
            id.setWidth(200);
            id.setTextSize(20.0f);

            created.setText(Utils.stringFrom(session.getCreationDate()));
            created.setWidth(400);
            created.setTextSize(20.0f);

            attempted.setText(Utils.stringFrom(session.getLastAttemptDate()));
            attempted.setWidth(400);
            attempted.setTextSize(20.0f);

            row.addView(id);
            row.addView(created);
            row.addView(attempted);
            row.setOnClickListener(sessionRowSelected);
            row.setTag(session);
            this.view.getSessionsTable().addView(row);
        }
    }

    @Override
    public void clearSessionsTable() {
        this.view.getSessionsTable().removeAllViews();
    }

    public View.OnClickListener patientRowSelected = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            patientRowSelected(view);
        }
    };

    @Override
    public void patientRowSelected(View view) {
        if(this.model.getCurrentRow() != null) {
            this.model.getCurrentRow().setBackgroundColor(Color.TRANSPARENT);
        }
        this.model.setCurrentRow((TableRow) view);

        Patient patient = (Patient) view.getTag();
        setSelectedPatient(patient);

        this.model.setSelectedSession(null);

        if(patient.getSessions() == null) {
            patient.setSessions(this.model.getPatientSessions(patient.getId()));
        }
        this.clearSessionsTable();
        if(patient.getSessions().size() > 0) {
            this.createSessionsTable(patient.getSessions());
        }

        view.setBackgroundColor(ContextCompat.getColor(this.view.getApplicationContext(), R.color.colorAccent));
        this.view.getNewSession().setVisibility(View.VISIBLE);
        this.view.getContinueSession().setVisibility(View.GONE);
    }

    public View.OnClickListener sessionRowSelected = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sessionRowSelected(view);
        }
    };

    public void sessionRowSelected(View view) {
        if(this.model.getSessionRow() != null) {
            this.model.getSessionRow().setBackgroundColor(Color.TRANSPARENT);
        }
        this.model.setSessionRow((TableRow) view);

        Session session = (Session) view.getTag();
        this.model.setSelectedSession(session);

        view.setBackgroundColor(ContextCompat.getColor(this.view.getApplicationContext(), R.color.colorAccent));
        this.view.getContinueSession().setVisibility(View.VISIBLE);
    }

    @Override
    public Context getContext() {
        return this.view.getApplicationContext();
    }


    // Button Click
    @Override
    public void createNewSession() {
        Session session = new Session();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        session.setCreationDate(date);
        session.setLastAttemptDate(date);
        session = this.model.saveSession(session);

        Attending attending = this.createAttendance(session);
        MainModel.getInstance(getContext()).setAttending(attending);
        this.model.createAttending(attending);

        this.gotoTestSelection(session);
    }

    // Button Click
    @Override
    public void continueExistingSession() {
        Session session = this.model.getSelectedSession();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        session.setLastAttemptDate(date);
        session = this.model.updateSession(session);

        Attending attending = this.createAttendance(session);

        if(!this.model.attendingExists(attending)) {
            attending = this.model.createAttending(attending);
        }
        MainModel.getInstance(getContext()).setAttending(attending);

        this.gotoTestSelection(session);
    }

    @Override
    public void gotoTestSelection(Session session) {
        MainModel.getInstance(getContext()).setSession(session);
        MainModel.getInstance(getContext()).setCurrentPatient(selectedPatient);

        Intent intent = new Intent(this.view, TestSelectionView.class);
        this.view.startActivity(intent);
    }

    @Override
    public Attending createAttendance(Session session) {
        MainModel mainModel = MainModel.getInstance(getContext());

        Attending attending = new Attending();
        attending.setTherapist(mainModel.getTherapist());
        attending.setPatient(this.getSelectedPatient());
        attending.setSession(session);

        return attending;
    }

    @Override
    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    @Override
    public void setSelectedPatient(Patient selectedPatient) {
        this.selectedPatient = selectedPatient;
    }
}

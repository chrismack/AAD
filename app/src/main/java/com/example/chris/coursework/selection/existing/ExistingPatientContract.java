package com.example.chris.coursework.selection.existing;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.chris.coursework.data.entities.Attending;
import com.example.chris.coursework.data.entities.Patient;
import com.example.chris.coursework.data.entities.Session;

import java.util.List;

/**
 * Created by Chris on 05/02/2018.
 */

public interface ExistingPatientContract {

    interface IExstingPatientPresnter {
        void setupSessiontableHeaders();

        void loadPatients();

        void createPatientRow(TableLayout layout, Patient patient);

        void createSessionsTable(List<Session> sessions);

        void clearSessionsTable();

        void patientRowSelected(View view);

        void sessionRowSelected(View view);

        Context getContext();

        void createNewSession();

        void continueExistingSession();

        Attending createAttendance(Session session);

        Patient getSelectedPatient();

        void setSelectedPatient(Patient patient);

        void gotoTestSelection(Session session);

        ExistingPatientView getView();
    }

    interface IExistingPatientView {
        TableLayout getTableLayout();

        void onNewSession(View view);

        void onContinueSession(View view);

        TableLayout getSessionsTable();

        void setSessionsTable(TableLayout sessionsTable);

        Button getNewSession();

        void setNewSession(Button newSession);

        Button getContinueSession();

        void setContinueSession(Button continueSession);
    }
}

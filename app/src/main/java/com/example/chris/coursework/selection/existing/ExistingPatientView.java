package com.example.chris.coursework.selection.existing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.chris.coursework.R;

public class ExistingPatientView extends AppCompatActivity implements ExistingPatientContract.IExistingPatientView {

    private TableLayout tableLayout;
    private TableLayout sessionsTable;

    private Button newSession;
    private Button continueSession;

    private ExistingPatientPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_patient);

        presenter = new ExistingPatientPresenter(this);

        tableLayout = (TableLayout) findViewById(R.id.tl_existing_patient);
        sessionsTable = (TableLayout) findViewById(R.id.tl_patient_sessions);

        newSession = (Button) findViewById(R.id.btn_newSession);
        continueSession = (Button) findViewById(R.id.bt_cont_session);
        presenter.loadPatients();
    }

    @Override
    public TableLayout getTableLayout() {
        return this.tableLayout;
    }

    @Override
    public void onNewSession(View view) {
        this.presenter.createNewSession();
    }

    @Override
    public void onContinueSession(View view) {
        this.presenter.continueExistingSession();
    }

    @Override
    public TableLayout getSessionsTable() {
        return sessionsTable;
    }

    @Override
    public void setSessionsTable(TableLayout sessionsTable) {
        this.sessionsTable = sessionsTable;
    }

    @Override
    public Button getNewSession() {
        return newSession;
    }

    @Override
    public void setNewSession(Button newSession) {
        this.newSession = newSession;
    }

    @Override
    public Button getContinueSession() {
        return continueSession;
    }

    @Override
    public void setContinueSession(Button continueSession) {
        this.continueSession = continueSession;
    }
}

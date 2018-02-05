package com.example.chris.coursework.selection.existing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.example.chris.coursework.R;

public class ExistingPatientView extends AppCompatActivity implements ExistingPatientContract.IExistingPatientView {

    private TableLayout tableLayout;

    private ExistingPatientPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_patient);

        presenter = new ExistingPatientPresenter(this);

        tableLayout = (TableLayout) findViewById(R.id.tl_existing_patient);

        presenter.loadPatients();
    }

    @Override
    public TableLayout getTableLayout() {
        return this.tableLayout;
    }
}

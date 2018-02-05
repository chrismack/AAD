package com.example.chris.coursework.selection.existing;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.chris.coursework.R;
import com.example.chris.coursework.common.Utils;
import com.example.chris.coursework.data.entities.Patient;

import java.util.List;

/**
 * Created by Chris on 05/02/2018.
 */

public class ExistingPatientPresenter implements ExistingPatientContract.IExstingPatientPresnter {

    private ExistingPatientView view;
    private ExistingPatientModel model;

    public ExistingPatientPresenter(ExistingPatientView view) {
        this.view = view;
        this.model = new ExistingPatientModel(this);
    }

    @Override
    public void loadPatients() {
        List<Patient> patients = this.model.getPatients();
        for(int i = 0; i < patients.size(); i++) {
            createRow(this.view.getTableLayout(), patients.get(i));
        }
    }

    @Override
    public void createRow(TableLayout layout, Patient patient) {
        TableRow row = new TableRow(this.view);
        row.setTag(patient);

        TextView firstName = new TextView(this.view);
        TextView lastName = new TextView(this.view);
        TextView dob = new TextView(this.view);
        TextView dateCreated = new TextView(this.view);



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

        row.setOnClickListener(rowSelected);

        layout.addView(row);
    }

    public View.OnClickListener rowSelected = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rowSelected(view);
        }
    };

    @Override
    public void rowSelected(View view) {
        if(this.model.getCurrentRow() != null) {
            this.model.getCurrentRow().setBackgroundColor(Color.TRANSPARENT);
        }
        this.model.setCurrentRow((TableRow) view);

        view.setBackgroundColor(ContextCompat.getColor(this.view.getApplicationContext(), R.color.colorAccent));
    }

    @Override
    public Context getContext() {
        return this.view.getApplicationContext();
    }
}

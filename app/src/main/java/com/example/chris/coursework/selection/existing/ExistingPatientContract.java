package com.example.chris.coursework.selection.existing;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;

import com.example.chris.coursework.data.entities.Patient;

/**
 * Created by Chris on 05/02/2018.
 */

public interface ExistingPatientContract {

    interface IExstingPatientPresnter {
        void loadPatients();
        void createRow(TableLayout layout, Patient patient);
        void rowSelected(View view);
        Context getContext();
    }

    interface IExistingPatientView {
        TableLayout getTableLayout();
    }
}

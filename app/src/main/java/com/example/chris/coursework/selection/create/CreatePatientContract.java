package com.example.chris.coursework.selection.create;

import android.app.AlertDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.DatePicker;

import com.example.chris.coursework.data.entities.Patient;

/**
 * Created by Chris on 04/02/2018.
 */

public interface CreatePatientContract {

    interface ICreatePatientPresenter {
        void submitPatient();

        void setErrorText(String errors);

        AlertDialog buildConfirmationBox(Patient patient);

        void confirmPatient(Patient patient);

        CreatePatientView getView();
    }

    interface ICreatePatientView {
        void onSubmit(View view);

        TextInputEditText getFirstname();

        TextInputEditText getLastname();

        DatePicker getDateofBirth();

        TextInputEditText getNHSNumber();

        TextInputEditText getAddress1();

        TextInputEditText getAddress2();

        TextInputEditText getCity();

        TextInputEditText getPostcode();

        void setErrorText(String errors);
    }
}

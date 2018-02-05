package com.example.chris.coursework.selection.create;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.common.Utils;
import com.example.chris.coursework.data.entities.Patient;
import com.example.chris.coursework.selection.tests.TestSelectionView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 04/02/2018.
 */

public class CreatePatientPresenter implements CreatePatientContract.ICreatePatientPresenter {

    private CreatePatientView view;
    private CreatePatientModel model;

    public CreatePatientPresenter(CreatePatientView view) {
        this.view = view;
        this.model = new CreatePatientModel(this);
    }

    @Override
    public void submitPatient() {

        Patient patient = this.model.buildPatient(
                this.view.getFirstname().getText().toString(),
                this.view.getLastname().getText().toString(),
                Utils.datepickerToCalendar(this.view.getDateofBirth()),
                this.view.getNHSNumber().getText().toString(),
                this.view.getAddress1().getText().toString(),
                this.view.getAddress2().getText().toString(),
                this.view.getCity().getText().toString(),
                this.view.getPostcode().getText().toString()
        );

        String errors = this.model.checkPatientErrors(patient);

        // If errors string is empty no errors found
        if(errors.equals("")) {
            AlertDialog confirmationWindow =  this.buildConfirmationBox(patient);
            confirmationWindow.show();
        } else {
            setErrorText(errors);
        }
    }

    @Override
    public void setErrorText(String errors) {
        this.view.setErrorText(errors);
    }

    @Override
    public AlertDialog buildConfirmationBox(final Patient patient) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this.view);
        alertBuilder.setTitle("Confirm patient Details");

        String confirmation = "";

        if(!patient.getFirstName().trim().equals("")) {
            confirmation += "First Name : " + patient.getFirstName() + "\n";
        }
        if(!patient.getLastName().trim().equals("")) {
            confirmation += "Last Name : " + patient.getLastName() + "\n";
        }
        if(patient.getDob() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dob = sdf.format(patient.getDob().getTime());
            confirmation += "Date of Birth : " + dob + "\n";
        }
        if(!patient.getNhsNumber().trim().equals("")) {
            confirmation += "NHS Number : " + patient.getNhsNumber() + "\n";
        }
        if(!patient.getAddress1().trim().equals("")) {
            confirmation += "Address 1 : " + patient.getAddress1() + "\n";
        }
        if(!patient.getAddress2().trim().equals("")) {
            confirmation += "Address 2 : " + patient.getAddress2() + "\n";
        }
        if(!patient.getCity().trim().equals("")) {
            confirmation += "City : " + patient.getCity() + "\n";
        }
        if(!patient.getPostcode().trim().equals("")) {
            confirmation += "Postcode : " + patient.getPostcode() + "\n";
        }

        alertBuilder.setMessage(confirmation)
                .setCancelable(false)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirmPatient(patient);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        return alertBuilder.create();
    }

    @Override
    public void confirmPatient(Patient patient) {
        this.model.insertPatient(patient);
        MainModel.getInstance(this.view.getApplicationContext()).setCurrentPatient(patient);

        Intent intent = new Intent(this.view, TestSelectionView.class);
        this.view.startActivity(intent);
    }

    @Override
    public CreatePatientView getView() {
        return this.view;
    }

}

package com.example.chris.coursework.selection.create;

import com.example.chris.coursework.data.DAO;
import com.example.chris.coursework.data.entities.Patient;

import java.util.Calendar;

/**
 * Created by Chris on 04/02/2018.
 */

public class CreatePatientModel {

    private CreatePatientPresenter presenter;

    private DAO dao;

    public CreatePatientModel(CreatePatientPresenter presenter) {
        this.presenter = presenter;
        this.dao = new DAO(presenter.getView().getApplicationContext());
    }

    /**
     * @param patient details entered
     * @return Error message, empty string if inputs are valid
     */
    public String checkPatientErrors(Patient patient) {
        String errors = "";

        if(patient.getFirstName().trim().equals("")) {
            errors += "First name is required\n";
        }

        if(patient.getLastName().trim().equals("")) {
            errors += "Last name is required\n";
        }

        if(patient.getDob() == null) {
            errors += "Date of Birth is required\n";
        }

        if(patient.getAddress1().trim().equals("") && patient.getAddress2().trim().equals("")) {
            errors += "At least one address line is required is required\n";
        }

        if(patient.getPostcode().trim().equals("")) {
            errors += "Postcode is required\n";
        }

        return errors;
    }

    public Patient buildPatient(String firstname, String lastname,
                                Calendar dob, String nhsNo,
                                String address1, String address2,
                                String city, String postcode) {

        Patient patient = new Patient();
        patient.setFirstName(firstname);
        patient.setLastName(lastname);
        patient.setAddress1(address1);
        patient.setAddress2(address2);
        patient.setDob(dob);
        patient.setNhsNumber(nhsNo);
        patient.setCity(city);
        patient.setPostcode(postcode);
        return patient;
    }

    public Patient insertPatient(Patient patient) {
        return this.dao.insertPatient(patient);
    }
}

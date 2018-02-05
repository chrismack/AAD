package com.example.chris.coursework.selection.existing;

import android.widget.TableRow;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.data.DAO;
import com.example.chris.coursework.data.entities.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 05/02/2018.
 */

public class ExistingPatientModel {

    private ExistingPatientPresenter presenter;
    private List<Patient> patients;
    private DAO dao;

    private TableRow currentRow;


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


}

package com.example.chris.coursework.selection.create;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.chris.coursework.R;

public class CreatePatientView extends AppCompatActivity implements CreatePatientContract.ICreatePatientView {

    private CreatePatientPresenter presenter;

    private TextInputEditText et_patient_firstname;
    private TextInputEditText et_patient_secondname;
    private DatePicker dp_patient_dob;
    private TextInputEditText et_patient_nshNo;
    private TextInputEditText et_patient_address1;
    private TextInputEditText et_patient_address2;
    private TextInputEditText et_patient_city;
    private TextInputEditText et_patient_postcode;

    private TextView tv_patient_create_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);

        presenter = new CreatePatientPresenter(this);

        et_patient_firstname = (TextInputEditText) findViewById(R.id.et_patient_firstname);
        et_patient_secondname = (TextInputEditText) findViewById(R.id.et_patient_secondname);
        dp_patient_dob = (DatePicker) findViewById(R.id.dp_patient_dob);
        et_patient_nshNo = (TextInputEditText) findViewById(R.id.et_patient_nshNo);
        et_patient_address1 = (TextInputEditText) findViewById(R.id.et_patient_address1);
        et_patient_address2 = (TextInputEditText) findViewById(R.id.et_patient_address2);
        et_patient_city = (TextInputEditText) findViewById(R.id.et_patient_city);
        et_patient_postcode = (TextInputEditText) findViewById(R.id.et_patient_postcode);

        tv_patient_create_error = (TextView) findViewById(R.id.tv_patient_create_error);
    }

    @Override
    public void onSubmit(View view) {
        this.presenter.submitPatient();
    }

    @Override
    public TextInputEditText getFirstname() {
        return et_patient_firstname;
    }

    @Override
    public TextInputEditText getLastname() {
        return et_patient_secondname;
    }

    @Override
    public DatePicker getDateofBirth() {
        return dp_patient_dob;
    }

    @Override
    public TextInputEditText getNHSNumber() {
        return et_patient_nshNo;
    }

    @Override
    public TextInputEditText getAddress1() {
        return et_patient_address1;
    }

    @Override
    public TextInputEditText getAddress2() {
        return et_patient_address2;
    }

    @Override
    public TextInputEditText getCity() {
        return et_patient_city;
    }

    @Override
    public TextInputEditText getPostcode() {
        return et_patient_postcode;
    }

    @Override
    public void setErrorText(String errors) {
        tv_patient_create_error.setText(errors);
    }
}

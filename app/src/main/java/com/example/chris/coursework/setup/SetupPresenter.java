package com.example.chris.coursework.setup;

import android.content.Intent;

import com.example.chris.coursework.selection.create.CreatePatientView;

/**
 * Created by Chris on 04/02/2018.
 */

public class SetupPresenter implements SetupContract.ISetupPresenter{

    private SetupView view;

    public SetupPresenter(SetupView view) {
        this.view = view;
    }

    @Override
    public void showNewPatient() {
        Intent intent = new Intent(this.view, CreatePatientView.class);
        this.view.startActivity(intent);
    }

    @Override
    public void showExistingPatients() {
        Intent intent = new Intent();
        this.view.startActivity(intent);
    }
}

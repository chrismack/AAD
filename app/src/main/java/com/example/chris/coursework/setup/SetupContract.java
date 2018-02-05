package com.example.chris.coursework.setup;

import android.view.View;

/**
 * Created by Chris on 04/02/2018.
 */

public interface SetupContract {

    interface ISetupPresenter {
        void showNewPatient();
        void showExistingPatients();
    }

    interface ISetupView {
        void onNewPatient(View view);
        void onExistingPatient(View view);
    }
}

package com.example.chris.coursework.setup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chris.coursework.R;

public class SetupView extends AppCompatActivity implements SetupContract.ISetupView{

    private SetupPresenter presenter;

    private Button bt_newPatient;
    private Button bt_existingPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        presenter = new SetupPresenter(this);

        bt_newPatient = (Button) findViewById(R.id.bt_newPatient);
        bt_existingPatient = (Button) findViewById(R.id.bt_existingPatient);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNewPatient(View view) {
        this.presenter.showNewPatient();
    }

    @Override
    public void onExistingPatient(View view) {
        this.presenter.showExistingPatients();
    }

    @Override
    public void onBackPressed() {}
}

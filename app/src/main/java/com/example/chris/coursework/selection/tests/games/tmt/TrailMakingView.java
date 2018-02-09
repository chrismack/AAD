package com.example.chris.coursework.selection.tests.games.tmt;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chris.coursework.R;

public class TrailMakingView extends AppCompatActivity implements TrailMakingContract.ITrailMakingView{

    private TrailMakingPresenter presenter;
    private ConstraintLayout testArea;
    private Button btn_fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_making);

        testArea = (ConstraintLayout) findViewById(R.id.con_1);
        btn_fin = (Button) findViewById(R.id.btn_fin);

        this.presenter = new TrailMakingPresenter(this);
    }

    @Override
    public ConstraintLayout getTestArea() {
        return this.testArea;
    }

    @Override
    public void onFinish(View view) {
        this.presenter.nextState();
    }

    @Override
    public Button getFinishButton() {
        return this.btn_fin;
    }
}

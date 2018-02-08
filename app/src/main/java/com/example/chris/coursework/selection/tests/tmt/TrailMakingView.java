package com.example.chris.coursework.selection.tests.tmt;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Canvas;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.example.chris.coursework.R;
import com.example.chris.coursework.common.views.DrawingView;

import java.util.List;

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

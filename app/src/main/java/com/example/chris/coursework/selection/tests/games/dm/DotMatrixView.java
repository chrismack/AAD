package com.example.chris.coursework.selection.tests.games.dm;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.example.chris.coursework.R;

public class DotMatrixView extends AppCompatActivity implements DotMatrixContract.IDotMatrixView{

    private DotMatrixPresenter presenter;
    private ImageView img_dm_sec1, img_dm_sec2;

    private ConstraintLayout section1, section2;

    //Drawing layouts
    private ConstraintLayout practiceLayout;
    private ConstraintLayout section1Draw, section2Draw;

    private Button btn_dm_next, btn_dm_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_matrix);

        section1 = (ConstraintLayout) findViewById(R.id.con_dm_sec1);
        section2 = (ConstraintLayout) findViewById(R.id.con_dm_sec2);

        img_dm_sec1 = (ImageView) findViewById(R.id.img_dm_sec1);
        img_dm_sec2 = (ImageView) findViewById(R.id.img_dm_sec2);

        section1Draw = (ConstraintLayout) findViewById(R.id.con_sec1_draw);
        section2Draw = (ConstraintLayout) findViewById(R.id.con_sec2_draw);

        practiceLayout = (ConstraintLayout) findViewById(R.id.con_dm_pac);

        btn_dm_next = (Button) findViewById(R.id.btn_dm_next);
        btn_dm_finish = (Button) findViewById(R.id.btn_dm_finish);

        this.presenter = new DotMatrixPresenter(this);

        img_dm_sec1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println(img_dm_sec1.getMeasuredWidth());
                System.out.println(img_dm_sec1.getMeasuredHeight());
            }
        });

        img_dm_sec2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println(img_dm_sec2.getMeasuredWidth());
                System.out.println(img_dm_sec2.getMeasuredHeight());
            }
        });

    }

    @Override
    public Button getNextButton() {
        return this.btn_dm_next;
    }

    @Override
    public Button getFinishButton() {
        return this.btn_dm_finish;
    }

    @Override
    public ConstraintLayout getSection1() {
        return this.section1;
    }

    @Override
    public ConstraintLayout getSection2() {
        return this.section2;
    }

    @Override
    public ImageView getSection1Image() {
        return this.img_dm_sec1;
    }

    @Override
    public ImageView getSection2Image() {
        return this.img_dm_sec2;
    }

    @Override
    public ConstraintLayout getSection1Draw() {
        return this.section1Draw;
    }

    @Override
    public ConstraintLayout getSection2Draw() {
        return this.section2Draw;
    }

    @Override
    public ConstraintLayout getPracticeLayout() {
        return this.practiceLayout;
    }

    @Override
    public void onSectionChange(View view) {
        this.presenter.nextSection();
    }

    @Override
    public void onFinish(View view) {
        this.presenter.patientFinished();
    }
}

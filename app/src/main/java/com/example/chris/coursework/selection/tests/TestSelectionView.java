package com.example.chris.coursework.selection.tests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.R;

public class TestSelectionView extends AppCompatActivity implements TestSelectionContract.ITestSelectionView{

    private TestSelectionPresenter presenter;

    RadioGroup rg_testSelection;
    Button btn_testConfirm;
    ImageView tmt_check, dc_check, rsr_check, smd_check, smc_check;

    ToggleButton tbtn_motCap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);

        rg_testSelection = (RadioGroup) findViewById(R.id.rg_test_selection);
        btn_testConfirm = (Button) findViewById(R.id.btn_test_confirm);

        tmt_check = (ImageView) findViewById(R.id.img_ts_tmt_check);
        dc_check = (ImageView) findViewById(R.id.img_ts_dc_check);
        rsr_check = (ImageView) findViewById(R.id.img_ts_rsr_check);
        smd_check = (ImageView) findViewById(R.id.img_ts_smd_check);
        smc_check = (ImageView) findViewById(R.id.img_ts_smc_check);

        this.presenter = new TestSelectionPresenter(this);

        tbtn_motCap = (ToggleButton) findViewById(R.id.tbtn_motCap);
        tbtn_motCap.setChecked(MainModel.getInstance(this).isRecordMotions());
        tbtn_motCap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onMotionToggle(b);
            }
        });

        rg_testSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                presenter.testChanged();
            }
        });

        this.presenter.displayCompleted();
    }

    @Override
    public RadioGroup getRg_testSelection() {
        return rg_testSelection;
    }

    @Override
    public void setRg_testSelection(RadioGroup rg_testSelection) {
        this.rg_testSelection = rg_testSelection;
    }

    @Override
    public Button getBtn_testConfirm() {
        return btn_testConfirm;
    }

    @Override
    public void setBtn_testConfirm(Button btn_testConfirm) {
        this.btn_testConfirm = btn_testConfirm;
    }

    @Override
    public void onTestConfirmed(View view) {
        this.presenter.testConfirmed();
    }

    @Override
    public void onReview(View view) {
        this.presenter.showReview();
    }

    @Override
    public void onMotionToggle(boolean bool) {
        this.presenter.toggleMotionCapture(bool);
    }

    public ImageView getTmt_check() {
        return tmt_check;
    }

    public ImageView getDc_check() {
        return dc_check;
    }

    public ImageView getRsr_check() {
        return rsr_check;
    }

    public ImageView getSmd_check() {
        return smd_check;
    }

    public ImageView getSmc_check() {
        return smc_check;
    }
}

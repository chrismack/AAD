package com.example.chris.coursework.selection.tests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.chris.coursework.R;

public class TestSelectionView extends AppCompatActivity implements TestSelectionContract.ITestSelectionView{

    private TestSelectionPresenter presenter;

    RadioGroup rg_testSelection;
    Button btn_testConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);

        rg_testSelection = (RadioGroup) findViewById(R.id.rg_test_selection);
        btn_testConfirm = (Button) findViewById(R.id.btn_test_confirm);

        rg_testSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                presenter.testChanged();
            }
        });

        this.presenter = new TestSelectionPresenter(this);
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
}

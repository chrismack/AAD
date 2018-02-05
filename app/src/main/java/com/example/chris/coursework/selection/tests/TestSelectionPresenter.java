package com.example.chris.coursework.selection.tests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.chris.coursework.R;

/**
 * Created by Chris on 05/02/2018.
 */

public class TestSelectionPresenter implements TestSelectionContract.ITestSelectionPresenter {

    TestSelectionView view;

    public TestSelectionPresenter(TestSelectionView view) {
        this.view = view;
        if(view.getRg_testSelection().getCheckedRadioButtonId() > -1) {
            view.getBtn_testConfirm().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void testChanged() {
        if(view.getRg_testSelection().getCheckedRadioButtonId() > -1) {
            view.getBtn_testConfirm().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void testConfirmed() {
        System.out.println(view.getRg_testSelection().getCheckedRadioButtonId());

        Class<AppCompatActivity> nextActivity = null;

        switch(this.view.getRg_testSelection().getCheckedRadioButtonId()) {
            case R.id.rb_dc:
                break;
            case R.id.rb_smd:
                break;
            case R.id.rb_smc:
                break;
            case R.id.rb_rsr:
                break;
            case R.id.rb_tmt:
                break;

        }
        if(nextActivity != null) {
            Intent intent = new Intent(this.view, nextActivity);
        }

    }
}

package com.example.chris.coursework.selection.tests;

import android.content.Intent;
import android.view.View;

import com.example.chris.coursework.R;
import com.example.chris.coursework.selection.tests.games.compass.Compass;
import com.example.chris.coursework.selection.tests.games.dm.DotMatrixView;
import com.example.chris.coursework.selection.tests.games.rsr.RoadSignRecognition;
import com.example.chris.coursework.selection.tests.games.tmt.TrailMakingView;
import com.example.chris.coursework.selection.tests.games.truckCarMatrix.truckCarMatrix;
import com.example.chris.coursework.selection.tests.review.ReviewView;

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

        Class<?> nextActivity = null;

        switch(this.view.getRg_testSelection().getCheckedRadioButtonId()) {
            case R.id.rb_dc:
                nextActivity = DotMatrixView.class;
                break;
            case R.id.rb_smd:
                nextActivity = truckCarMatrix.class;
                break;
            case R.id.rb_smc:
                nextActivity = Compass.class;
                break;
            case R.id.rb_rsr:
                nextActivity = RoadSignRecognition.class;
                break;
            case R.id.rb_tmt:
                nextActivity = TrailMakingView.class;
                break;

        }
        if(nextActivity != null) {
            Intent intent = new Intent(this.view, nextActivity);
            this.view.startActivity(intent);
        }

    }

    @Override
    public void showReview() {
        Intent intent = new Intent(this.view.getApplicationContext(), ReviewView.class);
        this.view.startActivity(intent);
    }
}

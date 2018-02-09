package com.example.chris.coursework.selection.tests.games.tmt;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;

/**
 * Created by Chris on 06/02/2018.
 */

public interface TrailMakingContract {

    interface ITrailMakingPresenter {
        Context getContext();
        TrailMakingView getView();
        void buildTestArea(int count, boolean alternating);
        void nextState();
        void disableFinish();
        void enableFinish();
        void finishTest();
    }

    interface ITrailMakingView {
        ConstraintLayout getTestArea();
        void onFinish(View view);
        Button getFinishButton();
    }

}

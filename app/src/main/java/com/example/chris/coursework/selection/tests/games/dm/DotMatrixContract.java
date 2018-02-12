package com.example.chris.coursework.selection.tests.games.dm;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Chris on 08/02/2018.
 */

public interface DotMatrixContract {

    interface IDotMatrixPresenter {
        Context getContext();

        void setVisability(View view, int visible);

        void nextSection();

        void showSection1();

        void showSection2();

        int getActiveSection();

        void testTouched(int activeSection, MotionEvent event);

        void patientFinished();

        void showReviewing();

        DotMatrixView getView();


    }

    interface IDotMatrixView {

        Button getNextButton();

        Button getFinishButton();

        ConstraintLayout getSection1();

        ConstraintLayout getSection2();

        ImageView getSection1Image();

        ImageView getSection2Image();

        ConstraintLayout getSection1Draw();

        ConstraintLayout getSection2Draw();

        ConstraintLayout getPracticeLayout();

        void onSectionChange(View view);

        void onFinish(View view);
    }
}

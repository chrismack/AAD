package com.example.chris.coursework.selection.tests;

import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * Created by Chris on 05/02/2018.
 */

public interface TestSelectionContract {

    interface ITestSelectionPresenter {
        void testChanged();

        void testConfirmed();
    }

    interface ITestSelectionView {
        RadioGroup getRg_testSelection();

        void setRg_testSelection(RadioGroup rg_testSelection);

        Button getBtn_testConfirm();

        void setBtn_testConfirm(Button btn_testConfirm);

        void onTestConfirmed(View view);
    }
}

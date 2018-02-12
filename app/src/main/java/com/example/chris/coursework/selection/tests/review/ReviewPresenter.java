package com.example.chris.coursework.selection.tests.review;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.data.entities.Session;

/**
 * Created by Chris on 11/02/2018.
 */

public class ReviewPresenter implements ReviewContract.IReviewPresenter {

    private ReviewView view;
    private Session selectedSession;

    public ReviewPresenter(ReviewView view) {
        this.view = view;

        selectedSession = MainModel.getInstance(this.view).getCurrentSession();

        view.getTv_rev_dc_tt().setText(selectedSession.getDm_timeTaken() + "");
        view.getTv_rev_dc_error().setText(selectedSession.getDm_trueNeg() + "");
        view.getTv_rev_dc_fp().setText(selectedSession.getDm_falsePos() + "");

        view.getTv_rev_smd_cd().setText(selectedSession.getSmd_correctCars() + "");
        view.getTv_rev_sqd_ds().setText(selectedSession.getSmd_correctLorries() + "");

        view.getTv_rev_rsr_ds().setText(selectedSession.getRsr_correctSigns() + "");

        view.getTv_rev_tmt_tt().setText((int) selectedSession.getTmt_timeTakenA() + "");

        view.getTv_rev_smc_ds().setText(selectedSession.getSmc_redCars() + "");
        view.getTv_rev_smc_cs().setText(selectedSession.getSmc_blueCars() + "");
    }
}

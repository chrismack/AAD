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

        view.getTv_rev_sqd_ds().setText(selectedSession.getSmd_correctLorries() + "");

        view.getTv_rev_rsr_ds().setText(selectedSession.getRsr_correctSigns() + "");

        view.getTv_rev_tmt_tt_a().setText((int) selectedSession.getTmt_timeTakenA() + "");
        view.getTv_rev_tmt_tt_b().setText((int) selectedSession.getTmt_timeTakenB() + "");

        view.getTv_rev_smc_cs().setText(selectedSession.getSmc_blueCars() + "");

        calculateAndDisplayFinals();
    }

    @Override
    public void calculateAndDisplayFinals() {
        int dcTimeSeconds = selectedSession.getDm_timeTaken();
        int dcErrors = selectedSession.getDm_trueNeg();
        int dc_falsePos = selectedSession.getDm_falsePos();

        int directionScore = selectedSession.getSmd_correctCars();
        int compassScore = selectedSession.getSmc_blueCars();

        int roadSignScore = selectedSession.getRsr_correctSigns();


        // Pass equation
        double pass =
                ((dcTimeSeconds * 0.012) +
                (dc_falsePos * 0.216) +
                (compassScore * 0.409) +
                (roadSignScore * 1.168)) - 13.79;

        double fail =
                ((dcTimeSeconds * 0.017) +
                (dc_falsePos * 0.035) +
                (compassScore * 0.185) +
                (roadSignScore * 0.813)) - 10.042;

        this.view.getTv_rev_fs_pass().setText(pass + "");
        this.view.getTv_rev_fs_fail().setText(fail + "");

    }
}

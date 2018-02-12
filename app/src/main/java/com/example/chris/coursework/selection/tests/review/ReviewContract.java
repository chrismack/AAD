package com.example.chris.coursework.selection.tests.review;

import android.widget.TextView;

/**
 * Created by Chris on 11/02/2018.
 */

interface ReviewContract {
    interface IReviewPresenter {

    }

    interface IReviewView {
        TextView getTv_rev_dc_error();

        void setTv_rev_dc_error(TextView tv_rev_dc_error);

        TextView getTv_rev_dc_fp();

        void setTv_rev_dc_fp(TextView tv_rev_dc_fp);

        TextView getTv_rev_dc_tt();

        void setTv_rev_dc_tt(TextView tv_rev_dc_tt);

        TextView getTv_rev_sqd_ds();

        void setTv_rev_sqd_ds(TextView tv_rev_sqd_ds);

        TextView getTv_rev_smd_cd();

        void setTv_rev_smd_cd(TextView tv_rev_smd_cd);

        TextView getTv_rev_rsr_ds();

        void setTv_rev_rsr_ds(TextView tv_rev_rsr_ds);

        TextView getTv_rev_tmt_tt();

        void setTv_rev_tmt_tt(TextView tv_rev_tmt_tt);

        TextView getTv_rev_smc_cs();

        void setTv_rev_smc_cs(TextView tv_rev_smc_cs);

        TextView getTv_rev_smc_ds();

        void setTv_rev_smc_ds(TextView tv_rev_smc_ds);
    }
}

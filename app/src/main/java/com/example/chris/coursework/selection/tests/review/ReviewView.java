package com.example.chris.coursework.selection.tests.review;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chris.coursework.R;

public class ReviewView extends AppCompatActivity implements ReviewContract.IReviewView {

    // Dot cancellation answers
    private TextView tv_rev_dc_error, tv_rev_dc_fp, tv_rev_dc_tt;
    // Square matrices direction
    private TextView tv_rev_sqd_ds, tv_rev_smd_cd;
    // Road sign recognition
    private TextView tv_rev_rsr_ds;
    // Trail Making Test
    private TextView tv_rev_tmt_tt;
    // Square matrices compass
    private TextView tv_rev_smc_cs, tv_rev_smc_ds;

    private ReviewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        tv_rev_dc_error = (TextView) findViewById(R.id.tv_rev_dc_error);
        tv_rev_dc_fp = (TextView) findViewById(R.id.tv_rev_dc_fp);
        tv_rev_dc_tt = (TextView) findViewById(R.id.tv_rev_dc_tt);
        tv_rev_sqd_ds = (TextView) findViewById(R.id.tv_rev_sqd_ds);
        tv_rev_smd_cd = (TextView) findViewById(R.id.tv_rev_smd_cd);
        tv_rev_rsr_ds = (TextView) findViewById(R.id.tv_rev_rsr_ds);
        tv_rev_tmt_tt = (TextView) findViewById(R.id.tv_rev_tmt_tt);
        tv_rev_smc_cs = (TextView) findViewById(R.id.tv_rev_smc_cs);
        tv_rev_smc_ds = (TextView) findViewById(R.id.tv_rev_smc_ds);

        presenter = new ReviewPresenter(this);
    }

    @Override
    public TextView getTv_rev_dc_error() {
        return tv_rev_dc_error;
    }

    @Override
    public void setTv_rev_dc_error(TextView tv_rev_dc_error) {
        this.tv_rev_dc_error = tv_rev_dc_error;
    }

    @Override
    public TextView getTv_rev_dc_fp() {
        return tv_rev_dc_fp;
    }

    @Override
    public void setTv_rev_dc_fp(TextView tv_rev_dc_fp) {
        this.tv_rev_dc_fp = tv_rev_dc_fp;
    }

    @Override
    public TextView getTv_rev_dc_tt() {
        return tv_rev_dc_tt;
    }

    @Override
    public void setTv_rev_dc_tt(TextView tv_rev_dc_tt) {
        this.tv_rev_dc_tt = tv_rev_dc_tt;
    }

    @Override
    public TextView getTv_rev_sqd_ds() {
        return tv_rev_sqd_ds;
    }

    @Override
    public void setTv_rev_sqd_ds(TextView tv_rev_sqd_ds) {
        this.tv_rev_sqd_ds = tv_rev_sqd_ds;
    }

    @Override
    public TextView getTv_rev_smd_cd() {
        return tv_rev_smd_cd;
    }

    @Override
    public void setTv_rev_smd_cd(TextView tv_rev_smd_cd) {
        this.tv_rev_smd_cd = tv_rev_smd_cd;
    }

    @Override
    public TextView getTv_rev_rsr_ds() {
        return tv_rev_rsr_ds;
    }

    @Override
    public void setTv_rev_rsr_ds(TextView tv_rev_rsr_ds) {
        this.tv_rev_rsr_ds = tv_rev_rsr_ds;
    }

    @Override
    public TextView getTv_rev_tmt_tt() {
        return tv_rev_tmt_tt;
    }

    @Override
    public void setTv_rev_tmt_tt(TextView tv_rev_tmt_tt) {
        this.tv_rev_tmt_tt = tv_rev_tmt_tt;
    }

    @Override
    public TextView getTv_rev_smc_cs() {
        return tv_rev_smc_cs;
    }

    @Override
    public void setTv_rev_smc_cs(TextView tv_rev_smc_cs) {
        this.tv_rev_smc_cs = tv_rev_smc_cs;
    }

    @Override
    public TextView getTv_rev_smc_ds() {
        return tv_rev_smc_ds;
    }

    @Override
    public void setTv_rev_smc_ds(TextView tv_rev_smc_ds) {
        this.tv_rev_smc_ds = tv_rev_smc_ds;
    }
}

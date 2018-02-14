package com.example.chris.coursework.selection.tests.review;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chris.coursework.R;

public class ReviewView extends AppCompatActivity implements ReviewContract.IReviewView {

    // Dot cancellation answers
    private TextView tv_rev_dc_error, tv_rev_dc_fp, tv_rev_dc_tt;
    // Square matrices direction
    private TextView tv_rev_sqd_ds;
    // Road sign recognition
    private TextView tv_rev_rsr_ds;
    // Trail Making Test
    private TextView tv_rev_tmt_tt_a, tv_rev_tmt_tt_b;
    // Square matrices compass
    private TextView tv_rev_smc_cs;

    // Final Scores
    private TextView tv_rev_fs_pass, tv_rev_fs_fail;

    private ReviewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        tv_rev_dc_error = (TextView) findViewById(R.id.tv_rev_dc_error);
        tv_rev_dc_fp = (TextView) findViewById(R.id.tv_rev_dc_fp);
        tv_rev_dc_tt = (TextView) findViewById(R.id.tv_rev_dc_tt);
        tv_rev_sqd_ds = (TextView) findViewById(R.id.tv_rev_sqd_ds);
        tv_rev_rsr_ds = (TextView) findViewById(R.id.tv_rev_rsr_ds);
        tv_rev_tmt_tt_a = (TextView) findViewById(R.id.tv_rev_tmt_tt_a);
        tv_rev_tmt_tt_b = (TextView) findViewById(R.id.tv_rev_tmt_tt_b);
        tv_rev_smc_cs = (TextView) findViewById(R.id.tv_rev_smc_cs);
        tv_rev_fs_pass = (TextView) findViewById(R.id.tv_rev_fs_pass);
        tv_rev_fs_fail = (TextView) findViewById(R.id.tv_rev_fs_fail);

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
    public TextView getTv_rev_rsr_ds() {
        return tv_rev_rsr_ds;
    }

    @Override
    public void setTv_rev_rsr_ds(TextView tv_rev_rsr_ds) {
        this.tv_rev_rsr_ds = tv_rev_rsr_ds;
    }

    @Override
    public TextView getTv_rev_tmt_tt_a() {
        return tv_rev_tmt_tt_a;
    }

    @Override
    public void setTv_rev_tmt_tt_a(TextView tv_rev_tmt_tt) {
        this.tv_rev_tmt_tt_a = tv_rev_tmt_tt_a;
    }

    @Override
    public TextView getTv_rev_tmt_tt_b() {
        return tv_rev_tmt_tt_b;
    }

    @Override
    public void setTv_rev_tmt_tt_b(TextView tv_rev_tmt_tt) {
        this.tv_rev_tmt_tt_b = tv_rev_tmt_tt_b;
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
    public TextView getTv_rev_fs_pass() {
        return this.tv_rev_fs_pass;
    }

    @Override
    public void setTv_rev_fs_pass(TextView tv_rev_fs_pass) {
        this.tv_rev_fs_pass = tv_rev_fs_pass;
    }

    @Override
    public TextView getTv_rev_fs_fail() {
        return this.tv_rev_fs_fail;
    }

    @Override
    public void setTv_rev_fs_fail(TextView tv_rev_fs_fail) {
        this.tv_rev_fs_fail = tv_rev_fs_fail;
    }

}

package com.example.chris.coursework.selection.tests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chris.coursework.R;

public class TestSelectionView extends AppCompatActivity implements TestSelectionContract.ITestSelectionView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);
    }
}

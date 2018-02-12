package com.example.chris.coursework.selection.tests.games.tmt;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.common.Pair;
import com.example.chris.coursework.common.views.DrawingView;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.selection.tests.TestSelectionView;

import java.util.List;

/**
 * Created by Chris on 06/02/2018.
 */

public class TrailMakingPresenter implements TrailMakingContract.ITrailMakingPresenter {

    private final TrailMakingView view;
    private TrailMakingModel model;

    private Context context;


    public TrailMakingPresenter(final TrailMakingView view) {
        this.view = view;
        this.model = new TrailMakingModel(this);
        this.context = view.getApplicationContext();

        this.model.runTest();

//        model.regionsTest(count, model.getImageWidth() / 2 + 10, model.getImageHeight() / 2 + 10, 1594 - 150, 2058 - 150);


    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public TrailMakingView getView() {
        return view;
    }

    @Override
    public void buildTestArea(int count, boolean alternating) {
        view.getTestArea().removeAllViews();
        this.model.setDrawView(new DrawingView(this.getContext(), this.model.getPaintSettings()));

        List<Pair<Integer, Integer>> positions = model.regionsTest(count, model.getImageWidth() / 2 + 20, model.getImageHeight() / 2 + 20, 1594 - 150, 2058 - 150);

        ImageView image;
        for (int i = 0; i < count; i++) {
            image = new ImageView(context);
            image.setAdjustViewBounds(true);
            String draw = "";
            if(alternating) {
                if(i < count / 2) {
                    draw = "n" + (i + 1);
                } else {
                    draw = Character.toString((char) (97 + i - (count / 2)));
                }
            } else {
                draw = "n" + (i + 1);
            }
            image.setImageResource(view.getResources().getIdentifier(
                    draw,
                    "drawable",
                    view.getPackageName()));
            image.setMaxWidth(model.getImageWidth());
            image.setMaxHeight(model.getImageHeight());


            image.setX(positions.get(i).getFirst());
            image.setY(positions.get(i).getLast());

            view.getTestArea().addView(image);
        }

        view.getTestArea().addView(model.getDrawView());
        model.getDrawView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                model.testTouched();
                return false;
            }
        });
    }

    @Override
    public void nextState() {
        this.model.runTest();
    }

    @Override
    public void disableFinish() {
        this.getView().getFinishButton().setEnabled(false);
    }

    @Override
    public void enableFinish() {
        this.getView().getFinishButton().setEnabled(true);
    }

}

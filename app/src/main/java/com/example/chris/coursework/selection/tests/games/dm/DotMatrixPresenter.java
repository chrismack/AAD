package com.example.chris.coursework.selection.tests.games.dm;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.chris.coursework.common.views.Rectangle;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Chris on 08/02/2018.
 */

public class DotMatrixPresenter implements DotMatrixContract.IDotMatrixPresenter {

    private DotMatrixView view;
    private DotMatrixModel model;

    // 0 = practice mode, 1 = section 1, 2 = section 2
    private int activeSection = 0;

    public DotMatrixPresenter(DotMatrixView view) {
        this.view = view;
        this.model = new DotMatrixModel(this);
        view.getPracticeLayout().addView(model.getPractiveDraw());
    }

    @Override
    public Context getContext() {
        return this.view.getApplicationContext();
    }

    @Override
    public void setVisability(View view, int visible) {
        view.setVisibility(visible);
    }

    @Override
    public void nextSection() {
        if(activeSection == 0) {
            showSection1();

            // Add the drawing views to the test sections
            // Prevents the patient drawing on test when practicing
            view.getSection1Draw().addView(this.model.getSection1Draw());
            view.getSection2Draw().addView(this.model.getSection2Draw());

            Paint paintSettings = new Paint();
            paintSettings.setColor(Color.BLACK);
            paintSettings.setStyle(Paint.Style.STROKE);
            paintSettings.setStrokeWidth(6);

            View.OnTouchListener touchListener = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    testTouched(activeSection, motionEvent);
                    return false;
                }
            };

            this.model.getSection1Draw().setOnTouchListener(touchListener);
            this.model.getSection2Draw().setOnTouchListener(touchListener);

        } else if(activeSection == 1) {
            showSection2();
        } else if(activeSection == 2) {
            showSection1();
        }
    }

    @Override
    public void showSection1() {
        this.setVisability(this.view.getSection1(), View.VISIBLE);
        this.setVisability(this.view.getSection2(), View.GONE);
        this.setVisability(this.view.getFinishButton(), View.GONE);

        this.view.getNextButton().setText("Next");

        this.activeSection = 1;

        if(this.model.getState() == StateManager.State.Finish ||
                this.model.getState() == StateManager.State.Reviewing) {
            this.view.getSection1Draw().removeAllViews();
            this.view.getSection1Draw().addView(this.model.getSection1Draw());


            List<List<Boolean>> section1Touched = this.model.getSection1Touched();
            List<List<Boolean>> section1TouchedAfterTime = this.model.getSection1TouchedAfterTime();

            Paint paintSettings = new Paint();
            paintSettings.setStyle(Paint.Style.STROKE);
            paintSettings.setColor(Color.BLACK);
            paintSettings.setStrokeWidth(3);

            Paint paintAfterTime = new Paint();
            paintAfterTime.setStyle(Paint.Style.STROKE);
            paintAfterTime.setColor(Color.RED);
            paintAfterTime.setStrokeWidth(3);

            for(int x = 0; x < section1Touched.size(); x++) {
                for(int y = 0; y < section1Touched.get(x).size(); y++) {
                    if(section1Touched.get(x).get(y) == true) {
                        this.view.getSection1Draw().addView(new Rectangle(
                                getContext(),
                                x * this.model.getSec1GroupWidth(),
                                y * this.model.getSec1GroupHeight(),
                                x * this.model.getSec1GroupWidth() + this.model.getSec1GroupWidth(),
                                y * this.model.getSec1GroupHeight() + this.model.getSec1GroupHeight(),
                                paintSettings
                                ));
                        //Display red square around group touched after timer
                    } else if(section1TouchedAfterTime.get(x).get(y) == true) {
                        this.view.getSection1Draw().addView(new Rectangle(
                                getContext(),
                                x * this.model.getSec1GroupWidth(),
                                y * this.model.getSec1GroupHeight(),
                                x * this.model.getSec1GroupWidth() + this.model.getSec1GroupWidth(),
                                y * this.model.getSec1GroupHeight() + this.model.getSec1GroupHeight(),
                                paintAfterTime
                        ));
                    }
                }
            }
        }
    }

    @Override
    public void showSection2() {
        this.setVisability(this.view.getSection2(), View.VISIBLE);
        this.setVisability(this.view.getSection1(), View.GONE);

        int finishVisible = this.model.hasTimerRan() ? View.VISIBLE : View.GONE;
        this.setVisability(this.view.getFinishButton(), finishVisible);

        this.view.getNextButton().setText("Previous");

        this.activeSection = 2;

        if(this.model.getState() == StateManager.State.Reviewing) {
            this.view.getSection2Draw().removeAllViews();
            this.view.getSection2Draw().addView(this.model.getSection2Draw());

            List<List<Boolean>> section2Touched = this.model.getSection2Touched();
            List<List<Boolean>> section2TouchedAfterTouch = this.model.getSection2TouchedAfterTime();
            Paint paintSettings = new Paint();
            paintSettings.setStyle(Paint.Style.STROKE);
            paintSettings.setColor(Color.BLACK);
            paintSettings.setStrokeWidth(3);

            Paint paintAfterTouch = new Paint();
            paintAfterTouch.setStyle(Paint.Style.STROKE);
            paintAfterTouch.setColor(Color.RED);
            paintAfterTouch.setStrokeWidth(3);

            for(int x = 0; x < section2Touched.size(); x++) {
                for(int y = 0; y < section2Touched.get(x).size(); y++) {
                    if(section2Touched.get(x).get(y) == true) {
                        this.view.getSection2Draw().addView(new Rectangle(
                                getContext(),
                                x * this.model.getSec2GroupWidth(),
                                y * this.model.getSec2GroupHeight(),
                                x * this.model.getSec2GroupWidth() + this.model.getSec2GroupWidth(),
                                y * this.model.getSec2GroupHeight() + this.model.getSec2GroupHeight(),
                                paintSettings
                        ));
                        // Display red squares around groups touched after timer
                    } else if(section2TouchedAfterTouch.get(x).get(y) == true) {
                        this.view.getSection2Draw().addView(new Rectangle(
                                getContext(),
                                x * this.model.getSec2GroupWidth(),
                                y * this.model.getSec2GroupHeight(),
                                x * this.model.getSec2GroupWidth() + this.model.getSec2GroupWidth(),
                                y * this.model.getSec2GroupHeight() + this.model.getSec2GroupHeight(),
                                paintAfterTouch
                        ));
                    }
                }
            }
        }
    }

    @Override
    public int getActiveSection() {
        return this.activeSection;
    }

    @Override
    public void testTouched(int activeSection, MotionEvent event) {
        boolean isReview = this.model.getState() == StateManager.State.Reviewing ? true : false;
        if(!this.model.hasTimerRan()) {
            this.model.runTest();
        }

        float x = event.getX();
        float y = event.getY();
        if(x > 0 && y > 0) {
            if(isReview) {
                // Toggle group selected for therapist review
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    this.model.updateAnswerSet(activeSection, x, y);
                    if(getActiveSection() == 1) {
                        showSection1();
                    } else {
                        showSection2();
                    }
                }
            } else {
                this.model.updateAnswerSet(activeSection, x, y);
            }
        }


    }

    @Override
    public void patientFinished() {
        if(!this.model.isTimeOver() && this.model.hasTimerRan() &&
                this.model.getState() != StateManager.State.Reviewing) {
            this.model.getTimer().cancel();
            this.model.setState(StateManager.State.Finish);
        }

        this.model.runTest();
    }

    @Override
    public void showReviewing() {
        this.model.getPractiveDraw().disbale();
        this.model.getSection1Draw().disbale();
        this.model.getSection2Draw().disbale();

        showSection1();
    }

    @Override
    public DotMatrixView getView() {
        return this.view;
    }
}

package com.example.chris.coursework.selection.tests.games.dm;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.common.Pair;
import com.example.chris.coursework.common.views.DrawingView;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.selection.tests.games.IState;
import com.example.chris.coursework.selection.tests.games.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by Chris on 08/02/2018.
 */

public class DotMatrixModel extends TestBase {

    private DotMatrixPresenter presenter;

    private DrawingView practiveDraw, section1Draw, section2Draw;
    private Paint paintSettings;

    /*
     * The number of groups that are in the test section
     * This does not include the practice set as these are not scored
     */
    private final int section1XDots = 25;
    private final int section2XDots = 25;
    private final int section1YDots = 10;
    private final int section2YDots = 10;

    /*
     * Total groups in sections
     */
    private final int section1TotalXDots = 25;
    private final int section2TotalXDots = 25;
    private final int section1TotalYDots = 11;
    private final int section2TotalYDots = 10;

    private final int sec1Width = 2590;
    private final int sec1Height = 1114;
    private final int sec2Width = 2564;
    private final int sec2Height = 1040;

    private final float sec1GroupWidth = sec1Width / section1TotalXDots;
    private final float sec1GroupHeight = sec1Height / section1TotalYDots;
    private final float sec2GroupWidth = sec2Width / section2TotalXDots;
    private final float sec2GroupHeight = sec2Height / section2TotalYDots;


    // Answers
    final int[][] section1Answers = {
            {5, 5, 3, 4, 3, 4, 3, 3, 3, 3, 5, 5, 5, 5, 5, 3, 5, 3, 5, 4, 5, 3, 4, 5, 3},
            {4, 3, 5, 3, 4, 5, 3, 3, 5, 4, 5, 3, 4, 3, 4, 3, 3, 3, 5, 5, 4, 3, 3, 5, 5},
            {3, 4, 5, 3, 4, 3, 3, 3, 5, 5, 5, 5, 3, 5, 5, 5, 4, 5, 5, 3, 3, 3, 5, 5, 3},
            {5, 5, 4, 5, 5, 4, 4, 3, 3, 3, 4, 4, 3, 3, 3, 5, 3, 3, 5, 5, 4, 3, 3, 5, 3},
            {5, 5, 5, 4, 3, 3, 3, 4, 3, 5, 3, 5, 5, 3, 4, 5, 3, 3, 3, 5, 4, 3, 5, 5, 3},
            {5, 4, 3, 5, 4, 5, 5, 5, 3, 4, 3, 4, 4, 3, 3, 5, 3, 5, 5, 3, 5, 5, 4, 3, 4},
            {3, 5, 4, 5, 3, 4, 3, 5, 5, 5, 3, 4, 4, 5, 3, 3, 3, 3, 4, 3, 3, 5, 3, 3, 3},
            {5, 3, 4, 3, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 5, 4, 5, 5, 4, 5, 3, 5, 5, 3, 3},
            {5, 3, 4, 3, 3, 3, 4, 5, 3, 5, 5, 5, 5, 3, 5, 5, 4, 3, 3, 3, 3, 5, 3, 5, 5},
            {5, 3, 3, 5, 4, 5, 5, 3, 3, 5, 5, 5, 3, 3, 5, 5, 5, 3, 4, 3, 3, 4, 5, 3, 3}
    };

    final int[][]  section2Answers = {
            {3, 5, 5, 5, 5, 3, 5, 3, 5, 3, 3, 5, 3, 4, 5, 5, 5, 4, 5, 3, 3, 3, 5, 3, 4},
            {5, 3, 5, 3, 5, 5, 4, 3, 3, 3, 3, 5, 3, 3, 4, 5, 5, 4, 3, 5, 4, 3, 3, 5, 3},
            {5, 4, 5, 5, 4, 3, 5, 5, 5, 3, 5, 5, 4, 5, 3, 5, 3, 5, 5, 5, 5, 5, 5, 5, 3},
            {5, 4, 3, 3, 3, 3, 5, 3, 3, 5, 3, 3, 3, 3, 5, 5, 5, 3, 5, 5, 3, 5, 3, 4, 5},
            {3, 5, 5, 3, 3, 4, 5, 5, 5, 3, 5, 3, 3, 5, 4, 5, 3, 5, 3, 3, 5, 5, 3, 3, 3},
            {3, 3, 3, 4, 3, 4, 5, 3, 5, 3, 3, 3, 5, 5, 3, 3, 3, 5, 3, 3, 5, 4, 3, 5, 5},
            {3, 5, 5, 3, 5, 3, 3, 5, 5, 5, 3, 5, 5, 5, 3, 5, 5, 3, 5, 3, 5, 5, 5, 3, 3},
            {3, 3, 3, 3, 5, 4, 5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 5, 5, 4, 3, 4, 5, 3, 5, 3},
            {3, 4, 3, 4, 3, 4, 3, 4, 5, 5, 5, 3, 4, 3, 4, 5, 5, 5, 5, 4, 5, 3, 3, 3, 3},
            {3, 3, 4, 3, 3, 3, 5, 3, 3, 5, 4, 3, 3, 4, 3, 4, 3, 5, 4, 3, 3, 5, 3, 5, 3}
    };

    private List<List<Boolean>> section1Touched = new ArrayList<>();
    private List<List<Boolean>> section2Touched = new ArrayList<>();

    private CountDownTimer timer;
    private final long testTimemills = (5 * 1000) * 60;
    private boolean timeOver = false;
    private boolean timerRan = false;
    private int timeTakenSeconds = 0;


    public DotMatrixModel(DotMatrixPresenter presenter) {
        super();
        this.presenter = presenter;
        setStateManager(new StateManager(this, presenter).getClass());
        setState(StateManager.State.Practice);

        this.paintSettings = new Paint();
        this.paintSettings.setAntiAlias(true);
        this.paintSettings.setDither(true);
        this.paintSettings.setColor(Color.BLACK);
        this.paintSettings.setStyle(Paint.Style.STROKE);
        this.paintSettings.setStrokeJoin(Paint.Join.ROUND);
        this.paintSettings.setStrokeCap(Paint.Cap.ROUND);
        this.paintSettings.setStrokeWidth(6);

        this.practiveDraw = new DrawingView(presenter.getContext(), this.paintSettings);
        this.section1Draw = new DrawingView(presenter.getContext(), this.paintSettings);
        this.section2Draw = new DrawingView(presenter.getContext(), this.paintSettings);

        this.setupAnswersTouched();

        // Run practice state
        this.runTest();

        timer = new CountDownTimer(testTimemills, 1000) {

            @Override
            public void onTick(long l) {
                timeTakenSeconds++;
            }

            @Override
            public void onFinish() {
                timeOver = true;
                if(getState() == StateManager.State.TimeOver) {
                    runTest();
                }
            }
        };
    }

    /*
     * Initialise all touched values to false
     * Set to true when a square is touched
     */
    private void setupAnswersTouched() {
        for(int x  = 0; x < this.section1XDots; x++) {
            section1Touched.add(new ArrayList<Boolean>());
            for(int y = 0; y < this.section1YDots; y++) {
                section1Touched.get(x).add(false);
            }
        }

        for(int x  = 0; x < this.section2XDots; x++) {
            section2Touched.add(new ArrayList<Boolean>());
            for(int y = 0; y < this.section2YDots; y++) {
                section2Touched.get(x).add(false);
            }
        }
    }

    @Override
    public void runTest() {
        super.runTest();
    }

    @Override
    public void finishTest(Context packageContext) {
        Pair<Integer, Integer> errors = getIncorrectCount();
        int falsePos = errors.getFirst();
        int trueNeg = errors.getLast();
        int timeTaken = timeTakenSeconds;

        MainModel mainModel = MainModel.getInstance(this.presenter.getView());

        Session session = mainModel.getCurrentSession();
        session.setDm_timeTaken(timeTaken);
        session.setDm_falsePos(falsePos);
        session.setDm_trueNeg(trueNeg);

        mainModel.updateSession(session);


        super.finishTest(packageContext);
    }

    private Pair<Integer, Integer> getIncorrectCount() {
        // False Pos, True Neg
        Pair<Integer, Integer> errors = new Pair<>(0, 0);

        // Section 1
        for(int x = 0; x < section1Touched.size() ; x++) {
            for(int y = 0; y < section1Touched.get(x).size(); y++) {
                if(section1Touched.get(x).get(y) && (section1Answers[y][x] == 5 || section1Answers[y][x] == 3)) {
                    errors.setFirst(errors.getFirst() + 1);
                } else if (!section1Touched.get(x).get(y) && section1Answers[y][x] == 4) {
                    errors.setLast(errors.getLast() + 1);
                }
            }
        }

        // Section 2
        for(int x = 0; x < section2Touched.size(); x++) {
            for(int y = 0; y < section2Touched.get(x).size(); y++) {
                if(section2Touched.get(x).get(y) && (section2Answers[y][x] == 5 || section2Answers[y][x] == 3)) {
                    errors.setFirst(errors.getFirst() + 1);
                } else if (!section2Touched.get(x).get(y) && section2Answers[y][x] == 4) {
                    errors.setLast(errors.getLast() + 1);
                }
            }
        }

        return errors;
    }

    public DrawingView getPractiveDraw() {
        return practiveDraw;
    }

    public void setPractiveDraw(DrawingView practiveDraw) {
        this.practiveDraw = practiveDraw;
    }

    public DrawingView getSection1Draw() {
        return section1Draw;
    }

    public void setSection1Draw(DrawingView section1Draw) {
        this.section1Draw = section1Draw;
    }

    public DrawingView getSection2Draw() {
        return section2Draw;
    }

    public void setSection2Draw(DrawingView section2Draw) {
        this.section2Draw = section2Draw;
    }

    public CountDownTimer getTimer() {
        return this.timer;
    }

    public void setTimerRan(boolean state) {
        this.timerRan = state;
    }

    public boolean hasTimerRan() {
        return this.timerRan;
    }

    public boolean isTimeOver() {
        return this.timeOver;
    }

    public void updateAnswerSet(int activeSection, float x, float y) {
        boolean isReview = getState() == StateManager.State.Reviewing ? true : false;

        if(!timeOver  || isReview) {
            int groupX, groupY;
            if(activeSection == 1) {
                groupX = (int)(x / sec1GroupWidth);
                groupY = (int)(y / sec1GroupHeight);
                if(groupX < section1Touched.size() && groupY < section1Touched.get(groupX).size()) {
                    if(isReview) {
                        this.section1Touched.get(groupX).set(groupY, !this.section1Touched.get(groupX).get(groupY));
                    } else {
                        this.section1Touched.get(groupX).set(groupY, true);
                    }
                }
            } else {
                groupX = (int)(x / sec2GroupWidth);
                groupY = (int)(y / sec2GroupHeight);
                if(groupX < section2Touched.size() && groupY < section2Touched.get(groupX).size()) {
                    if(isReview) {
                        this.section2Touched.get(groupX).set(groupY, !this.section2Touched.get(groupX).get(groupY));
                    } else {
                        this.section2Touched.get(groupX).set(groupY, true);
                    }
                }
            }
        }
    }

    public List<List<Boolean>> getSection1Touched() {
        return section1Touched;
    }

    public List<List<Boolean>> getSection2Touched() {
        return section2Touched;
    }

    public float getSec1GroupWidth() {
        return sec1GroupWidth;
    }

    public float getSec1GroupHeight() {
        return sec1GroupHeight;
    }

    public float getSec2GroupWidth() {
        return sec2GroupWidth;
    }

    public float getSec2GroupHeight() {
        return sec2GroupHeight;
    }
}

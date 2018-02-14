package com.example.chris.coursework.selection.tests.games.tmt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.common.Pair;
import com.example.chris.coursework.common.Timer;
import com.example.chris.coursework.common.Utils;
import com.example.chris.coursework.common.views.DrawingView;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.selection.tests.games.IState;
import com.example.chris.coursework.selection.tests.games.IStateManager;
import com.example.chris.coursework.selection.tests.games.TestBase;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chris on 06/02/2018.
 */

public class TrailMakingModel extends TestBase {

    private TrailMakingPresenter presenter;


    private Paint paintSettings;
    private DrawingView drawView;

    private Timer testATimer;
    private Timer testBTimer;


    private final int imageWidth = 100;
    private final int imageHeight = 100;

    private static final int DOT_LIMIT = 25;

    private List<Pair<Integer, Integer>> imagePositions;

    public TrailMakingModel(TrailMakingPresenter presenter) {
        super();
        this.presenter = presenter;
        StateManager stateManager = new StateManager(this, presenter);
        setStateManager(stateManager.getClass());
        setState(StateManager.State.PracticeA);

        this.testATimer = new Timer();
        this.testBTimer = new Timer();

        this.paintSettings = new Paint();
        this.paintSettings.setAntiAlias(true);
        this.paintSettings.setDither(true);
        this.paintSettings.setColor(Color.BLACK);
        this.paintSettings.setStyle(Paint.Style.STROKE);
        this.paintSettings.setStrokeJoin(Paint.Join.ROUND);
        this.paintSettings.setStrokeCap(Paint.Cap.ROUND);
        this.paintSettings.setStrokeWidth(6);

        this.drawView = new DrawingView(presenter.getView(), this.paintSettings);
        this.imagePositions = new ArrayList<>();
    }

    public Paint getPaintSettings() {
        return paintSettings;
    }

    public void setPaintSettings(Paint paintSettings) {
        this.paintSettings = paintSettings;
    }

    public List<Pair<Integer, Integer>> getImagePositions() {
        return imagePositions;
    }

    public DrawingView getDrawView() {
        return drawView;
    }

    public void setDrawView(DrawingView drawView) {
        this.drawView = drawView;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void runTest() {
        super.runTest();
        if(getState() == StateManager.State.PracticeB) {
            super.runTest();
        }
    }

    @Override
    public void finishTest(Context packageContext) {
        Session session = MainModel.getInstance(this.presenter.getView()).getCurrentSession();
        session.setTmt_timeTakenA(this.getTestATimer().getTimeElapsedSeconds());
        session.setTmt_timeTakenB(this.getTestBTimer().getTimeElapsedSeconds());
        MainModel.getInstance(this.presenter.getView()).updateSession(session);
        super.finishTest(packageContext);
    }

    public Timer getTestATimer() {
        return testATimer;
    }

    public void setTestATimer(Timer testATimer) {
        this.testATimer = testATimer;
    }

    public Timer getTestBTimer() {
        return testBTimer;
    }

    public void setTestBTimer(Timer testBTimer) {
        this.testBTimer = testBTimer;
    }

    public void testTouched() {
        if(getState() == StateManager.State.TimingA || getState() == StateManager.State.TimingB) {
            setState((Enum<? extends IState>) ((IState)getState()).runState());
        }
    }

    private List<Pair<Integer, Integer>> generateRandomPositions(int count, int minSpaceX, int minSpaceY, int maxX, int maxY, int minX, int minY, long randomSeed) {
        List<Pair<Integer, Integer>> randomPositions = new ArrayList<>();

        List<List<List<Pair<Integer, Integer>>>> regions = new ArrayList<>();

        int randomX, randomY;
        Pair<Integer, Integer> pair;

        Random random = new Random(randomSeed);

        boolean validPosition = false;
        for(int i = 0; i < count; i++) {
            validPosition = false;
            while(!validPosition) {
                randomX = random.nextInt((maxX - minX - minSpaceX) + 1) + minX + minSpaceX;
                randomY = random.nextInt((maxY - minY - minSpaceY) + 1) + minY + minSpaceY;
                pair = new Pair<>(randomX, randomY);

                if (randomPositions.size() < 1) {
                    randomPositions.add(pair);
                    validPosition = true;
                } else {
                    validPosition = true;
                    for (Pair<Integer, Integer> existing : randomPositions) {
                        if(Math.abs(randomX - existing.getFirst()) < minSpaceX || Math.abs(randomY - existing.getLast()) < minSpaceY) {
                            validPosition = false;
                            break;
                        }
                    }
                    if(validPosition) {
                        randomPositions.add(pair);
                    }
                }
            }

        }


        return randomPositions;
    }

    public List<Pair<Integer, Integer>> regionsTest(int count, int minSpaceX, int minSpaceY, int maxX, int maxY) {
        // 3 x 3 region
        List<Pair<Integer, Integer>> concatRegions = new ArrayList<>();

        if(count > DOT_LIMIT) {
            count = DOT_LIMIT;
        }

        int loopCount = 0;
        for(int x = 1; x <= 3; x++) {
            for(int y = 1; y <= 3; y++){
                loopCount++;
                int baseX = Math.round((maxX / 3));
                int xBoundTop = Math.round((maxX / 3) * x);
                int xBoundBottom = Math.round((maxX / 3) * x) - baseX;

                int baseY = Math.round((maxY / 3));
                int yBoundTop = Math.round((maxY / 3) * y);
                int yBoundBottom = Math.round((maxY / 3) * y) - baseY;


                int extras = count % 9;
                int iterations = loopCount <= extras ? (int) Math.ceil((double) count / 9) : (int) count / 9;
                long seed = System.currentTimeMillis() * x * y + iterations * x * y;
                List<Pair<Integer, Integer>> regionValues =  generateRandomPositions(iterations, minSpaceX, minSpaceY, xBoundTop, yBoundTop, xBoundBottom, yBoundBottom, seed);

                for(Pair<Integer, Integer> pair : regionValues) {
                    concatRegions.add(pair);
                }
            }
        }
        return (List<Pair<Integer, Integer>>) Utils.randomiseList(concatRegions);
    }

}

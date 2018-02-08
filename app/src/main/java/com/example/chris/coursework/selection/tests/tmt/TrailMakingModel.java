package com.example.chris.coursework.selection.tests.tmt;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.chris.coursework.common.Pair;
import com.example.chris.coursework.common.Timer;
import com.example.chris.coursework.common.Utils;
import com.example.chris.coursework.common.views.DrawingView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chris on 06/02/2018.
 */

public class TrailMakingModel {

    private TrailMakingPresenter presenter;
    private StateManager stateManager;
    private StateManager.State state;


    private Paint paintSettings;
    private DrawingView drawView;

    private Timer testATimer;
    private Timer testBTimer;


    private final int imageWidth = 100;
    private final int imageHeight = 100;

    private List<Pair<Integer, Integer>> imagePositions;

    public TrailMakingModel(TrailMakingPresenter presenter) {
        this.presenter = presenter;
        this.stateManager = new StateManager(this, this.presenter);
        this.state = StateManager.State.PracticeA;

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

    public StateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
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

    public void runTrailMaking() {
        this.state = this.state.runState();
        if(this.state == StateManager.State.PracticeB) {
            this.state = this.state.runState();
        }
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
        if(this.state == StateManager.State.TimingA || this.state == StateManager.State.TimingB) {
            this.state = this.state.runState();
        }
    }

    public List<Pair<Integer, Integer>> generateRandomPositions(int count, int minSpaceX, int minSpaceY, int maxX, int maxY, int minX, int minY, long randomSeed) {
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

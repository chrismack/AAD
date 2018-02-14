package com.example.chris.coursework.selection.tests.games.tmt;

import com.example.chris.coursework.common.Pair;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chris on 13/02/2018.
 */

public class TrailMakingModelTest {

    private TrailMakingModel model;
    @Mock TrailMakingPresenter mockPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        model = new TrailMakingModel(mockPresenter);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void randomPositionsDontTouch() throws Exception {
        boolean expected = false;
        int count = 5;
        int spacerX = 10;
        int spacerY = 10;
        int maxX = 100;
        int maxY = 100;
        int minX = 0;
        int minY = 0;
        long randomSeed = System.currentTimeMillis();
        List<Pair<Integer, Integer>> location = Whitebox.invokeMethod(model, "generateRandomPositions", count, spacerX, spacerY, maxX, maxY, minX, minY, randomSeed);

        boolean result = false;
        outerloop:
        for(int i = 0; i < location.size(); i++) {
            Pair<Integer, Integer> currentLocation = location.get(i);
            for(int j = 0; j < location.size(); j++) {
                // Do not compare the pair with itself
                if(i != j) {
                    Pair<Integer, Integer> comparePair = location.get(j);

                    if(currentLocation.getFirst() == comparePair.getFirst() ||
                            currentLocation.getLast() == comparePair.getLast()) {
                        result = true;
                        break outerloop;
                    }
                }

            }
        }
        assertEquals(expected, result);
    }

    // Max count 25 based on the number of available images
    @Test
    public void regionTestMorethanMax() throws Exception {
        int expected = 25;

        int count = 50;
        int spacerX = 10;
        int spacerY = 10;
        int maxX = 2000;
        int maxY = 2000;

        int result = model.regionsTest(count, spacerX, spacerY, maxX, maxY).size();

        assertEquals(expected, result);
    }


    @Test
    public void regionTest20() throws Exception {
        int expected = 20;

        int count = 20;
        int spacerX = 10;
        int spacerY = 10;
        int maxX = 2000;
        int maxY = 2000;

        int result = model.regionsTest(count, spacerX, spacerY, maxX, maxY).size();

        assertEquals(expected, result);
    }

    @Test
    public void regionTest1() throws Exception {
        int expected = 1;

        int count = 1;
        int spacerX = 10;
        int spacerY = 10;
        int maxX = 2000;
        int maxY = 2000;

        int result = model.regionsTest(count, spacerX, spacerY, maxX, maxY).size();

        assertEquals(expected, result);
    }

    @Test
    public void regionTestNoPositions() throws Exception {
        int expected = 0;

        int count = 0;
        int spacerX = 10;
        int spacerY = 10;
        int maxX = 2000;
        int maxY = 2000;

        int result = model.regionsTest(count, spacerX, spacerY, maxX, maxY).size();

        assertEquals(expected, result);
    }

}

package com.example.chris.coursework.selection.tests.games.dm;

import com.example.chris.coursework.common.Pair;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;

/**
 * Created by Chris on 13/02/2018.
 */

public class DotMatrixModelTest {

    private DotMatrixModel model;
    @Mock DotMatrixPresenter mockPresenter;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        model = new DotMatrixModel(mockPresenter);
        doReturn(null).when(mockPresenter).getContext();
    }

    @After
    public void tearDown() {
        model = null;
    }

    @Test
    public void scoreArraysInitalised() throws Exception {
        boolean expected = false;

        List<List<Boolean>> section1Touched = model.getSection1Touched();
        List<List<Boolean>> section2Touched = model.getSection2Touched();

        int x1 = Whitebox.getInternalState(model, "section1XDots");
        int y1 = Whitebox.getInternalState(model, "section1YDots");

        int x2 = Whitebox.getInternalState(model, "section2XDots");
        int y2 = Whitebox.getInternalState(model, "section2YDots");

        for(int x = 0; x < x1; x++) {
            for(int y = 0; y < y1; y++) {
                assertEquals(expected, section1Touched.get(x).get(y));
            }
        }

        for(int x = 0; x < x2; x++) {
            for(int y = 0; y < y2; y++) {
                assertEquals(expected, section2Touched.get(x).get(y));
            }
        }
    }

    @Test
    public void afterTimeArraysInitialised() throws Exception {
        boolean expected = false;

        List<List<Boolean>> section1Touched = model.getSection1TouchedAfterTime();
        List<List<Boolean>> section2Touched = model.getSection2TouchedAfterTime();

        int x1 = Whitebox.getInternalState(model, "section1XDots");
        int y1 = Whitebox.getInternalState(model, "section1YDots");

        int x2 = Whitebox.getInternalState(model, "section2XDots");
        int y2 = Whitebox.getInternalState(model, "section2YDots");

        for(int x = 0; x < x1; x++) {
            for(int y = 0; y < y1; y++) {
                assertEquals(expected, section1Touched.get(x).get(y));
            }
        }

        for(int x = 0; x < x2; x++) {
            for(int y = 0; y < y2; y++) {
                assertEquals(expected, section2Touched.get(x).get(y));
            }
        }
    }

    @Test
    public void checkIncorrectCountAllFalse() throws Exception {
        Pair<Integer, Integer> expected = new Pair(0, 76);
        Pair<Integer, Integer> result = Whitebox.invokeMethod(model, "getIncorrectCount");

        assertEquals(expected.getFirst(), result.getFirst());
        assertEquals(expected.getLast(), result.getLast());
    }

    @Test
    public void checkIncorrectCountAllTrue() throws Exception {
        Pair<Integer, Integer> expected = new Pair(424, 0);

        List<List<Boolean>> section1Touched = model.getSection1TouchedAfterTime();
        List<List<Boolean>> section2Touched = model.getSection2TouchedAfterTime();

        int x1 = Whitebox.getInternalState(model, "section1XDots");
        int y1 = Whitebox.getInternalState(model, "section1YDots");

        int x2 = Whitebox.getInternalState(model, "section2XDots");
        int y2 = Whitebox.getInternalState(model, "section2YDots");

        for(int x = 0; x < x1; x++) {
            for(int y = 0; y < y1; y++) {
                section1Touched.get(x).set(y, true);
            }
        }

        for(int x = 0; x < x2; x++) {
            for(int y = 0; y < y2; y++) {
                section2Touched.get(x).set(y, true);
            }
        }

        Whitebox.setInternalState(model, "section1Touched", section1Touched);
        Whitebox.setInternalState(model, "section2Touched", section2Touched);

        Pair<Integer, Integer> result = Whitebox.invokeMethod(model, "getIncorrectCount");

        // Reset touched state
        for(int x = 0; x < x1; x++) {
            for(int y = 0; y < y1; y++) {
                section1Touched.get(x).set(y, false);
            }
        }

        for(int x = 0; x < x2; x++) {
            for(int y = 0; y < y2; y++) {
                section2Touched.get(x).set(y, false);
            }
        }

        Whitebox.setInternalState(model, "section1Touched", section1Touched);
        Whitebox.setInternalState(model, "section2Touched", section2Touched);

        assertEquals(expected.getFirst(), result.getFirst());
        assertEquals(expected.getLast(), result.getLast());
    }

    @Test
    public void checkUpdateCoordinates() throws Exception {
        int activeSection = 1;
        float sec1GroupWidth = Whitebox.getInternalState(model, "sec1GroupWidth");
        float sec1GroupHeight = Whitebox.getInternalState(model, "sec1GroupHeight");

        int x1 = Whitebox.getInternalState(model, "section1XDots");
        int y1 = Whitebox.getInternalState(model, "section1YDots");

        boolean expected = true;

        for(int i = 0; i < x1; i++) {
            for(int j = 0; j < y1; j++) {
                model.updateAnswerSet(activeSection, i * sec1GroupWidth, j * sec1GroupHeight);
                assertEquals(expected, model.getSection1Touched().get(i).get(j));
            }
        }

    }

}

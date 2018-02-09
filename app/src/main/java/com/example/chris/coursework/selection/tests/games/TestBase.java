package com.example.chris.coursework.selection.tests.games;

import android.content.Context;
import android.content.Intent;

import com.example.chris.coursework.selection.tests.TestSelectionView;
import com.example.chris.coursework.selection.tests.games.tmt.StateManager;

/**
 * Created by Chris on 08/02/2018.
 */

public abstract class TestBase {

    Class<? extends IStateManager> stateManager;

    Enum<? extends IState> state;

    public TestBase() {
    }

    public Class<? extends IStateManager> getStateManager() {
        return stateManager;
    }

    public void setStateManager(Class<? extends IStateManager> stateManager) {
        this.stateManager = stateManager;
    }

    public Enum<? extends IState> getState() {
        return state;
    }

    public void setState(Enum<? extends IState> state) {
        this.state = state;
    }

    public void runTest() {
        setState((Enum<? extends IState>) ((IState)getState()).runState());
    }

    public void finishTest(Context packageContext) {
        Intent intent = new Intent(packageContext, TestSelectionView.class);
        packageContext.startActivity(intent);
    }
}

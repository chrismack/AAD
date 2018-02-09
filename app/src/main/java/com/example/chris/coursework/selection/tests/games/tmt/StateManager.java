package com.example.chris.coursework.selection.tests.games.tmt;

import com.example.chris.coursework.selection.tests.TestSelectionView;
import com.example.chris.coursework.selection.tests.games.IState;
import com.example.chris.coursework.selection.tests.games.IStateManager;

/**
 * Created by Chris on 06/02/2018.
 */

public class StateManager implements IStateManager{

    private static TrailMakingModel model;
    private static TrailMakingPresenter presenter;


    public StateManager(TrailMakingModel model, TrailMakingPresenter presenter) {
        this.model = model;
        this.presenter = presenter;
    }

    public enum State implements IState {
        PracticeA {
            @Override
            public State runState() {
                presenter.buildTestArea(10, false);
                return ReadyA;
            }
        },
        ReadyA {
            @Override
            public State runState() {
                presenter.buildTestArea(25, false);
                presenter.disableFinish();
                return TimingA;
            }
        },
        TimingA {
            @Override
            public State runState() {
                model.getTestATimer().startTimer(System.currentTimeMillis());
                presenter.enableFinish();
                return FinsiedA;
            }
        },
        FinsiedA {
            @Override
            public State runState() {
                model.getTestATimer().endTimer(System.currentTimeMillis());
                return PracticeB;
            }
        },
        PracticeB {
            @Override
            public State runState() {
                presenter.buildTestArea(10, true);
                return ReadyB;
            }
        },
        ReadyB {
            @Override
            public State runState() {
                presenter.buildTestArea(25, true);
                presenter.disableFinish();
                return TimingB;
            }
        },
        TimingB {
            @Override
            public State runState() {
                model.getTestBTimer().startTimer(System.currentTimeMillis());
                presenter.enableFinish();
                return FinsiedB;
            }
        },
        FinsiedB {
            @Override
            public State runState() {
                model.getTestBTimer().endTimer(System.currentTimeMillis());
                model.finishTest(presenter.getContext());
                return null;
            }
        };

        @Override
        public abstract Enum<State> runState();
    }


}

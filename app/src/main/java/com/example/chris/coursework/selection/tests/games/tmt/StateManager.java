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
                presenter.buildTestArea(8, false);
                return ReadyA;
            }

            @Override
            public String getName() {
                return "Practice A";
            }
        },
        ReadyA {
            @Override
            public State runState() {
                presenter.buildTestArea(25, false);
                presenter.disableFinish();
                return TimingA;
            }

            @Override
            public String getName() {
                return "Ready A";
            }
        },
        TimingA {
            @Override
            public State runState() {
                model.getTestATimer().startTimer(System.currentTimeMillis());
                presenter.enableFinish();
                return FinsiedA;
            }

            @Override
            public String getName() {
                return "Timing A";
            }
        },
        FinsiedA {
            @Override
            public State runState() {
                model.getTestATimer().endTimer(System.currentTimeMillis());
                return PracticeB;
            }

            @Override
            public String getName() {
                return "Finished A";
            }
        },
        PracticeB {
            @Override
            public State runState() {
                presenter.buildTestArea(8, true);
                return ReadyB;
            }

            @Override
            public String getName() {
                return "Practice B";
            }
        },
        ReadyB {
            @Override
            public State runState() {
                presenter.buildTestArea(25, true);
                presenter.disableFinish();
                return TimingB;
            }

            @Override
            public String getName() {
                return "Ready B";
            }

        },
        TimingB {
            @Override
            public State runState() {
                model.getTestBTimer().startTimer(System.currentTimeMillis());
                presenter.enableFinish();
                return FinsiedB;
            }

            @Override
            public String getName() {
                return "Timing B";
            }
        },
        FinsiedB {
            @Override
            public State runState() {
                model.getTestBTimer().endTimer(System.currentTimeMillis());
                model.finishTest(presenter.getContext());
                return null;
            }

            @Override
            public String getName() {
                return "Finished B";
            }
        };
    }


}

package com.example.chris.coursework.selection.tests.tmt;

/**
 * Created by Chris on 06/02/2018.
 */

public class StateManager {

    private static TrailMakingModel model;
    private static TrailMakingPresenter presenter;

    public StateManager(TrailMakingModel model, TrailMakingPresenter presenter) {
        this.model = model;
        this.presenter = presenter;
    }

    public enum State {
        PracticeA {
            @Override
            State runState() {
                presenter.buildTestArea(10, false);
                return ReadyA;
            }
        },
        ReadyA {
            @Override
            State runState() {
                presenter.buildTestArea(25, false);
                presenter.disableFinish();
                return TimingA;
            }
        },
        TimingA {
            @Override
            State runState() {
                model.getTestATimer().startTimer(System.currentTimeMillis());
                presenter.enableFinish();
                return FinsiedA;
            }
        },
        FinsiedA {
            @Override
            State runState() {
                model.getTestATimer().endTimer(System.currentTimeMillis());
                return PracticeB;
            }
        },
        PracticeB {
            @Override
            State runState() {
                presenter.buildTestArea(10, true);
                return ReadyB;
            }
        },
        ReadyB {
            @Override
            State runState() {
                presenter.buildTestArea(25, true);
                presenter.disableFinish();
                return TimingB;
            }
        },
        TimingB {
            @Override
            State runState() {
                model.getTestBTimer().startTimer(System.currentTimeMillis());
                presenter.enableFinish();
                return FinsiedB;
            }
        },
        FinsiedB {
            @Override
            State runState() {
                model.getTestBTimer().endTimer(System.currentTimeMillis());
                presenter.finishTest();
                return null;
            }
        };

        abstract State runState();
    }
}

package com.example.chris.coursework.selection.tests.games.dm;

import com.example.chris.coursework.selection.tests.games.IState;
import com.example.chris.coursework.selection.tests.games.IStateManager;

/**
 * Created by Chris on 08/02/2018.
 */

public class StateManager implements IStateManager {

    private static DotMatrixModel model;
    private static DotMatrixPresenter presenter;

    public StateManager(DotMatrixModel model, DotMatrixPresenter presenter) {
        this.model = model;
        this.presenter = presenter;
    }

    enum State implements IState {
        Practice {
            @Override
            public Enum<?> runState() {
                return Timing;
            }
        },
        Timing {
            @Override
            public Enum<?> runState() {
                model.getTimer().start();
                model.setTimerRan(true);
                return TimeOver;
            }
        },
        TimeOver {
            @Override
            public Enum<?> runState() {
                return Finish;
            }
        },
        Finish {
            @Override
            public Enum<?> runState() {
                presenter.showReviewing();
                return Reviewing;
            }
        },
        Reviewing {
            @Override
            public Enum<?> runState() {
                model.finishTest(presenter.getContext());
                return null;
            }
        };

        @Override
        public Enum<?> runState() {
            return null;
        }
    }
}

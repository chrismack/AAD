package com.example.chris.coursework.login;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chris on 01/02/2018.
 */

public interface LoginContract {

    interface ILoginPresenter {
        boolean onPasswordChange(TextView textView, int id, KeyEvent keyEvent, String username, String password);
        boolean attemptLogin(String username, String password);
        void setLoginError(String message);
        void showSetup();
    }

    interface ILoginView {
        void onLogin(View view);
        void setLoginError(String error);
    }
}

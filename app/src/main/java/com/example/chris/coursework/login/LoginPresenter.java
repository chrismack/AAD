package com.example.chris.coursework.login;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

/**
 * Created by Chris on 01/02/2018.
 */

public class LoginPresenter implements LoginContract.ILoginPresenter{

    private LoginView view;

    public LoginPresenter(LoginContract.ILoginView view) {
        this.view = (LoginView) view;
    }

    @Override
    public boolean onPasswordChange(TextView textView, int id, KeyEvent keyEvent) {
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            attemptLogin();
            return true;
        }
        return false;
    }

    @Override
    public void attemptLogin() {

    }

}

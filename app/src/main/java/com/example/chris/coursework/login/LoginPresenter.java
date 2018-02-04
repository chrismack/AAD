package com.example.chris.coursework.login;

import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.chris.coursework.data.DAO;
import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.data.entities.Therapist;

/**
 * Created by Chris on 01/02/2018.
 */

public class LoginPresenter implements LoginContract.ILoginPresenter{



    private LoginView view;
    private LoginModel model;
    public LoginPresenter(LoginContract.ILoginView view) {
        this.view = (LoginView) view;
        this.model = new LoginModel(this);
    }

    @Override
    public boolean onPasswordChange(TextView textView, int id, KeyEvent keyEvent, String username, String password) {
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            attemptLogin(username, password);
            return true;
        }
        return false;
    }

    @Override
    public boolean attemptLogin(String username, String password) {
        if(!username.equals("") && !(password).equals("")) {
            if(this.model.verifyPassword(username, password)) {
                //Todo: set intent
                System.out.println("LOG IN");
            } else {
                setLoginError("Incorrect login details");
            }
        }
        return false;
    }

    @Override
    public void setLoginError(String message) {
        this.view.setLoginError(message);
    }

    public LoginView getView() {
        return view;
    }

    public void setView(LoginView view) {
        this.view = view;
    }

}

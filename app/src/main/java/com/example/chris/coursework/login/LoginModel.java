package com.example.chris.coursework.login;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.data.DAO;
import com.example.chris.coursework.data.entities.Therapist;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.interfaces.PBEKey;

/**
 * Created by Chris on 04/02/2018.
 */

public class LoginModel {

    private LoginPresenter presenter;

    public LoginModel(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    public Therapist verifyPassword(String email, String password) {
        DAO dao = MainModel.getInstance(presenter.getView()).getDAO();

        if(!dao.checkForTherapist("foo@example.com")) {
            dao.tmpInsertLogin();
        }

        Therapist therapist = dao.getTherapist(email);

        if(therapist != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(therapist.getSalt().getBytes());
                byte[] bytes = md.digest(password.getBytes());

                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }

                String storedPassword = sb.toString();
                System.out.println(sb.toString());
                if(therapist.getPassword().equals(storedPassword)) {
                    return therapist;
                } else {
                    return null;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

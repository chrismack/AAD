package com.example.chris.coursework;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.chris.coursework.common.Utils;
import com.example.chris.coursework.data.DAO;
import com.example.chris.coursework.data.entities.Attending;
import com.example.chris.coursework.data.entities.Patient;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.data.entities.Therapist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Chris on 03/02/2018.
 */

public class MainModel {
    private static MainModel instance = null;

    private DAO database = null;
    private static Context context;
    private static AppCompatActivity view;

    private Therapist therapist;
    private Patient patient;
    private Session session;
    private Attending attending;

    private boolean recordMotions = true;

    public MainModel(AppCompatActivity view) {
        this.view = view;
        if (view != null) {
            this.context = view.getApplicationContext();
        }
        database = new DAO(view);
    }

    public static MainModel getInstance(AppCompatActivity lview) {
        if (instance == null) {
            instance = new MainModel(lview);
        } else {
            view = lview;
            if (lview != null) {
                context = view.getApplicationContext();
            }
        }
        return instance;
    }

    public DAO getDAO() {
        return new DAO(this.view);
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public void setCurrentPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getCurrentPatient() {
        return this.patient;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getCurrentSession() {
        return this.session;
    }

    public Attending getAttending() {
        return attending;
    }

    public void setAttending(Attending attending) {
        this.attending = attending;
    }

    public Session updateSession(Session session) {
        return this.getDAO().updateSession(session);
    }

    public boolean isRecordMotions() {
        return recordMotions;
    }

    public void setRecordMotions(boolean recordMotions) {
        this.recordMotions = recordMotions;
    }

    public void writeTest(String fileName, List<String> messages) {
        if (recordMotions) {

            if (Build.VERSION.SDK_INT >= 23) {
                if (context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.v(TAG, "Permission is granted");

                } else {

                    Log.v(TAG, "Permission is revoked");
                    ActivityCompat.requestPermissions(view, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                }
            } else { //permission is automatically granted on sdk<23 upon installation
                Log.v(TAG, "Permission is granted");
            }

            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File dir = new File(root, "/aad_tests");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, fileName);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream f = new FileOutputStream(file, true);
                PrintWriter pw = new PrintWriter(f);
                for (String str : messages) {
                    pw.write(str + "\n");
                }
                pw.flush();
                pw.close();
                f.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.i(TAG, "******* File not found. Did you" +
                        " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}

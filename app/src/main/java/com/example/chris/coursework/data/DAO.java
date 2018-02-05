package com.example.chris.coursework.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chris.coursework.data.entities.Patient;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.data.entities.Therapist;

import java.text.SimpleDateFormat;

/**
 * Created by Chris on 01/02/2018.
 */

//Singleton
public class DAO {

    private DatabaseSchema schema;
    private Context context;

    public DAO(Context context) {
        this.context = context;
        this.schema = new DatabaseSchema(context);
    }

    public SQLiteDatabase getReadDatabase() {
        return schema.getReadableDatabase();
    }

    public SQLiteDatabase getWriteDatabase() {
        return schema.getWritableDatabase();
    }

    public Therapist getTherapist(String email) {
        SQLiteDatabase db = getReadDatabase();

        String whereClause = "email = ?";
        String[] whereArg = new String[] {email};
        Cursor cursor = db.query(
                DatabaseSchema.THERAPIST,
                null,
                whereClause,
                whereArg,
                null,
                null,
                null
                );

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            Therapist therapist = new Therapist();

                therapist.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
                therapist.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow("firstName")));
                therapist.setLastName(cursor.getString(cursor.getColumnIndexOrThrow("lastName")));
                therapist.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
                therapist.setSalt(cursor.getString(cursor.getColumnIndexOrThrow("salt")));

                cursor.close();
                db.close();
            return therapist;
        } else {
            return null;
        }
    }

    public Patient insertPatient(Patient patient) {
        SQLiteDatabase db = getWriteDatabase();
        ContentValues cv = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dob = sdf.format(patient.getDob().getTime());

        cv.put("firstname", patient.getFirstName());
        cv.put("lastname", patient.getLastName());
        cv.put("otherNames", patient.getOtherNames());
        cv.put("dob", dob);
        cv.put("address1", patient.getAddress1());
        cv.put("address2", patient.getAddress2());
        cv.put("city", patient.getCity());
        cv.put("postcode", patient.getPostcode());
        cv.put("nshNumber", patient.getNhsNumber());
        long id = db.insert(
                DatabaseSchema.PATIENT,
                null,
                cv
        );

        patient.setId((int) id);

        db.close();
        return patient;
    }

    public Session createSession(Session session) {

        return null;
    }

    public Session updateSession(Session session) {
        return null;
    }

    public void tmpAllPatients() {
        SQLiteDatabase db = getReadDatabase();
        Cursor c = db.query(DatabaseSchema.PATIENT,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(c != null && c.getCount() > 0) {
            c.moveToFirst();
            System.out.println(c.getInt(c.getColumnIndexOrThrow("id")));
            System.out.println(c.getString(c.getColumnIndexOrThrow("firstName")));
        }
    }

    public boolean checkForTherapist(String email) {
        SQLiteDatabase db = getReadDatabase();
        String whereClause = "email = ?";
        String[] whereArg = new String[] {email};
        Cursor c = db.query(
                DatabaseSchema.THERAPIST,
                null,
                whereClause,
                whereArg,
                null,
                null,
                null
                );

        return (c != null && c.getCount() > 0);

    }

    public void tmpInsertLogin() {
        SQLiteDatabase db = getWriteDatabase();
        ContentValues cv = new ContentValues();

        cv.put("email", "foo@example.com");
        cv.put("firstName", "Chris");
        cv.put("lastName", "Mack");
        cv.put("password", "c729eaf54df19d13da8a6b99c6c977e5e804f60f398f36f5efae3031d15b6a23");
        cv.put("salt", "1");

        long id = db.insert(
                DatabaseSchema.THERAPIST,
                null,
                cv
        );

        db.close();
    }


}

package com.example.chris.coursework.data;


import android.content.ContentValues;
import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.chris.coursework.common.Utils;
import com.example.chris.coursework.data.entities.Attending;
import com.example.chris.coursework.data.entities.Patient;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.data.entities.Therapist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public List<Patient> getAllPatients() {
        SQLiteDatabase db = getReadDatabase();
        List<Patient> patients = new ArrayList<>();

        Cursor c = db.query(
                DatabaseSchema.PATIENT,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(c != null && c.getCount() > 0) {
            c.moveToFirst();
            Patient patient;
            while(!c.isAfterLast()) {
                patient = new Patient();
                patient.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                patient.setFirstName(c.getString(c.getColumnIndexOrThrow("firstName")));
                patient.setLastName(c.getString(c.getColumnIndexOrThrow("lastName")));
                patient.setOtherNames(c.getString(c.getColumnIndexOrThrow("otherNames")));
                patient.setDob(c.getString(c.getColumnIndexOrThrow("dob")));
                patient.setAddress1(c.getString(c.getColumnIndexOrThrow("address1")));
                patient.setAddress2(c.getString(c.getColumnIndexOrThrow("address2")));
                patient.setCity(c.getString(c.getColumnIndexOrThrow("city")));
                patient.setPostcode(c.getString(c.getColumnIndexOrThrow("postcode")));
                patient.setNhsNumber(c.getString(c.getColumnIndexOrThrow("nshNumber")));

                patients.add(patient);
                c.moveToNext();
            }
        }

        c.close();
        db.close();

        return patients;
    }

    public List<Session> getAllPatientSessions(int patientId) {
        List<Session> sessions = new ArrayList<>();

        SQLiteDatabase db = getReadDatabase();
        String[] args = new String[] {String.valueOf(patientId)};
        String sql = "SELECT * FROM session " +
                "WHERE session.sessionId in " +
                "(SELECT session_id FROM attending WHERE patient_id = ?)";
        Cursor c = db.rawQuery(sql, args);
        if(c != null && c.getCount() > 0) {
            c.moveToFirst();
            Session session;
            while(!c.isAfterLast()) {
                session = new Session();

                session.setSessionId(c.getInt(c.getColumnIndexOrThrow("sessionId")));
                session.setDm_timeTaken(c.getInt(c.getColumnIndexOrThrow("dm_timeTaken")));
                session.setDm_falsePos(c.getInt(c.getColumnIndexOrThrow("dm_falsePos")));
                session.setDm_trueNeg(c.getInt(c.getColumnIndexOrThrow("dm_trueNeg")));
                session.setSmd_timeTaken(c.getInt(c.getColumnIndexOrThrow("smd_timeTaken")));
                session.setSmd_correctCars(c.getInt(c.getColumnIndexOrThrow("smd_correctCars")));
                session.setSmd_correctLorries(c.getInt(c.getColumnIndexOrThrow("smd_correctLorries")));
                session.setSmc_timeTaken(c.getInt(c.getColumnIndexOrThrow("smc_timeTaken")));
                session.setSmc_redCars(c.getInt(c.getColumnIndexOrThrow("smc_redCars")));
                session.setSmc_blueCars(c.getInt(c.getColumnIndexOrThrow("smc_blueCars")));
                session.setRsr_timeTaken(c.getInt(c.getColumnIndexOrThrow("rsr_timeTaken")));
                session.setRsr_correctSigns(c.getInt(c.getColumnIndexOrThrow("rsr_correctSigns")));
                session.setTmt_timeTakenA(c.getInt(c.getColumnIndexOrThrow("tmt_timeTakenA")));
                session.setTmt_timeTakenB(c.getInt(c.getColumnIndexOrThrow("tmt_timeTakenB")));

                session.setCreationDate(Utils.createCalendarFrom(c.getString(c.getColumnIndexOrThrow("creationDate"))).getTime());
                session.setLastAttemptDate(Utils.createCalendarFrom(c.getString(c.getColumnIndexOrThrow("lastAttempt"))).getTime());

                sessions.add(session);
                c.moveToNext();
            }
        }

        c.close();
        db.close();
        return sessions;
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

    private ContentValues setSessionContentValues(Session session) {
        Calendar cal = Calendar.getInstance();
        if(session.getCreationDate() != null) {
            cal.setTime(session.getCreationDate());
        }
        String creation = Utils.calendarToString(cal);
        if(session.getLastAttemptDate() != null) {
            cal.setTime(session.getLastAttemptDate());
        }
        String lastAttempt = Utils.calendarToString(cal);

        ContentValues cv = new ContentValues();
        cv.put("dm_timeTaken", session.getDm_timeTaken());
        cv.put("dm_falsePos", session.getDm_falsePos());
        cv.put("dm_trueNeg", session.getDm_trueNeg());
        cv.put("smd_timeTaken", session.getSmd_timeTaken());
        cv.put("smd_correctCars", session.getSmd_correctCars());
        cv.put("smd_correctLorries", session.getSmd_correctLorries());
        cv.put("smc_timeTaken", session.getSmc_timeTaken());
        cv.put("smc_redCars", session.getSmc_redCars());
        cv.put("smc_blueCars", session.getSmc_blueCars());
        cv.put("rsr_timeTaken", session.getRsr_timeTaken());
        cv.put("rsr_correctSigns", session.getRsr_correctSigns());
        cv.put("tmt_timeTakenA", session.getTmt_timeTakenA());
        cv.put("tmt_timeTakenB", session.getTmt_timeTakenB());
        cv.put("creationDate", creation);
        cv.put("lastAttempt", lastAttempt);

        return cv;
    }

    public Session createSession(Session session) {
        SQLiteDatabase db = getWriteDatabase();

        ContentValues cv = setSessionContentValues(session);

        long id = db.insert(
                DatabaseSchema.SESSION,
                null,
                cv
        );

        session.setSessionId((int) id);
        return session;
    }

    public Session updateSession(Session session) {
        SQLiteDatabase db = getWriteDatabase();

        ContentValues cv = setSessionContentValues(session);
        cv.put("sessionId", session.getSessionId());

        String whereClause = "sessionId = ?";
        String[] whereArg = new String[] {String.valueOf(session.getSessionId())};

        long id = db.update(
                DatabaseSchema.SESSION,
                cv,
                whereClause,
                whereArg
        );

        session.setSessionId((int) id);
        return session;
    }

    public Attending createNewMeeting(Attending attending) {
        SQLiteDatabase db = getWriteDatabase();
        ContentValues cv = new ContentValues();
        cv.put("therapist_email", attending.getTherapist().getEmail());
        cv.put("patient_id", attending.getPatient().getId());
        cv.put("session_id", attending.getSession().getSessionId());

        db.insert(
                DatabaseSchema.ATTENDING,
                null,
                cv
        );

        return attending;
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


    public boolean attendingExists(Attending attending) {
        SQLiteDatabase db = getReadDatabase();

        String whereClause = "therapist_email = ? AND patient_id = ? AND session_id = ?";
        String[] whereArg = new String[] {
                attending.getTherapist().getEmail(),
                String.valueOf(attending.getPatient().getId()),
                String.valueOf(attending.getSession().getSessionId())
        };
        Cursor c = db.query(
                DatabaseSchema.ATTENDING,
                null,
                whereClause,
                whereArg,
                null,
                null,
                null
        );

        return (c != null && c.getCount() > 0);
    }
}

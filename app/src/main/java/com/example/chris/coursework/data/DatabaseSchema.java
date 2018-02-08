package com.example.chris.coursework.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by Chris on 03/02/2018.
 */

public class DatabaseSchema extends SQLiteOpenHelper {


    private static final String DB_NAME = "aad_medical.db";
    private static final int DB_VERSION = 1;

    //Tables
    public static final String THERAPIST = "therapist";
    public static final String PATIENT = "patient";
    public static final String ATTENDING = "attending";
    public static final String SESSION = "session";

    private static final String COMMA_SPACE = ", ";
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String PRIMARY_KEY = "PRIMARY KEY ";
    private static final String FOREIGN_KEY = "FOREIGN KEY ";
    private static final String REFERENCES = " REFERENCES ";
    private static final String UNIQUE = "UNIQUE ";
    private static final String TYPE_TEXT = " TEXT ";
    private static final String TYPE_DATE = " DATETIME ";
    private static final String TYPE_INT = " INTEGER ";
    private static final String DEFAULT = "DEFAULT ";
    private static final String AUTOINCREMENT = "AUTOINCREMENT ";
    private static final String NOT_NULL = "NOT NULL ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    private static final String CREATE_THERAPIST =
            CREATE_TABLE + THERAPIST + " ( " +
                    "email" + TYPE_TEXT + NOT_NULL + PRIMARY_KEY + COMMA_SPACE +
                    "firstName" + TYPE_TEXT + COMMA_SPACE +
                    "lastName" + TYPE_TEXT + COMMA_SPACE +
                    "password" + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
                    "salt" + TYPE_TEXT +
                    ")";

    private static final String CREATE_PATIENT =
            CREATE_TABLE + PATIENT + " ( " +
                    "id" + TYPE_INT + NOT_NULL + PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE +
                    "firstName" + TYPE_TEXT + COMMA_SPACE +
                    "lastName" + TYPE_TEXT + COMMA_SPACE +
                    "otherNames" + TYPE_TEXT + COMMA_SPACE +
                    "dob" + TYPE_DATE + COMMA_SPACE +
                    "address1" + TYPE_TEXT + COMMA_SPACE +
                    "address2" + TYPE_TEXT + COMMA_SPACE +
                    "city" + TYPE_TEXT + COMMA_SPACE +
                    "postcode" + TYPE_TEXT + COMMA_SPACE +
                    "nshNumber" + TYPE_TEXT +
                    ")";

    private static final String CREATE_SESSION =
            CREATE_TABLE + SESSION + " ( " +
                    "sessionId" + TYPE_INT + NOT_NULL + PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE +
                    "dm_timeTaken" + TYPE_INT + COMMA_SPACE +
                    "dm_falsePos" + TYPE_INT + COMMA_SPACE +
                    "dm_trueNeg" + TYPE_INT + COMMA_SPACE +
                    "smd_timeTaken" + TYPE_INT + COMMA_SPACE +
                    "smd_correctCars" + TYPE_INT + COMMA_SPACE +
                    "smd_correctLorries" + TYPE_INT + COMMA_SPACE +
                    "smc_timeTaken" + TYPE_INT + COMMA_SPACE +
                    "smc_redCars" + TYPE_INT + COMMA_SPACE +
                    "smc_blueCars" + TYPE_INT + COMMA_SPACE +
                    "rsr_timeTaken" + TYPE_INT + COMMA_SPACE +
                    "rsr_correctSigns" + TYPE_INT + COMMA_SPACE +
                    "tmt_timeTakenA" + TYPE_INT  + COMMA_SPACE +
                    "tmt_timeTakenB" + TYPE_INT  + COMMA_SPACE +
                    "creationDate" + TYPE_DATE + COMMA_SPACE +
                    "lastAttempt" + TYPE_DATE +
                    ")";

    private static final String CREATE_ATTENDING =
            CREATE_TABLE + ATTENDING + "(" +
                    "therapist_email" + TYPE_TEXT + NOT_NULL  + COMMA_SPACE +
                    "patient_id" + TYPE_INT + NOT_NULL + COMMA_SPACE +
                    "session_id" + TYPE_INT + NOT_NULL + COMMA_SPACE +
                    PRIMARY_KEY + "(therapist_email, patient_id, session_id)" + COMMA_SPACE +
                    FOREIGN_KEY + "(therapist_email)" + REFERENCES + THERAPIST + "(email)" + COMMA_SPACE +
                    FOREIGN_KEY + "(patient_id)" + REFERENCES + PATIENT + "(id)" + COMMA_SPACE +
                    FOREIGN_KEY + "(session_id)" + REFERENCES + SESSION + "(sessionId)" +
                    ")";


    public DatabaseSchema(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_THERAPIST);
        db.execSQL(CREATE_PATIENT);
        db.execSQL(CREATE_SESSION);
        db.execSQL(CREATE_ATTENDING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}

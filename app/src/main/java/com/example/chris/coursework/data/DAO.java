package com.example.chris.coursework.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chris.coursework.data.entities.Therapist;

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


}

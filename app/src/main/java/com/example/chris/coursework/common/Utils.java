package com.example.chris.coursework.common;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.view.View;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.util.Collections.*;

/**
 * Created by Chris on 04/02/2018.
 */

public class Utils {

    public static Calendar datepickerToCalendar(DatePicker picker) {
        int day = picker.getDayOfMonth();
        int month = picker.getMonth();
        int year = picker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar;
    }

    public static Calendar createCalendarFrom(String str) {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(str);
            cal.setTime(date);
        } catch (ParseException e) {
            // Most likely a malformed date format
            return null;
        }
        return cal;
    }

    public static String calendarToString(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(cal.getTime());
        return date;
    }

    public static String stringFrom(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        return sdf.format(date);
    }

    public static List<?> randomiseList(List<?> list) {
        Collections.shuffle(list);
        return list;
    }

    public static boolean externalStorageWriteable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean externalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}

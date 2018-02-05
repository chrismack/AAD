package com.example.chris.coursework.common;

import android.widget.DatePicker;

import java.util.Calendar;

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
}

package com.bignerdranch.android.todo;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DaysController {
    private static final String TAG = "DaysController";

    private Day[] mDays;
    private static DaysController sDaysController;

    private DaysController() {
        mDays = createWeek();
    }

    public static DaysController getInstance() {
        if (sDaysController == null) {
            sDaysController = new DaysController();
        }

        return sDaysController;
    }

    public Day getDay(int position) {
        Log.i(TAG, "What's wrong?");
        return mDays[position];
    }

    public Day[] getDays() {
        return mDays;
    }

    private Day[] createWeek() {
        Day[] week = new Day[7];
        int numberOfTheDay = 0;
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                numberOfTheDay = 0;
                break;
            case Calendar.MONDAY:
                numberOfTheDay = 1;
                break;
            case Calendar.TUESDAY:
                numberOfTheDay = 2;
                break;
            case Calendar.WEDNESDAY:
                numberOfTheDay = 3;
                break;
            case Calendar.THURSDAY:
                numberOfTheDay = 4;
                break;
            case Calendar.FRIDAY:
                numberOfTheDay = 5;
                break;
            case Calendar.SATURDAY:
                numberOfTheDay = 6;
        }

        for (int i = 0; i < 7; i++) {
            week[i] = new Day();
            switch (numberOfTheDay) {
                case 0:
                    week[i].setDayOfTheWeek("Sunday");
                    break;
                case 1:
                    week[i].setDayOfTheWeek("Monday");
                    break;
                case 2:
                    week[i].setDayOfTheWeek("Tuesday");
                    break;
                case 3:
                    week[i].setDayOfTheWeek("Wednesday");
                    break;
                case 4:
                    week[i].setDayOfTheWeek("Thursday");
                    break;
                case 5:
                    week[i].setDayOfTheWeek("Friday");
                    break;
                case 6:
                    week[i].setDayOfTheWeek("Saturday");
            }
            numberOfTheDay++;
            numberOfTheDay %= 7;

            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, i);
            String currentDateString = sdf.format(calendar.getTime());
            week[i].setDateOfTheWeek(currentDateString);
        }

        return week;
    }
}
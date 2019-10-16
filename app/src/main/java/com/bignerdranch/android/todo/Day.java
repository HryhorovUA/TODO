package com.bignerdranch.android.todo;

import android.util.Log;

import java.util.ArrayList;

public class Day {
    private static String TAG = "Day";
    private String dayOfTheWeek;

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public static Day[] createWeek() {
        Log.i(TAG, "First log DAY.java");

        Day[] week = new Day[7];

        Log.i(TAG, "Second log DAY.java");

        week[0] = new Day();
        week[0].setDayOfTheWeek("Sunday");
        week[1] = new Day();
        week[1].setDayOfTheWeek("Monday");
        week[2] = new Day();
        week[2].setDayOfTheWeek("Tuesday");
        week[3] = new Day();
        week[3].setDayOfTheWeek("Wednesday");
        week[4] = new Day();
        week[4].setDayOfTheWeek("Thursday");
        week[5] = new Day();
        week[5].setDayOfTheWeek("Friday");
        week[6] = new Day();
        week[6].setDayOfTheWeek("Saturday");

        Log.i(TAG, "Last log DAY.java");

        return week;
    }
}

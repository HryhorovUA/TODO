package com.bignerdranch.android.todo;

import android.util.Log;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Day implements Serializable {
    private static String TAG = "Day";
    private String dayOfTheWeek;
    private String dateOfTheWeek;
    private static ArrayList<SimpleNotice> noticeList = new ArrayList<SimpleNotice>();

    public String getDateOfTheWeek() {
        return dateOfTheWeek;
    }

    public void setDateOfTheWeek(String dateOfTheWeek) {
        this.dateOfTheWeek = dateOfTheWeek;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public static void addNotice(String notice) {
        SimpleNotice simpleNotice = new SimpleNotice(notice, "2018-12-12");
        noticeList.add(simpleNotice);
    }

    public static ArrayList<SimpleNotice> getListNotice() {
        return noticeList;
    }

    public static Day[] createWeek() {
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

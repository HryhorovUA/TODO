package com.bignerdranch.android.todo;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class Day implements Serializable {
    private static final String TAG = "Day";
    private String dayOfTheWeek;
    private String dateOfTheWeek;

    private ArrayList<SimpleNotice> noticeList = new ArrayList<>();

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

    public void deleteNotice(int position) {
        Log.i(TAG, Integer.toString(noticeList.size()));
        noticeList.remove(position);
        Log.i(TAG, Integer.toString(noticeList.size()));
    }

    public void addNotice(String notice) {
        Log.i(TAG, "HellDay-0");
        SimpleNotice simpleNotice = new SimpleNotice(notice, "2018-12-12");
        Log.i(TAG, "HellDay-1");
        noticeList.add(simpleNotice);
        Log.i(TAG, "HellDay-2");
    }

    public ArrayList<SimpleNotice> getListNotice() {
        return noticeList;
    }

}

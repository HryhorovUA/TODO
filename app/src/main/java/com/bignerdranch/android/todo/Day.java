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

    public void addNotice(String notice, int hour, int minutes) {
        String date = Integer.toString(hour) + ":" + Integer.toString(minutes);
        SimpleNotice simpleNotice = new SimpleNotice(notice, date);
        simpleNotice.setHour(hour);
        simpleNotice.setMinute(minutes);
        noticeList.add(simpleNotice);
    }

    public ArrayList<SimpleNotice> getListNotice() {
        return noticeList;
    }

    public void sort() {
        int size = noticeList.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                SimpleNotice noticeI = noticeList.get(i);
                SimpleNotice noticeJ = noticeList.get(j);
                int hourI = noticeI.getHour();
                int minuteI = noticeI.getMinute();
                int hourJ = noticeJ.getHour();
                int minuteJ = noticeJ.getMinute();
                if (hourI > hourJ) {
                    SimpleNotice timeNotice = noticeList.get(i);
                    noticeList.set(i, noticeList.get(j));
                    noticeList.set(j, timeNotice);
                }
            }
        }
    }

}


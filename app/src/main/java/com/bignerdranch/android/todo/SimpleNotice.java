package com.bignerdranch.android.todo;

import java.io.Serializable;

public class SimpleNotice implements Serializable {
    private String notice;
    private String date;
    private int hour;
    private int minute;

    public SimpleNotice(String notice, String date) {
        this.date = date;
        this.notice = notice;
    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getNotice() {
        return notice;
    }

    public String getDateNotice() {
        return (date + " " + notice);
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package com.bignerdranch.android.todo;

public class SimpleNotice {
    private String notice;
    private String date;

    public SimpleNotice(String notice, String date) {
        this.date = date;
        this.notice = notice;
    }

    public String getNotice() {
        return notice;
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

package com.bignerdranch.android.todo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedDay extends Activity {
    private static String TAG = "DetailedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "WORK");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);
    }
}
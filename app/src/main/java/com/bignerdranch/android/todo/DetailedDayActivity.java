package com.bignerdranch.android.todo;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class DetailedDayActivity extends SingleFragmentActivity {

    public static final String EXTRA_DAY = "EXTRA_DAY";
    //private static Day mDay;

    public static Intent newIntent(Context packageContext, int dayPosition) {
        Intent intent = new Intent(packageContext, DetailedDayActivity.class);
        intent.putExtra(EXTRA_DAY, dayPosition);
        //mDay = day;
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return DetailedDayFragment.newInstance();
    }

//    public static Day getDay() {
//        return mDay;
//    }
}
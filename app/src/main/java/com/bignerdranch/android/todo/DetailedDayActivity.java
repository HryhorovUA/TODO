package com.bignerdranch.android.todo;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class DetailedDayActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return DetailedDayFragment.newInstance();
    }
}
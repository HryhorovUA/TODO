package com.bignerdranch.android.todo;

import android.util.Log;

import androidx.fragment.app.Fragment;

public class AddingActivity extends SingleFragmentActivity{
    private static String TAG = "AddingActivity";


    @Override
    protected Fragment createFragment() {
        return AddingFragment.newInstance();
    }
}

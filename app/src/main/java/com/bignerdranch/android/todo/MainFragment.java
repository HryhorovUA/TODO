package com.bignerdranch.android.todo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    //private RecyclerView mRecyclerView;
    private static String TAG = "MainFragment";

    Day[] mDays = new Day[7];

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_days);

        Log.i(TAG, "First log MAIN");

        mDays = Day.createWeek();

        Log.i(TAG, "Second log MAIN");

        WeekAdapter weekAdapter = new WeekAdapter(mDays);

        Log.i(TAG, "Last log MAIN");

        mRecyclerView.setAdapter(weekAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
}

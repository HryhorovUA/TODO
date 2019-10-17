package com.bignerdranch.android.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailedDayFragment extends Fragment {
    private static final String DAY_EXTRA = "DAY_EXTRA";

    FloatingActionButton mFloatingActionButton;

    public static DetailedDayFragment newInstance() {
        return new DetailedDayFragment();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detailed_fragment, container, false);

        mFloatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab_second);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddingActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}

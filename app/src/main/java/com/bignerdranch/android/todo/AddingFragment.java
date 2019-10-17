package com.bignerdranch.android.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class AddingFragment extends Fragment {
    public static AddingFragment newInstance() {
        return new AddingFragment();
    }

    @Override
    public View  onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.adding_fragment, container, false);

        return v;
    }
}

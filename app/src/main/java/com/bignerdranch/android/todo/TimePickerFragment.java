package com.bignerdranch.android.todo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

public class TimePickerFragment extends DialogFragment {
    private static final String ART_TIME = "time";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Choose the time")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}

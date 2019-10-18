package com.bignerdranch.android.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class AddingFragment extends Fragment {
    private static final String TAG = "AddingFragment";

    private static final String DIALOG_DATE = "DIALOG_DATE";
    private static final String DIALOG_TIME = "DIALOG_TIME";
    private Button mChoiceDayButton;
    private Button mChoiceTimeButton;
    private Button mConfirmButton;
    private Calendar mCalendar = Calendar.getInstance();
    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimePickerDialog;
    private EditText mEditTextNotice;

    private String getNotice;

    private int positionOfDay;

    public static AddingFragment newInstance() {
        return new AddingFragment();
    }

    @Override
    public View  onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.adding_fragment, container, false);

        mChoiceDayButton = (Button) v.findViewById(R.id.date_button_choice);
        mChoiceDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

        mChoiceTimeButton = (Button) v.findViewById(R.id.time_button_choice);
        mChoiceTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.show(manager, DIALOG_TIME);
            }
        });

        mConfirmButton = (Button) v.findViewById(R.id.confirm_button);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment.addNoticeDay(positionOfDay, "Notice: " + getNotice);
            }
        });

        mEditTextNotice= (EditText) v.findViewById(R.id.edit_notice);
        getNotice = (String) mEditTextNotice.getText().toString();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            positionOfDay = initDatePicker();
        }

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int initDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int currentDay = (int) calendar.get(Calendar.DAY_OF_WEEK);
        AtomicInteger position = new AtomicInteger();

        mDatePickerDialog = new DatePickerDialog(getContext());
        mDatePickerDialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
        mCalendar.add(Calendar.DAY_OF_YEAR, 6);
        mDatePickerDialog.getDatePicker().setMaxDate(mCalendar.getTimeInMillis());
        mDatePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            mCalendar.set(Calendar.MONTH, month);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            int clicked = mCalendar.get(Calendar.DAY_OF_WEEK);

            if (clicked < currentDay) {
                position.set(7 - (clicked - 1));
            } else if (clicked > currentDay) {
                position.set(clicked - currentDay);
            } else {
                position.set(0);
            }


            //MainFragment.addNoticeDay(position, "Notice" + position);
        });

        int finalPos = position.get();

        return finalPos;
    }

}
